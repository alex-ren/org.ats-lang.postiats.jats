grammar ATSIL;

options {
  language = Java;
  output = AST;
}


tokens {
  VAR; //  = 'var';
  VAR_VOID;
  STAT_VAR; //  = 'var';
  STAT_VAR_VOID;
  
  PROGRAM;
  BLOCK;
  
  FUNC_DECL;
  FUNC_DEF; 
  FUNC_CALL;
  
  EXP_LIST;
  PARA_LIST;
  TYPE_LIST;

  GLOBAL;
  STATIC;
  
  TYPEDEF;
  STRUCT;

  TYPE;
//  TYPE_INT;
//  TYPE_CHAR;
//  TYPE_ULINT;
//  TYPE_BOOL;
//  TYPE_STRING;
//  TYPE_FLOAT;
//  TYPE_PTR;
//  TYPE_REF;
//  TYPE_ARRPTR;
  
  TYPE_ARR;
  
  TYPE_DEC_TYPE;
  TYPE_DEC_T0YPE;
  
  PARA_TYPE;
  PARA_TYPE_REF0;
  PARA_TYPE_REF1;
  
  IFSTAT;
  IF;
  ELSE;
  WHILE;
  DOWHILE;
  GOTOTAG;
  
  // statement
  ATSINS_STORE;
  ATSINS_STORE_ARRPSZ_ASZ;
  ATSINS_STORE_ARRPSZ_PTR;
  ATSINS_STORE_FLTREC_OFS;
  ATSINSstore_boxrec_ofs;
  ATSINS_MOVE;
  ATSINS_MOVE_VOID;
  ATSINSmove_boxrec;
  ATSINS_PMOVE;
  ATSINS_MOVE_ARRPSZ_PTR;
  ATSINS_UPDATE_PTRINC;
  ATS_RETURN;
  ATS_RETURN_VOID;
  
  // expression
  ATSPMV_CASTFN;
  
	ATSPMV_INT;
	ATSPMVintrep;
	
	ATSPMV_TRUE;
	ATSPMV_FALSE;
	ATSPMV_CHAR;
	ATSPMV_FLOAT;
	ATSPMV_STRING;
	
	ATSPMV_I0NT;
	ATSPMV_F0LOAT;
	
	ATSCSTSPmyfil;
	ATSCSTSPmyloc;
	
	ATSPMV_SIZEOF;
	ATS_DEREF;
	ATS_EMPTY;
	ATSPMV_REFARG0;
	ATSPMV_REFARG1;
	
	
	ATSMAIN;


}

@header {
  package org.ats_lang.postiats.jats.parser;
}

@lexer::header {
  package org.ats_lang.postiats.jats.parser;
}


@lexer::members {
String m_str;

public String getEscaped() {
    return m_str;
}
}

rule: program EOF
    ;
    
program
    : gstat* main_impl? -> ^(PROGRAM gstat* main_impl?)  // omit the macro_commet
    ;

gstat
    : typedef
    | func_decorator? rettype=atstype ID LParen paralst RParen
        (Semicol -> ^(FUNC_DECL ID func_decorator? $rettype paralst)
        | LBrace block RBrace -> ^(FUNC_DEF ID func_decorator? $rettype paralst block)
        )
    | stat_tmpdec Semicol!
    | ats_dyn_cst Semicol!
    | ats_dyn_load1 Semicol!
    | the_atsexncon_initize
    ;
    
the_atsexncon_initize:
    'extern void the_atsexncon_initize (atstype_exncon *d2c, char *exnmsg)' Semicol ->
    ;

//int
//main
//(
//int argc, char **argv, char **envp
//) {
//int err = 0 ;
//_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test02_056$dats__dynload() ;
//ATSmainats_void_0(err) ;
//return (err) ;
//} /* end of [main] */
main_impl
    : 'int' Main LParen 'int' ID Comma 'char **' ID Comma 'char **' ID RParen LBrace
    'int' ID Assign INT Semicol
    func_call Semicol
    atsmain LParen explst RParen Semicol
    simple_return Semicol
    RBrace -> ^(ATSMAIN ID func_call atsmain)
    ;

