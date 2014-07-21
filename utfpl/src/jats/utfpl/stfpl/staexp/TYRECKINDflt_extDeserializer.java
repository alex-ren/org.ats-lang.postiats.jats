package jats.utfpl.stfpl.staexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TYRECKINDflt_extDeserializer implements JsonDeserializer< TYRECKINDflt_ext> {

    @Override
    public  TYRECKINDflt_ext deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        
        JsonArray jarr = json.getAsJsonArray();
        
        JsonElement je0 = jarr.get(0);
        String name = je0.getAsString();
        
        return new TYRECKINDflt_ext(name);
    }

}
