package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TignoredDeserializer implements JsonDeserializer<P2Tignored> {

    @Override
    public P2Tignored deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return P2Tignored.cInstance;
    }
    
}
