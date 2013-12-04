package jats.utfpl.postiats;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CignoredDeserializer implements JsonDeserializer<D2Cignored> {

    @Override
    public D2Cignored deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return D2Cignored.cInstance;
    }

}
