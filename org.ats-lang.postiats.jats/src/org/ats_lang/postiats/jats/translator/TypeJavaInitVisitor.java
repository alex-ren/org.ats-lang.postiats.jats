package org.ats_lang.postiats.jats.translator;

import java.util.List;

import javax.lang.model.type.TypeVisitor;

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
import org.ats_lang.postiats.jats.type.StructType.Pair;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class TypeJavaInitVisitor implements ATSTypeVisitor {

    private STGroup m_stg;
    
    public TypeJavaInitVisitor(STGroup stg) {
        m_stg = stg;
    }
    
    @Override
    public Object visit(ArrayType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ArrPszType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ArrPtrType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(BoolType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(BoxedType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CharType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(DoubleType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(FuncType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(IntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(LDoubleType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(LIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(LLIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PtrkType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(RefType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(SCharType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(SIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(SizeType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(SSizeType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(StringType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(StructType ty) {
        ST st = m_stg.getInstanceOf("stats_structtype_st");
        st.add("name", ty.getName());
        
        ATSTypeVisitor tyMemVisitor = new TypeJavaInstanceVisitor(m_stg);
        
        List<Pair> members = ty.getMembers();
        for (Pair p: members) {
            ST stMem = m_stg.getInstanceOf("structtype_members_st");
            stMem.add("name", ty.getName());
            stMem.add("id", p.m_id);
            stMem.add("type", p.m_ty.accept(tyMemVisitor));
            
            st.add("structtype_members", stMem);
        }
        
        return st;
    }

    @Override
    public Object visit(UCharType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(UIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ULIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ULLIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(USIntType ty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(VoidType ty) {
        // TODO Auto-generated method stub
        return null;
    }

}
