package org.ats_lang.postiats.jats.tree;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoxedType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsMoveBoxrec extends ATSTypeNode {
    ATSType m_tyrec;
    String m_tmp;
    ATSType m_tmptype;

    // boxty = BoxedType or RefType (BoxedType)
    // tyrec = StructType
    public AtsInsMoveBoxrec(ATSType tmpty, String tmp, ATSType tyrec) {
        super(VoidType.cType);
        
        m_tmp = tmp;
        m_tyrec = tyrec;
        m_tmptype = tmpty;
        
    }
    
    // #define ATSINSmove_boxrec(tmp, tyrec) (tmp = ATS_MALLOC(sizeof(tyrec)))
    @Override
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        
        if (m_tmptype instanceof RefType) {
            Ptrk p = new Ptrk(new HashMap<String, Object>());
            scope.addValue(m_tmp, p);
        } else {
            scope.addValue(m_tmp, new HashMap<String, Object>());
        }
        
        return SingletonValue.VOID;
    }

}
