package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CdcstdecsDeserializer implements JsonDeserializer<D2Cdcstdecs> {

    @Override
    public D2Cdcstdecs deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je0 = jarr.get(0);
        int knd = je0.getAsInt();
        
        JsonElement je1 = jarr.get(1);
        Edcstkind dck = context.deserialize(je1, Edcstkind.class);
        
        JsonElement je2 = jarr.get(2);
        List<Cd2cst> csts = JsonUtilities.deserializeList(je2, Cd2cst.class, context);
        
        D2Cdcstdecs ret = new D2Cdcstdecs(knd, dck, csts);
        return ret;
    }

}
