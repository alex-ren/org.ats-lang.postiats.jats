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
  import org.ats_lang.postiats.jats.utils.*;
  
  import java.util.Map;
  import java.util.HashMap;
  import java.util.ArrayList;
  
  
}

@members {
    private Program m_prog;
    private ATSScope<ATSType> m_tyscope;  // name of values and corresponding types

}

// START:rules
program[Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> gvscope, ATSScope tyscope] returns [Program prog]
@init {
  m_prog = new Program(types, funcs, gvscope);
  prog = m_prog; 
  
  m_tyscope = tyscope;  // mapping of value to type
}
    : ^(PROGRAM gstat* main_impl?)
    ;

gstat
    : type_def
    | func_decl
    | func_def  {m_prog.defineFunc($func_def.definition);}
    | var_def {m_prog.addStat($var_def.node);}
    | ats_dyn_cst
    | ats_dyn_load1
    ;

main_impl
    : ^(ATSMAIN ID func_call atsmain) {m_prog.setMain($ID.text, $func_call.node, $atsmain.mainName);}
    ;

atsmain returns [String mainName]
    : ATSmainats_void_0 {mainName = $ATSmainats_void_0.text;}
    | ATSmainats_argc_argv_0 {mainName = $ATSmainats_argc_argv_0.text;}
    | ATSmainats_argc_argv_envp_0 {mainName = $ATSmainats_argc_argv_envp_0.text;}
    | ATSmainats_void_int {mainName = $ATSmainats_void_int.text;}
    | ATSmainats_argc_argv_int {mainName = $ATSmainats_argc_argv_int.text;}
    | ATSmainats_argc_argv_envp_int {mainName = $ATSmainats_argc_argv_envp_int.text;}
    ;
    
// Simply ignore ats_dyn_load1    
ats_dyn_load1
    : ^(ATSdynload1 ID)
    ;
     
ats_dyn_cst
    : ^(ATSdyncst_mac ID) 
    | ^(ATSdyncst_castfn ID)
    | ^(ATSdyncst_extfun ID atstypelst atstype) {m_tyscope.addValue($ID.text, new FuncType($atstype.type, null));}
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
    | atsins_xstore {node = $atsins_xstore.node;}
    | atsins_store_arrpsz_asz {node = $atsins_store_arrpsz_asz.node;}
    | atsins_store_arrpsz_ptr {node = $atsins_store_arrpsz_ptr.node;}
    | atsins_store_fltrec_ofs {node = $atsins_store_fltrec_ofs.node;}
    | atsins_store_boxrec_ofs {node = $atsins_store_boxrec_ofs.node;}
    | atsins_move {node = $atsins_move.node;}
    | atsins_move_void {node = $atsins_move_void.node;}
    | atsins_move_boxrec {node = $atsins_move_boxrec.node;}
    | atsins_pmove {node = $atsins_pmove.node;}
    | atsins_move_arrpsz_ptr {node = $atsins_move_arrpsz_ptr.node;}
    | atsins_update_ptrinc {node = $atsins_update_ptrinc.node;}
    | ats_return {node = $ats_return.node;}
    | ats_return_void {node = $ats_return_void.node;}
    | ats_dyn_load0 {node = $ats_dyn_load0.node;} 
    | ats_dyn_load_set {node = $ats_dyn_load_set.node;}
    ; 
 
atsins_load returns [AtsInsLoad node]
    // : ^(ATSINSload dest=exp cont=exp) {node = new AtsInsLoad($dest.node, $cont.node);}
    : ^(ATSINSload dest=ID cont=exp) {node = new AtsInsLoad($ID.text, m_tyscope.getValue($ID.text), $cont.node);}
    ;
    
atsins_store returns [AtsInsStore node]
    : ^(ATSINSstore dest=exp cont=exp) {node = new AtsInsStore($dest.node, $cont.node);}
    ;
    
atsins_xstore returns [AtsInsXStore node]
    : ^(ATSINSxstore ID pmv1=exp pmv2=exp) {node = new AtsInsXStore((ATSReferableType)m_tyscope.getValue($ID.text), $ID.text, $pmv1.node, $pmv2.node);}
    ;

atsins_store_arrpsz_asz returns [AtsInsStoreArrpszAsz node]
    : ^(ATSINS_STORE_ARRPSZ_ASZ ID exp) {node = new AtsInsStoreArrpszAsz($ID.text, $exp.node);}
    ;
    
atsins_store_arrpsz_ptr returns [AtsInsStoreArrpszPtr node]
    : ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp) {node = new AtsInsStoreArrpszPtr(m_tyscope.getValue($ID.text), $ID.text, (ATSReferableType)$atstype.type, $exp.node);}
    ;
    
