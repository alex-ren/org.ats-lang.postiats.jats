package jats.utfpl.utfpl.staexp;

import java.lang.reflect.Type;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EintDeserializer implements JsonDeserializer<S2Eint> {

    @Override
    public S2Eint deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        int i = json.getAsInt();
        return new S2Eint(i);
    }

}
