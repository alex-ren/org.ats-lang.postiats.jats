package org.ats_lang.postiats.jats.tree;

public interface ATSTreeVisitor {
    Object visit(AtsCkIseqz node);

    Object visit(AtsDeref node);

    Object visit(ATSdynload0 node);

    Object visit(AtsEmpty node);

    Object visit(AtsInsLoad node);

    Object visit(AtsInsMoveArrpszPtr node);

    Object visit(AtsInsMoveBoxrec node);

    Object visit(AtsInsMove node);

    Object visit(AtsInsMoveVoid node);

    Object visit(AtsInsPMove node);

    Object visit(AtsInsStoreArrpszAsz node);

    Object visit(AtsInsStoreArrpszPtr node);

    Object visit(AtsInsStoreBoxrecOfs node);

    Object visit(AtsInsStoreFltrecOfs node);

    Object visit(AtsInsStore node);

    Object visit(AtsInsUpdatePtrInc node);

    Object visit(AtsInsXStore node);

    Object visit(AtsPmvCastFn node);

    Object visit(AtsPmvIntRepNode node);

    Object visit(AtsPmvPtrof node);

    Object visit(AtsPmvPtrofVoid node);

    Object visit(AtsPmvRefArg node);

    Object visit(AtsPmvSimpleCastNode node);

    Object visit(AtsPmvSizeofNode node);

    Object visit(AtsReturn node);

    Object visit(AtsSelArrInd node);

    Object visit(AtsSelArrPtrInd node);

    Object visit(AtsSelBoxRec node);

    Object visit(AtsSelFltRec node);

    Object visit(AtsSelRecsinNode node);

    Object visit(BlockNode node);

    Object visit(DefinitionNode node);

    Object visit(FuncCallNode node);

    Object visit(IdentifierNode node);

    Object visit(IfNode node);

    Object visit(ValueNode node);

}
