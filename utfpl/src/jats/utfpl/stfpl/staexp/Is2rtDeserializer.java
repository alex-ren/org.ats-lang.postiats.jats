package jats.utfpl.stfpl.staexp;


import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import com.google.gson.JsonParseException;


public class Is2rtDeserializer implements JsonDeserializer<Is2rt> {

    @Override
    public Is2rt deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        String srt = json.getAsString();
        S2RT ret = new S2RT(srt);
        return ret;
    }
}
