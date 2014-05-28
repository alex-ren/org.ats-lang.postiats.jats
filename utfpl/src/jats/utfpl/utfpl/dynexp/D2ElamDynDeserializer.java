package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2ElamDynDeserializer implements JsonDeserializer<D2ElamDyn> {

    @Override
    public D2ElamDyn deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() < 4) {
            throw new Error("type not match");
        }
        
        // jarr has 4 elements.
        // The first one is lin, which has something to do with linear type, I think.
        // The second one is npf (number of proof args); 
        //     if npf >= 0, then it tells you how many of the given arguments are proof arguments.
        // The third one is the list of arguments including both proof arguments and normal arguments.
        // The Fourth one is the body of the function.
        int lin = jarr.get(0).getAsInt();
        int npf = jarr.get(1).getAsInt();
        
        JsonElement je1 = jarr.get(2);
        List<Cp2at> p2ts = JsonUtilities.deserializeList(je1, Cp2at.class, context);
        
        JsonElement je2 = jarr.get(3);
        Cd2exp d2e_body = context.deserialize(je2, Cd2exp.class);
        
        D2ElamDyn ret = new D2ElamDyn(lin, npf, p2ts, d2e_body);
        return ret;
    }
    
}
