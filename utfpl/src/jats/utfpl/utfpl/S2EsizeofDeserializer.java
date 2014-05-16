package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EsizeofDeserializer implements JsonDeserializer<S2Esizeof> {

    @Override
    public S2Esizeof deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        Is2exp_node s2exp = context.deserialize(json, Is2exp_node.class);
                
        return new S2Esizeof(s2exp);
    }

}
