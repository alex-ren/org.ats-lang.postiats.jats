package org.ats_lang.postiats.jats.tree;


import org.ats_lang.postiats.jats.type.ATSType;

import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.VoidType;


public class AtsInsStoreBoxrecOfs extends ATSTypeNode {
    public String m_tmp; // name of the structure object
    public String m_lab; // name of the member
    public ATSType m_ty; // type of the object // can be RefType(BoxedType)
    public StructType m_tyrec; // type of the structure
    public ATSNode m_val; // value of the member

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
//
//    // #define ATSINSstore_boxrec_ofs(tmp, tyrec, lab, val) (((tyrec*)(tmp))->lab = val)
//    @Override
//    public SingletonValue evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        Object rec = scope.getValue(m_tmp);
//        
//        if (rec instanceof Ptrk) {  // m_ty = RefType(BoxedType)
//            rec = ((Ptrk) rec).getValue(BoxedType.cType);
//        }
//        
//        @SuppressWarnings("unchecked")
//        Map<String, Object> recm = (Map<String, Object>) rec;
//        
//        Object target = m_val.evaluate(types, funcs, scope);
//        ATSType target_ty = m_val.getType();
//        if (target_ty instanceof RefType) {
//            target = RefType.cloneValue(target, ((RefType) target_ty).defType());
//        }
//        recm.put(m_lab, target);
//        
//        return SingletonValue.VOID;       
//            
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}

