package jats.utfpl.stfpl.pats;


public class PExpPatLabDecompose implements PExp {
    public int m_index;
    public PExp m_tup;
    
    public PExpPatLabDecompose(int index, PExp tup) {
        m_index = index;
        m_tup = tup;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
