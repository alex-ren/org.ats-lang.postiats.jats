package jats.utfpl.tree;

import java.util.List;

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
            throw new Error("This shall not happen.");
        }
        
        node.m_id.updateTID(tid);  // update function's tid
        
        m_scope = m_scope.newScope();
        for (IdExp para: node.m_paralst) {
            tid = TID.createPara(para.m_sid);
            para.updateTID(tid);
            this.m_scope.addValue(tid.getID(), tid);
        }
        node.m_body.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }

    @Override
    public Object visit(IdExp node) {
        TID tid = m_scope.getValue(node.m_sid);
        if (null == tid) {
            // tid = TID.createUnique(node.m_sid);
            throw new Error("This shall not happen." + " And m_sid is " + node.m_sid);
        }
        node.updateTID(tid);
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
           TID tid = TID.createPara(para.m_sid);
           para.updateTID(tid);
           this.m_scope.addValue(tid.getID(), tid);
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
        if (null != node.m_id) {
            TID tid = TID.createLocalVar(node.m_id.m_sid);
            node.m_id.updateTID(tid);
            this.m_scope.addValue(tid.getID(), tid);
        }
        
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
        TID tid = TID.createGloVar(node.m_id.m_sid);
        node.m_id.updateTID(tid);
        this.m_scope.addValue(tid.getID(), tid);

        if (null != node.m_exp) {
            node.m_exp.accept(this);
        }
        
        return null;
    }

    @Override
    public Object visit(VarAssign node) {
        // System.out.println("VarAssign " + node.m_id.m_id);
        TID tid = m_scope.getValue(node.m_id.m_sid);
        if (null == tid) {
            throw new Error("This shall not happen.");
        }
        node.m_id.updateTID(tid);

        node.m_exp.accept(this);
        return null;
    }

}
