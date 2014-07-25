package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ClistDeserializer implements JsonDeserializer<D2Clist> {

    @Override
    public D2Clist deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
 
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        List<Cd2ecl> d2cs = JsonUtilities.deserializeList(je, Cd2ecl.class, context);
        
        D2Clist ret = new D2Clist(d2cs);
        return ret;
    }


}
