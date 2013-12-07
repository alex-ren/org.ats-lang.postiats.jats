package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TpatDeserializer implements JsonDeserializer<P2Tpat> {

    @Override
    public P2Tpat deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        int len = jarr.size();
        if (len < 2) {
            throw new Error("Length is < 2");
        }
        JsonElement je = jarr.get(0);
        Cp2at p2at = context.deserialize(je, Cp2at.class);
        return new P2Tpat(p2at);
        
    }

}
