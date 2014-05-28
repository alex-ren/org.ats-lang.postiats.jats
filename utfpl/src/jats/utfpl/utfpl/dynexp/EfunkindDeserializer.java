package jats.utfpl.utfpl.dynexp;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EfunkindDeserializer implements JsonDeserializer<Efunkind> {

    @Override
    public Efunkind deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        String knd = json.getAsString();
        if (knd.equals("FK_fn")) {
            return Efunkind.FK_fn;
        } else if (knd.equals("FK_fnx")) {
            return Efunkind.FK_fnx;
        } else if (knd.equals("FK_fun")) {
            return Efunkind.FK_fun;
        } else {
            return Efunkind.Fk_ignored;
        }
    }

}
