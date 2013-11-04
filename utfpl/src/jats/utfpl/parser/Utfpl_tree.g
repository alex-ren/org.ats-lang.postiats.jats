tree grammar Utfpl_tree;

options {
  language = Java;
  tokenVocab = Utfpl;
  ASTLabelType = CommonTree;
}


@header {
  package jats.utfpl.parser;
  
     
  import jats.utfpl.tree.*;
  
  import java.util.Map;
  import java.util.HashMap;
  import java.util.ArrayList;

}
 

rule returns [Program prog]
    : ^(PROGRAM gdecs) {prog = new Program($gdecs.gdecs);}
    ;

exp returns [Exp node]
    : lam_exp {node = $lam_exp.node;}
    | if_exp {node = $if_exp.node;}
    | app_exp {node = $app_exp.node;}
    | let_exp {node = $let_exp.node;}
    | atom_exp {node = $atom_exp.node;}
    | tuple_exp {node = $tuple_exp.node;}
    ;
    
tuple_exp returns [TupleExp node]
    : ^(TUPLE exp explst) {node = new TupleExp($exp.node, $explst.explst);}
    ;

lam_exp returns [LamExp node]
    : ^(LAM paralst exp) {node = new LamExp($paralst.paralst, $exp.node);}
    ;

if_exp returns [IfExp node]
    : ^(IF cond=exp btrue=exp bfalse=exp) {node = new IfExp($cond.node, $btrue.node, $bfalse.node);}
    ;
    
app_exp returns [AppExp node]
    : ^(APP exp explst) {node = new AppExp($exp.node, $explst.explst);}
    ;
    
let_exp returns [LetExp node]
    : ^(LET decs exp) {node = new LetExp($decs.decs, $exp.node);}
    ;
    
atom_exp returns [Exp node]
    : INT {node = new AtomExp($INT.text);}
//    | FLOAT
//    | CHAR
    | STRING {node = new AtomExp($STRING.text);}
    | BOOL {node = new AtomExp($BOOL.text);}
    | id_exp {node = $id_exp.node;}
    | NULL {node = TupleExp.Void;}
    ;
    
id_exp returns [IdExp node]
    : ID {node = new IdExp($ID.text);} 
    ;

explst returns [List<Exp> explst]
@init {
  explst = new ArrayList<Exp>();
}
    : ^(EXPLST (exp{explst.add($exp.node);})*) 
    ;

gdecs returns [List<Dec> gdecs]
@init {
  gdecs = new ArrayList<Dec>();
}
    : (gdec {gdecs.add($gdec.node);})* 
    ;

gdec returns [Dec node]
    : ^(Var ID exp?) {node = new VarDef(new IdExp($ID.text), $exp.node);}
    | ^(Var ID index) {node = new VarArrayDef(new IdExp($ID.text), $index.size, Type.eInteger);}
//    | ^(VarObj ID exp?) {node = new VarDef(new IdExp($ID.text), $exp.node, Type.eValue);}  // not necessary
//    | ^(VarObj ID index) {node = new VarArrayDef(new IdExp($ID.text), $index.size, Type.eValue);}   // I decide not to allow such array of boxed values.
    | ^(Val pat=exp v=exp) {node = new ValDef($pat.node, $v.node);}
    | ^(ASSIGN ID exp) {node = new VarAssign(new IdExp($ID.text), $exp.node);}
    | fungroup {node = new FunGroup($fungroup.funLst);}
    ;
    
index returns [int size]
    : ^(INDEX INT) {size = Integer.parseInt($INT.text);}
    ;

decs returns [List<Dec> decs]
@init {
  decs = new ArrayList<Dec>();
}
    : ^(DECLST (dec {decs.add($dec.node);})*) 
    ;
    
dec returns [Dec node]
    : ^(Val pat=exp v=exp) {node = new ValBind($pat.node, $v.node);}
    | ^(ASSIGN ID exp) {node = new VarAssign(new IdExp($ID.text), $exp.node);}
    | fungroup {node = new FunGroup($fungroup.funLst);}
    ; 

fungroup returns [List<FunDef> funLst]
@init {
  funLst = new ArrayList<FunDef>();
}
    : ^(FUNGROUP (fundef{funLst.add($fundef.node);})+)
    ;

fundef returns [FunDef node]
    : ^(FUN id_exp paralst exp) {node = new FunDef($id_exp.node, $paralst.paralst, $exp.node);}
    ;
    
paralst returns [List<IdExp> paralst]
@init {
  paralst = new ArrayList<IdExp>();
}
    : ^(PARALST (ID{paralst.add(new IdExp($ID.text));})*) 
    ;


    
    
    