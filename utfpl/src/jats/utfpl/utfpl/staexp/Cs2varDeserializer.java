package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Cs2varDeserializer implements JsonDeserializer<Cs2var> {
    private Map<Cstamp, Cs2var> m_map;
    
    public Cs2varDeserializer() {
        m_map = new HashMap<Cstamp, Cs2var>();
    }
    
    @Override
    public Cs2var deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("s2var_stamp");
        Cstamp stamp = context.deserialize(je1, Cstamp.class);
        
        Cs2var s2var = m_map.get(stamp);
        if (null != s2var) {
            return s2var;
        } else {
            JsonElement je2 = jo.get("s2var_name");
            Csymbol symbol = context.deserialize(je2, Csymbol.class);
            
            JsonElement je3 = jo.get("s2var_srt");
            Is2rt srt = context.deserialize(je3, Is2rt.class);
            
            s2var = new Cs2var(symbol, stamp, srt);
            m_map.put(stamp, s2var);
            return s2var;
            
        }
    }   

}
