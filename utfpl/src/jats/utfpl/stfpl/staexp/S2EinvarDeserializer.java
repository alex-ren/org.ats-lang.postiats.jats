package jats.utfpl.stfpl.staexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EinvarDeserializer implements JsonDeserializer<S2Einvar> {

    @Override
    public S2Einvar deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
    	JsonArray jarr = json.getAsJsonArray();
    	JsonElement je = jarr.get(0);
    	Cs2exp s2exp = context.deserialize(je, Cs2exp.class);
                
        return new S2Einvar(s2exp);
    }

}
