package org.ats_lang.postiats.jats.tree;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoxedType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsMoveBoxrec extends ATSTypeNode {
    public StructType m_tyrec;
    public String m_tmp;
    public ATSType m_tmptype;

    // tmpty = BoxedType or RefType (BoxedType)
    // tyrec = StructType
    public AtsInsMoveBoxrec(ATSType tmpty, String tmp, ATSType tyrec) {
        super(VoidType.cType);
        
        if (tyrec instanceof StructType) {
        } else {
            throw new Error("check here");
        }
        
        m_tmp = tmp;
        m_tyrec = (StructType)tyrec;
        m_tmptype = tmpty;
        if (m_tmptype instanceof RefType) {
            ((RefType)m_tmptype).updateType(m_tyrec);
        }
        
    }
    
//    // #define ATSINSmove_boxrec(tmp, tyrec) (tmp = ATS_MALLOC(sizeof(tyrec)))
//    @Override
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        
//        if (m_tmptype instanceof RefType) {
//            RefType.update(scope.getValue(m_tmp), new HashMap<String, Object>(), BoxedType.cType);  // BoxedType <-> HashMap
//        } else {
//            scope.addValue(m_tmp, new HashMap<String, Object>());
//        }
//        
//        return SingletonValue.VOID;
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}


