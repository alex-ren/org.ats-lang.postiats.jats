package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2RTtupDeserializer implements JsonDeserializer<S2RTtup> {

    @Override
    public S2RTtup deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonArray jarr = json.getAsJsonArray();
        JsonElement je_s2ts = jarr.get(0);
        
        List<Is2rt> s2ts = JsonUtilities.deserializeList(je_s2ts, Is2rt.class, context);
        
        S2RTtup ret = new S2RTtup(s2ts);
        return ret;

    }


}
