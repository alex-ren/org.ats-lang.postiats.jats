package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        Set<Map.Entry<String, JsonElement>> itemSet = jo.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iter = itemSet.iterator();

        Map.Entry<String, JsonElement> item = iter.next();
        if (iter.hasNext()) {
        	throw new Error("type not match");
        }

        String name = item.getKey();
        JsonElement je2 = item.getValue();
        
        if (name.equals("D2EXPARGsta")) {
            return context.deserialize(je2, D2EXPARGsta.class);
        } else if (name.equals("D2EXPARGdyn")) {
            return context.deserialize(je2, D2EXPARGdyn.class);
        } else {
            throw new Error("type not match");
        }
    }

}
