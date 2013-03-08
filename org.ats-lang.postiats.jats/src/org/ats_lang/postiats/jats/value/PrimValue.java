package org.ats_lang.postiats.jats.value;

/*
 * The type is to handle the following primitive types.
 * int
 * float
 * boolean
 * character
 * string
 */
public abstract class PrimValue implements ATSValue {

    protected Object m_mem;

    public PrimValue(Object mem) {
        m_mem = mem;
    }
    
    public abstract PrimValue castFrom(PrimValue pv);

    @Override
    public void copyfrom(ATSValue v) {
        if (v instanceof PrimValue) {
            PrimValue tmp = null;
            if (v.getType() != this.getType()) {
                tmp = this.castFrom((PrimValue) v);
            } else {
                tmp = (PrimValue) v;
            }
            m_mem = tmp.getContent();
        } else {
            throw new Error(
                    "PrimValue::copyfrom: copy from non-primitive value.");
        }
    }

}
