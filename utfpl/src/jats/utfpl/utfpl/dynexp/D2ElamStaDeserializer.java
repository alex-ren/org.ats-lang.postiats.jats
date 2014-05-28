package jats.utfpl.utfpl.dynexp;


import jats.utfpl.utfpl.JsonUtilities;
import jats.utfpl.utfpl.staexp.Cs2exp;
import jats.utfpl.utfpl.staexp.Cs2var;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ElamStaDeserializer implements JsonDeserializer<D2ElamSta> {

    @Override
    public D2ElamSta deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        JsonElement je0 = jarr.get(0);
        List<Cs2var> s2vs = JsonUtilities.deserializeList(je0, Cs2var.class, context);
        
        JsonElement je1 = jarr.get(1);
        List<Cs2exp> s2ps = JsonUtilities.deserializeList(je1, Cs2exp.class, context);
        
        JsonElement je2 = jarr.get(2);
        Cd2exp d2exp = context.deserialize(je2, Cd2exp.class);
        D2ElamSta ret = new D2ElamSta(s2vs, s2ps, d2exp);

        return ret;
    }

}
