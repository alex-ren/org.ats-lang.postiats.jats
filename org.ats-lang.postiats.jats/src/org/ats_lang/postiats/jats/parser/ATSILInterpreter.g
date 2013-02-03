tree grammar ATSILInterpreter;

options {
  language = Java;
  tokenVocab = ATSIL;
  ASTLabelType = CommonTree;
}

@header {
  package org.ats_lang.postiats.jats.parser;
  import org.ats_lang.postiats.jats.tree.*;
  import org.ats_lang.postiats.jats.type.*;
  import org.ats_lang.postiats.jats.value.*;
  import org.ats_lang.postiats.jats.*;
  
  import java.util.Map;
  import java.util.HashMap;
  import java.util.ArrayList;
}

@members {
    private static final IntType m_intType = new IntType();
    
    private Map<String, ATSType> m_types = new HashMap<String, ATSType>();
    
    private void defineType(String id, ATSType type) {
        m_types.put(id, type);
    }
}

// START:rules
program returns [ATSNode node]
@init {

  ProgramNode pn = new ProgramNode();
  node = pn;
}
    : ^(PROGRAM (p=program {pn.addProg($p.node);} 
                 | type_def {}
                 | minclude {}
                 | gstat {pn.addStat($gstat.node);}
                 )*
        )
    ;




gstat returns [ATSNode node]
    : func_decl  {node = $func_decl.node;}
    | func_def {node = $func_def.node;}
    | var_def  {node = $var_def.node;}
    | var_assign {node = $var_assign.node;}
    ;

minclude
    : ^(MACRO_INCLUDE STRING)
    ;

block returns [ATSNode node]
@init {
  BlockNode bn = new BlockNode();
  node = bn;
}
    : ^(BLOCK (bstat {bn.addStat($bstat.node);})*)
    ;
    
bstat returns [ATSNode node]
    : var_def  {node = $var_def.node;}
    | ifstat {node = $ifstat.node;}
    // todo while dowhile
    | atsins {node = $atsins.node;}
    ;

atsins returns [ATSNode node]
    : atsins_load
    | atsins_store
    | atsins_store_arrpsz_asz
    | atsins_store_arrpsz_ptr
    | atsins_store_fltrec_ofs
    | atsins_move   
    | atsins_pmove
    | atsins_move_arrpsz_ptr    
    | atsins_update_ptrinc
    | ats_return
    ;

atsins_load returns [ATSNode node]
    : ^(ATSINS_LOAD dest=exp cont=exp) {node = new AtsInsLoad($dest.node, $cont.node);}
    ;
    
atsins_store returns [ATSNode node]
    : ^(ATSINS_STORE dest=exp cont=exp) {node = new AtsInsStore($dest.node, $cont.node);}
    ;
    
atsins_store_arrpsz_asz returns [ATSNode node]
    : ^(ATSINS_STORE_ARRPSZ_ASZ ID exp) {node = new AtsInsStoreArrpszAsz($ID.text, $exp.node);}
    ;
    
atsins_store_arrpsz_ptr returns [ATSNode node]
    : ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp) {node = new AtsInsStoreArrpszPtr($ID.text, $atstype.type, $exp.node);}
    ;
    
atsins_store_fltrec_ofs returns [ATSNode node]
    : ^(ATSINS_STORE_FLTREC_OFS ida=ID atstype idb=ID exp)
      {node = new AtsInsStoreFltrecOfs($ida.text, $atstype.type, $idb.text, $exp.node);}
    ;
    
atsins_move returns [ATSNode node]
    : ^(ATSINS_MOVE ID exp) {node = new AtsInsMove($ID.text, $exp.node);}
    ;
    
atsins_pmove returns [ATSNode node]
    : ^(ATSINS_PMOVE ID atstype exp) {node = new AtsInsPMove($ID.text, $atstype.type, $exp.node);}
    ;
    
atsins_move_arrpsz_ptr returns [ATSNode node]
    : ^(ATSINS_MOVE_ARRPSZ_PTR ID exp) {node = new AtsInsMoveArrpszPtr($ID.text, $exp.node);}
    ;
    
atsins_update_ptrinc returns [ATSNode node]
    : ^(ATSINS_UPDATE_PTRINC ID atstype) {node = new AtsInsUpdatePtrInc($ID.text, $atstype.type);} 
    ;
    
ats_return returns [ATSNode node]
    : ^(ATS_RETURN exp?) {node = new AtsReturnNode($exp.node);}
    ;
    
ifstat returns [ATSNode node]
@init {
  IfNode ifnode = new IfNode();
}
@after {
  node = ifnode;
}
    : ^(IF ifStat[ifnode] (elseIfStat[ifnode])* (elseStat[ifnode])?)
    ;

ifStat [IfNode parent]
    : ^(IF exp block) {parent.addIf($exp.node, $block.node);}
    ;
      
elseIfStat [IfNode parent]
    : ^(IF exp block) {parent.addIf($exp.node, $block.node);}
    ;

elseStat [IfNode parent]
    : ^(ELSE block)  {parent.addElse($block.node);}
    ;

