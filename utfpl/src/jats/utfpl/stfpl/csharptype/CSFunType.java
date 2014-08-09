package jats.utfpl.stfpl.csharptype;

import java.util.List;

public class CSFunType implements ICSNamedType {
    private ICSTypeName m_name;
    private List<ICSType> m_paras;
    private ICSType m_ret;
    
    public CSFunType(ICSTypeName name, List<ICSType> paras, ICSType ret) {
        m_name = name;
        m_paras = paras;
        m_ret = ret;
    }

    @Override
    public ICSTypeName getName() {
        return m_name;
    }

}
