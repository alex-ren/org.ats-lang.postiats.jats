package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;


public interface ILabPat {
    public void normalize();
    
    public ISType getType();

    public boolean sameLab(ILabPat pat1);

    public void match(ILabPat next);

    public ILabPat instantiate(Map<PolyParaType, ISType> map);
    
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track);
    
    public ST toSTStfpl3(STGroup stg);
    
    public boolean isProof();
    
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc);
//
//    public boolean equalCSharp(ILabPat next,
//            Map<PolyParaType, PolyParaType> env);
    
}
