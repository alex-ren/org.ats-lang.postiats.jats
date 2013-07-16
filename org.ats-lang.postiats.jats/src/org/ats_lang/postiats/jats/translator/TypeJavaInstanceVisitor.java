package org.ats_lang.postiats.jats.translator;


import org.ats_lang.postiats.jats.type.ATSTypeVisitor;
import org.ats_lang.postiats.jats.type.ArrPszType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.BoxedType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.LDoubleType;
import org.ats_lang.postiats.jats.type.LIntType;
import org.ats_lang.postiats.jats.type.LLIntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.SCharType;
import org.ats_lang.postiats.jats.type.SIntType;
import org.ats_lang.postiats.jats.type.SSizeType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.UCharType;
import org.ats_lang.postiats.jats.type.UIntType;
import org.ats_lang.postiats.jats.type.ULIntType;
import org.ats_lang.postiats.jats.type.ULLIntType;
import org.ats_lang.postiats.jats.type.USIntType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class TypeJavaInstanceVisitor implements ATSTypeVisitor {
    private STGroup m_stg;
    
    public TypeJavaInstanceVisitor(STGroup stg) {
        m_stg = stg;
    }

    @Override
    public Object visit(ArrayType ty) {
        throw new Error("not supported");
    }

    @Override
    public Object visit(ArrPszType ty) {
        return ArrPszType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(ArrPtrType ty) {
        return ArrPtrType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(BoolType ty) {
        return BoolType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(BoxedType ty) {
        return BoxedType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(CharType ty) {
        return CharType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(DoubleType ty) {
        return DoubleType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(FuncType ty) {
        throw new Error("not supported");
    }

    @Override
    public Object visit(IntType ty) {
        return IntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(LDoubleType ty) {
        return LDoubleType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(LIntType ty) {
        return LIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(LLIntType ty) {
        return LLIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(PtrkType ty) {
        return PtrkType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(RefType ty) {
        return "new RefType(" + ty.defType().accept(this) + ")";
    }

    @Override
    public Object visit(SCharType ty) {
        return SCharType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(SIntType ty) {
        return SIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(SizeType ty) {
        return SizeType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(SSizeType ty) {
        return SSizeType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(StringType ty) {
        throw new Error("not supported");
    }

    @Override
    public Object visit(StructType ty) {
        return ty.getName();
    }

    @Override
    public Object visit(UCharType ty) {
        return UCharType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(UIntType ty) {
        return UIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(ULIntType ty) {
        return ULIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(ULLIntType ty) {
        return ULLIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(USIntType ty) {
        return USIntType.class.getSimpleName() + ".cType";
    }

    @Override
    public Object visit(VoidType ty) {
        return VoidType.class.getSimpleName() + ".cType";
    }



}
