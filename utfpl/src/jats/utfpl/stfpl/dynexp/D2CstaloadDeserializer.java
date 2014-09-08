package jats.utfpl.stfpl.dynexp;


import java.lang.reflect.Type;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CstaloadDeserializer implements JsonDeserializer<D2Cstaload> {

    @Override
    public D2Cstaload deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je1 = jarr.get(1);
        
        String file = je1.getAsString();
        
        D2Cstaload ret = new D2Cstaload(file);
        return ret;
    }

}
