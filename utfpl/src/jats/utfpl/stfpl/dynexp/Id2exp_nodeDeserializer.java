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

public class Id2exp_nodeDeserializer implements JsonDeserializer<Id2exp_node> {

	@Override
	public Id2exp_node deserialize(JsonElement json, Type typeOfT,
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
        
        if (name.equals("D2Ecst")) {
            return context.deserialize(je2, D2Ecst.class);
        } else if (name.equals("D2Evar")) {
            return context.deserialize(je2, D2Evar.class);
        } else if (name.equals("D2Eint")) {
            Log.log4j.error("D2Eint not supported");
            throw new Error("D2Eint not supported");
        } else if (name.equals("D2Eintrep")) {
        	// treat D2Eintrep as D2Ei0nt.
        	return context.deserialize(je2, D2Ei0nt.class);
        } else if (name.equals("D2Ei0nt")) {
            return context.deserialize(je2, D2Ei0nt.class);
        } else if (name.equals("D2Ec0har")) {
            Log.log4j.error("D2Ec0har not supported");
            throw new Error("D2Ec0har not supported");
        } else if (name.equals("D2Ef0loat")) {
            return context.deserialize(je2, D2Ef0loat.class);
        } else if (name.equals("D2Es0tring")) {
            return context.deserialize(je2, D2Es0tring.class);
        } else if (name.equals("D2Esym")) {
            return context.deserialize(je2, D2Esym.class);
        } else if (name.equals("D2Eempty")) {
            return D2Eempty.cInstance;
        } else if (name.equals("D2Elet")) {
            return context.deserialize(je2, D2Elet.class);
        } else if (name.equals("D2Ewhere")) {
            Log.log4j.error("D2Ewhere not supported");
            throw new Error("D2Ewhere not supported");
        } else if (name.equals("D2Eapplst")) {
            return context.deserialize(je2, D2Eapplst.class);
        } else if (name.equals("D2Eifhead")) {
            return context.deserialize(je2, D2Eifopt.class);
        } else if (name.equals("D2Elist")) {
            return context.deserialize(je2, D2Elist.class);
        } else if (name.equals("D2Etup")) {
            return context.deserialize(je2, D2Etup.class);
        } else if (name.equals("D2Erec")) {
            Log.log4j.error("D2Erec not supported");
            throw new Error("D2Erec not supported");
        } else if (name.equals("D2Eseq")) {
            Log.log4j.error("D2Eseq not supported");
            throw new Error("D2Eseq not supported");
        } else if (name.equals("D2Eselab")) {
            Log.log4j.error("D2Eselab not supported");
            throw new Error("D2Eselab not supported");
		} else if (name.equals("D2Elam_dyn")) {
			return context.deserialize(je2, D2ElamDyn.class);
        } else if (name.equals("D2Elam_met")) {
            return context.deserialize(je2, D2ElamMet.class);   
		} else if (name.equals("D2Elam_sta")) {
		    return context.deserialize(je2, D2ElamSta.class);
        } else if (name.equals("D2Eann_type")) {
            return context.deserialize(je2, D2EannType.class);
        } else if (name.equals("D2Eann_seff")) {
			return context.deserialize(je2, D2EannSeff.class);
		} else if (name.equals("D2Eann_funclo")) {
		    return context.deserialize(je2, D2EannFunclo.class);

		} else if (name.equals("D2Eerrexp")) {
            Log.log4j.error("D2Eerrexp not supported");
            throw new Error("D2Eerrexp not supported");
		} else if (name.equals("D2ignored")) {
		    return context.deserialize(je2, D2Eignored.class);
		} else {
            Log.log4j.error(name + " unexpected");
            throw new Error(name + " unexpected");
		}

	}

}
