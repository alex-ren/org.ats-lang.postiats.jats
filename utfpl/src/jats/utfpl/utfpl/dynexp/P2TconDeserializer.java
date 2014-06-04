package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TconDeserializer implements JsonDeserializer<P2Tcon> {

    @Override
    public P2Tcon deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        
        JsonArray jarr = json.getAsJsonArray();
        
        JsonElement je0 = jarr.get(0);
        Epckind pcknd = context.deserialize(je0, Epckind.class);
        
        JsonElement je1 = jarr.get(1);
        Cd2con d2c = context.deserialize(je1, Cd2con.class);
        
        JsonElement je4 = jarr.get(4);
        int npf = je4.getAsInt();
        
        JsonElement je5 = jarr.get(5);
        List<Cp2at> p2ts = JsonUtilities.deserializeList(je5, Cp2at.class, context);
        
        P2Tcon ret = new P2Tcon(pcknd, d2c, npf, p2ts);
        
        return ret;
    }

}
