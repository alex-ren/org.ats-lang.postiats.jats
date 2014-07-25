package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CincludeDeserializer implements JsonDeserializer<D2Cinclude> {

    @Override
    public D2Cinclude deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
 
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je0 = jarr.get(0);
        int knd = je0.getAsInt();
        
        JsonElement je1 = jarr.get(1);
        List<Cd2ecl> d2cs = JsonUtilities.deserializeList(je1, Cd2ecl.class, context);
        
        D2Cinclude ret = new D2Cinclude(knd, d2cs);
        return ret;
    }

}



