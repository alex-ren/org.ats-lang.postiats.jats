package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.instructions.SId.SIdCategory;
import jats.utfpl.stfpl.stype.ISType;

public class SIdFactory {
    
    private SId m_anony;
    
    private VNameFactory m_name_factory;

    private Map<IVarName, SId> m_map = new HashMap<IVarName, SId>(); 
    
    
    public SId getAnony() {
        return m_anony;
    }
    
    public SIdFactory() {
        m_name_factory = new VNameFactory();
        m_anony = new SId(m_name_factory.getAnonyId(), SIdCategory.eOther);
        m_map = new HashMap<IVarName, SId>();
    }


    public SId mapFromCd3var(Cd3var d3var, SIdCategory cat) {
        VNameVar name = m_name_factory.mapVNameVar(d3var);
        SId id = m_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            m_map.put(name, id);
            return id;
        } else {
            throw new Error("Should not happen.");
        }
    }
    
//    public SId tryGetSIdFromCd3var(Cd3var var) {
//        VNameVar name = m_name_factory.mapVNameVar(var);
//        SId sid = m_map.get(name);
//        return sid;
//        // sid can be null.
//    }
    

    // This function should be called when we are sure SId has already
    // been created.
    public SId getSIdFromCd3var(Cd3var d3var) {
        VNameVar name = m_name_factory.mapVNameVar(d3var);
        SId id = m_map.get(name);
        if (null == id) {
            throw new Error("Should not happen.");
        } else {
            return id;
        }
    }

    public SIdUser createSIdUserByCd3var(Cd3var d3var, boolean from_env) {
        VNameVar name = m_name_factory.mapVNameVar(d3var);
        SId sid = m_map.get(name);
        if (null == sid) {
            throw new Error("should not happen");
        }
        SIdUser su = new SIdUser(sid, from_env);
        return su;
    }

    public SId mapFromCd3cst(Cd3cst d3cst, SIdCategory cat) {
        VNameCst name = m_name_factory.mapCd3cst(d3cst);
        SId id = m_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            m_map.put(name, id);
            return id;
        } else {
            return id;
        }
    }

    // called by D3Esym
    // Identity is not important.
    public SId createFromCd3sym(Cd3sym d3sym, SIdCategory cat) {
        VNameSym name = m_name_factory.createVNameSym(d3sym);
        SId id = new SId(name, cat);
        return id;
    }
    
    // ==============

    // Give RetHolder a name.
    public SId createRetHolder(String name, ISType stype) {
        VNameId id = m_name_factory.createVNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eRetHolder);
        return ret;
    }
    
    // Give name to a local temporary value without a name
    public SId createLocalVar(String name, ISType stype) {
        VNameId id = m_name_factory.createVNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eLocalVar);
        return ret;
    }
    
    // Give lambda expression a name
    public SId createLambdaFunction(String name, ISType stype) {
        VNameId id = m_name_factory.createVNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eUserFun);
        return ret;
    }
    
    public SId createEnvForclosure(String name, ISType stype) {
        VNameId id = m_name_factory.createVNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eLocalVar);
        return ret;
    }
    
    public SId createEnvForPara(String name, ISType stype) {
        VNameId id = m_name_factory.createVNameId(name, stype);
        SId ret = new SId(id, SIdCategory.ePara);
        return ret;
    }
    
    
}



