package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EifoptDeserializer implements JsonDeserializer<D2Eifopt> {

    @Override
    public D2Eifopt deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        // skip 0
        JsonElement je1 = jarr.get(1);
        Cd2exp _test = context.deserialize(je1, Cd2exp.class);
        
        JsonElement je2 = jarr.get(2);
        Cd2exp _then = context.deserialize(je2, Cd2exp.class);
        
        JsonElement je3 = jarr.get(3);
        Cd2exp _else = JsonUtilities.deserializeOption(je3, Cd2exp.class, context);
        
        D2Eifopt ret = new D2Eifopt(_test, _then, _else);
        
        return ret;        
        
    }

}
