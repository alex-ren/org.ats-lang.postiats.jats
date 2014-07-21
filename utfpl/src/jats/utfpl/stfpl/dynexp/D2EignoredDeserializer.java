package jats.utfpl.stfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EignoredDeserializer implements JsonDeserializer<D2Eignored> {

    @Override
    public D2Eignored deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return D2Eignored.cInstance;
    }

}
