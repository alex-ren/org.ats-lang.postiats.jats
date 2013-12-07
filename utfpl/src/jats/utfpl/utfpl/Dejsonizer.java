package jats.utfpl.utfpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Dejsonizer {
    
    interface Converter<T> {
        T dejsonize(JsonElement je) throws IOException;
    }
    
    static class CVTstamp implements Converter<Cstamp> {
        static final CVTstamp sWorker = new CVTstamp();
        
        private CVTstamp() {
        }

        @Override
        public Cstamp dejsonize(JsonElement je) {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    static class CVTd2sym implements Converter<Cd2sym> {
        static final CVTd2sym sWorker = new CVTd2sym();
        
        private CVTd2sym() {
        }

        @Override
        public Cd2sym dejsonize(JsonElement je) {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
//    static class CVTd2cst implements Converter<Cd2cst> {
//        static final CVTd2cst sWorker = new CVTd2cst();
//        
//        private Map<Cstamp, Cd2cst> m_map;
//        
//        public CVTd2cst() {
//            m_map = new HashMap<Cstamp, Cd2cst>();
//        }
//        
//        @Override
//        public Cd2cst dejsonize(JsonElement je) throws IOException {
//            JsonObject jo = je.getAsJsonObject();
//
//            JsonElement jeStamp = jo.get("d2cst_stamp");
//            Cstamp stamp = CVTstamp.sWorker.dejsonize(jeStamp);
//            Cd2cst d2cst = m_map.get(stamp);
//            if (null != d2cst) {
//                return d2cst;
//            } else {
//                JsonElement jeSym = jo.get("d2cst_name");
//                Cd2sym sym = CVTd2sym.sWorker.dejsonize(jeSym);
//                d2cst = new Cd2cst(stamp, sym);
//                m_map.put(stamp, d2cst);
//                return d2cst ;
//            }
//        }
//    }
    
//    implement
//    parse_d2ecl
//      (jsv0) = let
//    //
//    val-~Some_vt (jsv) =
//      jsonval_get_field (jsv0, "d2ecl_loc") 
//    val loc = parse_location (jsv)
//    val-~Some_vt (jsv) =
//      jsonval_get_field (jsv0, "d2ecl_node") 
//    val node = parse_d2ecl_node (jsv)
//    //
//    in
//      d2ecl_make_node (loc, node)
//    end // end of [parse_d2ecl]

    
//    static class CVTd2ecl implements Converter<Cd2ecl> {
//        static final CVTd2ecl sWorker = new CVTd2ecl();
//        
//        public CVTd2ecl() {
//        }
//
//        @Override
//        public Cd2ecl dejsonize(JsonElement je) throws IOException {
//            JsonObject jo = je.getAsJsonObject();
//
//            JsonElement jeLoc = jo.get("d2ecl_loc");
//            Cloc_t loc = CVTloc_t.sWorker.dejsonize(jeLoc);
//            
//            JsonElement jeNode = jo.get("d2ecl_node");
//            Id2ecl_node node = CVTd2ecl_node.sWorker.dejsonize(jeNode);
//            
//            return new Cd2ecl(loc, node);
//        }
//    }
    
//    static class CVTd2ecl_node implements Converter<Id2ecl_node> {
//        static final CVTd2ecl_node sWorker = new CVTd2ecl_node();
//        
//        public CVTd2ecl_node() {
//        }
//
//        @Override
//        public Id2ecl_node dejsonize(JsonElement je) throws IOException {
//            reader.beginObject();
//        }
//    }

    static class CVTloc_t implements Converter<Cloc_t> {
        static final CVTloc_t sWorker = new CVTloc_t();
        
        public CVTloc_t() {
        }

        @Override
        public Cloc_t dejsonize(JsonElement je) throws IOException {
            // TODO Auto-generated method stub
            return null;
        }
    }
}



































































