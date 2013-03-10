package org.ats_lang.postiats.jats.ccomp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LibFunc;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;



public class CCompUtils {
    
    public static final String prefix = "atspre_";
    
    public static int populateFuncs(Map<String, FuncDef> funcs, Class<?> cls) {
        Method [] methods = cls.getDeclaredMethods();
        int nFuncs = 0;

        for (final Method method: methods) {
            String name = method.getName();
            
            if (name.startsWith(CCompUtils.prefix)) {
                // System.out.println(name);
                Class<?> [] paraTypes = method.getParameterTypes();
                final int argc = paraTypes.length;
                LibFunc func = new LibFunc() {
                  public ATSValue evaluate(List<ATSValue> paras) {
                      Iterator<ATSValue> iter = null;
                      
                      if (paras != null) {
                          iter = paras.iterator();
                      }
                      Object[] args = new Object[argc];
                      for (int i = 0; i < argc; ++i) {
                          args[i] = iter.next();
                      }
                      try {
                        return (ATSValue) method.invoke(null, args);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        throw new Error("evaluate fails");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new Error("evaluate fails");
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        throw new Error("evaluate fails");
                    }
                  }
                };
                
                funcs.put(name, func);
                nFuncs++;
            }
        }
        
        return nFuncs;
        
    }
    
    public static void populateAllFuncs(Map<String, FuncDef> funcs) {
        populateFuncs(funcs, CCompBasics.class);
        populateFuncs(funcs, CCompInteger.class);
        populateFuncs(funcs, CCompFloat.class);
        populateFuncs(funcs, CCompArrayPtr.class);
        populateFuncs(funcs, CCompString.class);
        
    }
    
    // =======================
    
    public static Map<String, ATSType> getLibTypes() {
        Map<String, ATSType> types = new HashMap<String, ATSType>();

        types.put("atstype_ptrk", CCompTypedefs.atstype_ptrk);
        
        types.put("atsvoid_t0ype", CCompTypedefs.atsvoid_t0ype);
        
        types.put("atstype_int", CCompTypedefs.atstype_int);
        
        types.put("atstype_double", CCompTypedefs.atstype_double);
        
        types.put("atstype_bool", CCompTypedefs.atstype_bool);
        
        types.put("atstype_arrptr", CCompTypedefs.atstype_arrptr);
        
        types.put("atstype_size", CCompTypedefs.atstype_size);
        
        types.put("atstype_arrpsz", CCompTypedefs.atstype_arrpsz);
        
        
        return types;
    }
    
    public static int populateFuncNames(Map<String, String> funcs, Class<?> cls) {
        Method [] methods = cls.getDeclaredMethods();
        String classname = cls.getSimpleName();
        int nFuncs = 0;

        for (final Method method: methods) {
            String name = method.getName();
            
            if (name.startsWith(CCompUtils.prefix)) {
                // System.out.println(name);

                funcs.put(name, classname + "." + name);
                nFuncs++;
            }
        }
        
        return nFuncs;
        
    }
    
    public static void populateAllFuncNames(Map<String, String> funcs) {
        populateFuncNames(funcs, CCompBasics.class);
        populateFuncNames(funcs, CCompInteger.class);
        populateFuncNames(funcs, CCompFloat.class);
        populateFuncNames(funcs, CCompArrayPtr.class);
        populateFuncNames(funcs, CCompString.class);
        
    }
}