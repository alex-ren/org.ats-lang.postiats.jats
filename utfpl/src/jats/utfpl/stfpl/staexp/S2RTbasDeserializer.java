package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Csymbol;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2RTbasDeserializer implements JsonDeserializer<S2RTbas> {

    @Override
    public S2RTbas deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        Csymbol sym = context.deserialize(je, Csymbol.class);
        S2RTbas ret = new S2RTbas(sym);
        return ret;
    }



}
