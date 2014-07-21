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

public class ItyreckindDeserializer implements JsonDeserializer<Ityreckind> {

    @Override
    public Ityreckind deserialize(JsonElement json, Type typeOfT,
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
        

        if (name.equals("TYRECKINDbox")) {
            return context.deserialize(je2, TYRECKINDbox.class);
        } else if (name.equals("TYRECKINDbox_lin")) {
            return context.deserialize(je2, TYRECKINDbox_lin.class);
        } else if (name.equals("TYRECKINDflt0")) {
            return context.deserialize(je2, TYRECKINDflt0.class);
        } else if (name.equals("TYRECKINDflt1")) {
            return context.deserialize(je2, TYRECKINDflt1.class);
        } else if (name.equals("TYRECKINDflt_ext")) {
            return context.deserialize(je2, TYRECKINDflt_ext.class);
        } else {
            Log.log4j.error(name + " unexpected");
            throw new Error(name + " unexpected");
        }
    }
}
