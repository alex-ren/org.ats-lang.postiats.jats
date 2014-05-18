package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TannDeserializer implements JsonDeserializer<P2Tann> {

    @Override
    public P2Tann deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        json.getAsJsonArray();
        
        
        return P2Tany.cInstance;
    }

}
