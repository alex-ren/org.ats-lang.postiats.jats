package jats.utfpl.utfpl.staexp;


import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EeqeqDeserializer implements JsonDeserializer<S2Eeqeq> {

    @Override
    public S2Eeqeq deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonArray jarr = json.getAsJsonArray();
        JsonElement je0 = jarr.get(0);
        Cs2exp left = context.deserialize(je0, Cs2exp.class);
        JsonElement je1 = jarr.get(1);
        Cs2exp right = context.deserialize(je1, Cs2exp.class);

        return new S2Eeqeq(left, right);
    }

}
