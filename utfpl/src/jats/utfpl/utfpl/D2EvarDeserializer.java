package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EvarDeserializer implements JsonDeserializer<D2Evar> {

    @Override
    public D2Evar deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        Cd2var d2var = context.deserialize(je, Cd2var.class);
        
        D2Evar ret = new D2Evar(d2var);
        return ret;
    }

}
