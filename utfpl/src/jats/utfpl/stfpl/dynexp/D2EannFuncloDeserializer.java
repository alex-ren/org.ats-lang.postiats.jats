package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.staexp.Ifunclo;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2EannFuncloDeserializer implements JsonDeserializer<D2EannFunclo> {

    @Override
    public D2EannFunclo deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonArray jarr = json.getAsJsonArray();

        JsonElement je0 = jarr.get(0);
        Cd2exp d2e = context.deserialize(je0, Cd2exp.class);
        
        JsonElement je1 = jarr.get(1);
        Ifunclo funclo = context.deserialize(je1, Ifunclo.class);
        
        D2EannFunclo ret = new D2EannFunclo(d2e, funclo);

        return ret;
    }

}
