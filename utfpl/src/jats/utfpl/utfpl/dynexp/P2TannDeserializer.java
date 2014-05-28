package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.staexp.Cs2exp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TannDeserializer implements JsonDeserializer<P2Tann> {

    @Override
    public P2Tann deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        Cp2at pat = context.deserialize(jarr.get(0), Cp2at.class);
        Cs2exp ann = context.deserialize(jarr.get(1), Cs2exp.class);
        
        P2Tann ret = new P2Tann(pat, ann);
        
        
        return ret;
    }

}
