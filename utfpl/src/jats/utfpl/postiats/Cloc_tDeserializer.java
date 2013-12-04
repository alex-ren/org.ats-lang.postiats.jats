package jats.utfpl.postiats;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class Cloc_tDeserializer implements JsonDeserializer<Cloc_t> {

    @Override
    public Cloc_t deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return new Cloc_t(json.getAsString());
    }

}
