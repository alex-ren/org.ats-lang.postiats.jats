package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Cd2varDeserializer implements JsonDeserializer<Cd2var> {
    private Map<Cstamp, Cd2var> m_map;
    
    public Cd2varDeserializer() {
        m_map = new HashMap<Cstamp, Cd2var>();
    }
    
    @Override
    public Cd2var deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2var_stamp");
        Cstamp stamp = context.deserialize(je1, Cstamp.class);
        
        Cd2var d2var = m_map.get(stamp);
        if (null != d2var) {
            return d2var;
        } else {
            JsonElement je2 = jo.get("d2var_name");
            Csymbol symbol = context.deserialize(je2, Csymbol.class);
            d2var = new Cd2var(symbol, stamp);
            m_map.put(stamp, d2var);
            return d2var;
            
        }
    }   

}
