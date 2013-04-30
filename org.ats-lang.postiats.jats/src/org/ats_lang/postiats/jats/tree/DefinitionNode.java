package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPszType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

/*
 * Variable definition  
 */
public class DefinitionNode extends ATSTypeNode {
    private ATSType m_ty;
    private String m_id;

    public DefinitionNode(ATSScope<ATSType> tyscope, ATSType ty, String id) {
        super(VoidType.cType);
        
        // obsolete 04/29/2013
////        // Caution: this is important
//        // ArrPszType
//        if (ty instanceof ArrPszType) {
//            ty = ArrPszType.createUpdatable();
//        }

        if (DefinitionNode.isRefName(id)) {
            ty = new RefType(ty);
        }

        m_id = id;
        m_ty = ty;

        tyscope.addValue(m_id, m_ty);
    }

    public static boolean isRefName(String name) {
        return name.matches(".*ref\\d+"); // reference
    }

    public ATSType getType() {
        return m_ty;
    }

    public String getID() {
        return m_id;
    }

    @Override
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        scope.addValue(m_id, m_ty.createNormalDefault());
        return SingletonValue.VOID;
    }
}
