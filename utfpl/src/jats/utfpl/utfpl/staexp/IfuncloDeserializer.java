package jats.utfpl.utfpl.staexp;

import jats.utfpl.stfpl.JsonUtilities;
import jats.utfpl.utils.Log;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class IfuncloDeserializer implements JsonDeserializer<Ifunclo> {

    @Override
    public Ifunclo deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je = jo.get("funclo_name");
        
        String name = je.getAsString();
        
        if (name.equals("FUNCLOfun")) {
            return FUNCLOfun.cInstance;
        } else if (name.equals("FUNCLOclo")) {
            JsonElement jeArgs = jo.get("funclo_arglst");
            List<Integer> arglst = JsonUtilities.deserializeList(jeArgs, Integer.class, context);
            if (arglst.size() != 1) {
                Log.log4j.error("type mismatch");
                throw new Error("type mismatch");
            }
            return new FUNCLOclo(arglst.get(0));
        } else {
            Log.log4j.error(name + " not supported");
            throw new Error(name + " not supported");
        }
    }
        
}

