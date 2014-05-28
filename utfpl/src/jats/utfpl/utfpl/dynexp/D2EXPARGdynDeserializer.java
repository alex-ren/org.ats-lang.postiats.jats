package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cloc_t;
import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EXPARGdynDeserializer implements JsonDeserializer<D2EXPARGdyn> {

    @Override
    public D2EXPARGdyn deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je1 = jarr.get(0);
        int npf = je1.getAsInt();
        
        JsonElement je2 = jarr.get(1);
        Cloc_t loc = context.deserialize(je2, Cloc_t.class);
        
        JsonElement je3 = jarr.get(2);
        List<Cd2exp> d2es = JsonUtilities.deserializeList(je3, Cd2exp.class, context);
        
        D2EXPARGdyn ret = new D2EXPARGdyn(npf, loc, d2es);
        return ret;
    }

}
