package jats.utfpl.utfpl;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CextcodeDeserializer  implements JsonDeserializer<D2Cextcode> {

    @Override
    public D2Cextcode deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() != 3) {
        	throw new Error("type not match");
        }
        
        JsonElement je1 = jarr.get(2);
        String extCode = je1.getAsString();
        
        D2Cextcode ret = new D2Cextcode(extCode);
        return ret;
    }
}
