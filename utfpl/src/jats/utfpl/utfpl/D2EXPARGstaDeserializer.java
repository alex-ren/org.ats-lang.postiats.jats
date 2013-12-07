package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EXPARGstaDeserializer implements JsonDeserializer<D2EXPARGsta> {

    @Override
    public D2EXPARGsta deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        json.getAsJsonArray();
        return D2EXPARGsta.cInstance;
    }

}
