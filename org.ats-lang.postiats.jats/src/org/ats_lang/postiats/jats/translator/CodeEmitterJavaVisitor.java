package org.ats_lang.postiats.jats.translator;

import org.ats_lang.postiats.jats.tree.ATSTreeVisitor;
import org.ats_lang.postiats.jats.tree.ATSdynload0;
import org.ats_lang.postiats.jats.tree.AtsCkIseqz;
import org.ats_lang.postiats.jats.tree.AtsDeref;
import org.ats_lang.postiats.jats.tree.AtsEmpty;
import org.ats_lang.postiats.jats.tree.AtsInsLoad;
import org.ats_lang.postiats.jats.tree.AtsInsMove;
import org.ats_lang.postiats.jats.tree.AtsInsMoveArrpszPtr;
import org.ats_lang.postiats.jats.tree.AtsInsMoveBoxrec;
import org.ats_lang.postiats.jats.tree.AtsInsMoveVoid;
import org.ats_lang.postiats.jats.tree.AtsInsPMove;
import org.ats_lang.postiats.jats.tree.AtsInsStore;
import org.ats_lang.postiats.jats.tree.AtsInsStoreArrpszAsz;
import org.ats_lang.postiats.jats.tree.AtsInsStoreArrpszPtr;
import org.ats_lang.postiats.jats.tree.AtsInsStoreBoxrecOfs;
import org.ats_lang.postiats.jats.tree.AtsInsStoreFltrecOfs;
import org.ats_lang.postiats.jats.tree.AtsInsUpdatePtrInc;
import org.ats_lang.postiats.jats.tree.AtsInsXStore;
import org.ats_lang.postiats.jats.tree.AtsPmvCastFn;
import org.ats_lang.postiats.jats.tree.AtsPmvIntRepNode;
import org.ats_lang.postiats.jats.tree.AtsPmvPtrof;
import org.ats_lang.postiats.jats.tree.AtsPmvPtrofVoid;
import org.ats_lang.postiats.jats.tree.AtsPmvRefArg;
import org.ats_lang.postiats.jats.tree.AtsPmvSimpleCastNode;
import org.ats_lang.postiats.jats.tree.AtsPmvSizeofNode;
import org.ats_lang.postiats.jats.tree.AtsReturn;
import org.ats_lang.postiats.jats.tree.AtsSelArrInd;
import org.ats_lang.postiats.jats.tree.AtsSelArrPtrInd;
import org.ats_lang.postiats.jats.tree.AtsSelBoxRec;
import org.ats_lang.postiats.jats.tree.AtsSelFltRec;
import org.ats_lang.postiats.jats.tree.AtsSelRecsinNode;
import org.ats_lang.postiats.jats.tree.BlockNode;
import org.ats_lang.postiats.jats.tree.DefinitionNode;
import org.ats_lang.postiats.jats.tree.FuncCallNode;
import org.ats_lang.postiats.jats.tree.IdentifierNode;
import org.ats_lang.postiats.jats.tree.IfNode;
import org.ats_lang.postiats.jats.tree.UserFunc;
import org.ats_lang.postiats.jats.tree.ValueNode;
import org.ats_lang.postiats.jats.type.ATSEltType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CodeEmitterJavaVisitor implements ATSTreeVisitor {

    private STGroup m_stg;
    
    public CodeEmitterJavaVisitor(STGroup stg) {
        m_stg = stg;
    }
    
    @Override
    public Object visit(AtsCkIseqz node) {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public Object visit(AtsDeref node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ATSdynload0 node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsEmpty node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsLoad node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsMoveArrpszPtr node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsMoveBoxrec node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsMove node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsMoveVoid node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsPMove node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsStoreArrpszAsz node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsStoreArrpszPtr node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsStoreBoxrecOfs node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsStoreFltrecOfs node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsStore node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsUpdatePtrInc node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsInsXStore node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvCastFn node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvIntRepNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvPtrof node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvPtrofVoid node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvRefArg node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvSimpleCastNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsPmvSizeofNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsReturn node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsSelArrInd node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsSelArrPtrInd node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsSelBoxRec node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsSelFltRec node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(AtsSelRecsinNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(BlockNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(DefinitionNode node) {
        if (node.m_ty.equals(VoidType.cType)) {
            return null;
        }
        
        ST st = m_stg.getInstanceOf("var_def_st");
        st.add("type", node.m_ty.toString());
        st.add("name", node.m_id);
        
        if (node.m_ty instanceof ATSEltType) {
            st.add("init", "null");
        } else if (node.m_ty instanceof StructType) {
            st.add("init", node.m_ty.toString() + ".createNormalDefault()");
        } else {
            throw new Error("not supported");
        }
        
        if (node.m_isStat) {
            ST stVarDef = m_stg.getInstanceOf("global_var_st");
            stVarDef.add("var", st);
            return stVarDef;
            
        } else {
            return st;
        }
        
    }

    @Override
    public Object visit(FuncCallNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(IdentifierNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(IfNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ValueNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(UserFunc node) {
        // TODO Auto-generated method stub
        return null;
    }

}