atsmain
    : 'ATSmainats_void_0' -> ATSmainats_void_0
    | 'ATSmainats_argc_argv_0' -> ATSmainats_argc_argv_0
    | 'ATSmainats_argc_argv_envp_0' -> ATSmainats_argc_argv_envp_0
    | 'ATSmainats_void_int' -> ATSmainats_void_int
    | 'ATSmainats_argc_argv_int' -> ATSmainats_argc_argv_int
    | 'ATSmainats_argc_argv_envp_int' -> ATSmainats_argc_argv_envp_int
    ;

ats_dyn_cst
    : 'ATSdyncst_mac' LParen ID RParen -> ^(ATSdyncst_mac ID)
    | 'ATSdyncst_castfn' LParen ID RParen -> ^(ATSdyncst_castfn ID)
    | 'ATSdyncst_extfun' LParen ID Comma LParen atstypelst RParen Comma atstype RParen -> ^(ATSdyncst_extfun ID atstypelst atstype)
    ;             
 
typedef
    : Typedef structure ID Semicol -> ^(TYPEDEF ID structure)
    ;

structure
    : Struct LBrace (atstype ID Semicol)+ RBrace -> ^(STRUCT ^(VAR atstype ID)+)
    ;
    
stat_tmpdec
    : stat_atstmpdec
    | stat_atstmpdec_void
    ;
    
tmpdec
    : atstmpdec
    | atstmpdec_void
    ;
    
block
    : (bstat)* -> ^(BLOCK bstat*)
    ;

bstat
    : tmpdec Semicol!

    | ifstat
    | whilestat
    | dowhilestat
    
    | gototag!  // Don't handle goto now.
    
    | atsins_load Semicol!
    | atsins_store Semicol!
    | atsins_xstore Semicol!
    | atsins_store_arrpsz_asz Semicol!
    | atsins_store_arrpsz_ptr Semicol!
    | atsins_store_fltrec_ofs Semicol!
    | atsins_store_boxrec_ofs Semicol!
    | atsins_move Semicol!
    | atsins_move_void Semicol!
    | atsins_move_boxrec Semicol!
    | atsins_pmove Semicol!
    | atsins_move_arrpsz_ptr Semicol!
    | atsins_update_ptrinc Semicol!
    | ats_return Semicol!
    | ats_return_void Semicol!
    | simple_return Semicol!
    | ats_dyn_load0 Semicol!
    | ats_dyn_load_set Semicol!
    | simple_assignment Semicol!
    ;
    
simple_assignment
    : ID Assign exp -> ^(ATSINS_MOVE ID exp)
    ;
    
simple_return
    : Return exp -> ^(ATS_RETURN exp)
    | Return -> ^(ATS_RETURN_VOID)
    ;
    
ats_dyn_load1
    : ATSdynload1 LParen ID RParen -> ^(ATSdynload1 ID)
    ;
    
ats_dyn_load0
    : ATSdynload0 LParen ID RParen -> ^(ATSdynload0 ID)
    ;
    
ats_dyn_load_set
    : ATSdynloadset LParen ID RParen -> ^(ATSdynloadset ID)
    ;
    
atsins_load
    : ATSINSload LParen exp Comma exp RParen -> ^(ATSINSload exp exp)
    ;

atsins_store
    : ATSINSstore LParen exp Comma exp RParen -> ^(ATSINSstore exp exp)
    ;

atsins_xstore
    : ATSINSxstore LParen ID Comma exp Comma exp RParen -> ^(ATSINSxstore ID exp exp)
    ;

atsins_store_arrpsz_asz
    : 'ATSINSstore_arrpsz_asz' LParen ID Comma exp RParen -> ^(ATSINS_STORE_ARRPSZ_ASZ ID exp)
    ;
    
atsins_store_arrpsz_ptr
    : 'ATSINSstore_arrpsz_ptr' LParen ID Comma atstype Comma exp RParen -> ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp)
    ;

atsins_store_fltrec_ofs
    : 'ATSINSstore_fltrec_ofs' LParen tmp=ID Comma atstype Comma lab=ID Comma exp RParen
       -> ^(ATSINS_STORE_FLTREC_OFS $tmp atstype $lab exp)
    ;

