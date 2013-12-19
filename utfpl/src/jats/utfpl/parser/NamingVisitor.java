package jats.utfpl.parser;


import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeArray;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.tree.ExpApp;
import jats.utfpl.tree.ExpAtom;
import jats.utfpl.tree.IDec;
import jats.utfpl.tree.IExp;
import jats.utfpl.tree.DecFunDef;
import jats.utfpl.tree.DecFunGroup;
import jats.utfpl.tree.ExpId;
import jats.utfpl.tree.ExpIf;
import jats.utfpl.tree.ExpLam;
import jats.utfpl.tree.ExpLet;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeVisitor;
import jats.utfpl.tree.ExpTuple;
import jats.utfpl.tree.DecValBind;
import jats.utfpl.tree.DecValDef;
import jats.utfpl.tree.DecVarArrayDef;
import jats.utfpl.tree.DecVarAssign;
import jats.utfpl.tree.DecVarDef;
import jats.utfpl.utils.MapScope;

public class NamingVisitor implements TreeVisitor {
    private MapScope<TID> m_scope;

    // libScope should have been populated by library functions.
    public NamingVisitor(MapScope<TID> libScope) {
        m_scope = libScope;
    }

    @Override
    public Object visit(ExpApp node) {
        node.m_fun.accept(this);
        for (IExp exp: node.m_explst) {
            exp.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(ExpAtom node) {
        return null;
    }

    @Override
    public Object visit(DecFunDef node) {
//        System.out.println("FunDef " + node.m_id.m_id);
        TID tid = m_scope.getValue(node.m_id.m_sid);
        if (null == tid) {
            // Functions should have all been collected.
            throw new Error("This shall not happen.");
        }
        
        node.m_id.updateForUsage(m_scope);  // update function's tid
        
        m_scope = m_scope.newScope();
        for (ExpId para: node.m_paralst) {
            para.updateForPara(m_scope);
        }
        node.m_body.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }

    @Override
    public Object visit(ExpId node) {
        node.updateForUsage(m_scope);
        return null;
    }

    @Override
    public Object visit(ExpIf node) {
        node.m_cond.accept(this);
        node.m_btrue.accept(this);
        if (null != node.m_bfalse) {
        	node.m_bfalse.accept(this);
        }
        
        return null;
    }

    @Override
    public Object visit(ExpLam node) {
//       System.out.println("LamExp");
       m_scope = m_scope.newScope();
       for (ExpId para: node.m_paralst) {
           para.updateForPara(m_scope);
       }
       node.m_body.accept(this);
       m_scope = m_scope.getParent();
       return null;
    }

    @Override
    public Object visit(ExpLet node) {
//        System.out.println("LetExp");
        m_scope = m_scope.newScope();

        for (IDec dec: node.m_decs) {
            dec.accept(this);
        }

        node.m_exp.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }

    @Override
    public Object visit(DecValDef node) {
//        System.out.println("ValDef " + node.m_id.m_id);

        node.m_id.updateForGlobalDef(m_scope);
        node.m_exp.accept(this);
        
        return null;
    }


    @Override
    public Object visit(DecValBind node) {
        node.m_id.updateForLocalDef(m_scope);
        node.m_exp.accept(this);
        return null;
    }

    
    @Override
    public Object visit(ProgramTree node) {
        // no need to create a new scope

        for (IDec dec: node.m_decs) {
            dec.accept(this);
        }
        
        return null;        
    }

    @Override
    public Object visit(ExpTuple node) {
        if (!node.isVoid()) {
            for (IExp exp : node.m_components) {
                exp.accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(DecVarDef node) {
        // System.out.println("VarDef " + node.m_id.m_id);
        node.m_id.updateForGlobalVar(m_scope, PATTypeSingleton.cUnknownType);

        if (null != node.m_exp) {
            node.m_exp.accept(this);
        }
        
        return null;
    }

    @Override
    public Object visit(DecVarAssign node) {
        // System.out.println("VarAssign " + node.m_id.m_id);
        node.m_id.updateForUsage(m_scope);

        node.m_exp.accept(this);
        return null;
    }

	@Override
    public Object visit(DecFunGroup node) {
		for (DecFunDef fundef: node.m_funLst) {
			String name = fundef.m_id.m_sid;
            TID tid = TID.createUserFun(name);
            m_scope.addValue(name, tid);
		}
		
		for (DecFunDef fundef: node.m_funLst) {
			fundef.accept(this);
		}
        
		return null;
    }

    @Override
    public Object visit(DecVarArrayDef node) {
        node.m_id.updateForGlobalVar(m_scope, new PATTypeArray(node.m_size));
        
        return null;
    }

}
