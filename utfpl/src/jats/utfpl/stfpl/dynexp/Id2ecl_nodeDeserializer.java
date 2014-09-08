package jats.utfpl.stfpl.dynexp;

import jats.utfpl.utils.Log;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Id2ecl_nodeDeserializer implements JsonDeserializer<Id2ecl_node> {

    @Override
    public Id2ecl_node deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
    	
        JsonObject jo = json.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> itemSet = jo.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iter = itemSet.iterator();

        Map.Entry<String, JsonElement> item = iter.next();
        if (iter.hasNext()) {
        	throw new Error("type not match");
        }

        String name = item.getKey();
        JsonElement je2 = item.getValue();
        
        if (name.equals("D2Cnone")) {
//            Log.log4j.warn("D2Cnone encountered");
            return D2Cnone.cInstance;
        } else if (name.equals("D2Clist")) {
            return context.deserialize(je2, D2Clist.class);
        } else if (name.equals("D2Cstacsts")) {
            return context.deserialize(je2, D2Cstacsts.class);
        } else if (name.equals("D2Cstacons")) {
            Log.log4j.error("D2Cstacons not supported");
            throw new Error("D2Cstacons not supported");
        } else if (name.equals("D2Cextype")) {
            Log.log4j.error("D2Cextype not supported");
            throw new Error("D2Cextype not supported");
        } else if (name.equals("D2Cextval")) {
            Log.log4j.error("D2Cextval not supported");
            throw new Error("D2Cextval not supported");
        } else if (name.equals("D2Cextcode")) {
            return context.deserialize(je2, D2Cextcode.class);
        } else if (name.equals("D2Cdatdecs")) {
        	return context.deserialize(je2, D2Cdatdecs.class);
        } else if (name.equals("D2Cexndecs")) {
            Log.log4j.error("D2Cexndecs not supported");
            throw new Error("D2Cexndecs not supported");
        } else if (name.equals("D2Cdcstdecs")) {
            return context.deserialize(je2, D2Cdcstdecs.class);
        } else if (name.equals("D2Cimpdec")) {
            return context.deserialize(je2, D2Cimpdec.class);
        } else if (name.equals("D2Cfundecs")) {
            return context.deserialize(je2, D2Cfundecs.class);
        } else if (name.equals("D2Cvaldecs")) {
            return context.deserialize(je2, D2Cvaldecs.class);
        } else if (name.equals("D2Cinclude")) {
            return context.deserialize(je2, D2Cinclude.class);
        } else if (name.equals("D2Clocal")) {
            Log.log4j.error("D2Clocal not supported");
            throw new Error("D2Clocal not supported");
        } else if (name.equals("D2Cignored")) {
            return context.deserialize(je2, D2Cignored.class);
        } else if (name.equals("D2Cstaload")) {
            return context.deserialize(je2, D2Cstaload.class);
        } else {
            Log.log4j.error(name + " unexpected");
            throw new Error(name + " unexpected");
        }
    }

}
