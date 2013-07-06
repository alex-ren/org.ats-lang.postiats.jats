package org.ats_lang.postiats.jats.type;

import java.util.Iterator;
import java.util.List;

import org.ats_lang.postiats.jats.interpreter.FuncPara;
import org.ats_lang.postiats.jats.value.Ptrk;

public class FuncType implements ATSType {

	@Override
	public int getSize() {
		return PtrkType.cType.getSize();
	}

	@Override
	public Object createNormalDefault() { 
		throw new Error("not supported");
	}

	@Override
	public Ptrk createRefDefault() {
		throw new Error("not supported");
	}
	
	
	private ATSType m_rettype;
	private List<FuncPara> m_paralst;
	
	public FuncType(ATSType rettype, List<FuncPara> paralst) {
	    m_rettype = rettype;
	    m_paralst = paralst;
	    
	}
	
	public ATSType getReturnType() {
	    return m_rettype;
	}

    @Override
    public boolean equals(ATSType ty) {
        if (this == ty) {
            return true;
        } else if (ty instanceof FuncType) {
            if (!m_rettype.equals(((FuncType) ty).m_rettype)) {
                return false;
            }
            List<FuncPara> leftlst = m_paralst;
            List<FuncPara> rightlst = ((FuncType) ty).m_paralst;
            if (leftlst.size() != rightlst.size()) {
                return false;
            }
            Iterator<FuncPara> leftiter = leftlst.iterator();
            Iterator<FuncPara> rightiter = rightlst.iterator();
            
            while (leftiter.hasNext()) {
                FuncPara left = leftiter.next();
                FuncPara right = rightiter.next();
                if (!left.equals(right)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }

}
