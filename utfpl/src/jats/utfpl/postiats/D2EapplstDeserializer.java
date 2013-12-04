package jats.utfpl.postiats;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EapplstDeserializer implements JsonDeserializer<D2Eapplst> {

    @Override
    public D2Eapplst deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je1 = jarr.get(0);
        
        Cd2exp d2e_fun = context.deserialize(je1, Cd2exp.class);
        
        JsonElement je2 = jarr.get(1);
        List<Id2exparg> d2as_arg = JsonUtilities.deserializeList(je2, Id2exparg.class, context);
        
        D2Eapplst applst = new D2Eapplst(d2e_fun, d2as_arg);
        return applst;
        
    }

}

