package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

// abstype atomref (a: t@ype)
// abst@ype thread_id_t = int


public class DataType extends BoxedType {
    public List<ISType> m_tyLst;
    public Cs2cst m_name;
    
    public DataType(Cs2cst name, List<ISType> tyLst) {
        m_tyLst = tyLst;
        m_name = name;
//        throw new Error("not allowed to use now, name is " + name.toStringNoStamp());
    }

    @Override
    public ISType normalize() {
        ListIterator<ISType> iter = m_tyLst.listIterator();
        if (iter.hasNext()) {
            ISType ty = iter.next().normalize();
            iter.set(ty);
        }
        return this;
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
    	List<ISType> nlst = new ArrayList<ISType>();
    	for (ISType ty: m_tyLst) {
    		nlst.add(ty.instantiate(map));
    	}
    	
        return new DataType(m_name, nlst);
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        this.normalize();
//        Log.log4j.info("type is " + this.toSTStfpl3(AuxSType.cStg).render());
        ISType right = ty.normalize();
//        Log.log4j.info("right is " + right.toSTStfpl3(AuxSType.cStg).render());
        if (right instanceof VarType) {
            ((VarType) right).setType(this);
            return new TypeCheckResult();
        } else if (right instanceof DataType) {
            DataType right1 = (DataType)right;
            if (m_name != right1.m_name) {
            	// Type assumuption is not supported yet.
            	// E.g.
            	//   
            	//  assume t1 = int
            	Log.log4j.warn("type mismatch: " + 
                              this.toSTStfpl3(AuxSType.cStg).render() +
                              " v.s. " +
                              right.toSTStfpl3(AuxSType.cStg).render());
            }
            
            if (m_name == right1.m_name) {
            	// Only do comparison when two constructors are of the same name.
	            for (int i = 0; i < m_tyLst.size(); ++i) {
	            	TypeCheckResult tyret = m_tyLst.get(i).match(right1.m_tyLst.get(i));
	            	if (!tyret.isGood()) {
	            		throw new Error("type error at " + tyret.getMsg());
	            	}
	            }
            }
            
//            Log.log4j.info("after type is " + this.toSTStfpl3(AuxSType.cStg).render());
//          throw new Error("eeeeeeeee");
//          Log.log4j.info("after right is " + right.toSTStfpl3(AuxSType.cStg).render());
          
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("not expecting " + ty);
        }
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        throw new Error("not supported yet");
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        // DataType_st(cst_name, tys) ::= <<
        ST st = stg.getInstanceOf("DataType_st");
        st.add("cst_name", m_name);
        for (ISType ty: m_tyLst) {
        	st.add("tys", ty.toSTStfpl3(stg));
        }
        return st;
        
    }

    @Override
    public ISType removeProof() {
//    	Log.log4j.warn("removeProof for datatype " + m_name.toStringNoStamp());
        return this;
    }

//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        throw new Error("not supported");
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        throw new Error("not supported");
//    }

}


