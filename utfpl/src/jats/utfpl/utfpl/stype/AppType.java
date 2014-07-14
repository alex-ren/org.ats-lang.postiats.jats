package jats.utfpl.utfpl.stype;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class AppType extends BoxedType {
    private String m_con;
    private List<ISType> m_tys;
    
    public AppType(String con, List<ISType> tys) {
        if (tys == null) {
            throw new Error("Not allowed");
        }
        m_con = con;
        m_tys = tys;
    }

    @Override
    public AppType normalize() {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.normalize();
            tys.add(nty);
        }
        
        m_tys = tys;
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        AppType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof AppType) {
            AppType right = (AppType)right0;
            if (!left.m_con.equals(right.m_con)) {
                return new TypeCheckResult("type mismatch 001");
            }
            
            if (left.m_tys.size() != right.m_tys.size()) {
                return new TypeCheckResult("type mismatch 002");
            }
            
            ListIterator<ISType> iter0 = left.m_tys.listIterator();
            ListIterator<ISType> iter1 = right.m_tys.listIterator();
            while (iter0.hasNext()) {
                iter0.next().match(iter1.next());
            }
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("type mismatch 003");
        }
        
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.instantiate(map);
            tys.add(nty);
        }
        
        return new AppType(m_con, tys);
    }

}







