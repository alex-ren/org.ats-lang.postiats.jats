package jats.utfpl.utfpl.stype;

import java.util.List;

public class FunType extends BoxedType {
    public int m_npf;
    public List<ISType> m_args;
    public ISType m_res;
    
    public FunType(int npf, List<ISType> args, ISType res) {
        m_npf = npf;
        m_args = args;
        m_res = res;
    }
}
