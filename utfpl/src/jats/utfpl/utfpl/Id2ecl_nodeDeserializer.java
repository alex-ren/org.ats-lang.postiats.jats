package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Id2ecl_nodeDeserializer implements JsonDeserializer<Id2ecl_node> {

    @Override
    public Id2ecl_node deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2ecl_name");
        JsonElement je2 = jo.get("d2ecl_arglst");
        
        String name = je1.getAsString();
        
        if (name.equals("D2Cimpdec")) {
            return context.deserialize(je2, D2Cimpdec.class);
        } else if (name.equals("D2Cfundecs")) {
            return context.deserialize(je2, D2Cfundecs.class);
        } else if (name.equals("D2Cvaldecs")) {
            return context.deserialize(je2, D2Cvaldecs.class);
        } else {
            return context.deserialize(je2, D2Cignored.class);
        }
    }

}
