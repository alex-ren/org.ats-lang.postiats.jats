package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.staexp.Ifunclo;

import java.net.URL;
import java.util.List;
import java.util.ListIterator;

import org.stringtemplate.v4.STGroupFile;

public class AuxSType {
	
    static public URL cFileURL = AuxSType.class.getResource("/jats/utfpl/stfpl/stype/stype.stg");
    static public STGroupFile cStg = new STGroupFile(cFileURL, "ascii", '<', '>');
    
    public static void matchTypeList(List<ISType> left, List<ISType> right) {
        if (left.size() != right.size()) {
            throw new Error("Type mismatch.");
        }
        
        ListIterator<ISType> iter0 = left.listIterator();
        ListIterator<ISType> iter1 = right.listIterator();
        
        while (iter0.hasNext()) {
            iter0.next().match(iter1.next());
        }
    }
    
    public static boolean isBool(ISType type) {
    	return type == BoolType.cInstance;
    }
    
    public static boolean isInt(ISType type) {
    	return type == IntType.cInstance;
    }
    
    public static boolean isVoid(ISType type) {
        return type == VoidType.cInstance;
    }
    
    public static FunType getFunctionType(ISType type) {
        if (type instanceof FunType) {
            return (FunType)type;
        } else if (type instanceof PolyType) {
            return getFunctionType(((PolyType)type).m_body);
        } else {
            return null;
        }  
    }
    
    private static Ifunclo getClosureInfo(ISType type) {
    	FunType fun_type = getFunctionType(type);
    	if (null == fun_type) {
    		throw new Error(type + " is not supported.");
    	} else {
    		return fun_type.getFunClo();
    	}
    }
    
    public static String showClosure(ISType type) {
    	Ifunclo clo = getClosureInfo(type);
    	return clo.toString();
    }
    
    public static boolean isClosure(ISType type) {
       	FunType fun_type = getFunctionType(type);
    	if (null == fun_type) {
    		return false;
    	} else {
    		return fun_type.getFunClo().isClosure();
    	}
    }
    
    public static ISType getRetType(ISType type) {
    	FunType fun_type = getFunctionType(type);
    	if (null == fun_type) {
    		throw new Error("unexcepted type " + type);
    	} else {
    		return fun_type.getRetType();
    	}
    }
    
    static public class ToCSTypeResult {
        public ICSType m_type;
        
        public Boolean m_new;  // is newly created or already in the map
                               // can be null, which indicates invalid.
        
        public ToCSTypeResult(ICSType type, Boolean _new) {
            m_type = type;
            m_new = _new;
        }
    }
    
    static public boolean hasEffect(ISType type) {
        FunType fun_type = getFunctionType(type);
        if (null == fun_type) {
            return false;
        } else {
            return fun_type.hasEffect();
        }
    }



}
