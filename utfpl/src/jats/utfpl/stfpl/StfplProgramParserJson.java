package jats.utfpl.stfpl;

import jats.utfpl.stfpl.ccomp.DefaultLibraryTypes;
import jats.utfpl.stfpl.dynexp.*;
import jats.utfpl.stfpl.staexp.Clabs2exp;
import jats.utfpl.stfpl.staexp.Clabs2expDeserializer;
import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.staexp.Cs2cstDeserializer;
import jats.utfpl.stfpl.staexp.Cs2var;
import jats.utfpl.stfpl.staexp.Cs2varDeserializer;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.staexp.IfuncloDeserializer;
import jats.utfpl.stfpl.staexp.Is2exp_node;
import jats.utfpl.stfpl.staexp.Is2exp_nodeDeserializer;
import jats.utfpl.stfpl.staexp.Is2rt;
import jats.utfpl.stfpl.staexp.Is2rtDeserializer;
import jats.utfpl.stfpl.staexp.Ityreckind;
import jats.utfpl.stfpl.staexp.ItyreckindDeserializer;
import jats.utfpl.stfpl.staexp.S2Eapp;
import jats.utfpl.stfpl.staexp.S2EappDeserializer;
import jats.utfpl.stfpl.staexp.S2Ecst;
import jats.utfpl.stfpl.staexp.S2EcstDeserializer;
import jats.utfpl.stfpl.staexp.S2Eeqeq;
import jats.utfpl.stfpl.staexp.S2EeqeqDeserializer;
import jats.utfpl.stfpl.staexp.S2Eerr;
import jats.utfpl.stfpl.staexp.S2EerrDeserializer;
import jats.utfpl.stfpl.staexp.S2Eexi;
import jats.utfpl.stfpl.staexp.S2EexiDeserializer;
import jats.utfpl.stfpl.staexp.S2Eextkind;
import jats.utfpl.stfpl.staexp.S2EextkindDeserializer;
import jats.utfpl.stfpl.staexp.S2Efun;
import jats.utfpl.stfpl.staexp.S2EfunDeserializer;
import jats.utfpl.stfpl.staexp.S2Eignored;
import jats.utfpl.stfpl.staexp.S2EignoredDeserializer;
import jats.utfpl.stfpl.staexp.S2Eint;
import jats.utfpl.stfpl.staexp.S2EintDeserializer;
import jats.utfpl.stfpl.staexp.S2Eintinf;
import jats.utfpl.stfpl.staexp.S2EintinfDeserializer;
import jats.utfpl.stfpl.staexp.S2Einvar;
import jats.utfpl.stfpl.staexp.S2EinvarDeserializer;
import jats.utfpl.stfpl.staexp.S2Esizeof;
import jats.utfpl.stfpl.staexp.S2EsizeofDeserializer;
import jats.utfpl.stfpl.staexp.S2Etyrec;
import jats.utfpl.stfpl.staexp.S2EtyrecDeserializer;
import jats.utfpl.stfpl.staexp.S2Euni;
import jats.utfpl.stfpl.staexp.S2EuniDeserializer;
import jats.utfpl.stfpl.staexp.S2Evar;
import jats.utfpl.stfpl.staexp.S2EvarDeserializer;
import jats.utfpl.stfpl.staexp.S2RTbas;
import jats.utfpl.stfpl.staexp.S2RTbasDeserializer;
import jats.utfpl.stfpl.staexp.S2RTfun;
import jats.utfpl.stfpl.staexp.S2RTfunDeserializer;
import jats.utfpl.stfpl.staexp.S2RTtup;
import jats.utfpl.stfpl.staexp.S2RTtupDeserializer;
import jats.utfpl.stfpl.staexp.TYRECKINDbox;
import jats.utfpl.stfpl.staexp.TYRECKINDboxDeserializer;
import jats.utfpl.stfpl.staexp.TYRECKINDbox_lin;
import jats.utfpl.stfpl.staexp.TYRECKINDbox_linDeserializer;
import jats.utfpl.stfpl.staexp.TYRECKINDflt0;
import jats.utfpl.stfpl.staexp.TYRECKINDflt0Deserializer;
import jats.utfpl.stfpl.staexp.TYRECKINDflt1;
import jats.utfpl.stfpl.staexp.TYRECKINDflt1Deserializer;
import jats.utfpl.stfpl.staexp.TYRECKINDflt_ext;
import jats.utfpl.stfpl.staexp.TYRECKINDflt_extDeserializer;

