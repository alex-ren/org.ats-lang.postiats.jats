package org.ats_lang.postiats.jats.translator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.FuncPara;
import org.ats_lang.postiats.jats.interpreter.LibFunc;
import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.tree.ATSTreeVisitor;
import org.ats_lang.postiats.jats.tree.ATSdynload0;
import org.ats_lang.postiats.jats.tree.AtsCkIseqz;
import org.ats_lang.postiats.jats.tree.AtsDeref;
import org.ats_lang.postiats.jats.tree.AtsEmpty;
import org.ats_lang.postiats.jats.tree.AtsInsLoad;
import org.ats_lang.postiats.jats.tree.AtsInsMove;
import org.ats_lang.postiats.jats.tree.AtsInsMoveArrpszPtr;
import org.ats_lang.postiats.jats.tree.AtsInsMoveBoxrec;
import org.ats_lang.postiats.jats.tree.AtsInsMoveVoid;
import org.ats_lang.postiats.jats.tree.AtsInsPMove;
import org.ats_lang.postiats.jats.tree.AtsInsStore;
import org.ats_lang.postiats.jats.tree.AtsInsStoreArrpszAsz;
import org.ats_lang.postiats.jats.tree.AtsInsStoreArrpszPtr;
import org.ats_lang.postiats.jats.tree.AtsInsStoreBoxrecOfs;
import org.ats_lang.postiats.jats.tree.AtsInsStoreFltrecOfs;
import org.ats_lang.postiats.jats.tree.AtsInsUpdatePtrInc;
import org.ats_lang.postiats.jats.tree.AtsInsXStore;
import org.ats_lang.postiats.jats.tree.AtsPmvCastFn;
import org.ats_lang.postiats.jats.tree.AtsPmvIntRepNode;
import org.ats_lang.postiats.jats.tree.AtsPmvPtrof;
import org.ats_lang.postiats.jats.tree.AtsPmvPtrofVoid;
import org.ats_lang.postiats.jats.tree.AtsPmvRefArg;
import org.ats_lang.postiats.jats.tree.AtsPmvSimpleCastNode;
import org.ats_lang.postiats.jats.tree.AtsPmvSizeofNode;
import org.ats_lang.postiats.jats.tree.AtsReturn;
import org.ats_lang.postiats.jats.tree.AtsSelArrInd;
import org.ats_lang.postiats.jats.tree.AtsSelArrPtrInd;
import org.ats_lang.postiats.jats.tree.AtsSelBoxRec;
import org.ats_lang.postiats.jats.tree.AtsSelFltRec;
import org.ats_lang.postiats.jats.tree.AtsSelRecsinNode;
import org.ats_lang.postiats.jats.tree.BlockNode;
import org.ats_lang.postiats.jats.tree.DefinitionNode;
import org.ats_lang.postiats.jats.tree.FuncCallNode;
import org.ats_lang.postiats.jats.tree.IdentifierNode;
import org.ats_lang.postiats.jats.tree.IfNode;
import org.ats_lang.postiats.jats.tree.UserFunc;
import org.ats_lang.postiats.jats.tree.ValueNode;
import org.ats_lang.postiats.jats.tree.IfNode.Choice;
import org.ats_lang.postiats.jats.type.ATSEltType;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ATSTypeVisitor;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CodeEmitterJavaVisitor implements ATSTreeVisitor {

    private STGroup m_stg;
    private Map<String, FuncDef> m_funcs;

    public CodeEmitterJavaVisitor(STGroup stg, Map<String, FuncDef> funcs) {
        m_stg = stg;
        m_funcs = funcs;
    }

    @Override
    public Object visit(AtsCkIseqz node) {
        ST st = m_stg.getInstanceOf("ats_ck_iseqz_st");
        st.add("val", node.m_exp.accept(this));
        return st;
    }

    @Override
    public Object visit(AtsDeref node) {
        return node.m_node.accept(this);
    }

    @Override
    public Object visit(ATSdynload0 node) {
        ST st = m_stg.getInstanceOf("ats_dynload0_st");
        st.add("dyn_val", node.m_dyn0);
        return st;
    }

    @Override
    public Object visit(AtsEmpty node) {
        return null;
    }

    @Override
    public Object visit(AtsInsLoad node) {
        // TODO Auto-generated method stub
        return "todo: AtsInsLoad";
    }

    @Override
    public Object visit(AtsInsMoveArrpszPtr node) {
        ST st = m_stg.getInstanceOf("atsins_move_arrpsz_ptr_st");
        st.add("tmp", node.m_tmp);
        st.add("psz", node.m_psz.accept(this));
        return st;
    }

    @Override
    public Object visit(AtsInsMoveBoxrec node) {
        ST st = null;
        if (node.m_tmptype instanceof RefType) {
            st = m_stg.getInstanceOf("atsins_move_boxrec_ref_st");
        } else {
            st = m_stg.getInstanceOf("atsins_move_boxrec_st");
        }
        st.add("tmp", node.m_tmp);
        return st;
    }

    @Override
    public Object visit(AtsInsMove node) {
        Object src = node.m_val.accept(this);
        ATSType srcty = node.m_val.getType();
        ST st = null;

        if (node.m_type instanceof RefType) {
            if (srcty instanceof RefType) {
                ATSType realty = ((RefType) srcty).defType();
                st = m_stg.getInstanceOf("atsins_move_ref_ref_st");
                st.add("tmp", node.m_tmp);
                st.add("val", src);
                st.add("type",
                        realty.accept(new TypeJavaInstanceVisitor(m_stg)));

            } else {
                st = m_stg.getInstanceOf("atsins_move_ref_val_st");
                st.add("tmp", node.m_tmp);
                st.add("val", src);
                st.add("type", srcty.accept(new TypeJavaInstanceVisitor(m_stg)));
            }
        } else {
            if (srcty instanceof RefType) {
                ATSType realty = ((RefType) srcty).defType();
                st = m_stg.getInstanceOf("atsins_move_val_ref_st");
                st.add("tmp", node.m_tmp);
                st.add("val", src);
                st.add("type",
                        realty.accept(new TypeJavaInstanceVisitor(m_stg)));
                st.add("type_java", realty.toString());
            } else {
                st = m_stg.getInstanceOf("atsins_move_val_val_st");
                st.add("tmp", node.m_tmp);
                st.add("val", src);
            }
        }

        return st;
    }

    @Override
    public Object visit(AtsInsMoveVoid node) {
        ST st = m_stg.getInstanceOf("atsins_move_void_st");
        st.add("val", node.m_val.accept(this));
        return st;
    }

    @Override
    public Object visit(AtsInsPMove node) {
        ST st = null;
        ATSType valtype = node.m_val.getType();
        if (valtype instanceof RefType) {
            st = m_stg.getInstanceOf("atsins_pmove_ref_st");
            st.add("tyval", ((RefType)valtype).defType().accept(new TypeJavaInstanceVisitor(m_stg)));
        } else {
            st = m_stg.getInstanceOf("atsins_pmove_st");
        }
        st.add("tmp", node.m_tmp);
        st.add("hit", node.m_hit.accept(new TypeJavaInstanceVisitor(m_stg)));
        st.add("val", node.m_val.accept(this));
        return st;
    }

    @Override
    public Object visit(AtsInsStoreArrpszAsz node) {
        // no-op
        return null;
    }

    @Override
    public Object visit(AtsInsStoreArrpszPtr node) {
        ST st = m_stg.getInstanceOf("atsins_store_arrpsz_ptr_st");
        st.add("tmp", node.m_tmp);
        st.add("tsz", node.m_asz.accept(this));
        st.add("tyelt", node.m_tyelt.accept(new TypeJavaInstanceVisitor(m_stg)));
        return st;
    }

    @Override
    public Object visit(AtsInsStoreBoxrecOfs node) {
        ST st = null;
        if (node.m_ty instanceof RefType) {
            ATSType valTy = node.m_val.getType();
            if (valTy instanceof RefType) {
                ATSType realTy = ((RefType)valTy).defType();
                st = m_stg.getInstanceOf("atsins_store_boxref_ofs_ref_ref_st");
                st.add("labtype", realTy.accept(new TypeJavaInstanceVisitor(m_stg)));
            } else {
                st = m_stg.getInstanceOf("atsins_store_boxref_ofs_ref_val_st");
            }
        } else {
            ATSType valTy = node.m_val.getType();
            if (valTy instanceof RefType) {
                ATSType realTy = ((RefType)valTy).defType();
                st = m_stg.getInstanceOf("atsins_store_boxref_ofs_val_ref_st");
                st.add("labtype", realTy.accept(new TypeJavaInstanceVisitor(m_stg)));
            } else {
                st = m_stg.getInstanceOf("atsins_store_boxref_ofs_val_val_st");
            }
        }
        st.add("tmp", node.m_tmp);
        st.add("lab", node.m_lab);
        st.add("val", node.m_val.accept(this));
        return st;
        
 
    }

    @Override
    public Object visit(AtsInsStoreFltrecOfs node) {
        ST st = null;
        
        ATSType val_ty = node.m_val.getType();
        ATSTypeVisitor tyVisitor = new TypeJavaInstanceVisitor(m_stg);
        if (node.m_ty instanceof RefType) {
            if (val_ty instanceof RefType) {
                st = m_stg.getInstanceOf("atsins_store_fltrec_ofs_ref_ref_st");
            } else {
                st = m_stg.getInstanceOf("atsins_store_fltrec_ofs_ref_val_st");
            }
        } else {
            if (val_ty instanceof RefType) {
                st = m_stg.getInstanceOf("atsins_store_fltrec_ofs_val_ref_st");
            } else {
                st = m_stg.getInstanceOf("atsins_store_fltrec_ofs_val_val_st");
            }
        }

        st.add("tyrec", node.m_tyrec.accept(tyVisitor));
        st.add("tylab", node.m_tyrec.getMember(node.m_lab).accept(tyVisitor));
        st.add("tmp", node.m_tmp);
        st.add("lab", node.m_lab);
        st.add("val", node.m_val.accept(this));
        
        return st;
    }

    @Override
    public Object visit(AtsInsStore node) {
        ST st = null;

        RefType refTy = (RefType) node.m_pmv1.getType();
        ATSType realTy = refTy.defType();

        ATSType srcTy = node.m_pmv2.getType();

        if (srcTy instanceof RefType) {
            st = m_stg.getInstanceOf("atsins_store_ref_ref_st");

        } else {
            st = m_stg.getInstanceOf("atsins_store_ref_val_st");

        }
        st.add("dst", node.m_pmv1.accept(this));
        st.add("src", node.m_pmv2.accept(this));
        st.add("type", realTy.accept(new TypeJavaInstanceVisitor(m_stg)));

        return st;
    }

    @Override
    public Object visit(AtsInsUpdatePtrInc node) {
        ST st = m_stg.getInstanceOf("atsins_update_ptrinc_st");
        st.add("tmp", node.m_tmp);
        st.add("sz", node.m_tyelt.getSize());
        return st;
    }

    @Override
    public Object visit(AtsInsXStore node) {
        // TODO Auto-generated method stub
        return "todo: AtsInsXStore";
    }

    @Override
    public Object visit(AtsPmvCastFn node) {
        ST st = m_stg.getInstanceOf("ats_pvm_castfn_st");
        st.add("exp", node.m_arg.accept(this));
        return st;
    }

    @Override
    public Object visit(AtsPmvIntRepNode node) {
        // TODO Auto-generated method stub
        return "todo: AtsPmvIntRepNode";
    }

    @Override
    public Object visit(AtsPmvPtrof node) {
        ST st = m_stg.getInstanceOf("ats_ptrof_st");
        st.add("lval", node.m_lval);
        return st;
    }

    @Override
    public Object visit(AtsPmvPtrofVoid node) {
        // TODO Auto-generated method stub
        return "todo: AtsPmvPtrofVoid";
    }

    @Override
    public Object visit(AtsPmvRefArg node) {
        return node.m_node.accept(this);
    }

    @Override
    public Object visit(AtsPmvSimpleCastNode node) {
        return node.m_node.accept(this);
    }

    @Override
    public Object visit(AtsPmvSizeofNode node) {
        ST st = m_stg.getInstanceOf("ats_pmv_sizeof_st");
        st.add("sz", node.m_hit.getSize());
        return st;
    }

    @Override
    public Object visit(AtsReturn node) {
        ST st = null;
        ATSType retType = node.m_nodety;
        if (retType instanceof RefType) {
            st = m_stg.getInstanceOf("ats_return_copy_st");
            st.add("val", node.m_exp.accept(this));
            st.add("type",
                    node.getType().accept(new TypeJavaInstanceVisitor(m_stg)));
        } else {
            st = m_stg.getInstanceOf("ats_return_st");
            st.add("val", node.m_exp.accept(this));
        }

        return st;
    }

    @Override
    public Object visit(AtsSelArrInd node) {
        // TODO Auto-generated method stub
        return "todo: AtsSelArrInd";
    }

    @Override
    public Object visit(AtsSelArrPtrInd node) {
        // TODO Auto-generated method stub
        return "todo: AtsSelArrPtrInd";
    }

    @Override
    public Object visit(AtsSelBoxRec node) {
        ST st = null;
        if (node.m_pmv.getType() instanceof RefType) {
            st = m_stg.getInstanceOf("ats_sel_box_rec_ref_st");
        } else {
            st = m_stg.getInstanceOf("ats_sel_box_rec_st");
        }
        st.add("pmv", node.m_pmv.accept(this));
        st.add("lab", node.m_lab);
        st.add("labty", node.getType().toString());
        return st;
    }

    @Override
    public Object visit(AtsSelFltRec node) {
        ST st = null;
        if (node.m_pmv.getType() instanceof RefType) {
//            ats_sel_flt_rec_ref_st(pmv, tylab, tyrec) ::= <<
//                    RefType.SelFltrecOfs(<pmv>, "<tylab>", <tyrec>);
//                    >>
            st = m_stg.getInstanceOf("ats_sel_flt_rec_ref_st");
            st.add("pmv", node.m_pmv.accept(this));
            st.add("lab", node.m_lab);
            st.add("tyrec", node.m_tyrec.accept(new TypeJavaInstanceVisitor(m_stg)));            
        } else {
            st = m_stg.getInstanceOf("ats_sel_flt_rec_st");
//            ats_sel_flt_rec_st(pmv, tylab, lab) ::= <<
//                    (<tylab>)<pmv>.get("<lab>")
//                    >>
            st.add("pmv", node.m_pmv.accept(this));
            st.add("lab", node.m_lab);
            st.add("tylab_java", node.m_tyrec.getMember(node.m_lab).toString());
        }
        
        return st;
    }

    @Override
    public Object visit(AtsSelRecsinNode node) {
        ST st = m_stg.getInstanceOf("ats_sel_recsin_st");
        st.add("pmv", node.m_pmv);
        return st;
    }

    @Override
    public Object visit(BlockNode node) {
        ST st = m_stg.getInstanceOf("block_st");
        for (ATSNode stat : node.m_statements) {
            st.add("bstats", stat.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(DefinitionNode node) {
        if (node.m_ty.equals(VoidType.cType)) {
            return null;
        }

        // gvar_def_st(type, name, init) ::= <<
        // <type> <name> = <init>;
        // >>
        ST st = m_stg.getInstanceOf("gvar_def_st");
        st.add("type", node.m_ty.toString());
        st.add("name", node.m_id);

        if (node.m_ty instanceof ATSEltType || node.m_ty instanceof ArrPtrType) {
            ST stInit = m_stg.getInstanceOf("elt_type_init_val_st");
            st.add("init", stInit);
        } else if (node.m_ty instanceof RefType) {
            ST stInit = m_stg.getInstanceOf("ref_type_init_val_st");
            stInit.add(
                    "realty",
                    ((RefType) node.m_ty).defType().accept(
                            new TypeJavaInstanceVisitor(m_stg)));
            st.add("init", stInit);
        } else {
            ST stInit = m_stg.getInstanceOf("nonref_type_init_val_st");
            stInit.add("ty",
                    node.m_ty.accept(new TypeJavaInstanceVisitor(m_stg)));
            st.add("init", stInit);
        }

        if (node.m_isStat) {
            ST stVarDef = m_stg.getInstanceOf("stats_gvar_st");
            stVarDef.add("gvar", st);
            return stVarDef;

        } else {
            return st;
        }

    }

    @Override
    public Object visit(FuncCallNode node) {
        // fun_call_st (name, arglst) ::= <<
        // <name>(<arglst:{e |<e>}; separator=", ">)
        // >>
        ST st = m_stg.getInstanceOf("fun_call_st");

        String funcName = node.m_id;
        FuncDef funcDef = m_funcs.get(funcName);

        // add class name in front of the function name
        if (funcDef instanceof LibFunc) {
            funcName = ((LibFunc) funcDef).getModuleName() + "." + funcName;
        }
        st.add("name", funcName);

        for (ATSNode para : node.m_paras) {
            ST stArg = null;
            ATSType paraType = para.getType();
            if (paraType instanceof RefType) {
                stArg = m_stg.getInstanceOf("arg_copy_st");
                ATSType realType = ((RefType) paraType).defType();
                stArg.add("type_ins", realType.accept(new TypeJavaInstanceVisitor(m_stg)));
                stArg.add("type_java", realType.toString());
            } else {
                stArg = m_stg.getInstanceOf("arg_st");
            }
            stArg.add("arg", para.accept(this));
            st.add("arglst", stArg);
        }

        return st;

    }

    @Override
    public Object visit(IdentifierNode node) {
        ST st = null;
        if (node.getType().equals(VoidType.cType)) {
            st = m_stg.getInstanceOf("void_exp_st");
            st.add("e", node.m_id);
        } else {
            st = m_stg.getInstanceOf("identifier_st");
            if (node.m_moduleName != "") {
                st.add("id", node.m_moduleName + "." + node.m_id);
            } else {
                st.add("id", node.m_id);
            }
            
        }
        return st;
    }

    @Override
    public Object visit(IfNode node) {
        ST st = m_stg.getInstanceOf("ifstatement_st");

        Iterator<Choice> ifs_iter = node.m_choices.iterator();
        ST if_st = m_stg.getInstanceOf("ifstat_st");
        Choice ifch = ifs_iter.next();
        if_st.add("exp", ifch.m_exp.accept(this));
        if_st.add("block", ifch.m_block.accept(this));
        st.add("aifstat", if_st);

        while (ifs_iter.hasNext()) {
            ST elseif_st = m_stg.getInstanceOf("elseifstat_st");
            Choice elseifch = ifs_iter.next();
            elseif_st.add("exp", elseifch.m_exp.accept(this));
            elseif_st.add("block", elseifch.m_block.accept(this));
            st.add("aelseifstats", elseif_st);
        }

        if (node.m_else != null) {
            ST else_st = m_stg.getInstanceOf("elsestat_st");
            else_st.add("block", node.m_else.accept(this));
            st.add("aelsestat", else_st);
        }

        return st;
    }

    @Override
    public Object visit(ValueNode node) {
        ATSType ty = node.getType();
        ST st = null;
        if (ty.equals(PtrkType.cType)) {
            st = m_stg.getInstanceOf("string_exp_st");
            st.add("str", node.m_text);
        } else if (ty.equals(VoidType.cType)) {
            st = m_stg.getInstanceOf("void_exp_st");
            st.add("e", node.m_text); 
        }
        else {
            st = m_stg.getInstanceOf("atom_exp_st");
            st.add("e", node.m_text);
        }

        return st;
    }

    @Override
    public Object visit(UserFunc node) {
        ST st = m_stg.getInstanceOf("fun_def_st");

        st.add("type", node.getRetType().toString());

        st.add("name", node.getName());

        ST paras_st = m_stg.getInstanceOf("paras_st");
        List<FuncPara> paraLst = node.getParalst();
        for (FuncPara para : paraLst) {
            ST para_st = m_stg.getInstanceOf("para_st");
            para_st.add("type", para.getType());
            para_st.add("name", para.getId());
            paras_st.add("paras", para_st);
        }

        st.add("paras", paras_st);

        st.add("body", node.getBody().accept(this));

        return st;
    }

}
