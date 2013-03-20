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
    | MACRO_IF0 program_sp MACRO_ENDIF  // to handle nested #if(0)
    | MACRO_INCLUDE STRING -> template(macro_inc={$MACRO_INCLUDE.text}, name={$STRING.text}) "// <macro_inc> <name>"
    ;

// =======================================================    
program_sp  // appear only in macro_commet
    : func_decorator? atstype_sp ID LParen paralst_sp? RParen
        (Semicol
        | LBrace block_sp RBrace
        )
    | MACRO_IF0 program_sp MACRO_ENDIF
    ;

    
paralst_sp
    : para_sp (Comma para_sp)*
    ;
    
para_sp : paratype_sp ID?;

paratype_sp
    : atstype_sp
    | para_decorator LParen atstype_sp RParen 
    ;

block_sp
    : (bstat_sp)*
    ;

bstat_sp
    : tmpdec_sp Semicol
    
    | gototag  // Don't handle goto now.
    
    | atsins_move_sp Semicol
    | atsins_load_sp Semicol    
    | ats_return Semicol
    | ats_return_void Semicol
    | simple_return Semicol

    ;

atsins_load_sp
    : 'ATSINSload' LParen exp_sp Comma exp_sp RParen
    ;


atsins_move_sp
    : 'ATSINSmove' LParen ID Comma exp_sp RParen
    ;

exp_sp
    : exp_sp1 ('->' exp_sp1)*
    ;

exp_sp1
    : func_call_sp
    | atom_exp_sp
    | ID (LT explst_sp GT)+
    | 'PMVtmpltcst' LParen explst_sp RParen LParen explst_sp RParen
    
    // copy from exp
    | ats_empty
    | ats_pvm_castfn
    | ats_simple_cast
    | ats_pmv_sizeof_sp
    | ats_deref_sp
    | ats_ref_arg
    | ats_pmv_ptrof
    | ats_sel_recsin
    | ats_sel_flt_rec
    | ats_sel_box_rec    
    | ats_ck_iseqz    
    ;
    
ats_deref_sp
    : 'ATSderef' LParen exp_sp Comma atstype_sp RParen
    ;
    
ats_pmv_sizeof_sp
    : 'ATSPMVsizeof' LParen atstype_sp RParen
    ;
        
atom_exp_sp
    : INT
    | FLOAT
    | CHAR
    | STRING
    | Bool    
    | ID
    | LParen exp_sp RParen
    ;
    
func_call_sp
    : ID LParen explst_sp? RParen
    ;
     
explst_sp
    : exp_sp (Comma exp_sp)*
    ;
    
tmpdec_sp
    : atstmpdec_sp
    | atstmpdec_void_sp
    ;

atstmpdec_void_sp
    : 'ATStmpdec_void' LParen ID Comma atstype_sp RParen 
    ;
    
atstmpdec_sp:
    'ATStmpdec' LParen ID Comma atstype_sp RParen 
    ;

atstype_sp  // only appear in comment #if(0)
    : atstype
    | 'atstyvar_type' LParen ID RParen  
    | kind_decorator LParen 'postiats_undef' LParen exp RParen RParen
    | 'HITundef' LParen exp_sp RParen
    ;  
   
       
// =======================================================
    
macro_ifndef_id: MACRO_IFNDEF ID -> template (macro_ifndef={$MACRO_IFNDEF.text}, id={$ID.text}) "// <macro_ifndef> <id>"
    ;
    
macro_endif: MACRO_ENDIF -> template (macro_endif={$MACRO_ENDIF.text}) "// <macro_endif>"
    ;

gstat
    : tmpdec Semicol
    | func_decorator? rettype=atstype ID LParen paralst? RParen
        (Semicol
        | LBrace block RBrace
        )
    | typedef
    | ats_dyn_cst 
    | ats_dyn_load1 Semicol
    | main_impl
    ;
    
main_impl
    : type_int Main LParen paralst RParen LBrace
    type_int ID Assign INT Semicol
    func_call Semicol
    atsmain LParen explst RParen Semicol
    simple_return Semicol
    RBrace
    ;
    
atsmain
    : 'ATSmainats_void_0'
    | 'ATSmainats_argc_argv_0'
    | 'ATSmainats_argc_argv_envp_0'
    ;
    
ats_dyn_cst
    : 'ATSdyncst_mac' LParen ID RParen
    | 'ATSdyncst_castfn' LParen ID RParen
    | 'ATSdyncst_extfun' LParen ID Comma LParen atstypelst RParen Comma atstype RParen
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
    | simple_return Semicol
    | ats_dyn_load0 Semicol
//    | Return exp Semicol
    ;

simple_return
    : Return exp?
    ; 

ats_dyn_load0
    : ATSdynload0 LParen ID RParen
    ;    
    
ats_dyn_load1
    : ATSdynload1 LParen ID RParen
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
    | ats_pvm_castfn
    | ats_simple_cast
    | ats_pmv_sizeof
    | ats_deref
    | ats_ref_arg
    | ats_pmv_ptrof
    | ats_sel_recsin
    | ats_sel_flt_rec
    | ats_sel_box_rec    
//    | ats_sel_arr_ind
    | ats_ck_iseqz
    
    | atom_exp
    

    ;

ats_ck_iseqz
    : ATSCKiseqz LParen exp RParen
    ;
    
ats_empty
    : 'ATSempty' LParen RParen
    ;
    
ats_pvm_castfn
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

ats_pmv_sizeof
    : 'ATSPMVsizeof' LParen atstype RParen
    ;
    
ats_deref
    : 'ATSderef' LParen exp Comma atstype RParen
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

ats_sel_box_rec
    : 'ATSselboxrec' LParen exp Comma atstype Comma ID RParen
    ;

//ats_sel_arr_ind
//    : 'ATSselarrind' LParen exp Comma atstype Comma ID RParen 
//    ;

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
    
para : paratype ID?;

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

atstypelst
    : atstype (Comma atstype)*
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
    | type_str_arr
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
type_str_arr: 'char **';

Main: 'main';

kind_decorator
    : 'atstkind_type' 
    | 'atstkind_t0ype' 
    ;

ATSCKiseqz: 'ATSCKiseqz';

ATSdynload0: 'ATSdynload0';
ATSdynload1: 'ATSdynload1';

MACRO_IF0:    '#if(0)';
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
//    |   '#if(0)' ( options {greedy=true;} : . )* '#endif' {$channel=HIDDEN;}  // todo
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
    
    