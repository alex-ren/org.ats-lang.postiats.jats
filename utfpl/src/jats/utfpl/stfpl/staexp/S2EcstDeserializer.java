package jats.utfpl.stfpl.staexp;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class S2EcstDeserializer implements JsonDeserializer<S2Ecst> {

    @Override
    public S2Ecst deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

    	JsonArray jarr = json.getAsJsonArray();
    	JsonElement je = jarr.get(0);
    	Cs2cst s2cst = context.deserialize(je, Cs2cst.class);
    	S2Ecst ret = new S2Ecst(s2cst);
    	return ret;
    }


}
