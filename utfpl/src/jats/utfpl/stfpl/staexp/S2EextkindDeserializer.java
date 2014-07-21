package jats.utfpl.stfpl.staexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EextkindDeserializer implements JsonDeserializer<S2Eextkind> {

    @Override
    public S2Eextkind deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
    	
    	JsonArray jarr = json.getAsJsonArray();
    	JsonElement je = jarr.get(0);
        String kind = je.getAsString();
        return new S2Eextkind(kind);
    }

}
