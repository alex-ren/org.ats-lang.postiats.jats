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

@parser::members {
int commentLevel = 0;

}

rule: program
    ;

program
    : (line | newline)* 
    ;
    
line
    : common_line 
    | include_line
    | if0_line {commentLevel++;} program endif_line {commentLevel--;}
    | ifndef_line program endif_line
    ;

common_line
    : COMMON_LINE line_end -> {commentLevel > 0}? template(t={$COMMON_LINE.text}, line_end={$line_end.st}) "// <t><line_end>"
                            -> template(t={$COMMON_LINE.text}, line_end={$line_end.st}) "<t><line_end>"
    ;

include_line
    : INCLUDE_LINE line_end -> template(t={$INCLUDE_LINE.text}, line_end={$line_end.st}) "// <t><line_end>"
    ;
    
if0_line : IF0_LINE line_end -> template (if0={$IF0_LINE.text}, line_end={$line_end.st}) "// <if0><line_end>"
    ;
    
endif_line
    : ENDIF_LINE line_end -> template (aendif={$ENDIF_LINE.text}, line_end={$line_end.st}) "// <aendif><line_end>"
    ;

ifndef_line
    : IFNDEF_LINE line_end -> template (ifndef={$IFNDEF_LINE.text}, line_end={$line_end.st}) "// <ifndef><line_end>"
    ;

newline
    : NEWLINE -> {commentLevel > 0}? template(t={$NEWLINE.text}) "// <t>"
              -> template(t={$NEWLINE.text}) "<t>"
    ;

line_end
    : NEWLINE -> template(t={$NEWLINE.text}) "<t>"
    | EOF 
    ;
    
NEWLINE
    : '\r'? '\n'
    ;

INCLUDE_LINE
    : '#include' ~('\r' | '\n')*
    ;
    

IFNDEF_LINE
    : '#ifndef' ~('\r' | '\n')*
    ;
    
IF0_LINE : '#if (0)' ~('\r' | '\n')*
    ;
    
ENDIF_LINE
    : '#endif' ~('\r' | '\n')*
    ;
    
COMMON_LINE 
    : ~('\r' | '\n')*
    ;
    
    
    
