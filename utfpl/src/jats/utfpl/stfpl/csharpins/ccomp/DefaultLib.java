package jats.utfpl.stfpl.csharpins.ccomp;

import java.util.HashMap;
import java.util.Map;

public class DefaultLib {
    static public Map<String, String> s_sym_table = new HashMap<String, String>();
    
    static {
        s_sym_table.put("+", "CComp.pats_add");
        s_sym_table.put("-", "CComp.pats_sub");
        s_sym_table.put("*", "CComp.pats_mul");
        s_sym_table.put("/", "CComp.pats_div");
        
        s_sym_table.put("<",  "CComp.pats_lt");
        s_sym_table.put("<=", "CComp.pats_lte");
        s_sym_table.put(">",  "CComp.pats_gt");
        s_sym_table.put(">=", "CComp.pats_gte");
        
        s_sym_table.put("==", "CComp.pats_eq");
        s_sym_table.put("<>", "CComp.pats_neq");
        
        s_sym_table.put("print", "CComp.pats_print");
    }

}
