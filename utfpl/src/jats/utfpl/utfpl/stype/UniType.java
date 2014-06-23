package jats.utfpl.utfpl.stype;

import java.util.ArrayList;
import java.util.List;

public class UniType extends BoxedType {

    private FunType m_funty;
    private List<PolyParaType> m_tyParaLst;
    private List<ISType> m_tyArgLst;
    
    public UniType(List<PolyParaType> tyParaLst, FunType funty) {
        m_tyParaLst = tyParaLst;
        m_funty = funty;
        m_tyArgLst = new ArrayList<ISType>();
        
    }
    
    @Override
    public UniType normalize() {
        m_funty.normalize();
        return this;
    }

    @Override
    public UniType instantiate(PolyParaType para, ISType arg) {
        if (m_tyParaLst.contains(para)) {
            throw new Error("Check this.");
        }
        
        FunType funtype = m_funty.instantiate(para, arg);
        return new UniType(m_tyParaLst, funtype);
    }
    
    public FunType getNormalFunType() {
        if (m_tyArgLst.size() != 0) {
            throw new Error("Check this.");
        }
        FunType funTy = m_funty;
        for (PolyParaType tyPara: m_tyParaLst) {
            VarType tyArg = new VarType();
            funTy = funTy.instantiate(tyPara, tyArg);
            m_tyArgLst.add(tyArg);
        }
        return funTy;
    }
    
    // m_funty may contain PolyParaType
    public FunType getParaFunType() {
        return m_funty;
    }

    @Override
    public void match(ISType ty) {
//        UniType left = this.normalize();
//        ISType right0 = ty.normalize();
//        
//        if (right0 instanceof VarType) {
//            ((VarType)right0).setType(left);
//            return;
//        } else if (right0 instanceof FunType) {
//            FunType right = (FunType)right0;
//            Aux.matchTypeList(left.m_args, right.m_args);
//            m_res.match(right.m_res);
//        } else {
//            throw new Error("Type mismatch.");
//        }
        throw new Error("check this to see how to handle it");
    }
        

}
