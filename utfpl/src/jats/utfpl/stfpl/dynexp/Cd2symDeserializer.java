package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.ccomp.DefaultLibraryTypes;
import jats.utfpl.stfpl.stype.ISType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Cd2symDeserializer implements JsonDeserializer<Cd2sym> {

    private Map<String, Cd2sym> m_map;
    
    private DefaultLibraryTypes m_fac;
    
    public Cd2symDeserializer(DefaultLibraryTypes fac) {
        m_map = new HashMap<String, Cd2sym>();
        m_fac = fac;
    }
    
    @Override
    public Cd2sym deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2sym_name");

        Csymbol sym = context.deserialize(je1, Csymbol.class);

        Cd2sym d2sym = m_map.get(sym.m_str);
        if (null != d2sym) {
            return d2sym;
        } else {
            ISType stype = m_fac.queryType(sym);
            Cd2sym ret = new Cd2sym(sym, stype);
            m_map.put(sym.m_str, ret);
            return ret;
        }
    }
}


