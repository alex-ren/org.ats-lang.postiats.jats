package jats.utfpl.stfpl.staexp;

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

public class Cs2cstDeserializer implements JsonDeserializer<Cs2cst> {
    
    private Map<Cstamp, Cs2cst> m_map;
    
    public Cs2cstDeserializer() {
        m_map = new HashMap<Cstamp, Cs2cst>();
    }

    @Override
    public Cs2cst deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("s2cst_stamp");
        Cstamp stamp = context.deserialize(je1, Cstamp.class);
        
        Cs2cst s2cst = m_map.get(stamp);
        if (null != s2cst) {
            return s2cst;
        } else {
            JsonElement je2 = jo.get("s2cst_name");
            Csymbol symbol = context.deserialize(je2, Csymbol.class);
            
            JsonElement je3 = jo.get("s2cst_srt");
            Is2rt s2rt = context.deserialize(je3, Is2rt.class);
            
            
            s2cst = new Cs2cst(stamp, symbol, s2rt);
            m_map.put(stamp, s2cst);
            return s2cst;
        }
        // todo 
        // s2cst_supcls
        
    }

}
