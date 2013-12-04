package jats.utfpl.postiats;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Ip2at_nodeDeserializer implements JsonDeserializer<Ip2at_node> {

    @Override
    public Ip2at_node deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement jsv1 = jo.get("p2at_name");
        JsonElement jsv2 = jo.get("p2at_arglst");
        
        String name = jsv1.getAsString();

        if (name.equals("P2Tany")) {
            return context.deserialize(jsv2, P2Tany.class);
        } else if (name.equals("P2Tvar")) {
            return context.deserialize(jsv2, P2Tvar.class);
        } else if (name.equals("P2Tann")) {
            return context.deserialize(jsv2, P2Tpat.class);
        } else {
            return context.deserialize(jsv2, P2Tignored.class);
        }

    }

}
