package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.ccomp.DefaultLibraryTypes;
import jats.utfpl.utfpl.staexp.Cs2exp;
import jats.utfpl.utfpl.stype.ISType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Cd2cstDeserializer implements JsonDeserializer<Cd2cst> {
    
    private Map<Cstamp, Cd2cst> m_map;
    
    public Cd2cstDeserializer() {
        m_map = new HashMap<Cstamp, Cd2cst>();
    }

    @Override
    public Cd2cst deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2cst_stamp");
        Cstamp stamp = context.deserialize(je1, Cstamp.class);
        
        Cd2cst d2cst = m_map.get(stamp);
        if (null != d2cst) {
            return d2cst;
        } else {
            JsonElement je2 = jo.get("d2cst_name");
            Csymbol symbol = context.deserialize(je2, Csymbol.class);
            
            JsonElement je3 = jo.get("d2cst_type");
            Cs2exp type = context.deserialize(je3, Cs2exp.class);
            
            ISType stype = DefaultLibraryTypes.queryType(symbol);
            
            d2cst = new Cd2cst(stamp, type, symbol, stype);
            m_map.put(stamp, d2cst);
            return d2cst;
        }
        
    }
    

}
