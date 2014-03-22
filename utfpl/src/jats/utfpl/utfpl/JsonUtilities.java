package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;

public class JsonUtilities {
    
    static public <T> List<T> deserializeList(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) {
        List<T> list = new ArrayList<T>();
        
        JsonArray jarr = json.getAsJsonArray();
        for (JsonElement je: jarr) {
            T ele = context.deserialize(je, typeOfT);
            list.add(ele);
        }
        
        return list;
    }
    
//    static public <T> List<T> deserializeList(JsonElement json, Type typeOfT,
//            JsonDeserializationContext context, int start) {
//        List<T> list = new ArrayList<T>();
//
//        JsonArray jarr = json.getAsJsonArray();
//        if (start > jarr.size()) {
//            throw new Error("starting exceeds the size");
//        }
//        
//        Iterator<JsonElement> iter = jarr.iterator();
//        for (int i = 0; i < start; ++i) {
//            iter.next();
//        }
//        
//        while (iter.hasNext()) {
//            T ele = context.deserialize(iter.next(), typeOfT);
//            list.add(ele);
//        }
//        
//        return list;
//    }
    
    static public <T> T deserializeOption(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) {
        
        JsonArray jarr = json.getAsJsonArray();
        if (jarr.size() == 0) {
            return null;
        } else {
            JsonElement je = jarr.get(0);
            return context.deserialize(je, typeOfT);
        }
    }

}
