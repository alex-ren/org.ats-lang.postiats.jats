package org.ats_lang.postiats.jats.value;

import java.util.Map;
import java.util.Set;

public class ATSValueUtil {

    public static void update (Object holder, Object src) {
        if (holder instanceof LValue) {
            
        } else if (holder instanceof Map<?, ?>) {  // Updating a variable of flat tuple
            // the src must be of type Map<String, Object>
            @SuppressWarnings("unchecked")
            Map<String, Object> mholder = (Map<String, Object>) holder;
            Set<String> names = mholder.keySet();
            for (String name: names) {
                Object old = mholder.get(name);
                if 
            }
            
        } else {
            throw new Error("Updating non-holder is forbidden.");
        }
    }

}
