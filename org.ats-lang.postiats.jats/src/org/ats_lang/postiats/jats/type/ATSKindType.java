/**
 * 
 */
package org.ats_lang.postiats.jats.type;

/**
 * @author aren
 *
 */
public abstract class ATSKindType implements ATSType {
    static public enum Decorator {TYPE, T0YPE};
    
    private Decorator m_dec;
    protected ATSKindType(Decorator dec) {
        m_dec = dec;
    }
    
    public Decorator getKind() {
        return m_dec;
    }

}
