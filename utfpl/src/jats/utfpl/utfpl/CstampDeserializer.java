package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class CstampDeserializer implements JsonDeserializer<Cstamp>{

    @Override
    public Cstamp deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return new Cstamp(json.getAsInt());
    }

}
