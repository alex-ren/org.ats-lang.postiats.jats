package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Ilabp2atDeserializer implements JsonDeserializer<Ilabp2at> {

    @Override
    public Ilabp2at deserialize(JsonElement json, Type typeOfT,
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
        

        if (name.equals("LABP2ATnorm")) {
            JsonArray jarr = je2.getAsJsonArray();
            Ilabel lab = context.deserialize(jarr.get(0), Ilabel.class);
            Cp2at pat = context.deserialize(jarr.get(1), Cp2at.class);
            return new LABP2ATnorm(lab, pat);
        } else if (name.equals("LABP2ATomit")) {
            JsonArray jarr = je2.getAsJsonArray();
            if (jarr.size() < 1) {
                throw new Error("type mismatch -- parsing/parsing_p2at.dats @138");
            }
            return new LABP2ATomit();
        } else {
            throw new Error("type not match -- parsing/parsing_p2at.dats @144");
        }

    }

}


