package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Csymbol;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Cd2symDeserializer implements JsonDeserializer<Cd2sym> {

    @Override
    public Cd2sym deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement je1 = jo.get("d2sym_name");

        Csymbol sym = context.deserialize(je1, Csymbol.class);
        Cd2sym ret = new Cd2sym(sym);
        return ret;
    }
}
