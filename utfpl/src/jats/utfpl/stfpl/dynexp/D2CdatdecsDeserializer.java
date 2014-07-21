package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.JsonUtilities;
import jats.utfpl.utfpl.staexp.Cs2cst;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CdatdecsDeserializer implements JsonDeserializer<D2Cdatdecs> {

    @Override
    public D2Cdatdecs deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je0 = jarr.get(0);
        int knd = je0.getAsInt();
        
        JsonElement je1 = jarr.get(1);
        List<Cs2cst> csts = JsonUtilities.deserializeList(je1, Cs2cst.class, context);
        
        D2Cdatdecs ret = new D2Cdatdecs(knd, csts);
        return ret;
    }

}
