package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CvaldecsDeserializer implements JsonDeserializer<D2Cvaldecs> {

    @Override
    public D2Cvaldecs deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je1 = jarr.get(0);
        Evalkind knd = context.deserialize(je1, Evalkind.class);
        
        JsonElement je2 = jarr.get(1);
        List<Cv2aldec> v2ds = JsonUtilities.deserializeList(je2, Cv2aldec.class, context);
        
        D2Cvaldecs ret = new D2Cvaldecs(knd, v2ds);
        return ret;
    }
    

}
