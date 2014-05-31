package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EexiDeserializer implements JsonDeserializer<S2Eexi> {

    @Override
    public S2Eexi deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        List<Cs2var> s2vs = JsonUtilities.deserializeList(jarr.get(0), Cs2var.class, context);
        List<Cs2exp> s2ps = JsonUtilities.deserializeList(jarr.get(1), Cs2exp.class, context);
        Cs2exp s2e_body = context.deserialize(jarr.get(2), Cs2exp.class);
                
        return new S2Eexi(s2vs, s2ps, s2e_body);
    }

}
