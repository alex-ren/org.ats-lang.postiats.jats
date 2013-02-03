grammar ATSIL;

options {
  language = Java;
  output = AST;
}


tokens {
  VAR = 'var';
  PROGRAM;
  BLOCK;
  
  FUNC_DECL;
  FUNC_DEF;
//  FUNC_CALL;
//  EXP_LIST;
  ARG_LIST;

  GLOBAL;
  STATIC;
  TYPEDEF;
  STRUCT;

  TYPE;
  TYPE_INT;
  TYPE_CHAR;
  TYPE_ULINT;
  TYPE_BOOL;
  TYPE_STRING;
  TYPE_FLOAT;
  TYPE_PTR;
  TYPE_REF;
  TYPE_ARRPTR;
  
  TYPE_DEC_TYPE;
  TYPE_DEC_T0YPE;
  
  ARGTYPE;
  ARGTYPE_REF0;
  ARGTYPE_REF1;
  
  IFSTAT;
  IF;
  ELSE;
  WHILE;
  DOWHILE;
  GOTOTAG;
  
  ATSINS_LOAD;
  ATSINS_STORE;
  ATSINS_STORE_ARRPSZ_ASZ;
  ATSINS_STORE_ARRPSZ_PTR;
  ATSINS_STORE_FLTREC_OFS;
  ATSINS_MOVE;
  ATSINS_PMOVE;
  ATSINS_MOVE_ARRPSZ_PTR;
  ATSINS_UPDATE_PTRINC;
  ATS_RETURN;
}

@header {
  package org.ats_lang.postiats.jats.parser;
}

@lexer::header {
  package org.ats_lang.postiats.jats.parser;
}


rule: program
    ;
    
program
    : stat_macro* -> ^(PROGRAM stat_macro*)
    ;
    
stat_macro
    : statement
    | macro_area
    ;
    
macro_area
    : MACRO_IFNDEF! ID! program MACRO_ENDIF!
    | MACRO_INCLUDE^ STRING
    ;

statement
    : tmpdec Semicol
    | fun_decorator? atstype ID LParen paralist? RParen
        (Semicol -> ^(FUNC_DECL ID fun_decorator atstype paralist)
        | LBrace block RBrace -> ^(FUNC_DEF ID fun_decorator atstype paralist block)
        )
    | typedef
    ;

typedef
    : Typedef structure ID Semicol -> ^(TYPEDEF ID structure)
    ;

structure
    : Struct LBrace (atstype ID Semicol)+ RBrace -> ^(STRUCT ^(VAR atstype ID)+)
    ;
    
tmpdec
    : atstmpdec
    | atstmpdec_void
    ;
    
block
    : bstat* -> ^(BLOCK bstat*)
    ;

bstat
    : tmpdec Semicol

    | ifstat
    | whilestat
    | dowhilestat
    | gototag
    
    | atsins_load Semicol
    | atsins_store Semicol
    | atsins_store_arrpsz_asz Semicol
    | atsins_store_arrpsz_ptr Semicol
    | atsins_store_fltrec_ofs Semicol
    | atsins_move Semicol
    | atsins_move_void Semicol       
    | atsins_pmove Semicol
    | atsins_move_arrpsz_ptr Semicol    
    | atsins_update_ptrinc Semicol
    | ats_return Semicol
    | ats_return_void Semicol
//    | Return exp Semicol
    ;
    
atsins_load
    : 'ATSINSload' LParen exp Comma exp RParen -> ^(ATSINS_LOAD exp exp)
    ;

atsins_store
    : 'ATSINSstore' LParen exp Comma exp RParen -> ^(ATSINS_STORE exp exp)
    ;
    
atsins_store_arrpsz_asz
    : 'ATSINSstore_arrpsz_asz' LParen ID Comma exp RParen -> ^(ATSINS_STORE_ARRPSZ_ASZ ID exp)
    ;
    
atsins_store_arrpsz_ptr
    : 'ATSINSstore_arrpsz_ptr' LParen ID Comma atstype Comma exp RParen -> ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp)
    ;

