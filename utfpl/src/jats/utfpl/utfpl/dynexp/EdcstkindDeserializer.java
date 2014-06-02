package jats.utfpl.utfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EdcstkindDeserializer implements JsonDeserializer<Edcstkind> {

    @Override
    public Edcstkind deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        String knd = json.getAsString();
        if (knd.equals("DCKfun")) {
            return Edcstkind.DCK_fun;
        } else if (knd.equals("DCKval")) {
            return Edcstkind.DCK_val;
        } else if (knd.equals("DCKprfun")) {
            return Edcstkind.DCK_prfun;            
        } else if (knd.equals("DCKprval")) {
            return Edcstkind.DCK_prval;   
        } else if (knd.equals("DCKpraxi")) {
            return Edcstkind.DCK_praxi;   
        } else if (knd.equals("DCKcastfn")) {
            return Edcstkind.DCK_castfn;   
        } else {
            throw new Error("kind " + knd + " not supported");
        }
    }
}
