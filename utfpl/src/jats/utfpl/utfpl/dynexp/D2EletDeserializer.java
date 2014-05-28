package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EletDeserializer implements JsonDeserializer<D2Elet> {

    @Override
    public D2Elet deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() < 2) {
            throw new Error("not match");
        }
        JsonElement je1 = jarr.get(0);
        List<Cd2ecl> d2cs = JsonUtilities.deserializeList(je1, Cd2ecl.class, context);
        JsonElement je2 = jarr.get(1);
        Cd2exp d2e_body = context.deserialize(je2, Cd2exp.class);
        
        D2Elet ret = new D2Elet(d2cs, d2e_body);
        return ret;
    }

}
