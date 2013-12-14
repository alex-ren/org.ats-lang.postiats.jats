package jats.utfpl.utfpl;

import java.lang.reflect.Type;

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
        JsonElement je1 = jo.get("d2exp_name");
        JsonElement je2 = jo.get("d2exp_arglst");
        
        String name = je1.getAsString();
        if (name.equals("D2Ecst")) {
            return context.deserialize(je2, D2Ecst.class);
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
        }else if (name.equals("D2Eapplst")) {
            return context.deserialize(je2, D2Eapplst.class);
        } else if (name.equals("D2Eifhead")) {
            return context.deserialize(je2, D2Eifopt.class);
        } else if (name.equals("D2Elam_dyn")) {
            return context.deserialize(je2, D2Elam.class);
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
        } else {
            return D2Eignored.cInstance;
        }
        
    }

}
