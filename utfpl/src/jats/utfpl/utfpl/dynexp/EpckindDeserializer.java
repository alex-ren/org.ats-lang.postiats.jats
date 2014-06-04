package jats.utfpl.utfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EpckindDeserializer implements JsonDeserializer<Epckind> {

    @Override
    public Epckind deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        String knd = json.getAsString();
        if (knd.equals("PCKcon")) {
            return Epckind.PCKcon;
        } else if (knd.equals("PCKlincon")) {
            return Epckind.PCKlincon;
        } else if (knd.equals("PCKfree")) {
            return Epckind.PCKfree;
        } else if (knd.equals("PCKunfold")) {
            return Epckind.PCKunfold;
        } else {
            throw new Error("pckind " + knd + " is not supported.");
        }
    }

}
