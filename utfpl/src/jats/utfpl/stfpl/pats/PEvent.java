package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

public class PEvent implements PNode {
    
    public List<PStat> m_statLst;
    public MCSId m_funname;
    public int m_no;
    
    public PEvent(List<PStat> statLst, MCSId funname, int no) {
    	if (funname == null) {
    		throw new Error("eeeeeeeeee");
    	}
        m_statLst = statLst;
        m_funname = funname;
        m_no = no;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
