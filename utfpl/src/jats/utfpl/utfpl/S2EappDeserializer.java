package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EappDeserializer implements JsonDeserializer<S2Eapp> {

    @Override
    public S2Eapp deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        JsonElement je0 = jarr.get(0);
        Is2exp_node s2e1 = context.deserialize(je0, Is2exp_node.class);
        
        JsonElement je1 = jarr.get(1);
        JsonArray jarglst = je1.getAsJsonArray();
        
        List<Is2exp_node> s2es2 = JsonUtilities.deserializeList(jarglst, Is2exp_node.class, context);
        S2Eapp s2eapp = new S2Eapp(s2e1, s2es2);
        
        return s2eapp;
    }

}
