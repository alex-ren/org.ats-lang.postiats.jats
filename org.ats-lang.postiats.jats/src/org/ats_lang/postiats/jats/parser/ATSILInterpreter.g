tree grammar ATSILInterpreter; 

options {
  language = Java;
  tokenVocab = ATSIL;
  ASTLabelType = CommonTree;
}

@header {
  package org.ats_lang.postiats.jats.parser;
  import org.ats_lang.postiats.jats.tree.*;
  import org.ats_lang.postiats.jats.type.*;
  import org.ats_lang.postiats.jats.value.*;
  import org.ats_lang.postiats.jats.*;
  import org.ats_lang.postiats.jats.interpreter.*;
  
  import java.util.Map;
  import java.util.HashMap;
  import java.util.ArrayList;
  
  
}

@members {
    private Map<String, ATSType> m_types;
    private Map<String, FuncDef> m_funcs;
    
    private void defineType(String id, ATSType type) {
        m_types.put(id, type);
    }
    
    private void defineFunc(UserFunc func) {
        m_funcs.put(func.getName(), func);
    }
    
    public Map<String, ATSType> getTypes() {
        return m_types;
    }
    
    public Map<String, FuncDef> getFuncs() {
        return m_funcs;
    }
    
//    private void setTypes(Map<String, ATSType> types) {
//        m_types = types;
//    }
//    
//    private void setFuncs(Map<String, FuncDef> funcs) {
//        m_funcs = funcs;
//    }
}

// START:rules
program[Map<String, ATSType> types, Map<String, FuncDef> funcs] returns [ProgramNode node]
@init {
  m_types = types;
  m_funcs = funcs;
  ProgramNode pn = new ProgramNode();
  node = pn;
}
    : ^(PROGRAM (  type_def
                 | func_decl  // omit declaration
                 | func_def  {defineFunc($func_def.definition);}
                 | gstat {pn.addStat($gstat.node);}
                 )*
        )
    ;




gstat returns [DefinitionNode node]
    : var_def  {node = $var_def.node;} //    | var_assign {node = $var_assign.node;} no assignment for global variable
    ;


block returns [BlockNode node]
@init {
  BlockNode bn = new BlockNode();
  node = bn;
}
    : ^(BLOCK (bstat {bn.addStat($bstat.node);})*)
    ;
    
bstat returns [ATSNode node]
    : var_def  {node = $var_def.node;}
    | ifstat {node = $ifstat.node;}
    // todo while dowhile
    | atsins {node = $atsins.node;}
    ;

atsins returns [ATSNode node]
    : atsins_load {node = $atsins_load.node;}
    | atsins_store {node = $atsins_store.node;}
    | atsins_store_arrpsz_asz {node = $atsins_store_arrpsz_asz.node;}
    | atsins_store_arrpsz_ptr {node = $atsins_store_arrpsz_ptr.node;}
    | atsins_store_fltrec_ofs {node = $atsins_store_fltrec_ofs.node;}
    | atsins_move {node = $atsins_move.node;}
    | atsins_move_void {node = $atsins_move_void.node;}
    | atsins_pmove {node = $atsins_pmove.node;}
    | atsins_move_arrpsz_ptr {node = $atsins_move_arrpsz_ptr.node;}
    | atsins_update_ptrinc {node = $atsins_update_ptrinc.node;}
    | ats_return {node = $ats_return.node;}
    ;

atsins_load returns [AtsInsLoad node]
    : ^(ATSINS_LOAD dest=exp cont=exp) {node = new AtsInsLoad($dest.node, $cont.node);}
    ;
    
atsins_store returns [AtsInsStore node]
    : ^(ATSINS_STORE dest=exp cont=exp) {node = new AtsInsStore($dest.node, $cont.node);}
    ;
    
atsins_store_arrpsz_asz returns [AtsInsStoreArrpszAsz node]
    : ^(ATSINS_STORE_ARRPSZ_ASZ ID exp) {node = new AtsInsStoreArrpszAsz($ID.text, $exp.node);}
    ;
    
atsins_store_arrpsz_ptr returns [AtsInsStoreArrpszPtr node]
    : ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp) {node = new AtsInsStoreArrpszPtr($ID.text, $atstype.type, $exp.node);}
    ;
    
atsins_store_fltrec_ofs returns [AtsInsStoreFltrecOfs node]
    : ^(ATSINS_STORE_FLTREC_OFS tmp=ID atstype lab=ID exp)
      {node = new AtsInsStoreFltrecOfs($tmp.text, $atstype.type, $lab.text, $exp.node);}
    ;
    
atsins_move returns [AtsInsMove node]
    : ^(ATSINS_MOVE ID exp) {node = new AtsInsMove($ID.text, $exp.node);}
    ;
    
atsins_move_void returns [AtsInsMoveVoid node]
    : ^(ATSINS_MOVE_VOID exp) {node = new AtsInsMoveVoid($exp.node);}
    ;
    
atsins_pmove returns [AtsInsPMove node]
    : ^(ATSINS_PMOVE tmp=exp atstype val=exp) {node = new AtsInsPMove($tmp.node, $atstype.type, $val.node);}
    ;
    
