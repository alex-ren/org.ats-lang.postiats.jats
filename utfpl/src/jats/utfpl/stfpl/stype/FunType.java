package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.staexp.Ifunclo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * This type should always appear inside UniType.
 */
public class FunType extends BoxedType {
    public int m_npf;
    public List<ISType> m_args;
    public ISType m_res;
    public Ifunclo m_funclo;
    
    public FunType(int npf, List<ISType> args, ISType res) {
        m_npf = npf;
        m_args = args;
        m_res = res;
        m_funclo = null;
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
            Aux.matchTypeList(left.m_args, right.m_args);
            m_res.match(right.m_res);
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("Type mismatch.");
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
        
        return new FunType(m_npf, args, res);
    }
    
}




    
    
    
    
