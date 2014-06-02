package jats.utfpl.utfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EvalkindDeserializer implements JsonDeserializer<Evalkind> {

    @Override
    public Evalkind deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        String knd = json.getAsString();
        if (knd.equals("VK_val")) {
            return Evalkind.VK_val;
        } else if (knd.equals("VK_val_pos")) {
            return Evalkind.VK_val_pos;
        } else if (knd.equals("VK_val_neg")) {
            return Evalkind.VK_val_neg;
        } else if (knd.equals("VK_prval")) {
            return Evalkind.VK_prval;
        } else {
            throw new Error("kind " + knd + " is not supported.");
        }
    }

}
