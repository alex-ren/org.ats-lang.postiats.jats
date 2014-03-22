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

public class IlabelDeserializer implements JsonDeserializer<Ilabel> {

    @Override
    public Ilabel deserialize(JsonElement json, Type typeOfT,
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
        
        if (name.equals("LABint")) {
            int ji = je2.getAsInt();
            return new LABint(ji);
        } else if (name.equals("LABsym")) {
            Csymbol sym = context.deserialize(je2, Csymbol.class);
            return new LABsym(sym);
        } else {
            throw new Error("type not match -- parsing/parsing.dats @100");
        }
    }
}

        