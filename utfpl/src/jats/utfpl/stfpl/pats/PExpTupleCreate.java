package jats.utfpl.stfpl.pats;


public class PExpTupleCreate implements PExp {

    public int m_len;
    
    public PExpTupleCreate(int len) {
        m_len = len;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

	
}
