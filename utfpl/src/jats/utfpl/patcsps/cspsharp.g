grammar cspsharp;

options {
  language = Java;
  backtrack = true;
  }
  
specification

            : (specBody)*

            ;

 

specBody
                 : library
                 | letDefintion  
                 | definition
                 | assertion
                 | alphabet
                 | define
                 | channel
                 ;

 

library
                  : '#' 'import' STRING ';' //import the library by full dll path or DLL name under the Lib folder

            | '#' 'include' STRING ';' //include the other models by full path or file name if under the same folder of current model

            ; 

 

channel

             : 'channel' ID ('[' additiveExpression ']')? additiveExpression ';'

             ;

 

assertion

             : '#' 'assert' definitionRef

              (

                 ( '|=' ( '(' | ')' | '[]' | '<>' | ID | STRING | '!' | '?' | '&&' | '||' | 'xor'| '->' | '<->' | '/\\' | '\\/' | '.' | INT )+ )
                               |  'deadlockfree' 
                               |  'nonterminating'     
                               |  'divergencefree'     
                               |  'deterministic'
                               |  'reaches' ID withClause?
                               |  'refines' definitionRef
                               |  'refines' '<F>' definitionRef
                               |  'refines' '<FD>' definitionRef

                )

             ';'

            ;

 

withClause
                    : 'with' ('min' | 'max') '(' expression ')'
                    ;

 

definitionRef

              : ID ('(' (argumentExpression(',' argumentExpression)*)? ')')?

              ;

 

alphabet

             : '#' 'alphabet' ID '{' eventList (',' eventList )* '}' ';'
             ;
define

             : '#' 'define' ID  '-'? INT ';' 

             | '#' 'define' ID  ('true' ';'  

                                         | 'false' ';') 

             | 'enum' '{' a=ID(',' b=ID)* '}'  ';' 

             | '#' 'define' ID dparameter? dstatement ';'

             ;


      dparameter
                  :  '(' ID (',' ID )* ')'        

            ;


      dstatement
                 : block  
                 | expression
                 ;

 

block

           : '{' (s=statement)* (e=expression)? '}' //At least a statement or expression has to be specified, i.e. s and e cannot be both null.

           ;

 

statement

          : block

          | localVariableDeclaration

          | ifExpression

          | whileExpression

          | expression ';'

          | ';'

          ;

 

//local variable that can be used in the block

localVariableDeclaration
                : 'var' ID ('=' expression)? ';'

          | 'var' ID '=' recordExpression ';'

          | 'var' ID ('[' expression ']')+ ('=' recordExpression)? ';' 

          ;

 

expression

          : conditionalOrExpression ('=' expression)?

          ;

           

conditionalOrExpression

          : '||' indexedExpression

          | conditionalAndExpression ( '||' conditionalAndExpression )*

          ;          

 

conditionalAndExpression

          : '&&' indexedExpression

          | conditionalXorExpression ( '&&' conditionalXorExpression)*

          ;

 

conditionalXorExpression

          : 'xor' indexedExpression

          | bitwiseLogicExpression ( 'xor' bitwiseLogicExpression)*

          ;

 

indexedExpression
                : (paralDef (';' paralDef )*) '@' expression 
                ;

 

bitwiseLogicExpression 
                : equalityExpression ( ( '&' | '|' | '^' ) equalityExpression)*

          ;

 

equalityExpression

            : relationalExpression ( ('=='|'!=') relationalExpression)*

            ;

           

relationalExpression

            : additiveExpression ( ('<' | '>' | '<=' | '>=') additiveExpression)*

            ;

           

additiveExpression

            : multiplicativeExpression ( ('+' | '-') multiplicativeExpression)*

            ;

multiplicativeExpression

            : unaryExpression ( ('*' | '/' | '%' ) unaryExpression)*

            ;

unaryExpression

            : '+' unaryExpression

            | '-' unaryExpression

            | '!' unaryExpressionNotPlusMinus

            | unaryExpressionNotPlusMinus '++' //Note: this is a syntax suger for unaryExpressionNotPlusMinus = unaryExpressionNotPlusMinus +1

          | unaryExpressionNotPlusMinus '--' //Note: this is a syntax suger for unaryExpressionNotPlusMinus = unaryExpressionNotPlusMinus - 1

            | unaryExpressionNotPlusMinus

            ;

 

arrayExpression

           : ID ('[' conditionalOrExpression ']')+

           ;

 

unaryExpressionNotPlusMinus

          : INT
                | 'true' 
                | 'false'  
                | 'call' '(' ID (',' argumentExpression)* ')' 

          | 'new' ID '(' (argumentExpression (',' argumentExpression)*)? ')' 

          |var=ID methods_fields_call 

          | a1=arrayExpression methods_fields_call  

          | arrayExpression 

          | '(' conditionalOrExpression ')'

          | ID  

          ;

 

methods_fields_call
               : '.' method=ID ('(' (argumentExpression (',' argumentExpression)* )? ')' )

         | '$' method=ID  

         ; 

 

letDefintion

        : ('var'|'hvar') ('<' userType=ID '>')? name=ID varaibleRange? ('=' (expression|'*') )? ';' //user defined datatype is supported using <type>

        | ('var'|'hvar') ID /*variableRange? todo no such thing */ '=' recordExpression ';'

       | ('var'|'hvar') ID ('[' expression ']')+ /*variableRange? todo no such thing */ ('=' (recordExpression|'*') )? ';' //multi-dimensional array is supported

        ;

 