atsins_store_fltrec_ofs
    : 'ATSINSstore_fltrec_ofs' LParen ida=ID Comma atstype Comma idb=ID Comma exp RParen
       -> ^(ATSINS_STORE_FLTREC_OFS $ida atstype $idb exp)
    ;
    
atsins_move
    : 'ATSINSmove' LParen ID Comma exp RParen -> ^(ATSINS_MOVE ID exp)
    ;

atsins_move_void:
    'ATSINSmove_void' LParen ID Comma exp RParen -> ^(ATSINS_MOVE ID exp)
    ;
    
atsins_pmove
    : 'ATSINSpmove' LParen ID Comma atstype Comma exp RParen -> ^(ATSINS_PMOVE ID atstype exp)
    ;   
     
atsins_move_arrpsz_ptr
    : 'ATSINSmove_arrpsz_ptr' LParen ID Comma exp RParen -> ^(ATSINS_MOVE_ARRPSZ_PTR ID exp)
    ;
    
atsins_update_ptrinc
    : 'ATSINSupdate_ptrinc' LParen ID Comma atstype RParen -> ^(ATSINS_UPDATE_PTRINC ID atstype)
    ;
    
ats_return
    : 'ATSreturn' LParen exp RParen -> ^(ATS_RETURN exp)
    ;

ats_return_void
    : 'ATSreturn_void' LParen exp RParen -> ^(ATS_RETURN)
    ;

ifstat
    : ('ATSif' /*| 'ATSifnot'*/) LParen exp RParen 'ATSthen' LParen RParen LBrace thenb=block RBrace ('ATSelse' LParen RParen LBrace elseb=block RBrace)?
      -> ^(IFSTAT ^(IF exp $thenb) ^(ELSE $elseb))
    ;
    
whilestat
    : 'ATSwhile' LParen exp RParen LBrace block RBrace -> ^(WHILE exp block)
    ;
    
dowhilestat
    : 'ATSdo' LParen RParen LBrace block RBrace 'ATSwhile' LParen exp RParen -> ^(DOWHILE block exp)
    ;
    
gototag
    : ID Colon -> ^(GOTOTAG ID)
    ;
        
exp : fun_call
    // ats spec
    | ats_cast
    | ats_simple_cast
    | ats_sizeof
    | ats_deref
    | ats_ref_arg
    | ats_pmv_ptrof
    | ats_sel_recsin
    | ats_sel_flt_rec
    | ats_sel_arr_ind
    | ats_sel_box_rec
    | atom_exp
    ;

ats_sel_flt_rec
    : 'ATSselfltrec' LParen exp Comma atstype Comma ID RParen
    ;

ats_sel_arr_ind
    : 'ATSselarrind' LParen exp Comma atstype Comma ID RParen
    ;

ats_sel_box_rec
    : 'ATSselboxrec' LParen exp Comma atstype Comma ID RParen
    ;
    
ats_sel_recsin
      : 'ATSselrecsin' LParen ID Comma atstype Comma ID RParen
      ;
      
ats_pmv_ptrof
    : 'ATSPMVptrof' LParen ID RParen
    ;

ats_simple_cast
    : 'ATSPMVint' LParen exp RParen 
    | 'ATSPMVbool_true' LParen RParen
    | 'ATSPMVbool_false' LParen RParen
    | 'ATSPMVchar' LParen exp RParen
    | 'ATSPMVstring' LParen exp RParen
    | 'ATSPMVi0nt' LParen exp RParen
    | 'ATSPMVf0loat' LParen exp RParen
    ;    

atom_exp
    : INT
    | FLOAT
    | CHAR
    | STRING
    | ID
    | Bool
    | LParen exp RParen
    ;

ats_ref_arg
    : 'ATSPMVrefarg0' LParen exp RParen
    | 'ATSPMVrefarg1' LParen exp RParen
    ;
    
ats_deref
    : 'ATSderef2' LParen exp Comma atstype RParen
    ;
    
ats_sizeof
    : 'ATSPMVsizeof' LParen atstype RParen
    ;
    
ats_cast
    : 'ATSPMVcastfn' LParen ID Comma atstype Comma exp RParen
    ;
    
    
fun_call
    : ID LParen arglist? RParen
    ;
    
