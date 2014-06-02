package jats.utfpl.utfpl.staexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EintinfDeserializer implements JsonDeserializer<S2Eintinf> {

    @Override
    public S2Eintinf deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
    	JsonArray jarr = json.getAsJsonArray();
    	JsonElement je = jarr.get(0);
        String i = je.getAsString();
        return new S2Eintinf(i);
    }

}
