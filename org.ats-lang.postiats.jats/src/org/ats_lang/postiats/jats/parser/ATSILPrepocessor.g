grammar ATSILPrepocessor;

options {
  language = Java;
  output = template;
  rewrite = true;
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
    : stat_macro*
    ;
    
stat_macro
    : gstat
    | macro_area
    ;

macro_area
    : macro_ifndef_id program macro_endif
    | MACRO_INCLUDE STRING -> template(macro_inc={$MACRO_INCLUDE.text}, name={$STRING.text}) "// <macro_inc> <name>"
    ;

macro_ifndef_id: MACRO_IFNDEF ID -> template (macro_ifndef={$MACRO_IFNDEF.text}, id={$ID.text}) "// <macro_ifndef> <id>";
macro_endif: MACRO_ENDIF -> template (macro_endif={$MACRO_ENDIF.text}) "// <macro_endif>";

gstat
    : tmpdec Semicol
    | func_decorator? rettype=atstype ID LParen paralst? RParen
        (Semicol
        | LBrace block RBrace
        )
    | typedef
    ;

typedef
    : Typedef structure ID Semicol
    ;

structure
    : Struct LBrace (atstype ID Semicol)+ RBrace
    ;
    
tmpdec
    : atstmpdec
    | atstmpdec_void
    ;
    
block
    : (bstat)*
    ;

bstat
    : tmpdec Semicol

    | ifstat
    | whilestat
    | dowhilestat
    
    | gototag  // Don't handle goto now.
    
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
    : 'ATSINSload' LParen exp Comma exp RParen
    ;

atsins_store
    : 'ATSINSstore' LParen exp Comma exp RParen
    ;
    
atsins_store_arrpsz_asz
    : 'ATSINSstore_arrpsz_asz' LParen ID Comma exp RParen
    ;
    
atsins_store_arrpsz_ptr
    : 'ATSINSstore_arrpsz_ptr' LParen ID Comma atstype Comma exp RParen
    ;

atsins_store_fltrec_ofs
    : 'ATSINSstore_fltrec_ofs' LParen ida=ID Comma atstype Comma idb=ID Comma exp RParen
    ;
    
atsins_move
    : 'ATSINSmove' LParen ID Comma exp RParen
    ;

atsins_move_void
    : 'ATSINSmove_void' LParen ID Comma exp RParen
    ;
    
atsins_pmove
    : 'ATSINSpmove' LParen ID Comma atstype Comma exp RParen
    ;   
     
atsins_move_arrpsz_ptr
    : 'ATSINSmove_arrpsz_ptr' LParen ID Comma exp RParen
    ;
    
atsins_update_ptrinc
    : 'ATSINSupdate_ptrinc' LParen ID Comma atstype RParen
    ;
    
ats_return
    : 'ATSreturn' LParen exp RParen
    ;

ats_return_void
    : 'ATSreturn_void' LParen exp RParen
    ;

ifstat
    : ('ATSif' /*| 'ATSifnot'*/) LParen exp RParen 'ATSthen' LParen RParen LBrace thenb=block RBrace ('ATSelse' LParen RParen LBrace elseb=block RBrace)?
    ;
    
whilestat
    : 'ATSwhile' LParen exp RParen LBrace block RBrace
    ;
    
dowhilestat
    : 'ATSdo' LParen RParen LBrace block RBrace 'ATSwhile' LParen exp RParen
    ;
    
gototag
    : ID Colon
    ;
        
exp 
    : func_call
    
    | ats_empty
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
    
ats_empty
    : 'ATSempty' LParen RParen
    ;
    
ats_cast
    : 'ATSPMVcastfn' LParen ID Comma atstype Comma exp RParen
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

ats_sizeof
    : 'ATSPMVsizeof' LParen atstype RParen
    ;
    
ats_deref
    : 'ATSderef2' LParen exp Comma atstype RParen
    | 'ATSderef' LParen exp RParen
    ;

ats_ref_arg
    : 'ATSPMVrefarg0' LParen exp RParen
    | 'ATSPMVrefarg1' LParen exp RParen
    ;
   
ats_pmv_ptrof
    : 'ATSPMVptrof' LParen ID RParen
    ;
    
ats_sel_recsin
      : 'ATSselrecsin' LParen pmv=ID Comma atstype Comma lab=ID RParen
      ;
   
ats_sel_flt_rec
    : 'ATSselfltrec' LParen pmv=exp Comma atstype Comma lab=ID RParen
    ;

ats_sel_arr_ind
    : 'ATSselarrind' LParen exp Comma atstype Comma ID RParen 
    ;

ats_sel_box_rec
    : 'ATSselboxrec' LParen exp Comma atstype Comma ID RParen
    ;

atom_exp
    : INT
    | FLOAT
    | CHAR
    | STRING
    | Bool    
    | ID
    | LParen exp RParen
    ;

func_call
    : ID LParen explst? RParen
    ;
    
explst
    : exp (Comma exp)*
    ;

    
//func_header
//    : func_decorator? atstype ID LParen paralst? RParen
//    ;

paralst
    : para (Comma para)*
    ;
    
para : paratype ID;

paratype
    : atstype
    | para_decorator LParen atstype RParen 
    ;
    
para_decorator
    : 'atsrefarg0_type'
    | 'atsrefarg1_type'
    ;

func_decorator
    : 'ATSstaticdec()' // same effect as GLOBAL
    | 'ATSglobaldec()'
    ;
    
atstmpdec_void
    : 'ATStmpdec_void' LParen ID Comma atstype RParen 
    ;
    
atstmpdec:
    'ATStmpdec' LParen ID Comma atstype RParen 
    ;

atstype
    : prim_type
    | ID
    | kind_decorator LParen ID RParen
    ;

prim_type
    : type_int
    | type_char
    | type_ulint 
    | type_bool 
    | type_string 
    | type_float
    | type_ptr 
    | type_ref
    | type_arrptr 
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
// Assign    : '=';  
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
    : '"' ( i=('0'..'9' | 'a'..'z' | 'A'..'Z' |
               '`' | '~' | '!' | '@' | '#' | '$' | '%' | '^' | '&' | '*' | '(' | ')' | '-' |
               '_' | '=' | '+' | '[' | '{' | ']' | '}' | '|' | ';' | ':' | ',' | '<' | '.' |
               '/' | '?' | ' '
              )
             | ESC_SEQ
           )+ '"'
    ;

CHAR
    : '\'' ( ESC_SEQ | i=~('\''|'\\'))
    ;


fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b' 
             |'t'
             |'n' 
             |'f' 
             |'r'
             |'\"'
             |'\''
             |'\\'
             )
    |   UNICODE_ESC  // todo
    |   OCTAL_ESC  // todo
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
    
    
