package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class UtfplProgramDeserializer implements JsonDeserializer<UtfplProgram> {

    @Override
    public UtfplProgram deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        
        List<Cd2ecl> d2ecs = JsonUtilities.deserializeList(jarr, Cd2ecl.class, context);
        UtfplProgram prog = new UtfplProgram(d2ecs);
        
        return prog;
    }


}
