package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.Aux;
import jats.utfpl.stfpl.csharptype.CSFunType;
import jats.utfpl.stfpl.csharptype.CSTBookingFun;
import jats.utfpl.stfpl.csharptype.CSTNameId;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.staexp.FUNCLOclo;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/*
 * This type should always appear inside UniType.
 */
public class FunType extends BoxedType {
    public int m_npf;
    public List<ISType> m_args;
    public ISType m_res;
    private Ifunclo m_funclo;
    
    public Ifunclo getFunClo() {
        return m_funclo;
    }
    
    public void updateFunClo(Ifunclo clo) {
        m_funclo = clo;
    }
    
    public FunType(int npf, List<ISType> args, ISType res, Ifunclo funclo) {
        m_npf = npf;
        m_args = args;
        m_res = res;

        m_funclo = funclo;
    }
    
    public FunType(List<ISType> args, ISType res, Ifunclo funclo) {
        m_npf = -999;  // todo handle this for "private ISType oftype(VarType funType0, List<Id2exparg> argsLst) {"
        m_args = args;
        m_res = res;
        m_funclo = funclo;
    }

    public ISType getRetType() {
        return m_res;
    }
    
    @Override
    public FunType normalize() {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_args) {
            ISType nty = ty.normalize();
            tys.add(nty);
        }
        
        m_args = tys;
        
        m_res = m_res.normalize();
        
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        FunType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof FunType) {
            FunType right = (FunType)right0;
            jats.utfpl.stfpl.stype.Aux.matchTypeList(left.m_args, right.m_args);
            m_res.match(right.m_res);
            
            if (-999 == m_npf) {
                m_npf = right.m_npf;
            } else {
                right.m_npf = m_npf;
            }
            
            if (null == m_funclo) {
                m_funclo = right.m_funclo;
            } else {
                right.m_funclo = m_funclo;
            }
            
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("Type mismatch: " + Log.getFilePos() + " right is " + right0);
        }
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        List<ISType> args = new ArrayList<ISType>();
        for (ISType ty: m_args) {
            ISType nty = ty.instantiate(map);
            args.add(nty);
        }
        
         ISType res = m_res.instantiate(map);
        
        return new FunType(m_npf, args, res, m_funclo);
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        List<ICSType> arg_types = new ArrayList<ICSType>();
        for (ISType arg_type: m_args) {
            ToCSTypeResult res = arg_type.toCSType(track);
            arg_types.add(res.m_type);
        }
        
        ICSType ret_type = m_res.toCSType(track).m_type;
        
        Ifunclo clo = null;
        if (null == m_funclo) {
            // no annotation, treat it as cloref
            clo = new FUNCLOclo(-1);
        } else {
            clo = m_funclo;
        }

        int arg_size = m_args.size();
        if (clo.isClosure()) {
            ++arg_size;
        }
        // find an appropriate name
        boolean is_void = (m_res instanceof VoidType);
        CSTBookingFun booking = Aux.findBookingFun(track, arg_size, is_void);
        ICSTypeName name = null;
        if (null == booking) {
            name = CSTNameId.createTypeId("fun"); 
            booking = new CSTBookingFun(name, arg_size, is_void);
            track.add(booking);   
        } else {
            name = booking.m_name;
        }
        
        CSFunType cstype = new CSFunType(name, arg_types, ret_type, clo);;
        return new ToCSTypeResult(cstype, null);
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        // FunType_st(paras, clo, ret) ::= <<
        ST st = stg.getInstanceOf("FunType_st");
        for (ISType arg: m_args) {
            st.add("paras", arg.toSTStfpl3(stg));
        }
        
        if (null == m_funclo) {
            st.add("clo", "n/a");
        } else if (m_funclo.isClosure()) {
            st.add("clo", "clo");
        } else {
            st.add("clo", "fun");
        }
        
        st.add("ret", m_res.toSTStfpl3(stg));
        return st;
    }

}




    
    
    
    
