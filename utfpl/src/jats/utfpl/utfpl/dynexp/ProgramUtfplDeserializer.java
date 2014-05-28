package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ProgramUtfplDeserializer implements JsonDeserializer<ProgramUtfpl> {

    @Override
    public ProgramUtfpl deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        List<Cd2ecl> d2ecs = JsonUtilities.deserializeList(jarr, Cd2ecl.class, context);
        ProgramUtfpl prog = new ProgramUtfpl(d2ecs);
        
        return prog;
    }


}
