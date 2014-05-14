package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EannTypeDeserializer implements JsonDeserializer<D2EannType> {

    @Override
    public D2EannType deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        // pats_dynexp2_jsonize.dats
        // line 537
        JsonArray jarr = json.getAsJsonArray();

        JsonElement jed = jarr.get(0);
        Cd2exp d2e = context.deserialize(jed, Cd2exp.class);
        
        JsonElement jes = jarr.get(1);
        Cs2exp s2e = context.deserialize(jes, Cs2exp.class);

        D2EannType ret = new D2EannType(d2e, s2e);
        return ret;
    }
}
