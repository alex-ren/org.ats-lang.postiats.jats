package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EeqeqDeserializer implements JsonDeserializer<S2Eeqeq> {

    @Override
    public S2Eeqeq deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        
        
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je0 = jarr.get(0);
        Is2exp_node left = context.deserialize(je0, Is2exp_node.class);
        JsonElement je1 = jarr.get(1);
        Is2exp_node right = context.deserialize(je1, Is2exp_node.class);
        
        
        return new S2Eeqeq(left, right);
    }

}
