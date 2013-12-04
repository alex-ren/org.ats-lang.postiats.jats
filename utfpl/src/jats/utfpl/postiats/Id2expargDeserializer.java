package jats.utfpl.postiats;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Id2expargDeserializer implements JsonDeserializer<Id2exparg> {

    @Override
    public Id2exparg deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2exparg_name");
        JsonElement je2 = jo.get("d2exparg_arglst");
        
        String name = je1.getAsString();
        
        if (name.equals("D2EXPARGsta")) {
            return context.deserialize(je2, D2EXPARGsta.class);
        } else if (name.equals("D2EXPARGdyn")) {
            return context.deserialize(je2, D2EXPARGdyn.class);
        } else {
            throw new Error("type not match");
        }
    }

}
