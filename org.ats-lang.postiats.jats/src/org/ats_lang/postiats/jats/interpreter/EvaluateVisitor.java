package org.ats_lang.postiats.jats.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.tree.ATSNode;
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
import org.ats_lang.postiats.jats.tree.AtsReturn.ReturnValue;
import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.BoxedType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class EvaluateVisitor implements ATSTreeVisitor {

    private Map<String, ATSType> m_types;
    private Map<String, FuncDef> m_funcs;
    private ATSScope<Object> m_scope;

    public EvaluateVisitor(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        m_types = types;
        m_funcs = funcs;
        m_scope = scope;
    }

    Object evaluate(ATSNode node) {
        return node.accept(this);
    }


    private void printIndent(int n) {
        System.out.println();
        for (int j = 0; j < n; ++j) {
            System.out.print(" ");
        }
    }
    private static int m_indent = 0;
    
    @Override
    // #define ATSCKiseqz(x) ((x)==0)
    public Object visit(AtsCkIseqz node) {
        Object v = node.m_exp.accept(this);
        // System.out.println("ATSCKiseqz " + v);
//
//        if (v instanceof Ptrk) {
//            System.out.println("lvalue in condition");
//            v = ((Ptrk) v).getValue(BoolType.cType);
//        }

        if (v instanceof Integer) {
            return (Integer) v == 0;
        } else {
            throw new Error("type mismatch in condition");
        }

    }

    @Override
    // #define ATSderef(pmv, hit) (*(hit*)pmv)
    public Object visit(AtsDeref node) {
        Object v = node.m_node.accept(this);
        return RefType.deref(v);

    }
    
    @Override
 // #define ATSdynload0(flag) int flag = 0
    public Object visit(ATSdynload0 node) {
        m_scope.addValue(node.m_dyn0, 0);

        return SingletonValue.VOID;

    }

    @Override
    public Object visit(AtsEmpty node) {
        return SingletonValue.VOID;

    }

    @Override
    // pats_ccomp_instrset
    // #define ATSINSload(tmp, pmv) (tmp = pmv)
    public Object visit(AtsInsLoad node) {
        throw new Error("not supported");

    }

    @Override
    // #define ATSINSmove_arrpsz_ptr(tmp, psz) (tmp = (psz).ptr)
    // example
//    ATStmpdec(tmp0, atstype_arrpsz) ;
//    ATStmpdec(tmp1, atstype_arrptr) ;
//    ATSINSmove_arrpsz_ptr(tmp1, tmp0) ;
    
    // tmp := ArrPtrType
    // psz := ArrPszType
    public Object visit(AtsInsMoveArrpszPtr node) {
        Object psz = node.m_psz.accept(this);
        // m_psz := ArrPszType
        if (psz instanceof ArrPsz) {
            Ptrk arrp = ((ArrPsz) psz).getPtr();
            m_scope.addValue(node.m_tmp, arrp);
            return SingletonValue.VOID;
        } else {
            throw new Error("Type mismatch");
        }


    }

    @Override
 // #define ATSINSmove_boxrec(tmp, tyrec) (tmp = ATS_MALLOC(sizeof(tyrec)))
    public Object visit(AtsInsMoveBoxrec node) {
        if (node.m_tmptype instanceof RefType) {
            RefType.update(m_scope.getValue(node.m_tmp), new HashMap<String, Object>(), BoxedType.cType);  // BoxedType <-> HashMap
        } else {
            m_scope.addValue(node.m_tmp, new HashMap<String, Object>());
        }
        
        return SingletonValue.VOID;

    }

    @Override
 // #define ATSINSmove(tmp, val) (tmp = val)
    public Object visit(AtsInsMove node) {
        Object src = node.m_val.accept(this);
        ATSType srcty = node.m_val.getType();
        if (node.m_type instanceof RefType) {
            RefType.update(m_scope.getValue(node.m_tmp), src, srcty);
        } else {
            if (srcty instanceof RefType) {
                m_scope.addValue(node.m_tmp,
                        RefType.cloneValue(src, ((RefType) srcty).defType()));
            } else {
                m_scope.addValue(node.m_tmp, src);
            }
        }

        return SingletonValue.VOID;

    }

    @Override
 // #define ATSINSmove_void(tmp, command) command
    public Object visit(AtsInsMoveVoid node) {
        node.m_val.accept(this);
        return SingletonValue.VOID;

    }

    @Override
 // #define ATSINSpmove(tmp, hit, val) (*(hit*)tmp = val)
    public Object visit(AtsInsPMove node) {
        Object val = node.m_val.accept(this);
        ATSType valtype = node.m_val.getType();
        
        Ptrk arrp = (Ptrk)m_scope.getValue(node.m_tmp);
        if (valtype instanceof RefType) {
            arrp.update(RefType.getValue(val, ((RefType) valtype).defType()), node.m_hit);
        } else if (valtype instanceof ATSReferableType) {
            arrp.update(val, node.m_hit); 
        } else {
            throw new Error("ATSINSpmove: only name is supported now");
        }
        
        return SingletonValue.VOID;

    }

    @Override
    // #define ATSINSstore_arrpsz_asz(tmp, asz) (tmp.size = asz)
    // example
    //    typedef
    //    struct {
    //      atstype_arrptr ptr ; atstype_size size ;
    //    } atstype_arrpsz ;
    //    ATStmpdec(tmp0, atstype_arrpsz) ;
    //    ATSINSstore_arrpsz_asz(tmp0, 3) ;
    public Object visit(AtsInsStoreArrpszAsz node) {
     // no-op.
        return SingletonValue.VOID;

    }

    @Override
    // #define ATSINSstore_arrpsz_ptr(tmp, tyelt, asz) (tmp.ptr = ATS_MALLOC(asz*sizeof(tyelt)))
    // example
//    typedef void* atstype_arrptr ;
//    typedef atstype_ulint atstype_size ;
//
//    typedef
//    struct {
//      atstype_arrptr ptr ; atstype_size size ;
//    } atstype_arrpsz ;
//    
//    #define atstkind_type(tk) tk
//    #define atstkind_t0ype(tk) tk
//    
//    ATStmpdec(tmp0, atstype_arrpsz) ;
//    ATSINSstore_arrpsz_ptr(tmp0, atstkind_t0ype(atstype_double), 3) ;    
    public Object visit(AtsInsStoreArrpszPtr node) {
        Object asz = node.m_asz.accept(this);
        
        Object arrpsz = m_scope.getValue(node.m_tmp);
        if (arrpsz instanceof ArrPsz) {
            ((ArrPsz)arrpsz).init(asz, node.m_tyelt);
        } else {
            throw new Error("Type mismatch");
        }
        
        
        return SingletonValue.VOID;

    }

    @Override
 // #define ATSINSstore_boxrec_ofs(tmp, tyrec, lab, val) (((tyrec*)(tmp))->lab = val)
    public Object visit(AtsInsStoreBoxrecOfs node) {
        Object rec = m_scope.getValue(node.m_tmp);
        
        if (rec instanceof Ptrk) {  // m_ty = RefType(BoxedType)
            rec = ((Ptrk) rec).getValue(BoxedType.cType);
        }
        
        @SuppressWarnings("unchecked")
        Map<String, Object> recm = (Map<String, Object>) rec;
        
        Object target = node.m_val.accept(this);
        ATSType target_ty = node.m_val.getType();
        if (target_ty instanceof RefType) {
            target = RefType.cloneValue(target, ((RefType) target_ty).defType());
        }
        recm.put(node.m_lab, target);
        
        return SingletonValue.VOID;       

    }

    @Override
    public Object visit(AtsInsStoreFltrecOfs node) {

        Object target = node.m_val.accept(this);
        ATSType target_ty = node.m_val.getType();

        Object rec = m_scope.getValue(node.m_tmp);
//        System.out.println("lab is " + m_lab);
        
        // m_ty == RefType
        if (node.m_ty instanceof RefType) {
            
            if (target_ty instanceof RefType) {
                target = RefType.getValue(target, ((RefType) target_ty).defType());
            }
            
            RefType.updateFltrecOfs(rec, target, node.m_lab, node.m_tyrec);
        } else if (node.m_ty instanceof StructType) {
            if (target_ty instanceof RefType) {
                target = RefType.cloneValue(target, ((RefType) target_ty).defType());
            }
            
            @SuppressWarnings("unchecked")
            Map<String, Object> mrec = (Map<String, Object>)rec;
            
            mrec.put(node.m_lab, target);
        } else {
            throw new Error("non record");
        }
        
        return SingletonValue.VOID;        

    }

    @Override
    // #define ATSINSstore(pmv1, pmv2) (pmv1 = pmv2)
    // pmv1 := RefType
    public Object visit(AtsInsStore node) {
        Object dst = node.m_pmv1.accept(this);
        Object src = node.m_pmv2.accept(this);

        ATSType dstType = node.m_pmv1.getType();
        ATSType srcType = node.m_pmv2.getType();
        
        if (dstType instanceof RefType) {
            RefType.update(dst, src, srcType);
        } else {
            throw new Error("Updating non-ref value");
        }
        
        return SingletonValue.VOID;
    }

    @Override
    public Object visit(AtsInsUpdatePtrInc node) {
        
        Object tmp = m_scope.getValue(node.m_tmp);
        
        if (tmp instanceof Ptrk) {
            Ptrk np = Ptrk.createPtrkOffset((Ptrk)tmp, node.m_tyelt.getSize());  
            // Caution: must put the new Ptrk back
            m_scope.addValue(node.m_tmp, np);
        } else {
            throw new Error("AtsInsUpdatePtrInc on non-ptr value");
        }
        
        return SingletonValue.VOID;

    }

    @Override
    // #define ATSINSxstore(tmp, pmv1, pmv2) (tmp = pmv1, pmv1 = pmv2, pmv2 = tmp)
    // example
//    ATStmpdec(tmp3$1, atstkind_t0ype(atstype_double)) ;
//    ATSINSxstore(tmp3$1, 
//                 ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg1]), 
//                 ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg2])) ;
    public Object visit(AtsInsXStore node) {
        Object pmv1 = node.m_pmv1.accept(this);
        Object pmv2 = node.m_pmv2.accept(this);
        
        Object temp = RefType.cloneValue(pmv1, node.m_elety);
        
        m_scope.addValue(node.m_tmp, temp);
        
        RefType.update(pmv1, pmv2, node.m_pmv2.getType());
        RefType.update(pmv2, temp, node.m_elety);
        
        return SingletonValue.VOID;

    }

    @Override
 // AtsPmvCastFn is a non-op.
    public Object visit(AtsPmvCastFn node) {
        Object v = node.m_arg.accept(this);
//      System.out.println("==cast " + m_arg.getType() + " to " + m_hit);
      

      if (node.m_d2c.equals("cast")) {
          ATSType arg_type = node.m_arg.getType();

          if (arg_type == node.m_hit) {
//              throw new Error("check this case");
              // return v.deepcopy();
          }
          
          if (arg_type instanceof RefType) {
              throw new Error("check this case");
          }

          // ptr2string
          if (arg_type instanceof ArrPtrType) {
              throw new Error("check this case");
          }

          return v;
      } else if (node.m_d2c.equals("string2ptr")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("ptr1_of_ptr0")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("string1_of_string0")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("arrayptr2ptr")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("arrayptr_encode2")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("arrayptrout2ptr")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("g1ofg0_string")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;
      } else if (node.m_d2c.equals("g1ofg0_ptr")) {
          if (!node.m_arg.getType().equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          if (!node.m_hit.equals(PtrkType.cType)) {
              throw new Error("check this case");
          }
          return v;          
      } else {
          throw new Error("Unknown cast: " + node.m_d2c);
      }
      
    }

    @Override
    public Object visit(AtsPmvIntRepNode node) {
        return (Integer)node.m_exp.accept(this);

    }

    @Override
    // #define ATSPMVptrof(lval) (&(lval))
    // example
//    ATStmpdec(tmp2, atstkind_t0ype(atstype_size))
//    ATSPMVptrof(tmp2)    
    public Object visit(AtsPmvPtrof node) {
        Object v = m_scope.getValue(node.m_lval);
        if (v instanceof Ptrk) {
            return RefType.ptrof((Ptrk) v);
        } else {
            throw new Error("type mismatch");
        }

    } 

    @Override
    public Object visit(AtsPmvPtrofVoid node) {
        System.out.println("Caution: AtsPmvPtrofVoid is used");
        return null;

    }

    @Override
    // #define ATSPMVrefarg0(val) (val)
    // #define ATSPMVrefarg1(ref) (ref)
    // no-op
    public Object visit(AtsPmvRefArg node) {
        return node.m_node.accept(this);

    }

    @Override
 // Currently, this is a non-op.
    public Object visit(AtsPmvSimpleCastNode node) {
        Object v = node.m_node.accept(this);
        ATSType toty = node.getType();
        ATSType fromty = node.m_node.getType();
        
        if (toty instanceof IntType) {
            Object temp = (Integer)v;
        } else if (toty instanceof CharType) {
            Object temp = (Character)v;
        } else if (toty instanceof DoubleType){
            Object temp = (Double)v;
        } else if (toty instanceof PtrkType) {
            Object temp = (Ptrk)v;
        } else {
            throw new Error("not supported");
        }
        return v;

    }

    @Override
    public Object visit(AtsPmvSizeofNode node) {
        return node.m_hit.getSize();

    }

    @Override
    // #define ATSreturn(x) return (x)
    // sample
    // ATSreturn(tmp21$1) ;
    public Object visit(AtsReturn node) {
        // Make the copy here since functions return value.
        Object v = node.m_exp.accept(this);
        if (node.m_nodety instanceof RefType) {
            return new ReturnValue(RefType.cloneValue(v, ((RefType) node.m_nodety).defType()));
        } else {
            return new ReturnValue(v);
        }

    }

    @Override
    // #define ATSselarrind(pmv, tyarr, lab) (((tyarr)pmv).lab)
    // example
    public Object visit(AtsSelArrInd node) {
        throw new Error("not supported");

    }

    @Override
    // #define ATSselarrptrind(pmv, tyelt, lab) (((tyelt*)pmv)lab)
    // example
    // ATSINSxstore(tmp3$1,
    // ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg1]),
    // ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg2])
    // ) ;    
    public Object visit(AtsSelArrPtrInd node) {
        Object pmv = node.m_pmv.accept(this);  // array
        Object asz = node.m_lab.accept(this);  // index
        
        if (asz instanceof Ptrk) {
            asz = ((Ptrk)asz).getValue(SizeType.cType);
        }
        Integer sz = null;
        if (asz instanceof Integer) {
         sz = (Integer)asz;
        } else {
         throw new Error("Type error");
        }
        
        Ptrk ret = ((Ptrk)pmv).SelArrInd(sz, node.m_tyelt);
        return ret;

    }

    @Override
    // #define ATSselboxrec(pmv, tyrec, lab) (((tyrec*)pmv)->lab)
    // example
    // ATSINSmove(tmp5, foo2_1(ATSselboxrec(tmp3, postiats_tyrec_0, atslab$0))) ;
    // ATSINSmove(tmp7, foo2_1(ATSselboxrec(tmpref4, postiats_tyrec_0, atslab$0))) ;
    public Object visit(AtsSelBoxRec node) {
//      System.out.println("==============AtsSelBoxRec");
        // x := RefType (BoxedType (StructType)) => x : Ptrk
        if (node.m_pmv.getType() instanceof RefType) {
//          System.out.println("==============AtsSelBoxRec-0001");
            Object p = node.m_pmv.accept(this);
//            System.out.println("==============AtsSelBoxRec-0002");
            Object m = RefType.getValue(p, BoxedType.cType);
//            System.out.println("==============AtsSelBoxRec-0003");
            @SuppressWarnings("unchecked")
            Map<String, Object> v = (Map<String, Object>) m;
//            System.out.println("==============AtsSelBoxRec-0004");
            return v.get(node.m_lab);
        } else {  // x := BoxType (StructType) => x : Map
            @SuppressWarnings("unchecked")
            Map<String, Object> v = (Map<String, Object>) node.m_pmv.accept(this);
            return v.get(node.m_lab);
        }

    }

    @Override
    // #define ATSselfltrec(pmv, tyrec, lab) ((pmv).lab)
    // example
    
//    typedef
//    struct {
//    atstkind_t0ype(atstype_int) atslab$0; 
//    atstkind_t0ype(atstype_int) atslab$1; 
//    } postiats_tyrec_0 ;
//    
//    atsvoid_t0ype
//    loop_1 (atsrefarg1_type(postiats_tyrec_0) arg0, atstkind_t0ype(atstype_int) arg1)
//    
//    ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$0)
    public Object visit(AtsSelFltRec node) {
//      System.out.println("==============AtsSelFltRec");

        // v := RefType (StructType) or StructType => v : Ptrk
        Object v = node.m_pmv.accept(this);

//        System.out.println("AtsSelFltRec.evaluate v is " + v + " and addr is " + System.identityHashCode(v));
        
        if (node.m_pmv.getType() instanceof RefType) {
            
            Ptrk ret = RefType.SelFltrecOfs(v, node.m_lab, node.m_tyrec);
//          System.out.println("==============AtsSelFltRec-00001");
            return ret;
            
        } else {
            @SuppressWarnings("unchecked")
            Map<String, Object> mrec = (Map<String, Object>) v;
            return mrec.get(node.m_lab);
        }

    }

    @Override
    // #define ATSselrecsin(pmv, tyrec, lab) (pmv)
    // example
//    ATStmpdec(tmp16, atstkind_t0ype(atstype_int)) ;
//    ATSselrecsin(tmp16, atstkind_t0ype(atstype_int), atslab$1)
    public Object visit(AtsSelRecsinNode node) {
        return m_scope.getValue(node.m_pmv);

    }

    @Override
    public Object visit(BlockNode node) {
        for (ATSNode state: node.m_statements) {
            Object v = state.accept(this);
            if (v instanceof AtsReturn.ReturnValue) {
                return (AtsReturn.ReturnValue) v;
            }
        }
        return SingletonValue.VOID;

    }

    @Override
    public Object visit(DefinitionNode node) {
        m_scope.addValue(node.m_id, node.m_ty.createNormalDefault());
        return SingletonValue.VOID;

    }

    @Override
    public Object visit(FuncCallNode node) {
//        printIndent(m_indent);
//        System.out.println("Entering function: " + node.m_id);
        FuncCallNode.m_indent += 4;

        FuncDef fun = m_funcs.get(node.m_id);
        if (null == fun) {
            System.out.println("FuncCallNode::evaluate, fun " + node.m_id
                    + " is not found");
            throw new Error("FuncCallNode::evaluate, fun " + node.m_id
                    + " is not found");
        }

        List<Object> m_args = null;

        if (node.m_paras != null) {
            m_args = new ArrayList<Object>();
            for (ATSNode para : node.m_paras) {
                // clone is important
                Object arg = para.accept(this);
                ATSType argtype = para.getType();
                if (argtype instanceof RefType) {
                    // reference needs to be deep copied.
                    arg = RefType.cloneValue(arg, ((RefType) argtype).defType());
                    // System.out.println("FuncCallNode deep copy");
                }
                // printIndent(m_indent);
                // System.out.println("para.getType is " + argtype + ", arg is "
                // + arg + " @" + System.identityHashCode(arg));
                m_args.add(arg);
            }
        }

        // Only global scope can be seen inside the function.
        ATSScope<Object> aScope = m_scope.getParent().newScope();

        Object ret;
        if (fun instanceof UserFunc) {
            ret = ((UserFunc) fun).evaluate(m_types, m_funcs, aScope, m_args);
        } else {
            ret = ((LibFunc) fun).evaluate(m_args);
        }
        FuncCallNode.m_indent -= 4;
//        printIndent(m_indent);
//        System.out.println("Leaving function: " + node.m_id);
        if (ret instanceof ReturnValue) {
            return ((ReturnValue) ret).getValue();
        } else {
            return ret;
        }

    }

    @Override
    public Object visit(IdentifierNode node) {
        Object ret = m_scope.getValue(node.m_id);
        if (null == ret) {
//            System.out.println(node.m_id + " is null.");
        }
        return ret;

    }

    @Override
    public Object visit(IfNode node) {
        for (IfNode.Choice ch: node.m_choices) {
            Object b = ch.m_exp.accept(this);
            if (BoolType.isTrue(b, ch.m_exp.getType())) {
                ch.m_block.accept(this);
                return SingletonValue.VOID;
            }
        }
        node.m_else.accept(this);
        return SingletonValue.VOID;

    }

    @Override
    public Object visit(ValueNode node) {
        return node.m_v;

    }

    @Override
    public Object visit(UserFunc node) {
        throw new Error("not supported since there is no need to visit function definition alone");
    }

}
