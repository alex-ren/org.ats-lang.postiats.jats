package jats.utfpl.utfpl;

import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class UtfplProgramParserJson {
    private Gson m_gson;
    
    public UtfplProgramParserJson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        
        gsonBuilder.registerTypeAdapter(Cd2cst.class, new Cd2cstDeserializer());
        gsonBuilder.registerTypeAdapter(Cd2var.class, new Cd2varDeserializer());
        gsonBuilder.registerTypeAdapter(Cloc_t.class, new Cloc_tDeserializer());
        gsonBuilder.registerTypeAdapter(Cstamp.class, new CstampDeserializer());
        gsonBuilder.registerTypeAdapter(Csymbol.class, new CsymbolDeserializer());
        
        gsonBuilder.registerTypeAdapter(D2Cfundecs.class, new D2CfundecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cignored.class, new D2CignoredDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cimpdec.class, new D2CimpdecDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cextcode.class, new D2CextcodeDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cvaldecs.class, new D2CvaldecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cdcstdecs.class, new D2CdcstdecsDeserializer());
        
        gsonBuilder.registerTypeAdapter(D2Eapplst.class, new D2EapplstDeserializer());
        gsonBuilder.registerTypeAdapter(D2Ecst.class, new D2EcstDeserializer());
        gsonBuilder.registerTypeAdapter(D2Ef0loat.class, new D2Ef0loatDeserializer());
        gsonBuilder.registerTypeAdapter(D2Ei0nt.class, new D2Ei0ntDeserializer());
        gsonBuilder.registerTypeAdapter(D2Eifopt.class, new D2EifoptDeserializer());
        gsonBuilder.registerTypeAdapter(D2Elam.class, new D2ElamDeserializer());
        gsonBuilder.registerTypeAdapter(D2ElamSta.class, new D2ElamStaDeserializer());
        gsonBuilder.registerTypeAdapter(D2ElamMet.class, new D2ElamMetDeserializer());
        gsonBuilder.registerTypeAdapter(D2Elet.class, new D2EletDeserializer());
        gsonBuilder.registerTypeAdapter(D2Es0tring.class, new D2Es0tringDeserializer());
        gsonBuilder.registerTypeAdapter(D2Esym.class, new D2EsymDeserializer());
        gsonBuilder.registerTypeAdapter(D2Evar.class, new D2EvarDeserializer());
        gsonBuilder.registerTypeAdapter(D2EXPARGdyn.class, new D2EXPARGdynDeserializer());
        gsonBuilder.registerTypeAdapter(D2EXPARGsta.class, new D2EXPARGstaDeserializer());
        
        gsonBuilder.registerTypeAdapter(Efunkind.class, new EfunkindDeserializer());
        gsonBuilder.registerTypeAdapter(Evalkind.class, new EvalkindDeserializer());
        gsonBuilder.registerTypeAdapter(Edcstkind.class, new EdcstkindDeserializer());
        
        
        
        gsonBuilder.registerTypeAdapter(Id2ecl_node.class, new Id2ecl_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Id2exp_node.class, new Id2exp_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Id2exparg.class, new Id2expargDeserializer());
        gsonBuilder.registerTypeAdapter(Ip2at_node.class, new Ip2at_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Ilabp2at.class, new Ilabp2atDeserializer());
        gsonBuilder.registerTypeAdapter(Ilabel.class, new IlabelDeserializer());
        
        gsonBuilder.registerTypeAdapter(P2Tany.class, new P2TanyDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tempty.class, new P2TemptyDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tignored.class, new P2TignoredDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tpat.class, new P2TpatDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tvar.class, new P2TvarDeserializer());
        gsonBuilder.registerTypeAdapter(P2Trec.class, new P2TrecDeserializer());
        

        
        
        
        gsonBuilder.registerTypeAdapter(ProgramUtfpl.class, new ProgramUtfplDeserializer());
        
        m_gson = gsonBuilder.create();
    }
    
    public ProgramUtfpl trans(Reader iReader) {
        JsonReader reader = new JsonReader(iReader);
        ProgramUtfpl prog = m_gson.fromJson(reader, ProgramUtfpl.class);
        return prog;
    }
}
