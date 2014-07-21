package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ElistDeserializer implements JsonDeserializer<D2Elist> {

    @Override
    public D2Elist deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() < 2) {
            throw new Error("not match");
        }
        JsonElement je0 = jarr.get(0);
        int npf = je0.getAsInt();
        
        JsonElement je1 = jarr.get(1);
        List<Cd2exp> d2es = JsonUtilities.deserializeList(je1, Cd2exp.class, context);

        D2Elist ret = new D2Elist(npf, d2es);
        return ret;
    }

}