assignment returns [ATSNode node]
    : ^(ASSIGNMENT ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
    ;
    
exp returns [ATSNode node]
    : func_call {node = $func_call.node;}
    | ID  {node = new IdentifierNode($ID.text);}
    | INT  {node = new ValueNode(new IntValue($INT.text));}
    ;

func_call returns [ATSNode node]
    : ^(FUNC_CALL ID explst?) {node = new FuncCallNode($ID.text, $explst.lst);}
    ;

explst returns [List<ATSNode> lst]
@init {
  List<ATSNode> es = new ArrayList<ATSNode>();
  lst = es;
}
    : ^(EXP_LIST (exp {es.add($exp.node);})+)
    ;
    
var_assign returns [ATSNode node]
    : ^(ASSIGN ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
    ;
    
var_def returns [ATSNode node]
    : ^(VAR atstype ID) {node = new DefinitionNode($atstype.type, $ID.text);}
    ;

atstype returns [ATSType type]
    : ^(TYPE prim_type) {type = $prim_type.type;}
    | ^(TYPE name_type)  {type = $name_type.type;}
    | ^(TYPE kind_decorator name_type) {type = new KindType($kind_decorator.kind, $name_type.type);}
    ;

name_type returns [ATSType type]
    : ID {type = m_types.get($ID.text);}
    ;
    
kind_decorator returns [ATSType.Decorator kind]
    : TYPE_DEC_TYPE {kind = ATSType.Decorator.TYPE;}
    | TYPE_DEC_T0YPE {kind = ATSType.Decorator.T0YPE;}
    ;
    
// todo
prim_type returns [ATSType type]
    : TYPE_INT {type = m_intType;}
    | TYPE_CHAR
    | TYPE_ULINT
    | TYPE_BOOL
    | TYPE_STRING
    | TYPE_FLOAT
    | TYPE_PTR
    | TYPE_REF
    | TYPE_ARRPTR
    ;


func_decl returns [ATSNode node]
    : ^(FUNC_DECL ID fun_decorator? argtype arglst?) {node = new FuncNode ($ID.text, $fun_decorator.dec, $argtype.type, $arglst.arglst, null);}
    ;

argtype returns [ATSType type]
    : ^(ARGTYPE arg_decorator? atstype) {type = new ArgType($arg_decorator.dec, $atstype.type);}
    ;

arg_decorator returns [ATSType.ArgDecorator dec]
    : ARGTYPE_REF0 {dec = ATSType.ArgDecorator.REF0;}
    | ARGTYPE_REF1 {dec = ATSType.ArgDecorator.REF1;}
    ;
    
fun_decorator returns [ATSNode.FunDecorator dec]
    : GLOBAL {dec = ATSNode.FunDecorator.GLOBAL;}
    | STATIC {dec = ATSNode.FunDecorator.STATIC;}
    ;
    
arglst returns [List<FuncPara> arglst]
@init {
  arglst = new ArrayList<FuncPara>();
}
    : ^(ARG_LIST (arg {arglst.add($arg.arg);})+)
    ;

arg returns [FuncPara arg]
    : atstype ID {arg = new FuncPara($atstype.type, $ID.text);}
    ;
    
func_def returns [ATSNode node]
    : ^(FUNC_DEF ID fun_decorator? argtype arglst? block) {node = new FuncNode ($ID.text, $fun_decorator.dec, $argtype.type, $arglst.arglst, $block.node);}
    ;

type_def
    : ^(TYPEDEF ID struct_def) {m_types.put($ID.text, $struct_def.type);}
    ;

struct_def returns [StructType type]
@init {
  StructType ty = new StructType();
  type = ty;
}
    : ^(STRUCT (var_def { DefinitionNode tynode = (DefinitionNode)$var_def.node;
                          ty.addMember(tynode.getID(), tynode.getType());
                         }
                )+
        )
    ;

///* ******* ******* */
//type_struct  // struct atstype_struct ; /* of indefinite size */
//
//void_t0ype   // typedef void atsvoid_t0ype ;
//
//type_int     // typedef int atstype_int ;
//type_uint    // typedef unsigned int atstype_uint ;
//
//type_lint    // typedef long int atstype_lint ;
//type_ulint   // typedef unsigned long int atstype_ulint ;
//
//type_llint   // typedef long long int atstype_llint ;
//type_ullint  // typedef unsigned long long int atstype_ullint ;
//
//type_sint    // typedef short int atstype_sint ;
//type_usint   // typedef unsigned short int atstype_usint ;
//
//type_ssize   // typedef atstype_lint atstype_ssize ;
//type_size    // typedef atstype_ulint atstype_size ;
//
//type_bool    // typedef int atstype_bool ; // true/false: 1/0
//
//type_char    // typedef char atstype_char ;
//type_schar   // typedef signed char atstype_schar ;
//type_uchar   // typedef unsigned char atstype_uchar ;
//
//type_string  // typedef char *atstype_string ;
//
//type_float   // typedef float atstype_float ;
//type_double  // typedef double atstype_double ;
//type_ldouble // typedef long double atstype_ldouble ;
//
//type_ptr     // typedef void *atstype_ptr ;
//type_ptrk    // typedef void *atstype_ptrk ;
//
//type_ref     // typedef void *atstype_ref ;
//
//type_arrptr  // typedef void* atstype_arrptr ;
//
//type_arrpsz

//typedef
//struct {
//  atstype_arrptr ptr ; atstype_size size ;
//} atstype_arrpsz ;


// #define atstkind_type(tk) tk
// #define atstkind_t0ype(tk) tk

// #define atsrefarg0_type(hit) hit
// #define atsrefarg1_type(hit) hit*


// -- example for tuple
// generate a struct for each tuple type
//typedef
//struct {
//atstkind_t0ype(atstype_int) atslab$0; 
//atstkind_t0ype(atstype_int) atslab$1; 
//} postiats_tyrec_0 ;


 
// todo: Doesn't make sense that I cannot remove this rule.
