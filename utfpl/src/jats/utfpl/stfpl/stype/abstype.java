package jats.utfpl.stfpl.stype;

import java.util.Map;
import java.util.Set;

public class abstype extends SortType {
    private ITypeName m_name;

    public abstype(ESort srt, ITypeName name) {
        super(srt);
        m_name = name;
    }
    
    public ITypeName getName() {
        return m_name;
    }

    @Override
    public ISType normalize() {
        throw new Error("check this");
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        throw new Error("check this");
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        throw new Error("check this");
    }

    @Override
    public NamifyResult namify(Map<ITypeName, NamedType> map,
            Set<PolyParaType> env) {
        throw new Error("not supported yet");
    }

}


