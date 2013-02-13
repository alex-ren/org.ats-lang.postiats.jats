package org.ats_lang.postiats.jats.value;


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
    
    public void copyfrom(ATSValue v);
    
    public Object getContent();
    
    public ATSValue deepcopy();
    

}