varaibleRange
              : ':' '{' (additiveExpression)? '..' (additiveExpression)? '}'              
              ;

 

argumentExpression
             : conditionalOrExpression

       | recordExpression 
             ;

 

//if definition

ifExpression            

       :  'if' '(' expression ')' statement ('else' statement)? 

       ;

           

whileExpression

      : 'while' '(' expression ')' statement

      ;

 

recordExpression

      : '[' recordElement (',' recordElement)* ']'

      ;

 

recordElement

     : e1=expression ('(' e2=expression ')')? //e2 means the number of e1, by default it's 1

     | e1=expression '..' e2=expression //e1 to e2 gives a range of constants

     ;

//process definitions

definition

            : ID ('(' (parameter(',' parameter)*)? ')')? '=' interleaveExpr ';'

            ; 

 

parameter
                  : ID varaibleRange?  

            ;         

 

interleaveExpr

            : parallelExpr ('|||' parallelExpr)*                    

            | '|||' (paralDef (';' paralDef )*) '@' interleaveExpr

            | '|||' paralDef2 '@' interleaveExpr           

            ;

           

parallelExpr

            : generalChoiceExpr ('||' generalChoiceExpr)*

            | '||' (paralDef (';' paralDef )*) '@' interleaveExpr

            ;

             

paralDef

            : ID ':' '{' additiveExpression (',' additiveExpression)*  '}'

            | ID ':' '{' additiveExpression '..' additiveExpression  '}'

            ;          

           

paralDef2                    

            : '{' '..' '}'

            | '{' additiveExpression '}'

            ;

 

generalChoiceExpr

            : internalChoiceExpr('[]' internalChoiceExpr)*

            | '[]' (paralDef (';' paralDef )*) '@' interleaveExpr

            ; 

             

internalChoiceExpr

            : externalChoiceExpr ('<>' externalChoiceExpr)*

            | '<>' (paralDef (';' paralDef )*) '@' interleaveExpr

            ;

           

externalChoiceExpr

            : interruptExpr ('[*]' interruptExpr)*

            | '[*]' (paralDef (';' paralDef )*) '@' interleaveExpr

            ;

interruptExpr

            : hidingExpr ('interrupt' hidingExpr)* 

            ;

 

hidingExpr
            : sequentialExpr
            | sequentialExpr  '\\' '{' eventList  (',' eventList )* '}'

            ;

           

sequentialExpr
  : guardExpr (';' guardExpr)*
            ;
guardExpr

            : channelExpr

            | '[' conditionalOrExpression ']' channelExpr

            ;

 

channelExpr

            : ID ('[' additiveExpression']')?  '!' expression ('.' expression)*  '->' channelExpr

            | ID ('[' additiveExpression']')?  '?' ('[' conditionalOrExpression ']')? expression ('.' expression)*  '->' channelExpr //here expression is either a single variable or expression that has no global variables. Optional conditionalOrExpression is the guard condition that stop the channel input event if the condition is false. 

            | eventExpr

            ;

 

eventExpr

            : eventM (block)? '->' channelExpr

            | block '->' channelExpr //un-labeled program, which is same as: tau block '->' eventExpr

            | '(' eventM (',' eventM)* ')' '->' channelExpr

            | caseExpr

            ;

 

caseExpr: 'case'

              '{'

                        caseCondition+

                        ('default' ':'  interleaveExpr)?

               '}'

            | ifExpr

            ;

caseCondition

            : (conditionalOrExpression ':' interleaveExpr)

            ;

ifExpr  :   atomicExpr    

            ifExprs

            ;

 

ifExprs

            : 'if' '(' conditionalOrExpression ')' '{' interleaveExpr  '}' ('else' ifBlock )? 

            | 'ifa' '(' conditionalOrExpression ')' '{' interleaveExpr  '}' ('else' ifBlock )? 

            | 'ifb' '(' conditionalOrExpression ')' '{' interleaveExpr  '}'  

            ;

 

ifBlock

            : ifExprs

            | '{' interleaveExpr '}'

            ;

 

atomicExpr  

            : atom 

            | 'atomic' '{' interleaveExpr  '}'

            ;

 

atom   :   ID  ('(' (expression (',' expression )*)?  ')')?

            |   'Skip' ('(' ')')?

            |   'Stop' ('(' ')')?

            |   'assert' '(' expression ')'

            |   '(' interleaveExpr ')'

            ;

 

eventM

            :  eventName

            | 'tau' //invisible tau event

            ;

 

eventList

            : eventName 
                  | (paralDef (';' paralDef )*) '@' eventName

            ;

 

eventName

            : ID ( '.' additiveExpression)*

            ;

 

ID        : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*

            ;

//string allows user to input the channel input/output as propersitions in the LTL

STRING 

:  '"' (~('\\'|'"') )* '"'

            ;

WS      :  ('  ' | '\t' | '\n' | '\r' | '\f')
;

 

INT      : ('0'..'9')+ ;

   

COMMENT :   '/*' ( : . )* '*/'

            ;

   

LINE_COMMENT

            : '//' ~('\n'|'\r')* '\r'? '\n'

            ;