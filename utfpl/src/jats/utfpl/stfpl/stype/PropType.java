package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;
import jats.utfpl.utils.Log;

import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;


public class PropType extends SortType {
    public static PropType cInstance = new PropType();

    private PropType() {
        super(ESort.prop);
    }

    @Override
    public PropType normalize() {
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        PropType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof PropType) {
            if (this != right0) {
                return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
            } else {
                return new TypeCheckResult();
            }
        } else {
            return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
        }
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        throw new Error("should not happen");
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        // PropType_st() ::= <<
        ST st = stg.getInstanceOf("PropType_st");
        return st;
    }

    @Override
    public ISType removeProof() {
        throw new Error("This should not happen. Check the program.");
    }



}