atsins_store_fltrec_ofs returns [AtsInsStoreFltrecOfs node]
    : ^(ATSINS_STORE_FLTREC_OFS tmp=ID atstype lab=ID exp)
      {node = new AtsInsStoreFltrecOfs(m_tyscope.getValue($tmp.text), $tmp.text, $atstype.type, $lab.text, $exp.node);}
    ;
    
atsins_store_boxrec_ofs returns [AtsInsStoreBoxrecOfs node]
    : ^(ATSINSstore_boxrec_ofs tmp=ID atstype lab=ID exp)
      {node = new AtsInsStoreBoxrecOfs(m_tyscope.getValue($tmp.text), $tmp.text, $atstype.type, $lab.text, $exp.node);}
    ;    
    
atsins_move returns [AtsInsMove node]
    : ^(ATSINS_MOVE ID exp) {node = new AtsInsMove(m_tyscope.getValue($ID.text), $ID.text, $exp.node);}
    ;
    
atsins_move_void returns [AtsInsMoveVoid node]
    : ^(ATSINS_MOVE_VOID exp) {node = new AtsInsMoveVoid($exp.node);}
    ;
    
atsins_move_boxrec returns [AtsInsMoveBoxrec node]
    : ^(ATSINSmove_boxrec ID atstype) {node = new AtsInsMoveBoxrec(m_tyscope.getValue($ID.text), $ID.text, $atstype.type);}
    ;
    
atsins_pmove returns [AtsInsPMove node]
    : ^(ATSINS_PMOVE tmp=ID atstype val=exp) {node = new AtsInsPMove(m_tyscope.getValue($ID.text), $ID.text, (ATSReferableType)$atstype.type, $val.node);}
    ;
    
atsins_move_arrpsz_ptr returns [AtsInsMoveArrpszPtr node]
    : ^(ATSINS_MOVE_ARRPSZ_PTR ID exp) {node = new AtsInsMoveArrpszPtr(m_tyscope.getValue($ID.text), $ID.text, $exp.node);}
    ;
    
atsins_update_ptrinc returns [AtsInsUpdatePtrInc node]
    : ^(ATSINS_UPDATE_PTRINC ID atstype) {node = new AtsInsUpdatePtrInc(m_tyscope.getValue($ID.text), $ID.text, $atstype.type);} 
    ;

ats_return returns [AtsReturn node]
    : ^(ATS_RETURN exp) {node = new AtsReturn($exp.node);}
    ;
    
ats_return_void returns [AtsReturn node]
@init {
  ATSNode retnode = new ValueNode(VoidType.cType, SingletonValue.VOID);
}
    : ^(ATS_RETURN_VOID (exp{retnode = $exp.node;})?) {node = new AtsReturn(retnode);}
    ;

ats_dyn_load0 returns [ATSNode node]
    : ^(ATSdynload0 ID)  {node = new ATSdynload0(m_tyscope, $ID.text);}
    ;

ats_dyn_load_set returns [ATSNode node]
    : ^(ATSdynloadset ID)  {node = new AtsInsMove(IntType.cType0, $ID.text, new ValueNode(IntType.cType0, IntType.fromString("1")));}
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
    
    | ats_empty {node = $ats_empty.node;}
    | ats_pvm_castfn {node = $ats_pvm_castfn.node;}
    | ats_simple_cast {node = $ats_simple_cast.node;}
    | ats_pmv_sizeof {node = $ats_pmv_sizeof.node;}
    | ats_deref {node = $ats_deref.node;}
    | ats_ref_arg {node = $ats_ref_arg.node;}
    | ats_pmv_ptrof {node = $ats_pmv_ptrof.node;}
    | ats_pmv_ptrof_void {node = $ats_pmv_ptrof_void.node;}
    | ats_sel_recsin {node = $ats_sel_recsin.node;}
    | ats_sel_flt_rec {node = $ats_sel_flt_rec.node;}
    | ats_sel_box_rec {node = $ats_sel_box_rec.node;}    
    | ats_sel_arr_ind {node = $ats_sel_arr_ind.node;}
    | ats_sel_arrptr_ind {node = $ats_sel_arrptr_ind.node;}
    | ats_ck_iseqz {node = $ats_ck_iseqz.node;}   
    
    | atom_exp {node = $atom_exp.node;}
    | ats_cst_pmy {node = $ats_cst_pmy.node;}
    ;
    
ats_cst_pmy returns [ATSNode node]
    : ^(ATSCSTSPmyfil exp) {node = $exp.node;}
    | ^(ATSCSTSPmyloc exp) {node = $exp.node;}
    ;    

ats_ck_iseqz returns [AtsCkIseqz node]
    : ^(ATSCKiseqz exp) {node = new AtsCkIseqz($exp.node);}
    ;

ats_pvm_castfn returns [AtsPmvCastFn node]
    : ^(ATSPMV_CASTFN ID atstype exp) {node = new AtsPmvCastFn($ID.text, $atstype.type, $exp.node);}
    ;
    
