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
 

rule returns [ProgramTree prog]
    : ^(PROGRAM gdecs) {prog = new ProgramTree($gdecs.gdecs);}
    ;

exp returns [IExp node]
    : lam_exp {node = $lam_exp.node;}
    | if_exp {node = $if_exp.node;}
    | app_exp {node = $app_exp.node;}
    | let_exp {node = $let_exp.node;}
    | atom_exp {node = $atom_exp.node;}
    | tuple_exp {node = $tuple_exp.node;}
    ;
    
tuple_exp returns [ExpTuple node]
    : ^(TUPLE exp explst) {node = new ExpTuple($exp.node, $explst.explst);}
    ;

lam_exp returns [ExpLam node]
    : ^(LAM paralst exp) {node = new ExpLam($paralst.paralst, $exp.node);}
    ;

if_exp returns [ExpIf node]
    : ^(IF cond=exp btrue=exp bfalse=exp) {node = new ExpIf($cond.node, $btrue.node, $bfalse.node);}
    ;
    
app_exp returns [ExpApp node]
    : ^(APP exp explst) {node = new ExpApp($exp.node, $explst.explst);}
    ;
    
let_exp returns [ExpLet node]
    : ^(LET decs exp) {node = new ExpLet($decs.decs, $exp.node);}
    ;
     
atom_exp returns [IExp node]
    : INT {node = new ExpAtom($INT.text);}
//    | FLOAT
//    | CHAR
    | STRING {node = new ExpAtom($STRING.text);}
    | BOOL {node = new ExpAtom($BOOL.text);}
    | id_exp {node = $id_exp.node;}
    | NULL {node = ExpTuple.Void;}
    ;
    
id_exp returns [ExpId node]
    : ID {node = new ExpId($ID.text);} 
    ;

explst returns [List<IExp> explst]
@init {
  explst = new ArrayList<IExp>();
}
    : ^(EXPLST (exp{explst.add($exp.node);})*) 
    ;

gdecs returns [List<IDec> gdecs]
@init {
  gdecs = new ArrayList<IDec>();
}
    : (gdec {gdecs.add($gdec.node);})* 
    ;

gdec returns [IDec node]
    : ^(Var ID exp?) {node = new DecVarDef(new ExpId($ID.text), $exp.node);}
    | ^(Var ID index) {node = new DecVarArrayDef(new ExpId($ID.text), $index.size, EType.eInteger);}
//    | ^(VarObj ID exp?) {node = new VarDef(new ExpId($ID.text), $exp.node, Type.eValue);}  // not necessary
//    | ^(VarObj ID index) {node = new VarArrayDef(new ExpId($ID.text), $index.size, Type.eValue);}   // I decide not to allow such array of boxed values.
    | ^(Val pat=exp v=exp) {node = new DecValDef($pat.node, $v.node);}
    | ^(ASSIGN ID exp) {node = new DecVarAssign(new ExpId($ID.text), $exp.node);}
    | fungroup {node = new DecFunGroup($fungroup.funLst);}
    ;
    
index returns [int size]
    : ^(INDEX INT) {size = Integer.parseInt($INT.text);}
    ;

decs returns [List<IDec> decs]
@init {
  decs = new ArrayList<IDec>();
}
    : ^(DECLST (dec {decs.add($dec.node);})*) 
    ;
    
dec returns [IDec node]
    : ^(Val pat=exp v=exp) {node = new DecValBind($pat.node, $v.node);}
    | ^(ASSIGN ID exp) {node = new DecVarAssign(new ExpId($ID.text), $exp.node);}
    | fungroup {node = new DecFunGroup($fungroup.funLst);}
    ; 

fungroup returns [List<DecFunDef> funLst]
@init {
  funLst = new ArrayList<DecFunDef>();
}
    : ^(FUNGROUP (fundef{funLst.add($fundef.node);})+)
    ;

fundef returns [DecFunDef node]
    : ^(FUN id_exp paralst exp) {node = new DecFunDef($id_exp.node, $paralst.paralst, $exp.node);}
    ;
    
paralst returns [List<ExpId> paralst]
@init {
  paralst = new ArrayList<ExpId>();
}
    : ^(PARALST (ID{paralst.add(new ExpId($ID.text));})*) 
    ;


    
    
    