atsins_store_boxrec_ofs
    : 'ATSINSstore_boxrec_ofs' LParen tmp=ID Comma atstype Comma lab=ID Comma exp RParen
       -> ^(ATSINSstore_boxrec_ofs $tmp atstype $lab exp)
    ;
    
atsins_move
    : 'ATSINSmove' LParen ID Comma exp RParen -> ^(ATSINS_MOVE ID exp)
    ;

atsins_move_void
    : 'ATSINSmove_void' LParen ID Comma exp RParen -> ^(ATSINS_MOVE_VOID exp)
    ;
    
atsins_move_boxrec
    : 'ATSINSmove_boxrec' LParen ID Comma atstype RParen -> ^(ATSINSmove_boxrec ID atstype)
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
    : 'ATSreturn_void' LParen exp? RParen -> ^(ATS_RETURN_VOID exp?)
    ;

ifstat
    : ('ATSif' | 'if' /*| 'ATSifnot'*/) LParen exp RParen ('ATSthen' LParen RParen)? LBrace thenb=block RBrace ('ATSelse' LParen RParen LBrace elseb=block RBrace)?
      -> ^(IFSTAT ^(IF exp $thenb) ^(ELSE $elseb)?)
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
        
exp 
    : func_call
    
    | ats_empty
    | ats_pvm_castfn
    | ats_simple_cast
    | ats_pmv_sizeof
    | ats_deref
    | ats_ref_arg
    | ats_pmv_ptrof
    | ats_pmv_ptrof_void
    
    | ats_sel_recsin
    | ats_sel_flt_rec
    | ats_sel_box_rec
    | ats_sel_arr_ind
    | ats_sel_arrptr_ind

    | ats_ck_iseqz
    
    | atom_exp
    | ats_cst_pmy

    ;

ats_cst_pmy
    : 'ATSCSTSPmyfil' LParen exp RParen -> ^(ATSCSTSPmyfil exp)
    | 'ATSCSTSPmyloc' LParen exp RParen -> ^(ATSCSTSPmyloc exp)
    ;
    
ats_ck_iseqz
    : ATSCKiseqz LParen exp RParen -> ^(ATSCKiseqz exp)
    ;
    
ats_empty
    : 'ATSempty' LParen RParen -> ATS_EMPTY
    ;
    
ats_pvm_castfn 
    : 'ATSPMVcastfn' LParen ID Comma atstype Comma exp RParen -> ^(ATSPMV_CASTFN ID atstype exp)
    ;

ats_simple_cast
    : 'ATSPMVint' LParen exp RParen -> ^(ATSPMV_INT exp)
    | 'ATSPMVintrep' LParen exp RParen -> ^(ATSPMVintrep exp)
    
    | 'ATSPMVbool_true' LParen RParen -> ATSPMV_TRUE
    | 'ATSPMVbool_false' LParen RParen -> ATSPMV_FALSE
    
    | 'ATSPMVchar' LParen exp RParen -> ^(ATSPMV_CHAR exp)
    | 'ATSPMVfloat' LParen exp RParen -> ^(ATSPMV_FLOAT exp)
    | 'ATSPMVstring' LParen exp RParen -> ^(ATSPMV_STRING exp)
    
    | 'ATSPMVi0nt' LParen exp RParen -> ^(ATSPMV_I0NT exp)
    | 'ATSPMVf0loat' LParen exp RParen -> ^(ATSPMV_F0LOAT exp)
    ;    

ats_pmv_sizeof
    : 'ATSPMVsizeof' LParen atstype RParen -> ^(ATSPMV_SIZEOF atstype)
    ;
    
ats_deref
    : 'ATSderef' LParen exp Comma atstype RParen -> ^(ATS_DEREF atstype exp)
    ;

ats_ref_arg
    : 'ATSPMVrefarg0' LParen exp RParen -> ^(ATSPMV_REFARG0 exp)
    | 'ATSPMVrefarg1' LParen exp RParen -> ^(ATSPMV_REFARG1 exp)
    ;
   
ats_pmv_ptrof
    : ATSPMVptrof LParen ID RParen -> ^(ATSPMVptrof ID)
    ;
    
ats_pmv_ptrof_void
    : ATSPMVptrof_void LParen ID RParen -> ^(ATSPMVptrof_void ID)
    ;    

