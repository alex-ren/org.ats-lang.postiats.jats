package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TrecDeserializer implements JsonDeserializer<P2Trec> {

    @Override
    public P2Trec deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        
        
        JsonArray jarr = json.getAsJsonArray();
        
        int knd = jarr.get(0).getAsInt();
        int npf = jarr.get(1).getAsInt();
        
        JsonElement je = jarr.get(2);
        List<Ilabp2at> labpats = JsonUtilities.deserializeList(je, Ilabp2at.class, context);
        
        return new P2Trec(labpats, npf, knd);
    }

}
