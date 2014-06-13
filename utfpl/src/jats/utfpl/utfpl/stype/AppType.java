package jats.utfpl.utfpl.stype;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
    public ISType instantiate(PolyParaType para, ISType arg) {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.instantiate(para, arg);
            tys.add(nty);
        }
        
        return new AppType(m_con, tys);
    }

    @Override
    public void match(ISType ty) {
        AppType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof AppType) {
            AppType right = (AppType)right0;
            if (!left.m_con.equals(right.m_con)) {
                throw new Error("type mismatch");
            }
            
            if (left.m_tys.size() != right.m_tys.size()) {
                throw new Error("type mismatch");
            }
            
            ListIterator<ISType> iter0 = left.m_tys.listIterator();
            ListIterator<ISType> iter1 = right.m_tys.listIterator();
            while (iter0.hasNext()) {
                iter0.next().match(iter1.next());
            }
        } else {
            throw new Error("type mismatch");
        }
        
    }

}







