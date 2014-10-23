package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ProgramStfpl2Deserializer implements JsonDeserializer<ProgramStfpl2> {

    @Override
    public ProgramStfpl2 deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        List<Cd2ecl> d2ecs = JsonUtilities.deserializeList(jarr, Cd2ecl.class, context);
        ProgramStfpl2 prog = new ProgramStfpl2(d2ecs);
        
        return prog;
    }


}