atsins_move_arrpsz_ptr returns [AtsInsMoveArrpszPtr node]
    : ^(ATSINS_MOVE_ARRPSZ_PTR ID exp) {node = new AtsInsMoveArrpszPtr($ID.text, $exp.node);}
    ;
    
atsins_update_ptrinc returns [AtsInsUpdatePtrInc node]
    : ^(ATSINS_UPDATE_PTRINC ID atstype) {node = new AtsInsUpdatePtrInc($ID.text, $atstype.type);} 
    ;

ats_return returns [AtsReturnNode node]
    : ^(ATS_RETURN exp?) {node = new AtsReturnNode($exp.node);}
    ;
    
ifstat returns [IfNode node]
@init {
  IfNode ifnode = new IfNode();
}
@after {
  node = ifnode;
}
    : ^(IFSTAT ifStat[ifnode] (elseIfStat[ifnode])* (elseStat[ifnode])?)
    ;

ifStat [IfNode parent]
    : ^(IF exp block) {parent.addIf($exp.node, $block.node);}
    ;
      
elseIfStat [IfNode parent]
    : ^(IF exp block) {parent.addIf($exp.node, $block.node);}
    ;

elseStat [IfNode parent]
    : ^(ELSE block)  {parent.addElse($block.node);}
    ;

//assignment returns [ATSNode node]
//    : ^(Assign ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
//    ;
    
exp returns [ATSNode node]
    : func_call {node = $func_call.node;}
    | ats_exp {node = $ats_exp.node;}
    | atom_exp {node = $atom_exp.node;}
    ;

ats_exp returns [ATSNode node]
    : ats_cast {node = $ats_cast.node;}
    | ats_empty {node = $ats_empty.node;}
    | ats_simple_cast {node = $ats_simple_cast.node;}
    | ats_sizeof {node = $ats_sizeof.node;}
    | ats_deref {node = $ats_deref.node;}
    | ats_ref_arg {node = $ats_ref_arg.node;}
    | ats_pmv_ptrof {node = $ats_pmv_ptrof.node;}
    | ats_sel_recsin {node = $ats_sel_recsin.node;}
    | ats_sel_flt_rec {node = $ats_sel_flt_rec.node;}
    | ats_sel_arr_ind {node = $ats_sel_arr_ind.node;}
    | ats_sel_box_rec {node = $ats_sel_box_rec.node;}
    ;

ats_cast returns [AtsPmvCastFn node]
    : ^(ATSPMV_CASTFN ID atstype exp) {node = new AtsPmvCastFn($ID.text, $atstype.type, $exp.node);}
    ;
    
ats_empty returns [AtsEmpty node]
    : ATS_EMPTY {node = new AtsEmpty();}
    ;

ats_simple_cast returns [ATSNode node]
    : ^(ATSPMV_INT exp)  {node = new AtsPmvSimpleCastNode(IntType.cType, $exp.node);}
    | ^(ATSPMV_I0NT exp) {node = new AtsPmvSimpleCastNode(IntType.cType, $exp.node);}
    | ^(ATSPMV_F0LOAT exp) {node = new AtsPmvSimpleCastNode(DoubleType.cType, $exp.node);}    
    | ATSPMV_TRUE {node = new ValueNode(BoolType.createTrue());}
    | ATSPMV_FALSE {node = new ValueNode(BoolType.createFalse());}
    | ^(ATSPMV_CHAR exp) {node = new AtsPmvSimpleCastNode(CharType.cType, $exp.node);}
    | ^(ATSPMV_STRING exp) {node = new AtsPmvSimpleCastNode(StringType.cType, $exp.node);}

    ;

ats_sizeof returns [AtsPmvSizeofNode node]
    : ^(ATSPMV_SIZEOF atstype) {node = new AtsPmvSizeofNode($atstype.type);}
    ;
    
ats_deref returns [AtsDerefNode node]
@init {
  ATSType ty = null;
}
    : ^(ATS_DEREF (atstype{ty = $atstype.type;})? exp) {node = new AtsDerefNode(ty, $exp.node);}
    ;

ats_ref_arg returns [AtsPmvRefArg node]
    : ^(ATSPMV_REFARG0 exp) {node = new AtsPmvRefArg($exp.node);}  // neglect refarg
    | ^(ATSPMV_REFARG1 exp) {node = new AtsPmvRefArg($exp.node);}  // neglect refarg
    ;
   
ats_pmv_ptrof returns [AtsPmvPtrofNode node]
    : ^(ATSPMV_PTROF ID) {node = new AtsPmvPtrofNode($ID.text);}
    ;
    
ats_sel_recsin returns [AtsSelRecsinNode node]
      : ^(ATS_SEL_RECSIN pmv=ID atstype lab=ID) {node = new AtsSelRecsinNode($pmv.text, $atstype.type, $lab.text);}
      ;
   
ats_sel_flt_rec returns [AtsSelFltRecNode node]
    : ^(ATS_SEL_FLT_REC pmv=exp atstype lab=ID) {node = new AtsSelFltRecNode($pmv.node, $atstype.type, $lab.text);}
    ;

