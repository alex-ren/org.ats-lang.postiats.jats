package jats.utfpl.utfpl;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
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
		} else if (name.equals("D2Eint")) {
			throw new Error("todo");
		} else if (name.equals("D2Eintrep")) {
			throw new Error("todo");
		} else if (name.equals("D2Ec0har")) {
			throw new Error("todo");
		} else if (name.equals("D2Elist")) {
		    // should add support for list containing two elements, one for proof, one for concrete
			throw new Error("todo");
		} else if (name.equals("D2Etup")) {
			throw new Error("todo");
		} else if (name.equals("D2Eseq")) {
			throw new Error("todo");
		} else if (name.equals("D2Eselab")) {
			throw new Error("todo");
		} else if (name.equals("D2Evar")) {
			return context.deserialize(je2, D2Evar.class);
		} else if (name.equals("D2Ei0nt")) {
			return context.deserialize(je2, D2Ei0nt.class);
		} else if (name.equals("D2Ef0loat")) {
			return context.deserialize(je2, D2Ef0loat.class);
		} else if (name.equals("D2Es0tring")) {
			return context.deserialize(je2, D2Es0tring.class);
		} else if (name.equals("D2Esym")) {
			return context.deserialize(je2, D2Esym.class);
		} else if (name.equals("D2Elet")) {
			return context.deserialize(je2, D2Elet.class);
		} else if (name.equals("D2Eapplst")) {
			return context.deserialize(je2, D2Eapplst.class);
		} else if (name.equals("D2Eifhead")) {
			return context.deserialize(je2, D2Eifopt.class);
		} else if (name.equals("D2Elam_dyn")) {
			return context.deserialize(je2, D2Elam.class);
		} else if (name.equals("D2Elam_sta")) {
		    return context.deserialize(je2, D2ElamSta.class);
        } else if (name.equals("D2Elam_met")) {
            return context.deserialize(je2, D2ElamMet.class);		    
        } else if (name.equals("D2Eann_seff")) {
			JsonArray jarr = je2.getAsJsonArray();
			if (jarr.size() < 2) {
				throw new Error("type not match");
			}
			JsonElement jele = jarr.get(0);
			Cd2exp d2e = context.deserialize(jele, Cd2exp.class);

			D2Eexp ret = new D2Eexp(d2e);
			return ret;
		} else if (name.equals("D2Eann_type")) {
			JsonArray jarr = je2.getAsJsonArray();
			if (jarr.size() < 2) {
				throw new Error("type not match");
			}
			JsonElement jele = jarr.get(0);
			Cd2exp d2e = context.deserialize(jele, Cd2exp.class);

			D2Eexp ret = new D2Eexp(d2e);
			return ret;
		} else if (name.equals("D2Eann_funclo")) {
			JsonArray jarr = je2.getAsJsonArray();
			if (jarr.size() < 2) {
				throw new Error("type not match");
			}
			JsonElement jele = jarr.get(0);
			Cd2exp d2e = context.deserialize(jele, Cd2exp.class);

			D2Eexp ret = new D2Eexp(d2e);
			return ret;
		} else if (name.equals("D2Eempty")) {
			return D2Eempty.cInstance;
		} else {
			System.err.println(name + " is translated into D2Eignored in Id2exp_nodeDeserializer.java @99.");
			return D2Eignored.cInstance;
		}

	}

}
