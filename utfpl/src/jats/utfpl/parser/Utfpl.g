grammar Utfpl;

options {
  language = Java;
  output = AST;
}


tokens {
  // VAR; //  = 'var';
  ASSIGN;
  FUN;
  PARALST;
  EXPLST;
  DECLST;
  LET;
  IF;
  LAM;
  APP;
  PROGRAM;
  TUPLE;
  NULL;  
}

@header {
  package jats.utfpl.parser;
}

@lexer::header {
  package jats.utfpl.parser;
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
    : gdecs -> ^(PROGRAM gdecs)
    ;
    
exp : lam_exp
    | if_exp
    | add_exp
    ;


if_exp
    : If cond=exp Then btrue=exp Else bfalse=exp -> ^(IF exp exp exp)
    ;
    
lam_exp
    : Lambda LParen paralst RParen Imply exp -> ^(LAM paralst exp)
    ;

let_exp:
    Let decs In exp End -> ^(LET decs exp)
    ;
    
add_exp
    : app_exp ((Add | Subtract)^ app_exp)*
    ;
    
app_exp
    : (atom_exp -> atom_exp) (LParen explst RParen -> ^(APP $app_exp explst) )* 
    ;

atom_exp
    : let_exp
    | INT
//    | FLOAT
//    | CHAR
    | STRING
    | BOOL    
    | ID
    | LParen (exp -> exp) (Comma explst -> ^(TUPLE $atom_exp explst))? RParen
    | LParen RParen -> NULL
    ;
    
explst
    : (exp (Comma exp)*)? -> ^(EXPLST exp*)
    ;

gdecs
    : gdec* -> gdec*
    ;

gdec
    : Var ID (ColonAssign exp)? -> ^(Var ID exp?)
    | dec
    ;
    
decs
    : dec* -> ^(DECLST dec*)
    ;

dec
    : Val pat=exp Assign v=exp -> ^(Val $pat $v)
    | ID ColonAssign exp -> ^(ASSIGN ID exp)
    | Fun ID LParen paralst RParen Assign exp -> ^(FUN ID paralst exp)
    ;

paralst
    : (ID (Comma ID)*)? -> ^(PARALST ID*)
    ;


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
ColonAssign: ':=';  
Comma     : ',';  
QMark     : '?';  

Let       : 'let';
In        : 'in';
End       : 'end';

Imply     : '=>';
Lambda    : 'lam';
Val       : 'val';
Var       : 'var';

Fun       : 'fun';

If        : 'if';
Then      : 'then';
Else      : 'else';

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
      