arglist
    : exp (Comma exp)*
    ;

    
//fun_header
//    : fun_decorator? atstype ID LParen paralist? RParen
//    ;

paralist
    : para (Comma para)* -> ^(ARG_LIST para+)
    ;
    
para : argtype ID;

argtype
    : atstype -> ^(ARGTYPE atstype)
    | argdecorator LParen atstype RParen -> ^(ARGTYPE argdecorator atstype)
    ;
    
argdecorator
    : 'atsrefarg0_type' -> ARGTYPE_REF0
    | 'atsrefarg1_type' -> ARGTYPE_REF1
    ;

fun_decorator
    : 'ATSstaticdec()' -> ^(GLOBAL)  // same effect as GLOBAL
    | 'ATSglobaldec()' -> STATIC
    ;
    
atstmpdec_void
    : 'ATStmpdec_void' LParen ID Comma atstype RParen -> ^(VAR atstype ID)
    ;
    
atstmpdec:
    'ATStmpdec' LParen ID Comma atstype RParen -> ^(VAR atstype ID)
    ;

atstype
    : prim_type -> ^(TYPE prim_type)
    | ID -> ^(TYPE ID)
    | kind_decorator LParen ID RParen -> ^(TYPE kind_decorator ID)
    ;

prim_type
    : type_int -> TYPE_INT
    | type_char -> TYPE_CHAR
    | type_ulint -> TYPE_ULINT
    | type_bool -> TYPE_BOOL
    | type_string -> TYPE_STRING
    | type_float -> TYPE_FLOAT
    | type_ptr -> TYPE_PTR
    | type_ref -> TYPE_REF
    | type_arrptr -> TYPE_ARRPTR
    ;

type_int    : 'int';
type_char   : 'char';
type_ulint  : 'unsigned' 'long' 'int';
type_bool   : 'bool';
type_string : 'string';
type_float  : 'float';
type_ptr    : 'ptr';
type_ref    : 'ref';
type_arrptr : 'arrptr';


kind_decorator
    : 'atstkind_type' -> TYPE_DEC_TYPE
    | 'atstkind_t0ype' -> TYPE_DEC_T0YPE
    ;

MACRO_ENDIF:  '#endif';
MACRO_IFNDEF: '#ifndef';
MACRO_IF:     '#if';
MACRO_INCLUDE:'#include';

Return  : 'return';
Typedef : 'typedef';
Struct  : 'struct';

Semicol   : ';';
Colon     : ':';
 
Or        : '||';  
And       : '&&';  
Equals    : '==';  
NEquals   : '!=';  
GTEquals  : '>=';  
LTEquals  : '<=';  
Pow       : '^';  
Excl      : '!';  
GT        : '>';  
LT        : '<';  
Add       : '+';  
Subtract  : '-';  
Multiply  : '*';   
Divide    : '/';  
Modulus   : '%';  
LBrace    : '{';  
RBrace    : '}';  
LBracket  : '[';  
RBracket  : ']';  
LParen    : '(';  
RParen    : ')';  
Assign    : '=';  
Comma     : ',';  
QMark     : '?';  
  
Bool  
  :  'true'   
  |  'false'   
  ;  
    
ID  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_' | '$')*
    ;

INT : '0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;
    
COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    |   '#if(0)' ( options {greedy=false;} : . )* '#endif' {$channel=HIDDEN;}  // todo
    |   '#define' ( options {greedy=false;} : . )* ('\n') {$channel=HIDDEN;}  // todo
    ;

WS  : ( ' '
      | '\t'
      | '\r'
      | '\n'
      ) {$channel=HIDDEN;}
      ;

STRING
    // :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    : '"' ('0'..'9' | 'a'..'z' | 'A'..'Z' |
           '`' | '~' | '!' | '@' | '#' | '$' | '%' | '^' | '&' | '*' | '(' | ')' | '-' |
            '_' | '=' | '+' | '[' | '{' | ']' | '}' | '|' | ';' | ':' | ',' | '<' | '.' |
            '/' | '?' | ' ' | ESC_SEQ  
           )+ '"' { System.out.println(getText()); }
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;


fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
