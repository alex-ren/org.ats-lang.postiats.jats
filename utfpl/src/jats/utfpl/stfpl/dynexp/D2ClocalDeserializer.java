package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ClocalDeserializer implements JsonDeserializer<D2Clocal> {

    @Override
    public D2Clocal deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() < 2) {
            throw new Error("not match");
        }
        
        // local xxx (a) in ... (b) end
        
        // Currently a is omitted.
        // JsonElement je1 = jarr.get(0);
        
        JsonElement je2 = jarr.get(1);
        List<Cd2ecl> d2cs = JsonUtilities.deserializeList(je2, Cd2ecl.class, context);

        D2Clocal ret = new D2Clocal(d2cs);
        return ret;
    }

}
