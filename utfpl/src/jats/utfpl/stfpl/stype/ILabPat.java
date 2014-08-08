package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSPolyParaType;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;


public interface ILabPat {
    public void normalize();

    public boolean sameLab(ILabPat pat1);

    public void match(ILabPat next);

    public ILabPat instantiate(Map<PolyParaType, ISType> map);
    
    public ToCSTypeResult toCSType(Map<ICSTypeName, ICSType> map,
            Set<CSPolyParaType> esc);
    
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc);
//
//    public boolean equalCSharp(ILabPat next,
//            Map<PolyParaType, PolyParaType> env);
    
}
