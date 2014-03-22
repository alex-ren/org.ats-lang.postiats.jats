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

public class Id2ecl_nodeDeserializer implements JsonDeserializer<Id2ecl_node> {

    @Override
    public Id2ecl_node deserialize(JsonElement json, Type typeOfT,
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
        
        if (name.equals("D2Cimpdec")) {
            return context.deserialize(je2, D2Cimpdec.class);
        } else if (name.equals("D2Cfundecs")) {
            return context.deserialize(je2, D2Cfundecs.class);
        } else if (name.equals("D2Cvaldecs")) {
            return context.deserialize(je2, D2Cvaldecs.class);
        } else if (name.equals("D2Clocal")) {
            throw new Error("not supported");
        } else if (name.equals("D2Cdcstdecs")) {
            return context.deserialize(je2, D2Cdcstdecs.class);
        } else if (name.equals("D2Cextcode")) {
        	return context.deserialize(je2, D2Cextcode.class);
        } else {
            return context.deserialize(je2, D2Cignored.class);
        }
    }

}
