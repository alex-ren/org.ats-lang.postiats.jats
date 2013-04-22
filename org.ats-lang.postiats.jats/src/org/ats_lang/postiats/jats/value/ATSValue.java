package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;


/* iats type | Java Type
 * int 	  -> Integer
 * float  -> Float
 * char   -> Char
 * string -> String
 * bool   -> Boolean
 * 
 * struct -> map<String, ATSValue>
 */

/*
 * This is actually a holder instead of just a value.
 */
public interface ATSValue {

    public void copyfrom(Object v);
    
    public ATSValue deepcopy();
    
    public Object getContent();
    
    public ATSType getType();

}x


