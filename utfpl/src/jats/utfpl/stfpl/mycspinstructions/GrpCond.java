package jats.utfpl.stfpl.mycspinstructions;


import java.util.List;


public class GrpCond extends MyCspGroup {
    public IMyCspTemp m_cond; // condition
    public List<MyCspGroup> m_tb; // true branch
    public List<MyCspGroup> m_fb; // false branch
//    private CTempID m_holder;
//    private CTempID m_holderTrue;
//    private CTempID m_holderFalse;

    public GrpCond(
            IMyCspTemp cond
    		, List<MyCspGroup> tb 
    		, List<MyCspGroup> fb
    		, int level
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
    
    public GrpCond() {
        m_cond = null;
        m_tb = null;
        m_fb = null;
//        m_holder = null;
//        m_holderTrue = null;
//        m_holderFalse = null;
    }
    
    public void reset(
            IMyCspTemp cond
            , List<MyCspGroup> tb 
            , List<MyCspGroup> fb
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
    
    
    public List<MyCspGroup> getTrueBranch() {
        return m_tb;
    }
    
    public List<MyCspGroup> getFalseBranch() {
        return m_fb;
    }
    
    public void setTrueBranch(List<MyCspGroup> cgs) {
        m_tb = cgs;
    }
    
    public void setFalseBranch(List<MyCspGroup> cgs) {
        m_fb = cgs;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        if (m_cond instanceof MyCspTempID) {
            ((MyCspTempID)m_cond).updateForUsage();
        }
        int offsetTrue = offset;
        int offsetFalse = offset;
        
        for (MyCspGroup cb: m_tb) {
            offsetTrue = cb.process(offsetTrue);
        }
        
        for (MyCspGroup cb: m_fb) {
            offsetFalse = cb.process(offsetFalse);
        }
        
        return offset;  // offset is unchanged.
    }

}


