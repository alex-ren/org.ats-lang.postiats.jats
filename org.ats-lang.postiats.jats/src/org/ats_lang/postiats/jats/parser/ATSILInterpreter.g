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
    : atsins_load {node = $atsins_load.node;}
    | atsins_store {node = $atsins_store.node;}
    | atsins_store_arrpsz_asz {node = $atsins_store_arrpsz_asz.node;}
    | atsins_store_arrpsz_ptr {node = $atsins_store_arrpsz_ptr.node;}
    | atsins_store_fltrec_ofs {node = $atsins_store_fltrec_ofs.node;}
    | atsins_move {node = $atsins_move.node;}
    | atsins_pmove {node = $atsins_pmove.node;}
    | atsins_move_arrpsz_ptr {node = $atsins_move_arrpsz_ptr.node;}
    | atsins_update_ptrinc {node = $atsins_update_ptrinc.node;}
    | ats_return {node = $ats_return.node;}
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
    : ^(IFSTAT ifStat[ifnode] (elseIfStat[ifnode])* (elseStat[ifnode])?)
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
    | ats_exp {node = $ats_exp.node;}
    | atom_exp {node = $atom_exp.node;}
    ;

ats_exp returns [ATSNode node]
    : ats_cast {node = $ats_cast.node;}
    | ats_simple_cast {node = $ats_simple_cast.node;}
    | ats_sizeof {node = $ats_sizeof.node;}
    | ats_deref {node = $ats_deref.node;}
    | ats_ref_arg {node = $ats_ref_arg.node;}
    | ats_pmv_ptrof {node = $ats_pmv_ptrof.node;}
    | ats_sel_recsin {node = $ats_sel_recsin.node;}
    | ats_sel_flt_rec {node = $ats_sel_flt_rec.node;}
    | ats_sel_arr_ind {node = $ats_sel_arr_ind.node;}
    | ats_sel_box_rec {node = $ats_sel_box_rec.node;}
    ;

ats_cast returns [ATSNode node]
    : ^(ATSPMV_CASTFN ID atstype exp) {node = new AtsPmvCastFn($ID.text, $atstype.type, $exp.node);}
    ;

ats_simple_cast returns [ATSNode node]
    : ^(ATSPMV_INT exp)  {node = new AtsPmvSimpleCastNode(IntType.cType, $exp.node);}
    | ATSPMV_TRUE {node = new ValueNode(BoolType.createTrue());}
    | ATSPMV_FALSE {node = new ValueNode(BoolType.createFalse());}
    | ^(ATSPMV_CHAR exp) {node = new AtsPmvSimpleCastNode(CharType.cType, $exp.node);}
    | ^(ATSPMV_STRING exp) {node = new AtsPmvSimpleCastNode(StringType.cType, $exp.node);}
    | ^(ATSPMV_I0NT exp) {node = new AtsPmvSimpleCastNode(I0ntType.cType, $exp.node);}
    | ^(ATSPMV_F0LOAT exp) {node = new AtsPmvSimpleCastNode(F0loatType.cType, $exp.node);}
    ;

ats_sizeof returns [ATSNode node]
    : ^(ATSPMV_SIZEOF atstype) {node = new AtsPmvSizeofNode($atstype.type);}
    ;
    
ats_deref returns [ATSNode node]
    : ^(ATS_DEREF atstype exp) {node = new AtsDerefNode($atstype.type, $exp.node);}
    ;

ats_ref_arg returns [ATSNode node]
    : ^(ATSPMV_REFARG0 exp) {node = new AtsPmvRefArg(ATSType.ParaDecorator.REF0, $exp.node);}
    | ^(ATSPMV_REFARG1 exp) {node = new AtsPmvRefArg(ATSType.ParaDecorator.REF1, $exp.node);}
    ;
   
ats_pmv_ptrof returns [ATSNode node]
    : ^(ATSPMV_PTROF ID) {node = new AtsPmvPtrofNode($ID.text);}
    ;
    
ats_sel_recsin returns [ATSNode node]
      : ^(ATS_SEL_RECSIN pmv=ID atstype lab=ID) {node = new AtsSelRecsinNode($pmv.text, $atstype.type, $lab.text);}
      ;
   
ats_sel_flt_rec returns [ATSNode node]
    : ^(ATS_SEL_FLT_REC pmv=exp atstype lab=ID) {node = new AtsSelFltRecNode($pmv.node, $atstype.type, $lab.text);}
    ;

ats_sel_arr_ind returns [ATSNode node]
    : ^(ATS_SEL_ARR_IND exp atstype ID) {node = new AtsSelArrIndNode($exp.node, $atstype.type, $ID.text);}
    ;

ats_sel_box_rec returns [ATSNode node]
    : ^(ATS_SEL_BOX_REC exp atstype ID) {node = new AtsSelBoxRecNode($exp.node, $atstype.type, $ID.text);}
    ;

atom_exp returns [ATSNode node]
    : ID     {node = new IdentifierNode($ID.text);}
    | INT    {node = new ValueNode(IntType.fromString($INT.text));}
    | FLOAT  {node = new ValueNode(FloatType.fromString($FLOAT.text));}
    | CHAR   {node = new ValueNode(CharType.fromString($CHAR.text));}
    | STRING {node = new ValueNode(StringType.fromString($STRING.text));} 
    | Bool   {node = new ValueNode(BoolType.fromString($Bool.text));}
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
    : TYPE_INT    {type = IntType.cType;}
    | TYPE_CHAR   {type = CharType.cType;}
    | TYPE_ULINT  {type = ULIntType.cType;}
    | TYPE_BOOL   {type = BoolType.cType;}
    | TYPE_STRING {type = StringType.cType;}
    | TYPE_FLOAT  {type = FloatType.cType;}
    | TYPE_PTR  // todo
    | TYPE_REF  // todo
    | TYPE_ARRPTR  // todo
    ;


func_decl returns [ATSNode node]
    : ^(FUNC_DECL ID func_decorator? atstype paralst?) {node = new FuncNode ($ID.text, $func_decorator.dec, $atstype.type, $paralst.paralst, null);}
    ;

para_decorator returns [ATSType.ParaDecorator dec]
    : PARA_TYPE_REF0 {dec = ATSType.ParaDecorator.REF0;}
    | PARA_TYPE_REF1 {dec = ATSType.ParaDecorator.REF1;}
    ;
    
func_decorator returns [ATSNode.FunDecorator dec]
    : GLOBAL {dec = ATSNode.FunDecorator.GLOBAL;}
    | STATIC {dec = ATSNode.FunDecorator.STATIC;}
    ;
    
paralst returns [List<FuncPara> paralst]
@init {
  paralst = new ArrayList<FuncPara>();
}
    : ^(PARA_LIST (para {paralst.add($para.para);})+)
    ;

para returns [FuncPara para]
    : paratype ID {para = new FuncPara($paratype.type, $ID.text);}
    ;

paratype returns [ATSType type]
    : ^(PARA_TYPE para_decorator? atstype) {type = new ParaType($para_decorator.dec, $atstype.type);}
    ;
    
func_def returns [ATSNode node]
    : ^(FUNC_DEF ID func_decorator? atstype paralst? block) {node = new FuncNode ($ID.text, $func_decorator.dec, $atstype.type, $paralst.paralst, $block.node);}
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

// -- example for tuple
// generate a struct for each tuple type
//typedef
//struct {
//atstkind_t0ype(atstype_int) atslab$0; 
//atstkind_t0ype(atstype_int) atslab$1; 
//} postiats_tyrec_0 ;

