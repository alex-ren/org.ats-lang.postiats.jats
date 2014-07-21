package jats.utfpl.stfpl.dynexp;


import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EannSeffDeserializer implements JsonDeserializer<D2EannSeff> {

    @Override
    public D2EannSeff deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        // pats_dynexp2_jsonize.dats
        // line 572
        JsonArray jarr = json.getAsJsonArray();

        JsonElement jed = jarr.get(0);
        Cd2exp d2e = context.deserialize(jed, Cd2exp.class);
        

        D2EannSeff ret = new D2EannSeff(d2e);
        return ret;
    }

}
