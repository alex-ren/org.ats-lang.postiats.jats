package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EuniDeserializer implements JsonDeserializer<S2Euni> {

    @Override
    public S2Euni deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        List<Cs2var> s2vs = JsonUtilities.deserializeList(jarr.get(0), Cs2var.class, context);
        List<Cs2exp> s2ps = JsonUtilities.deserializeList(jarr.get(1), Cs2exp.class, context);
        Cs2exp s2e_body = context.deserialize(jarr.get(2), Cs2exp.class);
                
        return new S2Euni(s2vs, s2ps, s2e_body);
    }

}
