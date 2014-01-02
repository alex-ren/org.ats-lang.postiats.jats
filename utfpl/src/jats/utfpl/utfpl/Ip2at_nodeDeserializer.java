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

public class Ip2at_nodeDeserializer implements JsonDeserializer<Ip2at_node> {

    @Override
    public Ip2at_node deserialize(JsonElement json, Type typeOfT,
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
        

        if (name.equals("P2Tany")) {
            return context.deserialize(je2, P2Tany.class);
        } else if (name.equals("P2Tvar")) {
            return context.deserialize(je2, P2Tvar.class);
        } else if (name.equals("P2Tann")) {
            return context.deserialize(je2, P2Tpat.class);
        } else if (name.equals("P2Tempty")) {
            return context.deserialize(je2, P2Tempty.class);
        } else if (name.equals("P2Trec")) {
        	throw new Error("todo");
        } else {
            return context.deserialize(je2, P2Tignored.class);
        }

    }

}
