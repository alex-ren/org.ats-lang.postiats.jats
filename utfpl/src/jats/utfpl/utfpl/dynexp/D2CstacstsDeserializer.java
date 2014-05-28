package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.JsonUtilities;
import jats.utfpl.utfpl.staexp.Cs2cst;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2CstacstsDeserializer implements JsonDeserializer<D2Cstacsts> {

    @Override
    public D2Cstacsts deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
 
        List<Cs2cst> s2csts = JsonUtilities.deserializeList(json, Cs2cst.class, context);
        
        D2Cstacsts ret = new D2Cstacsts(s2csts);
        return ret;
    }

}
