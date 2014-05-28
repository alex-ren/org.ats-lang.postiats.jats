package jats.utfpl.utfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2Ef0loatDeserializer implements JsonDeserializer<D2Ef0loat> {

    @Override
    public D2Ef0loat deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        String f0loat = je.getAsString();
        
        D2Ef0loat ret = new D2Ef0loat(f0loat);
        return ret;
    }
    

}
