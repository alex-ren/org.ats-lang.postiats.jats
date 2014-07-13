package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EtyrecDeserializer implements JsonDeserializer<S2Etyrec> {

    @Override
    public S2Etyrec deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        Ityreckind knd = context.deserialize(jarr.get(0), Ityreckind.class);

        int npf = jarr.get(1).getAsInt();
        
        List<Clabs2exp> ls2es = JsonUtilities.deserializeList(jarr.get(2), Clabs2exp.class, context);
        
        S2Etyrec ret = new S2Etyrec(knd, npf, ls2es);
        return ret;
    }

}