ats_empty returns [AtsEmpty node]
    : ATS_EMPTY {node = new AtsEmpty();}
    ;

ats_simple_cast returns [ATSNode node]
    : ^(ATSPMV_INT exp)  {node = new AtsPmvSimpleCastNode(IntType.cType0, $exp.node);}
    | ^(ATSPMVintrep exp) {node = new AtsPmvIntRepNode($exp.node);}
   
    | ATSPMV_TRUE {node = new ValueNode(BoolType.cType0, true);}
    | ATSPMV_FALSE {node = new ValueNode(BoolType.cType0, false);}
    
    | ^(ATSPMV_CHAR exp) {node = new AtsPmvSimpleCastNode(CharType.cType0, $exp.node);}
    | ^(ATSPMV_FLOAT exp) {node = new AtsPmvSimpleCastNode(DoubleType.cType0, $exp.node);}         
    | ^(ATSPMV_STRING exp) {node = new AtsPmvSimpleCastNode(PtrkType.cType, $exp.node);}
    
    | ^(ATSPMV_I0NT exp) {node = new AtsPmvSimpleCastNode(IntType.cType0, $exp.node);}
    | ^(ATSPMV_F0LOAT exp) {node = new AtsPmvSimpleCastNode(DoubleType.cType0, $exp.node);}     

    ;

ats_pmv_sizeof returns [AtsPmvSizeofNode node]
    : ^(ATSPMV_SIZEOF atstype) {node = new AtsPmvSizeofNode($atstype.type);}
    ;
    
ats_deref returns [AtsDeref node]
    : ^(ATS_DEREF atstype exp) {node = new AtsDeref((ATSReferableType)$atstype.type, $exp.node);}
    ;

ats_ref_arg returns [AtsPmvRefArg node]
    : ^(ATSPMV_REFARG0 exp) {node = new AtsPmvRefArg($exp.node);}  // neglect refarg
    | ^(ATSPMV_REFARG1 exp) {node = new AtsPmvRefArg($exp.node);}  // neglect refarg
    ;
   
ats_pmv_ptrof returns [AtsPmvPtrof node]
    : ^(ATSPMVptrof ID) {node = new AtsPmvPtrof(m_tyscope.getValue($ID.text), $ID.text);}
    ;
    
ats_pmv_ptrof_void returns [AtsPmvPtrofVoid node]
    : ^(ATSPMVptrof_void ID) {node = new AtsPmvPtrofVoid(m_tyscope.getValue($ID.text), $ID.text);}
    ;    
    
ats_sel_recsin returns [AtsSelRecsinNode node]
      : ^(ATSselrecsin pmv=ID atstype lab=ID) {node = new AtsSelRecsinNode($pmv.text, $atstype.type, $lab.text);}
      ;
   
ats_sel_flt_rec returns [AtsSelFltRec node]
    : ^(ATSselfltrec pmv=exp atstype lab=ID) {node = new AtsSelFltRec($pmv.node, (StructType)$atstype.type, $lab.text);}
    ;

ats_sel_box_rec returns [AtsSelBoxRec node]
    : ^(ATSselboxrec exp atstype ID) {node = new AtsSelBoxRec($exp.node, (StructType)$atstype.type, $ID.text);}
    ;
    
ats_sel_arr_ind returns [AtsSelArrInd node]
    : ^(ATSselarrind exp atstype ID) {node = new AtsSelArrInd($exp.node, $atstype.type, $ID.text);}
    ;

ats_sel_arrptr_ind returns [AtsSelArrPtrInd node] 
    : ^(ATSselarrptrind pmv=exp atstype lab=exp) {node = new AtsSelArrPtrInd($pmv.node, $atstype.type, $lab.node);}
    ;


atom_exp returns [ATSNode node]
    : ID     {node = new IdentifierNode(m_tyscope.getValue($ID.text), $ID.text);}
    | INT    {node = new ValueNode(IntType.cType0, IntType.fromString($INT.text));}
    | FLOAT  {node = new ValueNode(DoubleType.cType0, DoubleType.fromString($FLOAT.text));}
    | CHAR   {node = new ValueNode(CharType.cType0, CharType.fromString(LiteralUtils.getCharEcsaped($CHAR.text)));}
    | STRING {node = new ValueNode(PtrkType.cType, StringType.fromString(LiteralUtils.getStringEcsaped($STRING.text)));} 
    | BOOL   {node = new ValueNode(BoolType.cType0, BoolType.fromString($BOOL.text));}
    ;

func_call returns [FuncCallNode node]
    : ^(FUNC_CALL ID explst?) {/*System.out.println("func call " + $ID.text);*/ node = new FuncCallNode(m_tyscope.getValue($ID.text), $ID.text, $explst.lst);}
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
    : ^(VAR atstype ID) {node = new DefinitionNode(m_tyscope, $atstype.type, $ID.text);}
    | ^(VAR_VOID atstype ID) {node = new DefinitionNode(m_tyscope, $atstype.type, $ID.text);}
    ;
 
