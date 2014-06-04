package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.staexp.Cs2exp;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Cd2conDeserializer implements JsonDeserializer<Cd2con> {
    
    private Map<Cstamp, Cd2con> m_map;
    
    public Cd2conDeserializer() {
        m_map = new HashMap<Cstamp, Cd2con>();
    }

    @Override
    public Cd2con deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2con_stamp");
        Cstamp stamp = context.deserialize(je1, Cstamp.class);
        
        Cd2con d2con = m_map.get(stamp);
        if (null != d2con) {
            return d2con;
        } else {
            JsonElement je2 = jo.get("d2con_name");
            Csymbol symbol = context.deserialize(je2, Csymbol.class);

            Cs2exp type = null;
            if (jo.has("d2con_type")) {
                JsonElement je3 = jo.get("d2con_type");
                type = context.deserialize(je3, Cs2exp.class);
            }
            d2con = new Cd2con(stamp, type, symbol);
            m_map.put(stamp, d2con);
            return d2con;
        }
        
    }
}
