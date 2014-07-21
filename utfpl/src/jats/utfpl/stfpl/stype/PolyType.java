package jats.utfpl.stfpl.stype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public TypeCheckResult match(ISType ty) {
        PolyType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof PolyType) {
            return new TypeCheckResult("not expecting this");
        } else {
            return new TypeCheckResult("Type mismatch.");
        }
    }
    
    public FunType getNormalFunType() {
        List<PolyParaType> paras = new ArrayList<PolyParaType>(m_paras);
        FunType funTy = getParaFunType(paras);
        
        Map<PolyParaType, ISType> map = new HashMap<PolyParaType, ISType>();
        for (PolyParaType para: paras) {
            map.put(para, new VarType());
        }
        
        ISType aType = funTy.instantiate(map);
        
        if (aType instanceof FunType) {
            return (FunType)aType;
        } else {
            throw new Error("Check this. aType is " + aType);
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
    
    private FunType getParaFunType(List<PolyParaType> paras) {
        paras.addAll(m_paras);
        
        if (m_body instanceof FunType) {
            return (FunType)m_body;
        } else if (m_body instanceof PolyType) {
            return ((PolyType)m_body).getParaFunType(paras);
        } else {
            throw new Error("Check this.");
        }
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        for (PolyParaType para: map.keySet()) {
            if (m_paras.contains(para)) {
                throw new Error("This is not allowed.");
            }
        }

        ISType body = m_body.instantiate(map);
        
        return new PolyType(m_paras, body);
    }
    

}
