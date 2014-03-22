package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ElamMetDeserializer implements JsonDeserializer<D2ElamMet> {

    @Override
    public D2ElamMet deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() < 2) {
            throw new Error("not match");
        }
        JsonElement je1 = jarr.get(1);
        Cd2exp d2exp = context.deserialize(je1, Cd2exp.class);
        D2ElamMet ret = new D2ElamMet(d2exp);

        return ret;
    }

}
