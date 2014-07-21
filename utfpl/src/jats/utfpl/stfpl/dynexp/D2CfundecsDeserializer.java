package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CfundecsDeserializer implements JsonDeserializer<D2Cfundecs> {

    @Override
    public D2Cfundecs deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je1 = jarr.get(0);
        Efunkind knd = context.deserialize(je1, Efunkind.class);
        
        JsonElement je2 = jarr.get(2);
        List<Cf2undec> f2ds = JsonUtilities.deserializeList(je2, Cf2undec.class, context);
        
        D2Cfundecs ret = new D2Cfundecs(knd, f2ds);
        return ret;
    }
    
}
