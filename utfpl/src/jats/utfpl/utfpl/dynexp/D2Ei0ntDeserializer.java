package jats.utfpl.utfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2Ei0ntDeserializer implements JsonDeserializer<D2Ei0nt> {

    @Override
    public D2Ei0nt deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        String i0nt = je.getAsString();
        
        D2Ei0nt ret = new D2Ei0nt(i0nt);
        return ret;
    }

}
