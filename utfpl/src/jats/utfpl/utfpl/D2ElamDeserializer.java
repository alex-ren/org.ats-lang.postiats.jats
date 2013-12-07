package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ElamDeserializer implements JsonDeserializer<D2Elam> {

    @Override
    public D2Elam deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() < 4) {
            throw new Error("type not match");
        }
        
        JsonElement je1 = jarr.get(2);
        List<Cp2at> p2ts = JsonUtilities.deserializeList(je1, Cp2at.class, context);
        
        JsonElement je2 = jarr.get(3);
        Cd2exp d2e_body = context.deserialize(je2, Cd2exp.class);
        
        D2Elam ret = new D2Elam(p2ts, d2e_body);
        return ret;
    }
    
}
