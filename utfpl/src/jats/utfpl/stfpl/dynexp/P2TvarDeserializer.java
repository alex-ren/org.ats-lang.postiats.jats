package jats.utfpl.stfpl.dynexp;


import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TvarDeserializer implements JsonDeserializer<P2Tvar> {

    @Override
    public P2Tvar deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        Cd2var d2var = context.deserialize(je, Cd2var.class);
        return new P2Tvar(d2var);
    }

}