import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class StfplProgramParserJson {
    private Gson m_gson;
    
    public StfplProgramParserJson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        DefaultLibraryTypes type_fac = new DefaultLibraryTypes();
        
        /* *********** common *********** */
        
        gsonBuilder.registerTypeAdapter(Cloc_t.class, new Cloc_tDeserializer());
        gsonBuilder.registerTypeAdapter(Cstamp.class, new CstampDeserializer());
        gsonBuilder.registerTypeAdapter(Csymbol.class, new CsymbolDeserializer());
        
        gsonBuilder.registerTypeAdapter(Ilabel.class, new IlabelDeserializer());

        /* *********** dynamics *********** */
        
        gsonBuilder.registerTypeAdapter(Cd2cst.class, new Cd2cstDeserializer(type_fac));
        gsonBuilder.registerTypeAdapter(Cd2var.class, new Cd2varDeserializer());
        gsonBuilder.registerTypeAdapter(Cd2con.class, new Cd2conDeserializer());
        gsonBuilder.registerTypeAdapter(Cd2sym.class, new Cd2symDeserializer(type_fac));

        
        gsonBuilder.registerTypeAdapter(Id2ecl_node.class, new Id2ecl_nodeDeserializer());
        
        gsonBuilder.registerTypeAdapter(D2Cfundecs.class, new D2CfundecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cignored.class, new D2CignoredDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cimpdec.class, new D2CimpdecDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cextcode.class, new D2CextcodeDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cvaldecs.class, new D2CvaldecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cdcstdecs.class, new D2CdcstdecsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cstacsts.class, new D2CstacstsDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cdatdecs.class, new D2CdatdecsDeserializer());  
        gsonBuilder.registerTypeAdapter(D2Cinclude.class, new D2CincludeDeserializer());
        gsonBuilder.registerTypeAdapter(D2Clist.class, new D2ClistDeserializer());
        gsonBuilder.registerTypeAdapter(D2Clocal.class, new D2ClocalDeserializer());
        gsonBuilder.registerTypeAdapter(D2Cstaload.class, new D2CstaloadDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Id2exp_node.class, new Id2exp_nodeDeserializer());
        
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
        gsonBuilder.registerTypeAdapter(D2EannType.class, new D2EannTypeDeserializer());
        gsonBuilder.registerTypeAdapter(D2EannSeff.class, new D2EannSeffDeserializer());
        gsonBuilder.registerTypeAdapter(D2EannFunclo.class, new D2EannFuncloDeserializer());
        gsonBuilder.registerTypeAdapter(D2Etup.class, new D2EtupDeserializer());  
        gsonBuilder.registerTypeAdapter(D2Elist.class, new D2ElistDeserializer());
        gsonBuilder.registerTypeAdapter(D2Eignored.class, new D2EignoredDeserializer());
        
        gsonBuilder.registerTypeAdapter(Id2exparg.class, new Id2expargDeserializer());
        
        gsonBuilder.registerTypeAdapter(D2EXPARGdyn.class, new D2EXPARGdynDeserializer());
        gsonBuilder.registerTypeAdapter(D2EXPARGsta.class, new D2EXPARGstaDeserializer());


        gsonBuilder.registerTypeAdapter(Ip2at_node.class, new Ip2at_nodeDeserializer());
        
        gsonBuilder.registerTypeAdapter(P2Tany.class, new P2TanyDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tempty.class, new P2TemptyDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tignored.class, new P2TignoredDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tpat.class, new P2TpatDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tvar.class, new P2TvarDeserializer());
        gsonBuilder.registerTypeAdapter(P2Trec.class, new P2TrecDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tann.class, new P2TannDeserializer());
        gsonBuilder.registerTypeAdapter(P2Tcon.class, new P2TconDeserializer());
        
 
        gsonBuilder.registerTypeAdapter(Efunkind.class, new EfunkindDeserializer());
        gsonBuilder.registerTypeAdapter(Evalkind.class, new EvalkindDeserializer());
        gsonBuilder.registerTypeAdapter(Edcstkind.class, new EdcstkindDeserializer());
        gsonBuilder.registerTypeAdapter(Epckind.class, new EpckindDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Ilabp2at.class, new Ilabp2atDeserializer());

        /* *********** statics *********** */
        
        gsonBuilder.registerTypeAdapter(Cs2cst.class, new Cs2cstDeserializer());
        gsonBuilder.registerTypeAdapter(Cs2var.class, new Cs2varDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Ifunclo.class, new IfuncloDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Is2rt.class, new Is2rtDeserializer());
        gsonBuilder.registerTypeAdapter(S2RTbas.class, new S2RTbasDeserializer());
        gsonBuilder.registerTypeAdapter(S2RTfun.class, new S2RTfunDeserializer());
        gsonBuilder.registerTypeAdapter(S2RTtup.class, new S2RTtupDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Ityreckind.class, new ItyreckindDeserializer());
        
        gsonBuilder.registerTypeAdapter(TYRECKINDbox.class, new TYRECKINDboxDeserializer());
        gsonBuilder.registerTypeAdapter(TYRECKINDbox_lin.class, new TYRECKINDbox_linDeserializer());
        gsonBuilder.registerTypeAdapter(TYRECKINDflt0.class, new TYRECKINDflt0Deserializer());
        gsonBuilder.registerTypeAdapter(TYRECKINDflt1.class, new TYRECKINDflt1Deserializer());
        gsonBuilder.registerTypeAdapter(TYRECKINDflt_ext.class, new TYRECKINDflt_extDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Clabs2exp.class, new Clabs2expDeserializer());
        
        
        gsonBuilder.registerTypeAdapter(Is2exp_node.class, new Is2exp_nodeDeserializer());
        
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
        gsonBuilder.registerTypeAdapter(S2Einvar.class, new S2EinvarDeserializer());
        gsonBuilder.registerTypeAdapter(S2Etyrec.class, new S2EtyrecDeserializer());
        

        /* *********** all *********** */
        
        gsonBuilder.registerTypeAdapter(ProgramStfpl2.class, new ProgramStfpl2Deserializer());
        
        m_gson = gsonBuilder.create();
    }
    
    public ProgramStfpl2 trans(Reader iReader) {
        JsonReader reader = new JsonReader(iReader);
        ProgramStfpl2 prog = m_gson.fromJson(reader, ProgramStfpl2.class);
        return prog;
    }
}
