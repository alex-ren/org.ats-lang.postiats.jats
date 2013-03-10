tree grammar ATSIL2JavaPass1; 

options {
  language = Java;
  tokenVocab = ATSIL;
  ASTLabelType = CommonTree;
  output = template;
}

@header {
  package org.ats_lang.postiats.jats.translator;

  import org.ats_lang.postiats.jats.type.*;
  import org.ats_lang.postiats.jats.value.*;
  import org.ats_lang.postiats.jats.interpreter.FuncPara;
  import org.ats_lang.postiats.jats.utils.*;
  
  import java.util.Map;
  import java.util.HashMap;
  import java.util.ArrayList;
}

@members {
    private Map<String, ATSType> m_types;
    private Map<String, String> m_libfuncs;
    private ATSScope<ATSType> m_typescope;
    
    private void defineType(String id, ATSType type) {
        m_types.put(id, type);
    }
    
    static enum MainSpec {NOMAIN, MAIN, MAINARGS};
    
    MainSpec m_main = MainSpec.NOMAIN;
}

// START:rules
program [Map<String, ATSType> types, Map<String, String> libfuncs, String filename]  // , Map<String, FuncDef> funcs]
@init {
  m_types = types;
  m_libfuncs = libfuncs;
  m_typescope = new MapScope<ATSType>();
}
    : ^(PROGRAM (stats+=type_def
                 | func_decl  // omit declaration
                 | stats+=func_def
                 | stats+=gstat
                 )*
        ) -> {m_main == MainSpec.NOMAIN}? program_t(stats={$stats}, filename={filename})
          -> {m_main == MainSpec.MAIN}?   program_t(stats={$stats}, filename={filename}, main={%main_no_arg_st()})
          ->                              program_t(stats={$stats}, filename={filename}, main={%main_args_st()})
    ;




gstat
    : var_def -> global_var_st(var={$var_def.st})  //    | var_assign {node = $var_assign.node;} no assignment for global variable
    ;

block
    : ^(BLOCK bstats+=bstat*) -> fun_body_st(bstats={$bstats})
    ;
    
bstat
    : var_def {retval.st = $var_def.st;}
    | ifstat {retval.st = $ifstat.st;}
    // todo while dowhile
    | atsins {retval.st = $atsins.st;}
    ;

atsins
    : atsins_load {retval.st = $atsins_load.st;}
    | atsins_store {retval.st = $atsins_store.st;}
    | atsins_store_arrpsz_asz {retval.st = $atsins_store_arrpsz_asz.st;}
    | atsins_store_arrpsz_ptr {retval.st = $atsins_store_arrpsz_ptr.st;}
    | atsins_store_fltrec_ofs {retval.st = $atsins_store_fltrec_ofs.st;}
    | atsins_move {retval.st = $atsins_move.st;}
    | atsins_move_void {retval.st = $atsins_move_void.st;}
    | atsins_pmove {retval.st = $atsins_pmove.st;}
    | atsins_move_arrpsz_ptr {retval.st = $atsins_move_arrpsz_ptr.st;}
    | atsins_update_ptrinc {retval.st = $atsins_update_ptrinc.st;}
    | ats_return {retval.st = $ats_return.st;}
    ;

atsins_load
    : ^(ATSINS_LOAD dest=exp cont=exp) -> atsins_load_st(dest={$dest.st}, cont={$cont.st})
    ;
    
atsins_store
    : ^(ATSINS_STORE dest=exp cont=exp) -> atsins_store_st(dest={$dest.st}, cont={$cont.st})
    ;
    
atsins_store_arrpsz_asz
    : ^(ATSINS_STORE_ARRPSZ_ASZ ID exp) -> atsins_store_arrpsz_asz_st(tmp={$ID.text}, asz={$exp.st})
    ;
    
atsins_store_arrpsz_ptr
    : ^(ATSINS_STORE_ARRPSZ_PTR ID atstype exp) -> atsins_store_arrpsz_ptr_st(tmp={$ID.text}, tyelt={$atstype.type}, tsz={$exp.st})
    ;
    
atsins_store_fltrec_ofs
    : ^(ATSINS_STORE_FLTREC_OFS tmp=ID atstype lab=ID exp) -> atsins_store_fltrec_ofs_st(tmp={$tmp.text}, tyrec={""}, lab={$lab.text}, val={$exp.st})
    ;
    
atsins_move
    : ^(ATSINS_MOVE ID exp) -> atsins_move_st(tmp={$ID.text}, val={$exp.st})
    ;
    
atsins_move_void
    : ^(ATSINS_MOVE_VOID exp) -> atsins_move_void_st(val={$exp.st})
    ;
    
atsins_pmove
    : ^(ATSINS_PMOVE tmp=exp atstype val=exp) -> atsins_pmove_st(tmp={$tmp.st}, hit={$atstype.type}, val={$val.st})
    ;
    
atsins_move_arrpsz_ptr
    : ^(ATSINS_MOVE_ARRPSZ_PTR ID exp) -> atsins_move_arrpsz_ptr_st(tmp={$ID.text}, psz={$exp.st})
    ;
    
