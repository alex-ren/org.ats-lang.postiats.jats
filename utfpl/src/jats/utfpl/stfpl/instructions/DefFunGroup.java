package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp.Efunkind;
import jats.utfpl.stfpl.stype.Aux;
import jats.utfpl.stfpl.stype.RecType;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class DefFunGroup implements IFunDef {
    public Efunkind m_knd;
    public List<DefFun> m_funs;
    
    // The type inside m_env_name is based on environment.
    // It's based on function type.
    public SId m_env_name;  // can be null if not closure
    public Set<SIdUser> m_env;  // can be null if not closure
    
    private RecType m_env_type;
    
    public DefFunGroup(Efunkind knd, List<DefFun> funs, SId env_name, Set<SIdUser> env) {
        m_knd = knd;
        m_funs = funs;
        m_env_name = env_name;
        m_env = env;
        
        m_env_type = null;  // has to be set later when all the DefFunGroup's have been created.
    }

    public boolean isClosure() {
    	DefFun fun0 = m_funs.get(0);
    	boolean is_clo0 = Aux.isClosure(fun0.m_name.getType());
    	
    	ListIterator<DefFun> iter = m_funs.listIterator(1);
    	while (iter.hasNext()) {
    		DefFun fun = iter.next();
    		if (is_clo0 != Aux.isClosure(fun.m_name.getType())) {
    			throw new Error("A group of functions should be similar.");
    		}
    	}
    	
    	return is_clo0;
    }
    
    
    public void updateEnvType(RecType env_type) {
    	m_env_type = env_type;
    }
    
    public RecType getEnvType() {
    	return m_env_type;
    }
    
    
}
