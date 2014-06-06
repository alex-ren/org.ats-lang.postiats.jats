package jats.utfpl.utfpl.stype;

import java.util.List;

public class TupleType extends BoxedType {
	private List<SortType> m_types;
	
	public TupleType(List<SortType> types) {
		m_types = types;
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
