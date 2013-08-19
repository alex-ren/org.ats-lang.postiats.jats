package jats.utfpl.csps;

import jats.utfpl.tree.TID;

import java.util.List;


public class CCondBlock implements CAdvancedBlock {
    public CTemp m_cond; // condition
    public List<CBlock> m_tb; // true branch
    public List<CBlock> m_fb; // false branch
//    private CTempID m_holder;
//    private CTempID m_holderTrue;
//    private CTempID m_holderFalse;

    public CCondBlock(
            CTemp cond
    		, List<CBlock> tb 
    		, List<CBlock> fb
//    		, CTempID holder
//    		, CTempID holderTrue
//    		, CTempID holderFalse
    		) {
        m_cond = cond;
        m_tb = tb;
        m_fb = fb;
//        m_holder = holder;
//        m_holderTrue = holderTrue;
//        m_holderFalse = holderFalse;
        
    }
    
    public CCondBlock() {
        m_cond = null;
        m_tb = null;
        m_fb = null;
//        m_holder = null;
//        m_holderTrue = null;
//        m_holderFalse = null;
    }
    
    public void reset(
            CTemp cond
            , List<CBlock> tb 
            , List<CBlock> fb
//          , CTempID holder
//          , CTempID holderTrue
//          , CTempID holderFalse
            ) {
        m_cond = cond;
        m_tb = tb;
        m_fb = fb;
//        m_holder = holder;
//        m_holderTrue = holderTrue;
//        m_holderFalse = holderFalse;
        
    }
    
    
    public List<CBlock> getTrueBranch() {
        return m_tb;
    }
    
    public List<CBlock> getFalseBranch() {
        return m_fb;
    }
    
    public void setTrueBranch(List<CBlock> cgs) {
        m_tb = cgs;
    }
    
    public void setFalseBranch(List<CBlock> cgs) {
        m_fb = cgs;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
}

