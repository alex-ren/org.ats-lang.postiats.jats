package org.ats_lang.postiats.jats.ccomp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LibFunc;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class CCompUtils {

    public static final String prefix = "atspre_";
    public static final String libfix = "atslib_";

    public static int populateFuncs(Map<String, FuncDef> funcs, Class<?> cls) {
        final String classname = cls.getSimpleName();
        
        Method[] methods = cls.getDeclaredMethods();
        int nFuncs = 0;

        for (final Method method : methods) {
            final String name = method.getName();

            if (name.startsWith(CCompUtils.prefix) || name.startsWith(CCompUtils.libfix)) {
//                 System.out.println(name);
                // Class<?>[] paraTypes = method.getParameterTypes();
                // Class<?> retType = method.getReturnType();

                // check whether the function is for use of interpreter
                boolean forInterpreter = true;
                // // check the return type
                // if (CCompCompositeValue.class.isAssignableFrom(retType)) {
                // forInterpreter = false;
                // }
                // // check the type of the parameters
                // if (forInterpreter == true) {
                // for (int argc = 0; argc < paraTypes.length; argc++) {
                // if (CCompCompositeValue.class
                // .isAssignableFrom(paraTypes[argc])) {
                // forInterpreter = false;
                // break;
                // }
                // }
                // }

                if (forInterpreter == true) {
                    LibFunc func = new LibFunc() {

                        public Object evaluate(List<Object> paras) {
                            // System.out.println("evaluating function " +
                            // name);
                            Object[] args = new Object[0];
                            // System.out.println("paras is " + paras);
                            if (paras != null) {
                                args = paras.toArray();
                            }
                            // System.out.println("args is " + args);
                            try {
                                return method.invoke(null, args);
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                                System.out.println("function " + name
                                        + " throw exception");
                                throw new Error("evaluate fails");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                System.out.println("function " + name
                                        + " throw exception");
                                throw new Error("evaluate fails");
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                                System.out.println("function " + name
                                        + " throw exception");
                                throw new Error("evaluate fails");
                            }
                        }

                        public String getModuleName() {
                            return classname;
                        }

                    };

                    funcs.put(name, func);
                    nFuncs++;
                }
            }
        }

        return nFuncs;
    }


    public static void populateAllFuncs(Map<String, FuncDef> funcs) {

        populateFuncs(funcs, CCompArrayPtr.class);
        populateFuncs(funcs, CCompBasics.class);
        populateFuncs(funcs, CCompChar.class);

        populateFuncs(funcs, CCompDebug.class);
        populateFuncs(funcs, CCompFileBas.class);
        populateFuncs(funcs, CCompFloat.class);
        populateFuncs(funcs, CCompInteger.class);
        populateFuncs(funcs, CCompMemory.class);
        populateFuncs(funcs, CCompPointer.class);
        populateFuncs(funcs, CCompStdlib.class);

        populateFuncs(funcs, CCompString.class);

    }
    
    
    public static int populateGlabalValues(ATSScope<Object> gvscope,
            Class<?> cls) {

        Field[] fields = cls.getFields();
        int nFields = 0;

        for (Field field : fields) {
            String name = field.getName();
            if (name.startsWith(CCompUtils.prefix)) {
                try {
                    gvscope.addValue(name, field.get(null));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new Error("check here");
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new Error("check here");
                }
                nFields++;
            }
        }
        return nFields;
    }
    
    public static void populateAllGlobalValues(ATSScope<Object> gvscope) {
        populateGlabalValues(gvscope, CCompArrayPtr.class);
        populateGlabalValues(gvscope, CCompBasics.class);
        populateGlabalValues(gvscope, CCompChar.class);

        populateGlabalValues(gvscope, CCompDebug.class);
        populateGlabalValues(gvscope, CCompFileBas.class);
        
        populateGlabalValues(gvscope, CCompFloat.class);
        populateGlabalValues(gvscope, CCompInteger.class);

        populateGlabalValues(gvscope, CCompMemory.class);
        populateGlabalValues(gvscope, CCompPointer.class);
        populateGlabalValues(gvscope, CCompStdlib.class);

        populateGlabalValues(gvscope, CCompString.class);
 
    }


    public static void populateAllGlobalValueTypes(ATSScope<ATSType> tyscope) {

        CCompArrayPtr.populateGlobalValueType(tyscope);
        CCompBasics.populateGlobalValueType(tyscope);
        CCompChar.populateGlobalValueType(tyscope);

        CCompDebug.populateGlobalValueType(tyscope);
        CCompFileBas.populateGlobalValueType(tyscope);

        CCompFloat.populateGlobalValueType(tyscope);
        CCompInteger.populateGlobalValueType(tyscope);

        CCompMemory.populateGlobalValueType(tyscope);
        CCompPointer.populateGlobalValueType(tyscope);
        CCompStdlib.populateGlobalValueType(tyscope);

        CCompString.populateGlobalValueType(tyscope);

    }

    public static void populateAllFuncTypes(ATSScope<ATSType> tyscope) {

        CCompArrayPtr.populateFuncType(tyscope);
        CCompBasics.populateFuncType(tyscope);
        CCompChar.populateFuncType(tyscope);

        CCompDebug.populateFuncType(tyscope);
        CCompFileBas.populateFuncType(tyscope);

        CCompFloat.populateFuncType(tyscope);
        CCompInteger.populateFuncType(tyscope);

        CCompMemory.populateFuncType(tyscope);
        CCompPointer.populateFuncType(tyscope);
        CCompStdlib.populateFuncType(tyscope);

        CCompString.populateFuncType(tyscope);

    }


    // =======================

    public static Map<String, ATSType> getLibTypes() {
        Map<String, ATSType> types = new HashMap<String, ATSType>();

        types.put("atstype_void", CCompTypedefs.m_atstype_void);
        types.put("atsvoid_t0ype", CCompTypedefs.m_atsvoid_t0ype);

        types.put("atstype_int", CCompTypedefs.m_atstype_int);
        types.put("atstype_uint", CCompTypedefs.m_atstype_uint);
        types.put("atstype_lint", CCompTypedefs.m_atstype_lint);
        types.put("atstype_ulint", CCompTypedefs.m_atstype_ulint);
        types.put("atstype_llint", CCompTypedefs.m_atstype_llint);
        types.put("atstype_ullint", CCompTypedefs.m_atstype_ullint);
        types.put("atstype_sint", CCompTypedefs.m_atstype_sint);
        types.put("atstype_usint", CCompTypedefs.m_atstype_usint);

        types.put("atstype_size", CCompTypedefs.m_atstype_size);
        types.put("atstype_ssize", CCompTypedefs.m_atstype_ssize);

        types.put("atstype_bool", CCompTypedefs.m_atstype_bool);

        types.put("atstype_char", CCompTypedefs.m_atstype_char);
        types.put("atstype_schar", CCompTypedefs.m_atstype_schar);
        types.put("atstype_uchar", CCompTypedefs.m_atstype_uchar);

        // types.put("atstype_string", CCompTypedefs.m_atstype_string);
        types.put("atstype_string", CCompTypedefs.m_atstype_ptrk);

        // types.put("atstype_strptr", CCompTypedefs.m_atstype_strptr);

        types.put("atstype_float", CCompTypedefs.m_atstype_float);
        types.put("atstype_double", CCompTypedefs.m_atstype_double);
        types.put("atstype_ldouble", CCompTypedefs.m_atstype_ldouble);

        // types.put("atstype_ptr", CCompTypedefs.m_atstype_ptr);
        types.put("atstype_ptrk", CCompTypedefs.m_atstype_ptrk);

        types.put("atstype_arrptr", CCompTypedefs.m_atstype_arrptr);

        types.put("atstype_arrpsz", CCompTypedefs.m_atstype_arrpsz);

        types.put("atstype_boxed", CCompTypedefs.m_atstype_boxed);

        types.put("demo", CCompTypedefs.m_demo);

        return types;
    }

}