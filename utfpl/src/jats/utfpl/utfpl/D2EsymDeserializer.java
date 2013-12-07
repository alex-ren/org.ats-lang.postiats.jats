package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EsymDeserializer implements JsonDeserializer<D2Esym> {

    @Override
    public D2Esym deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        Cd2sym d2s = context.deserialize(je, Cd2sym.class);
        
        D2Esym ret = new D2Esym(d2s);
        return ret;
    }

}
