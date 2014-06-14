package jats.utfpl.utfpl.stype;

import java.util.ArrayList;
import java.util.List;

public class TupleType extends BoxedType {
	private List<ISType> m_tys;
	
	public TupleType(List<ISType> tys) {
	    m_tys = tys;
	}

    @Override
    public TupleType normalize() {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.normalize();
            tys.add(nty);
        }
        
        m_tys = tys;
        return this;
    }

    @Override
    public ISType instantiate(PolyParaType para, ISType arg) {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.instantiate(para, arg);
            tys.add(nty);
        }
        
        return new TupleType(tys);
    }

    @Override
    public void match(ISType ty) {
        TupleType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof TupleType) {
            TupleType right = (TupleType)right0;
            Aux.matchTypeList(left.m_tys, right.m_tys);
        } else {
            throw new Error("Type mismatch.");
        }
        
    }

//	@Override
//    public boolean equals(ISType ty) {
//		if (this == ty) {
//			return true;
//		} else if (ty instanceof TupleType) {
//			TupleType right = (TupleType)ty;
//			return m_types.equals(right.m_types);
//		} else {
//			return false;
//		}
//
//    }
    

}
