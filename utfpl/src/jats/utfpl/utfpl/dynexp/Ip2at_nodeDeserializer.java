package jats.utfpl.utfpl.dynexp;


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
        } else if (name.equals("P2Tcon")) {
        	return context.deserialize(je2, P2Tcon.class);
        } else if (name.equals("P2Tint")) {
            throw new Error("P2Tint is not supported");
        } else if (name.equals("P2Tintrep")) {
            throw new Error("P2Tintrep");
        } else if (name.equals("P2Tbool")) {
            throw new Error("P2Tbool is not supported");
        } else if (name.equals("P2Tchar")) {
            throw new Error("P2Tchar is not supported");
        } else if (name.equals("P2Tfloat")) {
            throw new Error("P2Tfloat is not supported");
        } else if (name.equals("P2Tstring")) {
            throw new Error("P2Tstring is not supported");
        } else if (name.equals("P2Ti0nt")) {
            throw new Error("P2Ti0nt is not supported");
        } else if (name.equals("P2Tf0loat")) {
            throw new Error("P2Tf0loat is not supported");
        } else if (name.equals("P2Tempty")) {
            return context.deserialize(je2, P2Tempty.class);
        } else if (name.equals("P2Trec")) {
            return context.deserialize(je2, P2Trec.class);
        } else if (name.equals("P2Trefas")) {
            throw new Error("P2Trefas is not supported");
        } else if (name.equals("P2Tvbox")) {
            throw new Error("P2Tvbox is not supported");
        } else if (name.equals("P2Tann")) {
            return context.deserialize(je2, P2Tann.class);
        } else if (name.equals("P2Terrpat")) {
            throw new Error("P2Terrpat is not supported");
        } else if (name.equals("P2Tignored")) {
            return context.deserialize(je2, P2Tignored.class);
        } else {
            throw new Error(name + "is not expected");
        }

    }

}
