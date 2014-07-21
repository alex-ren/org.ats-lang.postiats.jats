package jats.utfpl.utfpl.staexp;

import jats.utfpl.stfpl.Cstamp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TYRECKINDflt1Deserializer implements JsonDeserializer< TYRECKINDflt1> {

    @Override
    public  TYRECKINDflt1 deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        
        JsonArray jarr = json.getAsJsonArray();
        
        JsonElement je0 = jarr.get(0);
        Cstamp s = context.deserialize(je0, Cstamp.class);
        
        return new TYRECKINDflt1(s);
    }

}
