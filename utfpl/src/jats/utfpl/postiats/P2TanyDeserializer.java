package jats.utfpl.postiats;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class P2TanyDeserializer  implements JsonDeserializer<P2Tany> {

    @Override
    public P2Tany deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        json.getAsJsonArray();
        return P2Tany.cInstance;
    }

}
