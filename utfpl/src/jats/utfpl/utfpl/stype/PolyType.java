package jats.utfpl.utfpl.stype;

import java.util.List;

public class PolyType extends BoxedType {
    public List<PolyParaType> m_paras;
    public ISType m_body;
    
    public PolyType(List<PolyParaType> paras, ISType body) {
        m_paras = paras;
        m_body = body;
    }

    @Override
    public PolyType normalize() {
        m_body = m_body.normalize();
        return this;
    }


    @Override
    public PolyType instantiate(PolyParaType para, ISType arg) {
        if (m_paras.contains(para)) {
            throw new Error("This is not allowed.");
        }
        ISType body = m_body.instantiate(para, arg);
        
        return new PolyType(m_paras, body);
    }

    @Override
    public void match(ISType ty) {
        PolyType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof PolyType) {
            throw new Error("not expecting this");
        } else {
            throw new Error("Type mismatch.");
        }
    }
    
    public FunType getNormalFunType() {
        ISType aType = m_body;
        for (PolyParaType tyPara: m_paras) {
            VarType tyArg = new VarType();
            aType = aType.instantiate(tyPara, tyArg);
        }
        
        if (aType instanceof FunType) {
            return (FunType)aType;
        } else {
            throw new Error("Check this.");
        }
    }
    
    public FunType getParaFunType() {
        if (m_body instanceof FunType) {
            return (FunType)m_body;
        } else if (m_body instanceof PolyType) {
            return ((PolyType)m_body).getParaFunType();
        } else {
            throw new Error("Check this.");
        }
    }
    

}