// ===================================
ats_sel_recsin
      : ATSSELrecsin LParen pmv=ID Comma atstype Comma lab=ID RParen -> ^(ATSSELrecsin $pmv atstype $lab)
      ;
   
ats_sel_flt_rec
    : ATSSELfltrec LParen pmv=exp Comma atstype Comma lab=ID RParen -> ^(ATSSELfltrec $pmv atstype $lab)
    ;

ats_sel_box_rec
    : ATSSELboxrec LParen exp Comma atstype Comma ID RParen -> ^(ATSSELboxrec exp atstype ID)
    ; 
    
ats_sel_arr_ind
    : ATSSELarrind LParen exp Comma atstype Comma ID RParen -> ^(ATSSELarrind exp atstype ID)
    ;

ats_sel_arrptr_ind
    : ATSSELarrptrind LParen pmv=exp Comma atstype Comma LBracket lab=exp RBracket RParen -> ^(ATSSELarrptrind $pmv atstype $lab)
    ;


atom_exp
    : INT
    | FLOAT
    | CHAR
    | STRING
    | BOOL    
    | ID
    | LParen! exp RParen!
    ;

func_call
    : ID LParen explst RParen -> ^(FUNC_CALL ID explst)
    ;
    
explst
    : (exp (Comma exp)*)? -> ^(EXP_LIST exp*)
    ;

    
//func_header
//    : func_decorator? atstype ID LParen paralst? RParen
//    ;

paralst
    : para? (Comma para)* -> ^(PARA_LIST para*)
    ;
    
para : paratype ID?;

paratype
    : para_decorator LParen atstype_noparadec RParen -> ^(PARA_TYPE para_decorator atstype_noparadec)
    | atstype_noparadec -> ^(PARA_TYPE atstype_noparadec)
    ;
    
para_decorator
    : atsrefarg0_type -> PARA_TYPE_REF0
    | atsrefarg1_type -> PARA_TYPE_REF1
    ;

func_decorator
    : ATSstaticdec -> ^(GLOBAL)  // same effect as GLOBAL
    | ATSglobaldec -> STATIC
    ;

stat_atstmpdec_void
    : 'ATSstatmpdec_void' LParen ID Comma atstype RParen -> ^(STAT_VAR_VOID atstype ID)
    ;
    
stat_atstmpdec
    : 'ATSstatmpdec' LParen ID Comma atstype RParen -> ^(STAT_VAR atstype ID)
    ;
    
atstmpdec_void
    : 'ATStmpdec_void' LParen ID Comma atstype RParen -> ^(VAR_VOID atstype ID)
    ;
    
atstmpdec
    : 'ATStmpdec' LParen ID Comma atstype RParen -> ^(VAR atstype ID)
    ;

atstypelst
    : atstype (Comma atstype)*  -> ^(TYPE_LIST atstype+)
    ;
    
atstype
    : atstype_noparadec
    | para_decorator LParen atstype_noparadec RParen -> atstype_noparadec  // neglect para_decorator
    ;
    
atstype_noparadec
    : // prim_type -> ^(TYPE prim_type)
      ID -> ^(TYPE ID)
    | kind_decorator LParen ID RParen -> ^(TYPE kind_decorator ID)
    | 'atstype_tyarr' LParen atstype_noparadec RParen -> ^(TYPE TYPE_ARR atstype_noparadec)
    ;

kind_decorator
    : atstkind_type -> TYPE_DEC_TYPE
    | atstkind_t0ype -> TYPE_DEC_T0YPE
    ;

//prim_type
//    : type_int -> TYPE_INT
////    | type_char -> TYPE_CHAR
////    | type_ulint -> TYPE_ULINT
////    | type_bool -> TYPE_BOOL
////    | type_string -> TYPE_STRING
////    | type_float -> TYPE_FLOAT
////    | type_ptr -> TYPE_PTR
////    | type_ref -> TYPE_REF
////    | type_arrptr -> TYPE_ARRPTR
//    | type_str_arr -> TYPE_ARRPTR  // still a pointer
//    ;

//type_int    : 'int';
////type_char   : 'char';
////type_ulint  : 'unsigned' 'long' 'int';
////type_bool   : 'bool';
////type_string : 'string';
////type_float  : 'float';
////type_ptr    : 'ptr';
////type_ref    : 'ref';
////type_arrptr : 'arrptr';
//type_str_arr: 'char **';


