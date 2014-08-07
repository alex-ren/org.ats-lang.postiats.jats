package jats.utfpl.stfpl.csharptype;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.PolyParaType;
import jats.utfpl.stfpl.stype.SortType;
import jats.utfpl.stfpl.stype.TypeCheckResult;
import jats.utfpl.stfpl.stype.ISType.NamifyResult;

import java.util.Map;
import java.util.Set;

/*
 * This is a special type used only after type checking.
 * This serves as a wrapper for unnamed types.
 * 
 * 08/06/2014
 * The equality of NamedType is based on their identity.
 * 
 * If m_type is abstype, then m_name is the name of the abstype.
 */
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

    @Override
    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
        throw new Error("This would not happen.");
    }

    @Override
    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
        if (type instanceof NamedType) {
            return m_name.equals(((NamedType)type).m_name);
        }
        
        return m_type.equalCSharp(type, env);
    }


}
