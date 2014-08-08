package jats.utfpl.stfpl.stype;

import jats.utfpl.utils.Log;

import java.util.Map;

public abstract class EleType extends FlatType {
    public EleType() {
        super();
    }

    @Override
    public EleType normalize() {
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        EleType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof EleType) {
            if (right0 != this) {
                return new TypeCheckResult("Type mismatch: " + "type mismatch: " + 
                  Log.getFilePos() + " 01. left is " + left + ", right is " + right0);
            } else {
                return new TypeCheckResult();
            }
        } else {
            return new TypeCheckResult("Type mismatch: " + "type mismatch: " + 
                    Log.getFilePos() + " 01. left is " + left + ", right is " + right0);
        }
        
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }
//
//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        NamifyResult ret = new NamifyResult(null, null, false);
//        return ret;
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        return this == type;
//    }

    
}


