package org.ats_lang.postiats.jats.ccomp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.LibFunc;
import org.ats_lang.postiats.jats.value.ATSValue;



public class CCompUtils {
    
    public static final String prefix = "atspre_";
    
    public static int populateFuncs(Map<String, FuncDef> funcs, Class<?> cls) {
        Method [] methods = cls.getDeclaredMethods();
        int nFuncs = 0;

        for (final Method method: methods) {
            String name = method.getName();
            
            if (name.startsWith(CCompUtils.prefix)) {
                System.out.println(name);
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
        
}