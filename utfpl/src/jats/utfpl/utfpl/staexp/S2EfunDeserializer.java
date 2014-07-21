package jats.utfpl.utfpl.staexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EfunDeserializer implements JsonDeserializer<S2Efun> {

    @Override
    public S2Efun deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        JsonElement je0 = jarr.get(0);
        int npf = je0.getAsInt();
        
        JsonElement je1 = jarr.get(1);
        List<Cs2exp> arg = JsonUtilities.deserializeList(je1, Cs2exp.class, context);
        
        JsonElement je2 = jarr.get(2);
        Cs2exp res = context.deserialize(je2, Cs2exp.class);
        
        
        return new S2Efun(npf, arg, res);
    }

}
