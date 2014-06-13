package jats.utfpl.utfpl.stype;

import java.util.ArrayList;
import java.util.List;

public class FunType extends BoxedType {
    public int m_npf;
    public List<ISType> m_args;
    public ISType m_res;
    
    public FunType(int npf, List<ISType> args, ISType res) {
        m_npf = npf;
        m_args = args;
        m_res = res;
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
    public ISType instantiate(PolyParaType para, ISType arg) {
        List<ISType> args = new ArrayList<ISType>();
        for (ISType ty: m_args) {
            ISType nty = ty.instantiate(para, arg);
            args.add(nty);
        }
        
         ISType res = m_res.instantiate(para, arg);
        
        return new FunType(m_npf, args, res);
    }

    @Override
    public void match(ISType ty) {
        FunType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof FunType) {
            FunType right = (FunType)right0;
            Aux.matchTypeList(left.m_args, right.m_args);
            m_res.match(right.m_res);
        } else {
            throw new Error("Type mismatch.");
        }
    }
    
}




    
    
    
    
