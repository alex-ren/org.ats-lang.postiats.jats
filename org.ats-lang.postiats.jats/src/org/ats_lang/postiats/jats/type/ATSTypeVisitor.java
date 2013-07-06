package org.ats_lang.postiats.jats.type;

public interface ATSTypeVisitor {
    Object visit(ArrayType ty);
    Object visit(ArrPszType ty);
    Object visit(ArrPtrType ty);
    Object visit(BoolType ty);
    Object visit(BoxedType ty);
    Object visit(CharType ty);
    Object visit(DoubleType ty);
    Object visit(FuncType ty);
    Object visit(IntType ty);
    Object visit(LDoubleType ty);
    Object visit(LIntType ty);
    Object visit(LLIntType ty);
    Object visit(PtrkType ty);
    Object visit(RefType ty);
    Object visit(SCharType ty);
    Object visit(SIntType ty);
    Object visit(SizeType ty);
    Object visit(SSizeType ty);
    Object visit(StringType ty);
    Object visit(StructType ty);
    Object visit(UCharType ty);
    Object visit(UIntType ty);
    Object visit(ULIntType ty);
    Object visit(ULLIntType ty);
    Object visit(USIntType ty);
    Object visit(VoidType ty);


}
 