// =====================================================
// lexer

Main: 'main';

// =====================================================
// pats_ccomp_basics.h

ATSstaticdec: 'ATSstaticdec()';
ATSglobaldec: 'ATSglobaldec()';

ATSdyncst_mac: 'ATSdyncst_mac';
ATSdyncst_castfn: 'ATSdyncst_castfn'; 
ATSdyncst_extfun: 'ATSdyncst_extfun';
      
      
ATSdynload0: 'ATSdynload0';
ATSdynload1: 'ATSdynload1';

ATSdynloadset: 'ATSdynloadset';

ATSmainats_void_0: 'ATSmainats_void_0';
ATSmainats_argc_argv_0: 'ATSmainats_argc_argv_0';
ATSmainats_argc_argv_envp_0: 'ATSmainats_argc_argv_envp_0';
ATSmainats_void_int: 'ATSmainats_void_int';
ATSmainats_argc_argv_int: 'ATSmainats_argc_argv_int';
ATSmainats_argc_argv_envp_int: 'ATSmainats_argc_argv_envp_int';


// =====================================================


ATSSELrecsin: 'ATSSELrecsin';
ATSSELfltrec: 'ATSSELfltrec';
ATSSELboxrec: 'ATSSELboxrec';
ATSSELarrind: 'ATSSELarrind';
ATSSELarrptrind: 'ATSSELarrptrind';

// =====================================================

ATSPMVptrof: 'ATSPMVptrof';
ATSPMVptrof_void: 'ATSPMVptrof_void';

// =====================================================

// pats_ccomp_typedefs.h 

atstkind_type: 'atstkind_type';
atstkind_t0ype: 'atstkind_t0ype';

atsrefarg0_type: 'atsrefarg0_type';
atsrefarg1_type: 'atsrefarg1_type';
    
// =====================================================
ATSINSload: 'ATSINSload';

ATSINSstore: 'ATSINSstore';

ATSINSxstore: 'ATSINSxstore';

ATSCKiseqz: 'ATSCKiseqz';


// =====================================================

MACRO_IF0:    '#if(0)';
MACRO_ENDIF:  '#endif';
MACRO_IFNDEF: '#ifndef';
MACRO_IF:     '#if';
MACRO_INCLUDE:'#include';

// =====================================================

Return  : 'return';
Typedef : 'typedef';
Struct  : 'struct';

// =====================================================

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

// =====================================================

BOOL  
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

STRING
@init { final StringBuilder buf = new StringBuilder(); }
    // :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    : '"' ( i=('0'..'9' | 'a'..'z' | 'A'..'Z' |
               '`' | '~' | '!' | '@' | '#' | '$' | '%' | '^' | '&' | '*' | '(' | ')' | '-' |
               '_' | '=' | '+' | '[' | '{' | ']' | '}' | '|' | ';' | ':' | ',' | '<' | '.' |
               '/' | '?' | ' '
              ) { buf.appendCodePoint(i); }
             | ESC_SEQ [buf]
           )* '"' {m_str = buf.toString();} // { setText(buf.toString()); /*System.out.println(getText());*/ } 
    ;

CHAR
@init { final StringBuilder buf = new StringBuilder(); }
    : '\'' ( ESC_SEQ[buf] | i=~('\''|'\\') { buf.appendCodePoint(i); } ) '\''  {m_str = buf.toString();}  // { setText(buf.toString()); }
    ;


fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ [StringBuilder buf]
    :   '\\' ('b' { buf.append('\b'); }
             |'t' { buf.append('\t'); }
             |'n' { buf.append('\n'); }
             |'f' { buf.append('\f'); }
             |'r' { buf.append('\r'); }
             |'\"'{ buf.append('\"'); }
             |'\''{ buf.append('\''); }
             |'\\'{ buf.append('\\'); }
             )
    |   UNICODE_ESC {System.out.println("UNICODE_ESC not supported.");} // todo
    |   OCTAL_ESC   {int code = Integer.parseInt($OCTAL_ESC.text.substring(1)); buf.append((char)code); }  
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

// =====================================================

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
      
      