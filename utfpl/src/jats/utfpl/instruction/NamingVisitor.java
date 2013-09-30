package jats.utfpl.instruction;

import java.util.List;

import jats.utfpl.patcsps.Type;
import jats.utfpl.tree.AppExp;
import jats.utfpl.tree.AtomExp;
import jats.utfpl.tree.Dec;
import jats.utfpl.tree.Exp;
import jats.utfpl.tree.FunDef;
import jats.utfpl.tree.IdExp;
import jats.utfpl.tree.IfExp;
import jats.utfpl.tree.LamExp;
import jats.utfpl.tree.LetExp;
import jats.utfpl.tree.Program;
import jats.utfpl.tree.TreeVisitor;
import jats.utfpl.tree.TupleExp;
import jats.utfpl.tree.ValDef;
import jats.utfpl.tree.VarAssign;
import jats.utfpl.tree.VarDef;
import jats.utfpl.utils.MapScope;

public class NamingVisitor implements TreeVisitor {
    private MapScope<TID> m_scope;

    public NamingVisitor(MapScope<TID> libScope) {
        m_scope = libScope;
        // We could populate the name of the library functions here.
//        m_scope.addValue("mul", TID.createUnique("mul"));
    }

    @Override
    public Object visit(AppExp node) {
        node.m_fun.accept(this);
        for (Exp exp: node.m_explst) {
            exp.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(AtomExp node) {
        return null;
    }

    @Override
    public Object visit(FunDef node) {
//        System.out.println("FunDef " + node.m_id.m_id);
        TID tid = m_scope.getValue(node.m_id.m_sid);
        if (null == tid) {
            // Functions should have all been collected.
            throw new Error("This shall not happen.");
        }
        
        node.m_id.updateForUsage(m_scope);  // update function's tid
        
        m_scope = m_scope.newScope();
        for (IdExp para: node.m_paralst) {
            para.updateForPara(m_scope);
        }
        node.m_body.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }

    @Override
    public Object visit(IdExp node) {
        node.updateForUsage(m_scope);
        return null;
    }

    @Override
    public Object visit(IfExp node) {
        node.m_cond.accept(this);
        node.m_btrue.accept(this);
        node.m_bfalse.accept(this);
        return null;
    }

    @Override
    public Object visit(LamExp node) {
//       System.out.println("LamExp");
       m_scope = m_scope.newScope();
       for (IdExp para: node.m_paralst) {
           para.updateForPara(m_scope);
       }
       node.m_body.accept(this);
       m_scope = m_scope.getParent();
       return null;
    }

    @Override
    public Object visit(LetExp node) {
//        System.out.println("LetExp");
        m_scope = m_scope.newScope();
        
        // first scan 
        scanScope(node.m_decs, m_scope);
        
        // second scan
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }

        node.m_exp.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }
    
    // Find all the function definitions in the scope.
    private void scanScope(List<Dec> decs, MapScope<TID> scope) {
        String name = null;
        TID tid = null;
        for (Dec dec: decs) {
            if (dec instanceof FunDef) {
                name = ((FunDef)dec).m_id.m_sid;
                tid = TID.createUserFun(name);
            } else {
                // nothing
            }
            scope.addValue(name, tid);
        }
        
    }

    @Override
    public Object visit(ValDef node) {
//        System.out.println("ValDef " + node.m_id.m_id);
        node.m_id.updateForLocalDef(m_scope);
        
        node.m_exp.accept(this);
        
        return null;
    }

    @Override
    public Object visit(Program node) {
        // no need to create a new scope
        
        // first scan 
        scanScope(node.m_decs, m_scope);
        
        // second scan
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }
        
        return null;        
    }

    @Override
    public Object visit(TupleExp node) {
        if (node != TupleExp.Void) {
            for (Exp exp : node.m_components) {
                exp.accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(VarDef node) {
        // System.out.println("VarDef " + node.m_id.m_id);
        node.m_id.updateForGlovalVar(m_scope);

        if (null != node.m_exp) {
            node.m_exp.accept(this);
        }
        
        return null;
    }

    @Override
    public Object visit(VarAssign node) {
        // System.out.println("VarAssign " + node.m_id.m_id);
        node.m_id.updateForUsage(m_scope);

        node.m_exp.accept(this);
        return null;
    }

}
