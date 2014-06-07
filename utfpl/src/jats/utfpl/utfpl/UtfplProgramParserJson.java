package jats.utfpl.utfpl;

import jats.utfpl.utfpl.dynexp.Cd2con;
import jats.utfpl.utfpl.dynexp.Cd2conDeserializer;
import jats.utfpl.utfpl.dynexp.Cd2cst;
import jats.utfpl.utfpl.dynexp.Cd2cstDeserializer;
import jats.utfpl.utfpl.dynexp.Cd2var;
import jats.utfpl.utfpl.dynexp.Cd2varDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.utfpl.dynexp.D2CdcstdecsDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cextcode;
import jats.utfpl.utfpl.dynexp.D2CextcodeDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cfundecs;
import jats.utfpl.utfpl.dynexp.D2CfundecsDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cignored;
import jats.utfpl.utfpl.dynexp.D2CignoredDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cimpdec;
import jats.utfpl.utfpl.dynexp.D2CimpdecDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cstacsts;
import jats.utfpl.utfpl.dynexp.D2CstacstsDeserializer;
import jats.utfpl.utfpl.dynexp.D2Cvaldecs;
import jats.utfpl.utfpl.dynexp.D2CvaldecsDeserializer;
import jats.utfpl.utfpl.dynexp.D2EXPARGdyn;
import jats.utfpl.utfpl.dynexp.D2EXPARGdynDeserializer;
import jats.utfpl.utfpl.dynexp.D2EXPARGsta;
import jats.utfpl.utfpl.dynexp.D2EXPARGstaDeserializer;
import jats.utfpl.utfpl.dynexp.D2EannFunclo;
import jats.utfpl.utfpl.dynexp.D2EannFuncloDeserializer;
import jats.utfpl.utfpl.dynexp.D2EannSeff;
import jats.utfpl.utfpl.dynexp.D2EannSeffDeserializer;
import jats.utfpl.utfpl.dynexp.D2EannType;
import jats.utfpl.utfpl.dynexp.D2EannTypeDeserializer;
import jats.utfpl.utfpl.dynexp.D2Eapplst;
import jats.utfpl.utfpl.dynexp.D2EapplstDeserializer;
import jats.utfpl.utfpl.dynexp.D2Ecst;
import jats.utfpl.utfpl.dynexp.D2EcstDeserializer;
import jats.utfpl.utfpl.dynexp.D2Ef0loat;
import jats.utfpl.utfpl.dynexp.D2Ef0loatDeserializer;
import jats.utfpl.utfpl.dynexp.D2Ei0nt;
import jats.utfpl.utfpl.dynexp.D2Ei0ntDeserializer;
import jats.utfpl.utfpl.dynexp.D2Eifopt;
import jats.utfpl.utfpl.dynexp.D2EifoptDeserializer;
import jats.utfpl.utfpl.dynexp.D2ElamDyn;
import jats.utfpl.utfpl.dynexp.D2ElamDynDeserializer;
import jats.utfpl.utfpl.dynexp.D2ElamMet;
import jats.utfpl.utfpl.dynexp.D2ElamMetDeserializer;
import jats.utfpl.utfpl.dynexp.D2ElamSta;
import jats.utfpl.utfpl.dynexp.D2ElamStaDeserializer;
import jats.utfpl.utfpl.dynexp.D2Elet;
import jats.utfpl.utfpl.dynexp.D2EletDeserializer;
import jats.utfpl.utfpl.dynexp.D2Es0tring;
import jats.utfpl.utfpl.dynexp.D2Es0tringDeserializer;
import jats.utfpl.utfpl.dynexp.D2Esym;
import jats.utfpl.utfpl.dynexp.D2EsymDeserializer;
import jats.utfpl.utfpl.dynexp.D2Etup;
import jats.utfpl.utfpl.dynexp.D2EtupDeserializer;
import jats.utfpl.utfpl.dynexp.D2Evar;
import jats.utfpl.utfpl.dynexp.D2EvarDeserializer;
import jats.utfpl.utfpl.dynexp.Edcstkind;
import jats.utfpl.utfpl.dynexp.EdcstkindDeserializer;
import jats.utfpl.utfpl.dynexp.Efunkind;
import jats.utfpl.utfpl.dynexp.EfunkindDeserializer;
import jats.utfpl.utfpl.dynexp.Epckind;
import jats.utfpl.utfpl.dynexp.EpckindDeserializer;
import jats.utfpl.utfpl.dynexp.Evalkind;
import jats.utfpl.utfpl.dynexp.EvalkindDeserializer;
import jats.utfpl.utfpl.dynexp.Id2ecl_node;
import jats.utfpl.utfpl.dynexp.Id2ecl_nodeDeserializer;
import jats.utfpl.utfpl.dynexp.Id2exp_node;
import jats.utfpl.utfpl.dynexp.Id2exp_nodeDeserializer;
import jats.utfpl.utfpl.dynexp.Id2exparg;
import jats.utfpl.utfpl.dynexp.Id2expargDeserializer;
import jats.utfpl.utfpl.dynexp.Ilabel;
import jats.utfpl.utfpl.dynexp.IlabelDeserializer;
import jats.utfpl.utfpl.dynexp.Ilabp2at;
import jats.utfpl.utfpl.dynexp.Ilabp2atDeserializer;
import jats.utfpl.utfpl.dynexp.Ip2at_node;
import jats.utfpl.utfpl.dynexp.Ip2at_nodeDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tann;
import jats.utfpl.utfpl.dynexp.P2TannDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tany;
import jats.utfpl.utfpl.dynexp.P2TanyDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tcon;
import jats.utfpl.utfpl.dynexp.P2TconDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tempty;
import jats.utfpl.utfpl.dynexp.P2TemptyDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tignored;
import jats.utfpl.utfpl.dynexp.P2TignoredDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tpat;
import jats.utfpl.utfpl.dynexp.P2TpatDeserializer;
import jats.utfpl.utfpl.dynexp.P2Trec;
import jats.utfpl.utfpl.dynexp.P2TrecDeserializer;
import jats.utfpl.utfpl.dynexp.P2Tvar;
import jats.utfpl.utfpl.dynexp.P2TvarDeserializer;
import jats.utfpl.utfpl.dynexp.ProgramUtfpl;
import jats.utfpl.utfpl.dynexp.ProgramUtfplDeserializer;
import jats.utfpl.utfpl.staexp.Cs2cst;
import jats.utfpl.utfpl.staexp.Cs2cstDeserializer;
import jats.utfpl.utfpl.staexp.Cs2var;
import jats.utfpl.utfpl.staexp.Cs2varDeserializer;
import jats.utfpl.utfpl.staexp.Ifunclo;
import jats.utfpl.utfpl.staexp.IfuncloDeserializer;
import jats.utfpl.utfpl.staexp.Is2exp_node;
import jats.utfpl.utfpl.staexp.Is2exp_nodeDeserializer;
import jats.utfpl.utfpl.staexp.Is2rt;
import jats.utfpl.utfpl.staexp.Is2rtDeserializer;
import jats.utfpl.utfpl.staexp.S2Eapp;
import jats.utfpl.utfpl.staexp.S2EappDeserializer;
import jats.utfpl.utfpl.staexp.S2Ecst;
import jats.utfpl.utfpl.staexp.S2EcstDeserializer;
import jats.utfpl.utfpl.staexp.S2Eeqeq;
import jats.utfpl.utfpl.staexp.S2EeqeqDeserializer;
import jats.utfpl.utfpl.staexp.S2Eerr;
import jats.utfpl.utfpl.staexp.S2EerrDeserializer;
import jats.utfpl.utfpl.staexp.S2Eexi;
import jats.utfpl.utfpl.staexp.S2EexiDeserializer;
import jats.utfpl.utfpl.staexp.S2Eextkind;
import jats.utfpl.utfpl.staexp.S2EextkindDeserializer;
import jats.utfpl.utfpl.staexp.S2Efun;
import jats.utfpl.utfpl.staexp.S2EfunDeserializer;
import jats.utfpl.utfpl.staexp.S2Eignored;
import jats.utfpl.utfpl.staexp.S2EignoredDeserializer;
import jats.utfpl.utfpl.staexp.S2Eint;
import jats.utfpl.utfpl.staexp.S2EintDeserializer;
import jats.utfpl.utfpl.staexp.S2Eintinf;
import jats.utfpl.utfpl.staexp.S2EintinfDeserializer;
import jats.utfpl.utfpl.staexp.S2Esizeof;
import jats.utfpl.utfpl.staexp.S2EsizeofDeserializer;
import jats.utfpl.utfpl.staexp.S2Euni;
import jats.utfpl.utfpl.staexp.S2EuniDeserializer;
import jats.utfpl.utfpl.staexp.S2Evar;
import jats.utfpl.utfpl.staexp.S2EvarDeserializer;

