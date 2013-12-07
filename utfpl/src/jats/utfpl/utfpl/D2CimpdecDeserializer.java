package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CimpdecDeserializer implements JsonDeserializer<D2Cimpdec> {

    @Override
    public D2Cimpdec deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je1 = jarr.get(0);
        int knd = je1.getAsInt();
        
        JsonElement je2 = jarr.get(1);
        Ci2mpdec imp = context.deserialize(je2, Ci2mpdec.class);
        
        D2Cimpdec ret = new D2Cimpdec(knd, imp);
        
        return ret;
    }

}


