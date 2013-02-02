grammar ATSIL;

options {
  language = Java;
 // output = AST;
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
    : (statement | macro_area)+
    ;
    
macro_area
    : MACRO_IFNDEF ID program MACRO_ENDIF
    | MACRO_INCLUDE STRING
    ;

statement
    : tmpdec Semicol
    | fun_header Semicol
    | fun_header LBrace block RBrace
    | typedef
    ;

typedef
    : Typedef Struct LBrace struct_item_list? RBrace ID Semicol
    ;

struct_item_list
    : atstype ID Semicol (atstype ID Semicol)*
    ;
    
tmpdec
    : atstmpdec
    | atstmpdec_void
    ;
    
block
    : bstat*
    ;

bstat
    : tmpdec Semicol
    | atsins_load Semicol
    | atsins_store Semicol
    | atsins_store_arrpsz_asz Semicol
    | atsins_store_arrpsz_ptr Semicol
    | atsins_store_fltrec_ofs Semicol
    | atsins_move_arrpsz_ptr Semicol
    | atsins_update_ptrinc Semicol
    | atsins_move Semicol
    | atsins_pmove Semicol
    | atsins_move_void Semicol
    | ats_return Semicol
    | ats_return_void Semicol
    | Return exp Semicol
    | ifstat
    | whilestat
    | dowhilestat
    | gototag
    ;

atsins_store
    : 'ATSINSstore' LParen exp Comma exp RParen
    ;



atsins_store_fltrec_ofs
    : 'ATSINSstore_fltrec_ofs' LParen ID Comma atstype Comma ID Comma exp RParen
    ;
    
whilestat
    : 'ATSwhile' LParen exp RParen LBrace block RBrace
    ;
    
dowhilestat
    : 'ATSdo' LParen RParen LBrace block RBrace 'ATSwhile' LParen exp RParen
    ;

ifstat
    : ('ATSif' | 'ATSifnot') LParen exp RParen 'ATSthen' LParen RParen LBrace block RBrace ('ATSelse' LParen RParen LBrace block RBrace)?
    ;
    
atsins_move_void:
    'ATSINSmove_void' LParen ID Comma exp RParen
    ;
    
atsins_update_ptrinc
    : 'ATSINSupdate_ptrinc' LParen ID Comma atstype RParen
    ;
    
atsins_move_arrpsz_ptr
    : 'ATSINSmove_arrpsz_ptr' LParen ID Comma exp RParen
    ;
    
atsins_store_arrpsz_ptr
    : 'ATSINSstore_arrpsz_ptr' LParen ID Comma atstype Comma exp RParen
    ;
    
atsins_store_arrpsz_asz
    : 'ATSINSstore_arrpsz_asz' LParen ID Comma exp RParen
    ;
    
ats_return
    : 'ATSreturn' LParen exp RParen
    ;

ats_return_void
    : 'ATSreturn_void' LParen exp RParen
    ;
    
atsins_load
    : 'ATSINSload' LParen exp Comma exp RParen
    ;
    
atsins_move
    : 'ATSINSmove' LParen ID Comma exp RParen
    ;

atsins_pmove
    : 'ATSINSpmove' LParen ID Comma atstype Comma exp RParen
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
    
gototag
    : ID Colon
    ;
    
    
fun_header
    : fun_decorator? atstype ID LParen paralist? RParen
    ;

paralist
    : para (Comma para)*
    ;
    
para : argtype ID;

argtype
    : atstype
    | argdecorator LParen atstype RParen
    ;
    
argdecorator
    : 'atsrefarg0_type'
    | 'atsrefarg1_type'
    ;

fun_decorator
    : 'ATSstaticdec()'
    | 'ATSglobaldec()'
    ;
    
atstmpdec_void
    : 'ATStmpdec_void' LParen ID Comma atstype RParen
    ;
    
atstmpdec:
    'ATStmpdec' LParen ID Comma atstype RParen
    ;

atstype
    : ID
    | kind_decorator LParen ID RParen  // todo
    ; 
    
kind_decorator
    : 'atstkind_type'
    | 'atstkind_t0ype'
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
