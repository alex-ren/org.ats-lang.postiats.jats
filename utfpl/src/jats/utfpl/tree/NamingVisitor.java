package jats.utfpl.tree;

import java.util.List;

import jats.utfpl.utils.MapScope;

public class NamingVisitor implements TreeVisitor {
    private MapScope<TID> m_scope;
    
    public NamingVisitor() {
        m_scope = new MapScope<TID>();
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
        TID tid = node.m_id.updateTIDNew();
        this.m_scope.addValue(tid.getID(), tid);
        
        m_scope = m_scope.newScope();
        for (IdExp para: node.m_paralst) {
            tid = para.updateTIDNew();
            this.m_scope.addValue(tid.getID(), tid);
        }
        node.m_body.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }

    @Override
    public Object visit(IdExp node) {
        TID tid = m_scope.getValue(node.m_id);
        if (null == tid) {
            tid = TID.createUnique(node.m_id);
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
           TID tid = para.updateTIDNew();
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
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }
        node.m_exp.accept(this);
        m_scope = m_scope.getParent();
        
        return null;
    }

    @Override
    public Object visit(ValDef node) {
//        System.out.println("ValDef " + node.m_id.m_id);
        TID tid = node.m_id.updateTIDNew();
        
        node.m_exp.accept(this);
        this.m_scope.addValue(tid.getID(), tid);
        return null;
    }

    @Override
    public Object visit(Program node) {
        List<Dec> decs = node.m_decs;
        for (Dec dec: decs) {
            dec.accept(this);
        }
        return null;        
    }

}
