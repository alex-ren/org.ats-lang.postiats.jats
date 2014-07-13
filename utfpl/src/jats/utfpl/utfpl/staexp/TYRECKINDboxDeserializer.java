package jats.utfpl.utfpl.staexp;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TYRECKINDboxDeserializer implements JsonDeserializer<TYRECKINDbox> {

    @Override
    public TYRECKINDbox deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return TYRECKINDbox.cInstance;
    }

}
