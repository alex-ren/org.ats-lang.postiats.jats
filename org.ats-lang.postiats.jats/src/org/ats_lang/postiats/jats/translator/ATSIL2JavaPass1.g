tree grammar ATSIL2JavaPass1; 

options {
  language = Java;
  tokenVocab = ATSIL;
  ASTLabelType = CommonTree;
  output = template;
}

@header {
  package org.ats_lang.postiats.jats.translator;

  import org.ats_lang.postiats.jats.tree.*;
  import org.ats_lang.postiats.jats.type.*;
  import org.ats_lang.postiats.jats.value.*;
  import org.ats_lang.postiats.jats.*;
  import org.ats_lang.postiats.jats.interpreter.*;
  
  import java.util.Map;
  import java.util.HashMap;
  import java.util.ArrayList;
}

@members {
    private Map<String, ATSType> m_types;
//    private Map<String, FuncDef> m_funcs;
    
    private void defineType(String id, ATSType type) {
        m_types.put(id, type);
    }
}

// START:rules
program [Map<String, ATSType> types]  // , Map<String, FuncDef> funcs]
@init {
  m_types = types;
}
    : ^(PROGRAM (ts+=type_def
                 | func_decl  // omit declaration
                 | func_def
                 | gstat
                 )*
        ) -> classes_st(classes={$ts})
    ;




gstat
    : var_def //    | var_assign {node = $var_assign.node;} no assignment for global variable
    ;

block
    : ^(BLOCK bstat*)
    ;
    
bstat
    : var_def
    | ifstat
    // todo while dowhile
    | atsins
    ;

atsins
    : atsins_load
    | atsins_store
    | atsins_store_arrpsz_asz
    | atsins_store_arrpsz_ptr
    | atsins_store_fltrec_ofs
    | atsins_move
    | atsins_move_void
    | atsins_pmove
    | atsins_move_arrpsz_ptr
    | atsins_update_ptrinc
    | ats_return
    ;

atsins_load
    : ^(ATSINS_LOAD dest=exp cont=exp)
    ;
    
atsins_store
    : ^(ATSINS_STORE dest=exp cont=exp)
    ;
    
atsins_store_arrpsz_asz
    : ^(ATSINS_STORE_ARRPSZ_ASZ ID exp)
    ;
    
atsins_store_arrpsz_ptr
    : ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp)
    ;
    
atsins_store_fltrec_ofs
    : ^(ATSINS_STORE_FLTREC_OFS tmp=ID atstype lab=ID exp)
    ;
    
atsins_move
    : ^(ATSINS_MOVE ID exp)
    ;
    
atsins_move_void
    : ^(ATSINS_MOVE_VOID exp)
    ;
    
atsins_pmove
    : ^(ATSINS_PMOVE tmp=exp atstype val=exp)
    ;
    
atsins_move_arrpsz_ptr
    : ^(ATSINS_MOVE_ARRPSZ_PTR ID exp)
    ;
    
atsins_update_ptrinc
    : ^(ATSINS_UPDATE_PTRINC ID atstype)
    ;

ats_return
    : ^(ATS_RETURN exp?)
    ;
    
ifstat

    : ^(IFSTAT ifStat (elseIfStat)* (elseStat)?)
    ;

ifStat
    : ^(IF exp block)
    ;
      
elseIfStat
    : ^(IF exp block)
    ;

elseStat
    : ^(ELSE block)
    ;

//assignment returns [ATSNode node]
//    : ^(Assign ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
//    ;
    
exp
    : func_call
    | ats_exp
    | atom_exp
    ;

ats_exp
    : ats_cast
    | ats_empty
    | ats_simple_cast
    | ats_sizeof
    | ats_deref
    | ats_ref_arg
    | ats_pmv_ptrof
    | ats_sel_recsin
    | ats_sel_flt_rec
    | ats_sel_arr_ind
    | ats_sel_box_rec
    ;

ats_cast
    : ^(ATSPMV_CASTFN ID atstype exp)
    ;
    
ats_empty
    : ATS_EMPTY
    ;

ats_simple_cast
    : ^(ATSPMV_INT exp)
    | ^(ATSPMV_I0NT exp)
    | ^(ATSPMV_F0LOAT exp)
    | ATSPMV_TRUE
    | ATSPMV_FALSE
    | ^(ATSPMV_CHAR exp)
    | ^(ATSPMV_STRING exp)

    ;