atsins_update_ptrinc
    : ^(ATSINS_UPDATE_PTRINC ID atstype) -> atsins_update_ptrinc_st(tmp={$ID.text}, tyelt={$atstype.type})
    ;

ats_return
    : ^(ATS_RETURN exp) -> ats_return_st(exp={$exp.st})
    | ^(ATS_RETURN_VOID exp) -> ats_return_st()
    ;
    
ifstat

    : ^(IFSTAT ifStat (elifsts+=elseIfStat)* (elseStat)?) -> ifstatement_st(aifstat={$ifStat.st}, aelseifstats={$elifsts}, aelsestat={$elseStat.st})
    ;

ifStat
    : ^(IF exp block) -> ifstat_st(exp={$exp.st}, block={$block.st})
    ;
      
elseIfStat
    : ^(IF exp block) -> elseifstat_st(exp={$exp.st}, block={$block.st})
    ;

elseStat
    : ^(ELSE block) -> elsestat_st(block={$block.st})
    ;

//assignment returns [ATSNode node]
//    : ^(Assign ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
//    ;
    
exp
    : func_call -> {retval.st=$func_call.st;}
    | ats_exp -> {retval.st=$ats_exp.st;}
    | atom_exp {retval.st=$atom_exp.st;}
    ;

ats_exp
    : ats_pvm_castfn -> {retval.st=$ats_pvm_castfn.st;}
    | ats_empty -> {retval.st=$ats_empty.st;}
    | ats_simple_cast -> {retval.st=$ats_simple_cast.st;}
    | ats_pmv_sizeof -> {retval.st=$ats_pmv_sizeof.st;}
    | ats_deref -> {retval.st=$ats_deref.st;}
    | ats_ref_arg -> {retval.st=$ats_ref_arg.st;}
    | ats_pmv_ptrof -> {retval.st=$ats_pmv_ptrof.st;}
    | ats_sel_recsin -> {retval.st=$ats_sel_recsin.st;}
    | ats_sel_flt_rec -> {retval.st=$ats_sel_flt_rec.st;}
    | ats_sel_box_rec -> {retval.st=$ats_sel_box_rec.st;}
//    | ats_sel_arr_ind -> {retval.st=$ats_sel_arr_ind.st;}

    ;

ats_pvm_castfn
    : ^(ATSPMV_CASTFN ID atstype exp) -> ats_pvm_castfn_st(hit={$atstype.type}, exp={$exp.st})
    ;
    
ats_empty
    : ATS_EMPTY ->
    ;

ats_simple_cast
    : ^(ATSPMV_INT exp) {retval.st = $exp.st;}  // no op now
    | ^(ATSPMV_I0NT exp) {retval.st = $exp.st;}  // no op now
    | ^(ATSPMV_F0LOAT exp) {retval.st = $exp.st;}  // no op now
    | ATSPMV_TRUE -> new_prim_st(valuetype={"BoolValue"}, content={"true"})
    | ATSPMV_FALSE -> new_prim_st(valuetype={"BoolValue"}, content={"false"})
    | ^(ATSPMV_CHAR exp) {retval.st = $exp.st;}  // no op now
    | ^(ATSPMV_STRING exp) {retval.st = $exp.st;}  // no op now

    ;

ats_pmv_sizeof
    : ^(ATSPMV_SIZEOF atstype) -> ats_pmv_sizeof_st(type={$atstype.type})
    ;
    
ats_deref
    : ^(ATS_DEREF atstype exp) -> ats_deref_st(pmv={$exp.st}, hit={$atstype.type})
    ;

ats_ref_arg
    : ^(ATSPMV_REFARG0 exp) {retval.st = $exp.st;} // neglect refarg
    | ^(ATSPMV_REFARG1 exp) {retval.st = $exp.st;} // neglect refarg
    ;
   
ats_pmv_ptrof
    : ^(ATSPMV_PTROF ID) -> ats_ptrof_st(lval={$ID.text}, type={m_typescope.getValue($ID.text)})
    ;
    
ats_sel_recsin  // no idea what this is
      : ^(ATS_SEL_RECSIN pmv=ID atstype lab=ID) -> ats_sel_recsin_st(pmv={$pmv.text}, tyrec={$atstype.type}, lab={$lab.text})
      ;
   
ats_sel_flt_rec
    : ^(ATS_SEL_FLT_REC pmv=exp atstype lab=ID) -> ats_sel_flt_rec_st(pmv={$exp.st}, tyrec={$atstype.type}, lab={$ID.text})
    ;

ats_sel_box_rec
    : ^(ATS_SEL_BOX_REC exp atstype ID) -> ats_sel_box_rec_st(pmv={$exp.st}, tyrec={$atstype.type}, lab={$ID.text})
    ;
//ats_sel_arr_ind
//    : ^(ATS_SEL_ARR_IND exp atstype ID)
//    ;


