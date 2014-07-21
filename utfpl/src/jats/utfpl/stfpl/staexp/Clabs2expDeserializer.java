package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Clabs2expDeserializer implements JsonDeserializer<Clabs2exp> {

    @Override
    public Clabs2exp deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je = jo.get("SL0ABELED");
        
        JsonArray jarr = je.getAsJsonArray();
                
        Ilabel label = context.deserialize(jarr.get(0), Ilabel.class);
        String name = JsonUtilities.deserializeOption(jarr.get(1), String.class, context);
        Cs2exp s2exp = context.deserialize(jarr.get(2), Cs2exp.class);
        
        Clabs2exp ret = new Clabs2exp(label, name, s2exp);
        
        return ret;
        
    }

}
