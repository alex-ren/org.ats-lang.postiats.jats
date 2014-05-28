package jats.utfpl.utfpl.dynexp;


import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EcstDeserializer implements JsonDeserializer<D2Ecst> {

    @Override
    public D2Ecst deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        Cd2cst d2cst = context.deserialize(je, Cd2cst.class);
        
        D2Ecst ret = new D2Ecst(d2cst);
        return ret;
    }

}
