package jats.utfpl.stfpl.staexp;


import jats.utfpl.utils.Log;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonParseException;


public class Is2rtDeserializer implements JsonDeserializer<Is2rt> {

    @Override
    public Is2rt deserialize(JsonElement json, Type typeOfT,
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
        

        if (name.equals("S2RTbas")) {
            return context.deserialize(je2, S2RTbas.class);
        } else if (name.equals("S2RTfun")) {
            return context.deserialize(je2, S2RTfun.class);
        } else if (name.equals("S2RTtup")) {
            return context.deserialize(je2, S2RTtup.class);
        } else {
            Log.log4j.error(name + " unexpected");
            throw new Error(name + " unexpected");
        }
    }
}
