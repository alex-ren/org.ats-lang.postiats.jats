package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EvarDeserializer implements JsonDeserializer<S2Evar> {

    @Override
    public S2Evar deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
    	JsonArray jarr = json.getAsJsonArray();
    	JsonElement je = jarr.get(0);
        Cs2var s2var = context.deserialize(je, Cs2var.class);
        S2Evar ret = new S2Evar(s2var);
        return ret;
    }


}