import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class UtfplProgramParserJson {
    private Gson m_gson;
    
    public UtfplProgramParserJson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        
        gsonBuilder.registerTypeAdapter(Cloc_t.class, new Cloc_tDeserializer());
        gsonBuilder.registerTypeAdapter(Cstamp.class, new CstampDeserializer());
        gsonBuilder.registerTypeAdapter(Csymbol.class, new CsymbolDeserializer());
        
        gsonBuilder.registerTypeAdapter(Cd2cst.class, new Cd2cstDeserializer());
        gsonBuilder.registerTypeAdapter(Cd2var.class, new Cd2varDeserializer());
        gsonBuilder.registerTypeAdapter(Cd2con.class, new Cd2conDeserializer());
        
        gsonBuilder.registerTypeAdapter(Cs2cst.class, new Cs2cstDeserializer());
        gsonBuilder.registerTypeAdapter(Cs2var.class, new Cs2varDeserializer());
        
        gsonBuilder.registerTypeAdapter(D2Cfundecs.class, new D2CfundecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cignored.class, new D2CignoredDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cimpdec.class, new D2CimpdecDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cextcode.class, new D2CextcodeDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cvaldecs.class, new D2CvaldecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cdcstdecs.class, new D2CdcstdecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cstacsts.class, new D2CstacstsDeserializer());
        
        gsonBuilder.registerTypeAdapter(D2Eapplst.class, new D2EapplstDeserializer());
        gsonBuilder.registerTypeAdapter(D2Ecst.class, new D2EcstDeserializer());
        gsonBuilder.registerTypeAdapter(D2Ef0loat.class, new D2Ef0loatDeserializer());
        gsonBuilder.registerTypeAdapter(D2Ei0nt.class, new D2Ei0ntDeserializer());
        gsonBuilder.registerTypeAdapter(D2Eifopt.class, new D2EifoptDeserializer());
        gsonBuilder.registerTypeAdapter(D2ElamDyn.class, new D2ElamDynDeserializer());
        gsonBuilder.registerTypeAdapter(D2ElamSta.class, new D2ElamStaDeserializer());
        gsonBuilder.registerTypeAdapter(D2ElamMet.class, new D2ElamMetDeserializer());
        gsonBuilder.registerTypeAdapter(D2Elet.class, new D2EletDeserializer());
        gsonBuilder.registerTypeAdapter(D2Es0tring.class, new D2Es0tringDeserializer());
        gsonBuilder.registerTypeAdapter(D2Esym.class, new D2EsymDeserializer());
        gsonBuilder.registerTypeAdapter(D2Evar.class, new D2EvarDeserializer());
        gsonBuilder.registerTypeAdapter(D2EXPARGdyn.class, new D2EXPARGdynDeserializer());
        gsonBuilder.registerTypeAdapter(D2EXPARGsta.class, new D2EXPARGstaDeserializer());
        gsonBuilder.registerTypeAdapter(D2EannType.class, new D2EannTypeDeserializer());
        gsonBuilder.registerTypeAdapter(D2EannSeff.class, new D2EannSeffDeserializer());
        gsonBuilder.registerTypeAdapter(D2EannFunclo.class, new D2EannFuncloDeserializer());
        gsonBuilder.registerTypeAdapter(D2Etup.class, new D2EtupDeserializer());
        
        
        
        gsonBuilder.registerTypeAdapter(Efunkind.class, new EfunkindDeserializer());
        gsonBuilder.registerTypeAdapter(Evalkind.class, new EvalkindDeserializer());
        gsonBuilder.registerTypeAdapter(Edcstkind.class, new EdcstkindDeserializer());
        gsonBuilder.registerTypeAdapter(Epckind.class, new EpckindDeserializer());
        
        
        
        gsonBuilder.registerTypeAdapter(Id2ecl_node.class, new Id2ecl_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Id2exp_node.class, new Id2exp_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Id2exparg.class, new Id2expargDeserializer());
        gsonBuilder.registerTypeAdapter(Ip2at_node.class, new Ip2at_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Ilabp2at.class, new Ilabp2atDeserializer());
        gsonBuilder.registerTypeAdapter(Ilabel.class, new IlabelDeserializer());
        gsonBuilder.registerTypeAdapter(Is2exp_node.class, new Is2exp_nodeDeserializer());
        gsonBuilder.registerTypeAdapter(Ifunclo.class, new IfuncloDeserializer());
        gsonBuilder.registerTypeAdapter(Is2rt.class, new Is2rtDeserializer());
        
        
        
        gsonBuilder.registerTypeAdapter(S2Eapp.class, new S2EappDeserializer());
        gsonBuilder.registerTypeAdapter(S2Ecst.class, new S2EcstDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eeqeq.class, new S2EeqeqDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eerr.class, new S2EerrDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eextkind.class, new S2EextkindDeserializer());
        gsonBuilder.registerTypeAdapter(S2Efun.class, new S2EfunDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eignored.class, new S2EignoredDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eint.class, new S2EintDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eintinf.class, new S2EintinfDeserializer());
        gsonBuilder.registerTypeAdapter(S2Esizeof.class, new S2EsizeofDeserializer());
        gsonBuilder.registerTypeAdapter(S2Eexi.class, new S2EexiDeserializer());
        gsonBuilder.registerTypeAdapter(S2Euni.class, new S2EuniDeserializer());
        gsonBuilder.registerTypeAdapter(S2Evar.class, new S2EvarDeserializer());
        
        
        
        
        
        gsonBuilder.registerTypeAdapter(P2Tany.class, new P2TanyDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tempty.class, new P2TemptyDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tignored.class, new P2TignoredDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tpat.class, new P2TpatDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tvar.class, new P2TvarDeserializer());
        gsonBuilder.registerTypeAdapter(P2Trec.class, new P2TrecDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tann.class, new P2TannDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tcon.class, new P2TconDeserializer());
        

        
        
        
        gsonBuilder.registerTypeAdapter(ProgramUtfpl.class, new ProgramUtfplDeserializer());
        
        m_gson = gsonBuilder.create();
    }
    
    public ProgramUtfpl trans(Reader iReader) {
        JsonReader reader = new JsonReader(iReader);
        ProgramUtfpl prog = m_gson.fromJson(reader, ProgramUtfpl.class);
        return prog;
    }
}
