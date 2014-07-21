package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;
import jats.utfpl.stfpl.staexp.Cs2cst;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CstacstsDeserializer implements JsonDeserializer<D2Cstacsts> {

    @Override
    public D2Cstacsts deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
 
    	JsonArray jarr = json.getAsJsonArray();
    	JsonElement je = jarr.get(1);
        List<Cs2cst> s2csts = JsonUtilities.deserializeList(je, Cs2cst.class, context);
        
        D2Cstacsts ret = new D2Cstacsts(s2csts);
        return ret;
    }

}
