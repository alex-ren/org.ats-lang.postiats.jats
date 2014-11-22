package jats.utfpl.stfpl.ccomp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.staexp.Cs2var;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.staexp.S2RTbas;
import jats.utfpl.stfpl.stype.BoolType;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.PolyParaType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.stfpl.stype.VoidType;

public class DefaultLibraryTypes {
    static private Map<String, ISType> m_map = new HashMap<String, ISType>();  // true_bool";
    
    
    static {
    	
        m_map.put("true_bool", BoolType.cInstance);
        m_map.put("false_bool", BoolType.cInstance);
        
        
        
        FunType main_type = new FunType(
                -1/*no proof*/, 
                new ArrayList<ISType>(), 
                VoidType.cInstance,
                FUNCLOfun.cInstance,
                1 /*has effect*/);
                
        m_map.put("main_void_0", main_type);

        
    }
    
    private int m_count;
    
    public DefaultLibraryTypes() {
    	m_count = 0;
    }
    
    public PolyParaType createPolyParaType() {
    	Csymbol sym_t0ype = new Csymbol("t@ype");
    	S2RTbas srt = new S2RTbas(sym_t0ype);
    	
    	Csymbol sym = new Csymbol("ta");
    	
    	Cstamp stamp = new Cstamp(0, ++m_count);
    	
    	Cs2var s2var = new Cs2var(sym, stamp, srt);
    	
    	PolyParaType type = new PolyParaType(s2var);
    	
    	return type;
    }
    
    public PolyType getType1p1() {
    	List<ISType> paras = new ArrayList<ISType>();
    	PolyParaType ty0 = createPolyParaType();
    	paras.add(ty0);
    	paras.add(ty0);
    	
    	FunType fun_type = new FunType(
                -1/*no proof*/, 
                paras, 
                ty0,
                FUNCLOfun.cInstance,
                0 /*has no effect*/);
    	
    	List<PolyParaType> polys = new ArrayList<PolyParaType>();
    	polys.add(ty0);
    	
        PolyType poly_type = new PolyType(polys, fun_type);
        return poly_type;

    }
    
    // Return value can be null.
    public ISType queryType(Csymbol sym) {
    	ISType type = m_map.get(sym.m_str);
    	if (null != type) {
    		return type;
    	}
    	
    	if (sym.equals("+") || 
    		sym.equals("-") ||
    		sym.equals("*") ||
    		sym.equals("/")) {
    		return getType1p1();
    	}
    	
    	return null;
    }
}


