package jats.utfpl.stfpl.stype;

import java.util.Map;

public class NamedType extends SortType {
    private SortType m_type;
    private ITypeName m_name;

    public NamedType(SortType type, ITypeName name) {
        super(type.m_srt);
        if (type instanceof NamedType) {
            throw new Error("should not happen");
        }
        m_name = name;
    }
    
    public ITypeName getName() {
        return m_name;
    }
    
    public SortType getContent() {
        return m_type;
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


}
