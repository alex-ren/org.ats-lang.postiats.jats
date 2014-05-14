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

public class Is2exp_nodeDeserializer implements JsonDeserializer<Is2exp_node> {

    @Override
    public Is2exp_node deserialize(JsonElement json, Type typeOfT,
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
        

        if (name.equals("S2Eint")) {
            return context.deserialize(je2, S2Eint.class);
        } else if (name.equals("S2Eintinf")) {
            return context.deserialize(je2, S2Eintinf.class);
        } else if (name.equals("S2Ecst")) {
            return context.deserialize(je2, S2Ecst.class);
        } else if (name.equals("S2Eextype")) {
            return context.deserialize(je2, S2Eextype.class);
        } else if (name.equals("S2Eextkind")) {
            return context.deserialize(je2, S2Eextkind.class);
        } else if (name.equals("S2Evar")) {
            return context.deserialize(je2, S2Evar.class);
        } else if (name.equals("S2EVar")) {
            return context.deserialize(je2, S2EVar.class);
        } else if (name.equals("S2Esizeof")) {
            return context.deserialize(je2, S2Esizeof.class);
        } else if (name.equals("S2Eeqeq")) {
            return context.deserialize(je2, S2Eeqeq.class);
        } else if (name.equals("S2Eapp")) {
            return context.deserialize(je2, S2Eapp.class);
        } else if (name.equals("S2Efun")) {
            return context.deserialize(je2, S2Efun.class);
        } else if (name.equals("S2Emetdec")) {
            return context.deserialize(je2, S2Emetdec.class);
        } else if (name.equals("S2Einvar")) {
            return context.deserialize(je2, S2Einvar.class);
        } else if (name.equals("S2Eerr")) {
            return context.deserialize(je2, S2Eerr.class);
        } else if (name.equals("S2Eignored")) {
            return context.deserialize(je2, S2Eignored.class);
        } else {
            
        }

    }
}
