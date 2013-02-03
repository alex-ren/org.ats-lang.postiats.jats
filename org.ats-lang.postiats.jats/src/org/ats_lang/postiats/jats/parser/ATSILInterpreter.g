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


 
// todo: Doesn't make sense that I cannot remove this rule.
zz: ^(RR 'zz');

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
    | assignment {node = $assignment.node;}
    | ifstat {node = $ifstat.node;}
    | returnstat {node = $returnstat.node;}
    ;

returnstat returns [ATSNode node]
    :  RETURN exp {node = new ReturnNode($exp.node);}
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
    : ^(EXP exp block) {parent.addIf($exp.node, $block.node);}
    ;
      
elseIfStat [IfNode parent]
    :  ^(EXP exp block) {parent.addIf($exp.node, $block.node);}
    ;

elseStat [IfNode parent]
    : ^(EXP block)  {parent.addElse($block.node);}
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
    : ^(VAR type ID) {node = new DefinitionNode($type.type, $ID.text);}
    ;

type returns [ATSType type]
    : prim_type {type = $prim_type.type;}
    | name_type {type = $name_type.type;}
    ;

name_type returns [ATSType type]
    : ID {type = m_types.get($ID.text);}
    ;
    
// todo
prim_type returns [ATSType type]
    : type_int {type = m_intType;}
    | type_char
    | type_ulint
    | type_bool
    | type_string
    | type_float
    | type_ptr
    | type_ref
    | type_arrptr
    ;

func_decl returns [ATSNode node]
    : ^(FUNC_DECL ID fun_decorator? type arglst?) {node = new FuncNode ($ID.text, $fun_decorator.dec, $type.type, $arglst.arglst, null);}
    ;

fun_decorator returns [String dec]
    : GLOBAL {dec = "global";}
    | STATIC {dec = "static";}
    ;
    
arglst returns [List<FuncPara> arglst]
@init {
  arglst = new ArrayList<FuncPara>();
}
    : ^(ARG_LIST (arg {arglst.add($arg.arg);})+)
    ;

arg returns [FuncPara arg]
    : type ID {arg = new FuncPara($type.type, $ID.text);}
    ;
    
func_def returns [ATSNode node]
    : ^(FUNC_DEF ID fun_decorator? type arglst? block) {node = new FuncNode ($ID.text, $fun_decorator.dec, $type.type, $arglst.arglst, $block.node);}
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

type_int  : 'int';
type_char : 'char';
type_ulint: 'unsigned long int';
type_bool : 'bool';
type_string:'string';
type_float: 'float';
type_ptr  : 'ptr';
type_ref  : 'ref';
type_arrptr: 'arrptr';

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
