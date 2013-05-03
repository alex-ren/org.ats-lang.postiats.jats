package org.ats_lang.postiats.jats.tree;

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

public class AtsInsStoreBoxrecOfs extends ATSTypeNode {
    private String m_tmp; // name of the structure object
    private String m_lab; // name of the member
    private ATSType m_ty; // type of the object // can be RefType(BoxedType)
    private StructType m_tyrec; // type of the structure
    private ATSNode m_val; // value of the member

    public AtsInsStoreBoxrecOfs(ATSType tmpty, String tmp, ATSType tyrec, String lab, ATSNode val) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_lab = lab;
        m_ty = tmpty;
        if (tyrec instanceof StructType) {
            m_tyrec = (StructType) tyrec;
        } else {
            throw new Error("type mismatch");
        }
        m_val = val;

    }

    // #define ATSINSstore_boxrec_ofs(tmp, tyrec, lab, val) (((tyrec*)(tmp))->lab = val)
    @Override
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object rec = scope.getValue(m_tmp);
        
        if (rec instanceof Ptrk) {  // m_ty = RefType(BoxedType)
            rec = ((Ptrk) rec).getValue();
            
        }
        
        @SuppressWarnings("unchecked")
        Map<String, Object> recm = (Map<String, Object>) rec;
        
        Object target = m_val.evaluate(types, funcs, scope);
        ATSType target_ty = m_val.getType();
        if (target_ty instanceof RefType) {
            target = RefType.cloneValue(target, target_ty);
        }
        recm.put(m_lab, target);
        
        return SingletonValue.VOID;       
            
    }

}
