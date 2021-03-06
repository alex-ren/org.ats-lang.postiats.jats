package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.ccomp.DefaultLibraryTypes;
import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.staexp.SExpTypeExtractor;
import jats.utfpl.stfpl.stype.ISType;

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
    private DefaultLibraryTypes m_fac;
    
    public Cd2cstDeserializer(DefaultLibraryTypes fac) {
        m_map = new HashMap<Cstamp, Cd2cst>();
        m_fac = fac;
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
            Cs2exp type = null;
            if (null != je3) {
                type = context.deserialize(je3, Cs2exp.class);
            }

//            // todo: This is just a hack now.
//            if (symbol.getData().equals("castvwtp1")) {
//            	ISType stype = m_fac.queryType(symbol);
//                d2cst = new Cd2cst(stamp, type, symbol, stype);
//                m_map.put(stamp, d2cst);
//                return d2cst;
//            	
//            }
            ISType stype = SExpTypeExtractor.extractType(type);
            if (null == stype) {
                stype = m_fac.queryType(symbol);
            }
            
            d2cst = new Cd2cst(stamp, type, symbol, stype);
            m_map.put(stamp, d2cst);
            return d2cst;
        } 
    }
    

}
