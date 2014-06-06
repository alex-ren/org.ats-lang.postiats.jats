package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EtupDeserializer implements JsonDeserializer<D2Etup> {

    @Override
    public D2Etup deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je0 = jarr.get(0);
        int knd = je0.getAsInt();
        
        JsonElement je1 = jarr.get(1);
        int npf = je1.getAsInt();
        
        JsonElement je2 = jarr.get(2);
        List<Cd2exp> d2es = JsonUtilities.deserializeList(je2, Cd2exp.class, context);
        
        D2Etup ret = new D2Etup(knd, npf, d2es);
        return ret;
    }
}
