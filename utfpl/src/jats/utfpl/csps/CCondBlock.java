package jats.utfpl.csps;

import jats.utfpl.tree.TID;

import java.util.List;


public class CCondBlock implements CGroup {
    private CTemp m_cond; // condition
    private List<CGroup> m_tb; // true branch
    private List<CGroup> m_fb; // false branch
    private CTempID m_holder;
    private CTempID m_holderTrue;
    private CTempID m_holderFalse;

    public CCondBlock(CTemp cond, 
    		List<CGroup> tb, List<CGroup> fb, 
    		CTempID holder,
    		CTempID holderTrue,
    		CTempID holderFalse) {
        m_cond = cond;
        m_tb = tb;
        m_fb = fb;
        m_holder = holder;
        m_holderTrue = holderTrue;
        m_holderFalse = holderFalse;
        
    }
    
    
    public List<CGroup> getTrueBranch() {
        return m_tb;
    }
    
    public List<CGroup> getFalseBranch() {
        return m_fb;
    }
    
    public void setTrueBranch(List<CGroup> cgs) {
        m_tb = cgs;
    }
    
    public void setFalseBranch(List<CGroup> cgs) {
        m_fb = cgs;
    }
}