ats_sizeof
    : ^(ATSPMV_SIZEOF atstype)
    ;
    
ats_deref
    : ^(ATS_DEREF (atstype)? exp)
    ;

ats_ref_arg
    : ^(ATSPMV_REFARG0 exp)  // neglect refarg
    | ^(ATSPMV_REFARG1 exp)  // neglect refarg
    ;
   
ats_pmv_ptrof
    : ^(ATSPMV_PTROF ID)
    ;
    
ats_sel_recsin
      : ^(ATS_SEL_RECSIN pmv=ID atstype lab=ID)
      ;
   
ats_sel_flt_rec
    : ^(ATS_SEL_FLT_REC pmv=exp atstype lab=ID)
    ;

ats_sel_arr_ind
    : ^(ATS_SEL_ARR_IND exp atstype ID)
    ;

ats_sel_box_rec
    : ^(ATS_SEL_BOX_REC exp atstype ID)
    ;

atom_exp
    : ID
    | INT
    | FLOAT
    | CHAR
    | STRING
    | Bool
    ;

func_call
    : ^(FUNC_CALL ID explst?)
    ;

explst 
    : ^(EXP_LIST (exp)+)
    ;
    
//var_assign returns [ATSNode node]
//    : ^(ASSIGN ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
//    ;
    
var_def returns [String name, ATSType type]
    : ^(VAR atstype ID) {retval.name = $ID.text;
                         retval.type = $atstype.type;}
    | ^(VAR_VOID atstype ID)
    ;

atstype returns [ATSType type]
    : ^(TYPE prim_type) {retval.type = $prim_type.type;}
    | ^(TYPE name_type) {retval.type = $name_type.type;}
    | ^(TYPE kind_decorator name_type) {retval.type = $name_type.type;}
    ;

name_type returns [ATSType type]
    : ID { retval.type = m_types.get($ID.text); }
    ;
    
kind_decorator
    : TYPE_DEC_TYPE
    | TYPE_DEC_T0YPE
    ;
    
// todo
prim_type returns [ATSType type]
    : TYPE_INT {retval.type = IntType.cType;}
    | TYPE_CHAR {retval.type = CharType.cType;}
    | TYPE_ULINT {retval.type = ULIntType.cType;}  
    | TYPE_BOOL {retval.type = BoolType.cType;}   
    | TYPE_STRING {retval.type = StringType.cType;} 
    | TYPE_FLOAT {retval.type = DoubleType.cType;}  
    | TYPE_PTR  {System.out.println("TYPE_PTR not supported");} // todo
    | TYPE_REF  {System.out.println("TYPE_REF not supported");} // todo
    | TYPE_ARRPTR  {System.out.println("TYPE_ARRPTR not supported");} // todo
    ;


func_decl
    : ^(FUNC_DECL ID func_decorator? atstype paralst?)
    ;

para_decorator
    : PARA_TYPE_REF0
    | PARA_TYPE_REF1
    ;
    
func_decorator
    : GLOBAL
    | STATIC
    ;
    
paralst
    : ^(PARA_LIST (para)+)
    ;

para
    : paratype ID
    ;

paratype
    : ^(PARA_TYPE para_decorator? atstype)
    ;
    
func_def
    : ^(FUNC_DEF ID func_decorator? atstype paralst? block)
    ;

type_def
    : ^(TYPEDEF ID struct_def[$ID.text]) {defineType($ID.text, $struct_def.type);}  // todo move to struct_def
       -> class_st(cls_name={$ID.text}, cls_desc={"line " + $ID.line}, body={$struct_def.st})
    ;

struct_def [String name] returns [ATSType type]
@init{
Map<String, ATSType> structMap = new HashMap<String, ATSType>();
StructType ty = new StructType(name);
retval.type = ty;
}
    : ^(STRUCT (var_def { if ($var_def.name != null) {
                          ty.addMember($var_def.name, $var_def.type);
                          structMap.put($var_def.name, $var_def.type);
                          } 
                        }
                )+
        ) -> body_st(name_type_map={structMap}, size={ATSTypeUtils.calcSize(structMap)}, f_crt_default={"createDefault"})
    ;


