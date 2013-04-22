package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ATSUpdatableType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;
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
        if (ty instanceof ATSUpdatableType) {
            ty = ((ATSUpdatableType)ty).createUpdatable(null);
        }
        
        if (DefinitionNode.isRefName(id)) {
            ty = new RefType(ty);
        }
        
        m_id = id;
        m_ty = ty;
        
        tyscope.addValue(m_id, m_ty);
    }

    public static boolean isRefName(String name) {
        return name.matches(".*ref\\d+");  // reference
    }

    public ATSType getType() {
        return m_ty;
    }

    public String getID() {
        return m_id;
    }

    @Override
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        if (m_ty instanceof ATSUpdatableType) {
            // do nothing (do later)
        } else {
            scope.addValue(m_id, m_ty.createDefault());
        }
        return SingletonValue.VOID;
    }
}
