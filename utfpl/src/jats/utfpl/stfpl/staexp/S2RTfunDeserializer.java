package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2RTfunDeserializer implements JsonDeserializer<S2RTfun> {

    @Override
    public S2RTfun deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonArray jarr = json.getAsJsonArray();
        JsonElement je_args = jarr.get(0);
        JsonElement je_res = jarr.get(1);
        
        List<Is2rt> args = JsonUtilities.deserializeList(je_args, Is2rt.class, context);
        Is2rt res = context.deserialize(je_res, Is2rt.class);
        
        S2RTfun ret = new S2RTfun(args, res);
        return ret;

    }


}