atom_exp
    : ID -> template (id={$ID.text}) "<id>"
    | INT -> new_prim_st(valuetype={"IntValue"}, content={$INT.text})
    | FLOAT -> new_prim_st(valuetype={"DoubleValue"}, content={$FLOAT.text})
    | CHAR -> new_prim_st(valuetype={"CharValue"}, content={$CHAR.text})
    | STRING -> new_prim_st(valuetype={"StringValue"}, content={$STRING.text})
    | Bool -> new_prim_st(valuetype={"BoolValue"}, content={$Bool.text})
    ;

func_call
@init {
String funcname = null;
}
    : ^(FUNC_CALL ID {funcname = m_libfuncs.get($ID.text); 
                      if (funcname == null) {
                          funcname = $ID.text;
                      }
                     } explst?) -> fun_call_st(name={funcname}, explst={$explst.sts})
    ;

explst returns [List<StringTemplate> sts]
@init {
    retval.sts = new ArrayList<StringTemplate>();
}
    : ^(EXP_LIST (exp {retval.sts.add($exp.st);})+) 
    ;
    
//var_assign returns [ATSNode node]
//    : ^(ASSIGN ID exp) {node = new AssignmentNode($ID.text, $exp.node);}
//    ;
    
var_def returns [String name, ATSType type]
    : ^(VAR atstype ID) {retval.name = $ID.text;
                         retval.type = $atstype.type;
                         m_typescope.addValue($ID.text, $atstype.type);
                         } -> var_def_st(type={retval.type}, name={retval.name})
    | ^(VAR_VOID atstype ID)  // no output, means null
    ;

atstype returns [ATSType type]
    : ^(TYPE prim_type) {retval.type = $prim_type.type;}
    | ^(TYPE name_type) {retval.type = $name_type.type;}
    | ^(TYPE kind_decorator name_type) {retval.type = $name_type.type;}
    ;

name_type returns [ATSType type]
    : ID { retval.type = m_types.get($ID.text); }
    ;
    
kind_decorator
    : TYPE_DEC_TYPE
    | TYPE_DEC_T0YPE
    ;
    
// todo
prim_type returns [ATSType type]
    : TYPE_INT {retval.type = IntType.cType;}
    | TYPE_CHAR {retval.type = CharType.cType;}
    | TYPE_ULINT {retval.type = ULIntType.cType;}  
    | TYPE_BOOL {retval.type = BoolType.cType;}   
    | TYPE_STRING {retval.type = StringType.cType;} 
    | TYPE_FLOAT {retval.type = DoubleType.cType;}  
    | TYPE_PTR  {System.out.println("TYPE_PTR not supported");} // todo
    | TYPE_REF  {System.out.println("TYPE_REF not supported");} // todo
    | TYPE_ARRPTR  {System.out.println("TYPE_ARRPTR not supported");} // todo
    ;


func_decl
    : ^(FUNC_DECL ID func_decorator? atstype paralst?)
    ;

para_decorator returns [FuncPara.ParaDecorator para_type]
    : PARA_TYPE_REF0 {retval.para_type = FuncPara.ParaDecorator.REF0;}
    | PARA_TYPE_REF1 {retval.para_type = FuncPara.ParaDecorator.REF1;}
    ;
    
func_decorator
    : GLOBAL
    | STATIC
    ;
    
paralst
    : ^(PARA_LIST (paras+=para)+) -> paras_st(paras={$paras})
    ;

para
    : paratype ID -> para_st(type={$paratype.type}, name={$ID.text})
    ;

paratype returns [ATSType type]
    : ^(PARA_TYPE para_dec=para_decorator? atstype) 
      {if ($para_dec.para_type == FuncPara.ParaDecorator.REF1) {
           retval.type = PtrType.cType;
       } else {
           retval.type = $atstype.type;
       }
      }
    ;
    
func_def
@init {
// add scope for the function body 
m_typescope = m_typescope.newScope();
}
@final {
// remove the scope since we leave the function body
m_typescope = m_typescope.getParent();
}
    : ^(FUNC_DEF ID {if ($ID.text.equals("mainats")) {m_main = MainSpec.MAIN;}}
                 func_decorator? atstype (paralst{m_main = MainSpec.MAINARGS;})? block) 
      -> fun_def_st(type={$atstype.type}, name={$ID.text}, paras={$paralst.st}, body={$block.st})
    ;

type_def
    : ^(TYPEDEF ID struct_def[$ID.text]) {defineType($ID.text, $struct_def.type);}
       -> class_st(cls_name={$ID.text}, cls_desc={"line " + $ID.line}, body={$struct_def.st})
    ;

struct_def [String name] returns [ATSType type]
@init{
Map<String, ATSType> structMap = new HashMap<String, ATSType>();
StructType ty = new StructType(name);
retval.type = ty;
}
    : ^(STRUCT (var_def { if ($var_def.name != null) {
                          ty.addMember($var_def.name, $var_def.type);
                          structMap.put($var_def.name, $var_def.type);
                          } 
                        }
                )+
        ) -> class_body_st(name_type_map={structMap}, size={ATSTypeUtils.calcSize(structMap)})
    ;