atstypelst
    : ^(TYPE_LIST atstype+)
    ;
    
atstype returns [ATSType type]
    : // ^(TYPE prim_type) {type = $prim_type.type;}
      ^(TYPE name_type)  {type = $name_type.type;}
    | ^(TYPE kind_decorator name_type) {type = $name_type.type;}  // We don't handle decorator now. {type = new KindType($kind_decorator.kind, $name_type.type);}
    | ^(TYPE TYPE_ARR atstype) {type = PtrkType.cType;}  // array is PtrkType, here we lost some type information.
    ;
 
name_type returns [ATSType type]
    : ID {type = m_prog.getType($ID.text); 
          if (null == type) {
              System.out.println("ATSILInterpreter::name_type, Type " + $ID.text + " is not provided.");
              throw new Error("ATSILInterpreter::name_type, Type " + $ID.text + " is not provided.");
          }
         }
    ;
    
kind_decorator returns [ATSKindType.Decorator kind]
    : TYPE_DEC_TYPE {kind = ATSKindType.Decorator.TYPE;}
    | TYPE_DEC_T0YPE {kind = ATSKindType.Decorator.T0YPE;}
    ;
    
//// todo
//prim_type returns [ATSType type]
//    : TYPE_INT    {type = IntType.cType;}
//    | TYPE_CHAR   {System.out.println("TYPE_CHAR not supported");} // todo
//    | TYPE_ULINT  {System.out.println("TYPE_ULINT not supported");} // todo
//    | TYPE_BOOL   {System.out.println("TYPE_BOOL not supported");} // todo
//    | TYPE_STRING {System.out.println("TYPE_STRING not supported");} // todo
//    | TYPE_FLOAT  {System.out.println("TYPE_FLOAT not supported");} // todo
//    | TYPE_PTR  {System.out.println("TYPE_PTR not supported");} // todo
//    | TYPE_REF  {System.out.println("TYPE_REF not supported");} // todo
//    | TYPE_ARRPTR  {System.out.println("TYPE_ARRPTR not supported");} // todo
//    ;

func_decl
    : ^(FUNC_DECL ID func_decorator? atstype paralst[null]?) {m_tyscope.addValue($ID.text, new FuncType($atstype.type, $paralst.paralst));}
    ;

para_decorator returns [FuncPara.ParaDecorator dec]
    : PARA_TYPE_REF0 {dec = FuncPara.ParaDecorator.REF0;}
    | PARA_TYPE_REF1 {dec = FuncPara.ParaDecorator.REF1;}
    ;
    
func_decorator returns [ATSNode.FunDecorator dec]
    : GLOBAL {dec = ATSNode.FunDecorator.GLOBAL;}
    | STATIC {dec = ATSNode.FunDecorator.STATIC;}
    ;
    
paralst [ATSScope<ATSType> tyscope] returns [List<FuncPara> paralst]
@init {
  paralst = new ArrayList<FuncPara>();
}
    : ^(PARA_LIST (para[tyscope] {paralst.add($para.para);})+)
    ;

para [ATSScope<ATSType> tyscope] returns [FuncPara para]
    : paratype ID? {
        if (tyscope != null) {
          para = new FuncPara($paratype.type, $ID.text);
          m_tyscope.addValue($ID.text, $paratype.type);
        }
      }
    ;

paratype returns [ATSType type]
    : ^(PARA_TYPE para_decorator? atstype) 
      {type = $atstype.type;
       if ($para_decorator.dec == FuncPara.ParaDecorator.REF1) {
           type = PtrkType.cType;  // type information is lost
       }
      }
    ;
    
func_def returns [UserFunc definition]
@init {
// add scope for the function body 
m_tyscope = m_tyscope.newScope();
}

@after {
// remove the scope since we leave the function body
m_tyscope = m_tyscope.getParent();
m_tyscope.addValue(definition.getName(), new FuncType(definition.getRetType(), definition.getParalst()));

}
    : ^(FUNC_DEF ID func_decorator? atstype paralst[m_tyscope]? block) 
      {definition = new UserFunc($ID.text, $func_decorator.dec, $atstype.type, $paralst.paralst, $block.node);}
    ;

type_def
    : ^(TYPEDEF ID struct_def[$ID.text]) {m_prog.defineType($ID.text, $struct_def.type);}   // todo move to struct_def
    ;

struct_def [String name] returns [StructType type]
@init {
  StructType ty = new StructType(name);
  type = ty;
}
    : ^(STRUCT (^(VAR atstype ID) { ty.addMember($ID.text, (ATSReferableType)$atstype.type);
                         }
                )+
        )
    ;