ats_sel_arr_ind returns [AtsSelArrIndNode node]
    : ^(ATS_SEL_ARR_IND exp atstype ID) {node = new AtsSelArrIndNode($exp.node, $atstype.type, $ID.text);}
    ;

ats_sel_box_rec returns [AtsSelBoxRecNode node]
    : ^(ATS_SEL_BOX_REC exp atstype ID) {node = new AtsSelBoxRecNode($exp.node, $atstype.type, $ID.text);}
    ;

atom_exp returns [ATSNode node]
    : ID     {node = new IdentifierNode($ID.text);}
    | INT    {node = new ValueNode(IntType.fromString($INT.text));}
    | FLOAT  {node = new ValueNode(DoubleType.fromString($FLOAT.text));}
    | CHAR   {node = new ValueNode(CharType.fromString($CHAR.text));}
    | STRING {node = new ValueNode(StringType.fromString($STRING.text));} 
    | Bool   {node = new ValueNode(BoolType.fromString($Bool.text));}
    ;

func_call returns [FuncCallNode node]
    : ^(FUNC_CALL ID explst?) {node = new FuncCallNode($ID.text, $explst.lst);}
    ;

explst returns [List<ATSNode> lst]
@init {
  List<ATSNode> es = new ArrayList<ATSNode>();
  lst = es;
}
    : ^(EXP_LIST (exp {es.add($exp.node);})+)
    ;
    
//var_assign returns [ATSNode node]
//    : ^(ASSIGN ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
//    ;
    
var_def returns [DefinitionNode node]
    : ^(VAR atstype ID) {node = new DefinitionNode($atstype.type, $ID.text);}
    | ^(VAR_VOID atstype ID) {node = new DefinitionNode($atstype.type, $ID.text);}
    ;

atstype returns [ATSType type]
    : ^(TYPE prim_type) {type = $prim_type.type;}
    | ^(TYPE name_type)  {type = $name_type.type;}
    | ^(TYPE kind_decorator name_type) {type = new KindType($kind_decorator.kind, $name_type.type);}
    ;

name_type returns [ATSType type]
    : ID {type = m_types.get($ID.text); 
          if (null == type) {
              System.out.println("ATSILInterpreter::name_type, Type " + $ID.text + " is not provided.");
              throw new Error("ATSILInterpreter::name_type, Type " + $ID.text + " is not provided.");
          }
         }
    ;
    
kind_decorator returns [ATSType.Decorator kind]
    : TYPE_DEC_TYPE {kind = ATSType.Decorator.TYPE;}
    | TYPE_DEC_T0YPE {kind = ATSType.Decorator.T0YPE;}
    ;
    
// todo
prim_type returns [ATSType type]
    : TYPE_INT    {type = IntType.cType;}
    | TYPE_CHAR   {type = CharType.cType;}
    | TYPE_ULINT  {type = ULIntType.cType;}
    | TYPE_BOOL   {type = BoolType.cType;}
    | TYPE_STRING {type = StringType.cType;}
    | TYPE_FLOAT  {type = DoubleType.cType;}
    | TYPE_PTR  {System.out.println("TYPE_PTR not supported");} // todo
    | TYPE_REF  {System.out.println("TYPE_REF not supported");} // todo
    | TYPE_ARRPTR  {System.out.println("TYPE_ARRPTR not supported");} // todo
    ;


func_decl
    : ^(FUNC_DECL ID func_decorator? atstype paralst?)
    ;

para_decorator
    : PARA_TYPE_REF0
    | PARA_TYPE_REF1
    ;
    
func_decorator returns [ATSNode.FunDecorator dec]
    : GLOBAL {dec = ATSNode.FunDecorator.GLOBAL;}
    | STATIC {dec = ATSNode.FunDecorator.STATIC;}
    ;
    
paralst returns [List<FuncPara> paralst]
@init {
  paralst = new ArrayList<FuncPara>();
}
    : ^(PARA_LIST (para {paralst.add($para.para);})+)
    ;

para returns [FuncPara para]
    : paratype ID {para = new FuncPara($paratype.type, $ID.text);}
    ;

paratype returns [ATSType type]
    : ^(PARA_TYPE para_decorator? atstype) {type = $atstype.type;}
    ;
    
func_def returns [UserFunc definition]
    : ^(FUNC_DEF ID func_decorator? atstype paralst? block) 
      {definition = new UserFunc($ID.text, $func_decorator.dec, $atstype.type, $paralst.paralst, $block.node);}
    ;

type_def
    : ^(TYPEDEF ID struct_def[$ID.text]) {defineType($ID.text, $struct_def.type);}   // todo move to struct_def
    ;

struct_def [String name] returns [StructType type]
@init {
  StructType ty = new StructType(name);
  type = ty;
}
    : ^(STRUCT (var_def { DefinitionNode tynode = (DefinitionNode)$var_def.node;
                          ty.addMember(tynode.getID(), tynode.getType());
                         }
                )+
        )
    ;




