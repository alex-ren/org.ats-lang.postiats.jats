package jats.utfpl.stfpl.dynexp;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class D2Es0tringDeserializer implements JsonDeserializer<D2Es0tring> {

    @Override
    public D2Es0tring deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonArray jarr = json.getAsJsonArray();
        JsonElement je = jarr.get(0);
        String s0tring = je.getAsString();
        String content = StringEscapeUtils.escapeJava(s0tring);
        content = "\"" + content + "\"";
        
        D2Es0tring ret = new D2Es0tring(content);

        return ret;
        
    }

}
