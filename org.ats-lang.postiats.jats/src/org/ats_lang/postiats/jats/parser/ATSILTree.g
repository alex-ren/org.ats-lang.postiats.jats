tree grammar ATSILTree; 

options {
  language = Java;
  tokenVocab = ATSIL;
  ASTLabelType = CommonTree;
}

@header {
  package org.ats_lang.postiats.jats.parser;
}

@members {
}

// START:rules
program
    : ^(PROGRAM (p=program
                 | type_def
                 | func_decl  // omit declaration
                 | func_def
                 | minclude
                 | gstat
                 )*
        )
    ;




gstat
    : var_def //    | var_assign {node = $var_assign.node;} no assignment for global variable
    ;

minclude
    : ^(MACRO_INCLUDE STRING)
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
    
var_def
    : ^(VAR atstype ID)
    | ^(VAR_VOID atstype ID)
    ;

atstype
    : ^(TYPE prim_type)
    | ^(TYPE name_type)
    | ^(TYPE kind_decorator name_type)
    ;

name_type
    : ID
    ;
    
kind_decorator
    : TYPE_DEC_TYPE
    | TYPE_DEC_T0YPE
    ;
    
// todo
prim_type
    : TYPE_INT    
    | TYPE_CHAR   
    | TYPE_ULINT  
    | TYPE_BOOL   
    | TYPE_STRING 
    | TYPE_FLOAT  
    | TYPE_PTR  // todo
    | TYPE_REF  // todo
    | TYPE_ARRPTR  // todo
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
    : ^(TYPEDEF ID struct_def)
    ;

struct_def
    : ^(STRUCT (var_def
                )+
        )
    ;

// Why do I need this?
dd: 'ddd';  



