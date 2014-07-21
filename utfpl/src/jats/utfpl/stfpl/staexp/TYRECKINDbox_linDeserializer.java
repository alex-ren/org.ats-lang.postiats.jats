package jats.utfpl.stfpl.staexp;



import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TYRECKINDbox_linDeserializer implements JsonDeserializer<TYRECKINDbox_lin> {

    @Override
    public TYRECKINDbox_lin deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return TYRECKINDbox_lin.cInstance;
    }

}
