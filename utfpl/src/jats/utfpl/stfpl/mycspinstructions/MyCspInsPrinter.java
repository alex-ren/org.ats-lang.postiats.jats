package jats.utfpl.stfpl.mycspinstructions;


public class MyCspInsPrinter {
    public String print(ProgramMyCspIns prog) {
        return "MyCspInstructionPrinter: todo ......................";
    }

//implements IMyCspInsVisitor {
//    private STGroup m_stg;
//    
//    public MyCspPrinter() {
//        URL fileURL = this.getClass().getResource("/jats/utfpl/csps/csps.stg");
//        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');    
//    }
//
//    public String printProgram(ProgramMyCspIns inputProg) {
//        ST st = print(inputProg);
//        
//        return st.render(80);
//    }
//    
//    private ST printMain(List<MyCspGroup> body) {
//        // main_proc_st(mainlab, body) ::= <<
//        ST st = m_stg.getInstanceOf("main_proc_st");
//        st.add("mainlab", "main");
//        st.add("body", print(body));
//        return st;        
//    }
//    
//
//    public ST print(FunctionMyCsp proc) {
//        // proc_def_st(lab, paras, body) ::= <<
//        ST st = m_stg.getInstanceOf("proc_def_st");
//        st.add("lab", proc.m_name);
//        
//        for (MyCspTempID para: proc.m_paras) {
//            st.add("paras", para.accept(this));
//        }
//        
//        st.add("body", print(proc.m_body));
//        
//        return st;
//    }
//
//    public ST print(ProgramMyCspIns prog) {
//        // program_st(gvarlst, proclst, mainproc, mainlab, extcodelst) ::= <<
//        ST st = m_stg.getInstanceOf("program_st");
//        for (VariableInfo gv: prog.m_globalVars) {
//            PATType ty = gv.getMCSId().getType();
//            if (ty instanceof PATTypeArray) {
//                ST st_gv = m_stg.getInstanceOf("garray_def_st");
//                st_gv.add("gvar", gv.getMCSId());
//                st_gv.add("sz", ((PATTypeArray)ty).getSize());
//
//                st.add("gvarlst", st_gv);
//                
//            } else {
//                ST st_gv = m_stg.getInstanceOf("gvar_def_st");
//                st_gv.add("gvar", gv.getMCSId());
//
//                st.add("gvarlst", st_gv);
//            }
//            
//        }
//        
//        for (GlobalExtCode extCode: prog.m_extCodeLst) {
//            st.add("extcodelst", extCode.m_content);
//        }
//        
//        for (FunctionMyCsp proc: prog.m_procLst) {
//            st.add("proclst", print(proc));
//        }
//        
//        st.add("mainproc", printMain(prog.m_main));
//        st.add("mainlab", "main");
//        
//        return st;
//    }
//
//
//    private ST print(List<MyCspGroup> blks) {
//        // blk_lst_st(blklst) ::= <<
//        ST st = m_stg.getInstanceOf("blk_lst_st");
//        ListIterator<MyCspGroup> iter = blks.listIterator();
//        while (iter.hasNext()) {
//            st.add("blklst", iter.next().accept(this));
//        }
//        return st;
//    }
//
//    @Override
//    public Object visit(GrpCond blk) {
//        ST st = m_stg.getInstanceOf("cond_block_st");
//        // cond_block_st(cond, btrue, bfalse) ::= <<
//        st.add("cond", blk.m_cond.accept(this));
//        st.add("btrue", print(blk.m_tb));
//        st.add("bfalse", print(blk.m_fb));
//        return st;
//    }
//
//    @Override
//    public Object visit(GrpEvent blk) {
//        ST st = m_stg.getInstanceOf("event_block_st");
//        for (MyCspInstruction ins: blk.m_inslst) {
//            st.add("inslst", ins.accept(this));
//        }
//        return st;
//    }
//
//    @Override
//    public Object visit(GrpProc blk) {
//        // process_call_block_st(lab) ::= <<
//        ST st = m_stg.getInstanceOf("process_call_block_st");
//        st.add("lab", blk.m_funlab);
//        return st;
//    }
//    
//
//    @Override
//    public Object visit(CIProcCallPrelogue node) {
//        // process_call_ins_prelogue_st(args, tail) ::= <<
//        ST st = m_stg.getInstanceOf("process_call_ins_prelogue_st");
//        for (IMyCspTemp arg: node.m_args) {
//            st.add("args", arg.accept(this));
//        }
//        st.add("tail", node.m_isTail);
//        
//        return st;
//    }
//
//    @Override
//    public Object visit(CIProcCallEpilog node) {
//        // process_call_ins_epilogue_st(lab, holder, escape) ::= <<
//        ST st = m_stg.getInstanceOf("process_call_ins_epilogue_st");
//        st.add("lab", node.m_funlab);
//        st.add("holder", node.m_holder);
//        st.add("escape", node.m_holder.isEscaped());
//        return st;
//    }
//
//
//    @Override
//    public Object visit(CIMove ins) {
//        ST st = null;
//       // move_ins_st(dst, src, v, escape, ret) ::= <<
//        st = m_stg.getInstanceOf("move_ins_st");
////        st.add("v", ins.m_holder);
//
//        st.add("escape", ins.m_holder.isEscaped());
//        st.add("dst", ins.m_holder.accept(this));
//        st.add("src", ins.m_vp.accept(this));
//        return st;
//    }
//
//    @Override
//    public Object visit(CIFunCall ins) {
//        ST st = null;
//        // fun_call_ins_st(dst, lab, args, escape) ::= <<
//        st = m_stg.getInstanceOf("fun_call_ins_st");
////        st.add("v", ins.m_ret);
//        st.add("escape", ins.m_ret.isEscaped());
////        else if (ins.isRet()) {
////            st.add("ret", true);
////        }
//
//        st.add("dst", ins.m_ret.accept(this));
//        st.add("lab", ins.m_funlab);
//        
//        for (IMyCspTemp arg: ins.m_args) {
//            st.add("args", arg.accept(this));
//        }
//        return st;
//    }
//
//    @Override
//    public Object visit(MyCspTempID v) {
//        ST st = null;
//        if (v.getTID().isGlobal()) {  // global variable / value
//            st = m_stg.getInstanceOf("global_id_st");
//            st.add("id", v);
//        } else {  // not global
//            if (v.isDefinition()) {
//                if (v.isPara()) {
//                    // para_def_st(para, escape, loc) ::= <<
//                    st = m_stg.getInstanceOf("para_def_st");
//                	st.add("para", v);
//                	st.add("loc", v.getStackInfo());
//                	st.add("escape", v.isEscaped());
//                    
//                } else {
//                    st = m_stg.getInstanceOf("val_def_st");
//                    st.add("v", v);
//                }
//
//            } else {  // this is the usage
//                if (null == v.getStackInfo()) {
//                    st = m_stg.getInstanceOf("val_use_name_st");
//                    st.add("v", v);
//                } else {
//                    if (v.getTID().isBool()) {
//                        st = m_stg.getInstanceOf("val_use_stack_bool_st");
//                    } else {
//                        st = m_stg.getInstanceOf("val_use_stack_st");
//                    }
//                    st.add("v", v);
//                    st.add("loc", v.getStackInfo());
//                }
//            }
//        }
//        return st;
//    }
//
//    @Override
//    public Object visit(MyCspTempVal v) {
//        ST st = null;
//        switch (v.m_type)
//        {
//            case atom:
//                // atom_val_st(v) ::= <<
//                st = m_stg.getInstanceOf("atom_val_st");
//                st.add("v", v.getAtomValue());
//                return st;
//            case tuple:
//                st = m_stg.getInstanceOf("tuple_val_st");
//                if (v.getTupleValue() == TupleValue.cNone) {
//                    return st;
//                } else {
//                    throw new Error("Not supported");
//                }
//            default:
//                throw new Error("shall not happen");
//        }
//
//    }
//
//    @Override
//    public Object visit(CIReturn node) {
//        ST st = m_stg.getInstanceOf("return_ins_st");
//        st.add("v", node.m_v.accept(this));
//        return st;
//    }
//
//    @Override
//    public Object visit(CILoad node) {
//        ST st = m_stg.getInstanceOf("load_ins_st");
//        st.add("dst", node.m_localHolder.accept(this));
//        st.add("src", node.m_globalVar.accept(this));
//        
//        st.add("escape", node.m_localHolder.isEscaped());
//        return st;
//    }
//
//    @Override
//    public Object visit(CILoadArray node) {
//        // load_array_ins_st(src, ind, dst, escape) ::= <<
//        ST st = m_stg.getInstanceOf("load_array_ins_st");
//        st.add("dst", node.m_localHolder.accept(this));
//        st.add("src", node.m_globalVar.accept(this));
//        st.add("ind", node.m_localIndex.accept(this));
//        
//        st.add("escape", node.m_localHolder.isEscaped());
//        return st;
//    }
//
//    @Override
//    public Object visit(CIStore node) {
//        // store_ins_st(src, dst) ::= <<
//        ST st = m_stg.getInstanceOf("store_ins_st");
//        st.add("src", node.m_localSrc.accept(this));
//        st.add("dst", node.m_globalVar.accept(this));
//        
//        return st;
//    }
//
//    @Override
//    public Object visit(CIStoreArray node) {
//        // store_array_ins_st(src, dst, ind) ::= <<
//        ST st = m_stg.getInstanceOf("store_array_ins_st");
//        st.add("src", node.m_localSrc.accept(this));
//        st.add("dst", node.m_globalVar.accept(this));
//        st.add("ind", node.m_localIndex.accept(this));
//        
//        return st;
//    }
//
//    @Override
//    public Object visit(CICond node) {
//        // cond_ins_st(holder, cond, tbranch, fbranch) ::= <<
//        ST st = m_stg.getInstanceOf("cond_ins_st");
//        st.add("cond", node.m_cond.accept(this));
//        for (MyCspInstruction ins: node.m_true) {
//            st.add("tbranch", ins.accept(this));
//        }
//        for (MyCspInstruction ins: node.m_false) {
//            st.add("fbranch", ins.accept(this));
//        }
//        
//        return st;
//    }
//
//    @Override
//    public Object visit(CIVarDef node) {
//        // vardef_ins_st(holder) ::= <<
//        ST st = m_stg.getInstanceOf("vardef_ins_st");
//        st.add("holder", node.m_id.getTID());
//        
//        return st;
//    }
//
//	@Override
//    public Object visit(GrpThreadCreate node) {
//		// thread_create_block_st(tid, funlab, args) ::= <<
//		ST st = m_stg.getInstanceOf("thread_create_block_st");
//		st.add("tid", node.m_tid.accept(this));
//		st.add("funlab", node.m_funlab);
//		st.add("args", node.m_args.accept(this));
//		return st;
//    }
//
//    @Override
//    public Object visit(CIMutexCreate node) {
//        // mutex_alloc_ins_st(dst, escape) ::= <<
//        ST st = m_stg.getInstanceOf("mutex_alloc_ins_st");
//        st.add("dst", node.m_holder.accept(this));
//        st.add("escape", node.m_holder.isEscaped());
//        
//        return st;
//    }
//
//	@Override
//    public Object visit(CIMutexRelease node) {
//        // mutex_release_ins_st(mutex) ::= <<
//        ST st = m_stg.getInstanceOf("mutex_release_ins_st");
//        st.add("mutex", node.m_mutex.accept(this));
//        
//        return st;
//    }
//	
//    @Override
//    public Object visit(CISharedCreateCond node) {
//        // cond_alloc_ins_st(dst, escape) ::= <<
//        ST st = m_stg.getInstanceOf("cond_alloc_ins_st");
//        st.add("dst", node.m_holder.accept(this));
//        st.add("escape", node.m_holder.isEscaped());
//        
//        return st;
//    }
//
//	@Override
//    public Object visit(CICondRelease node) {
//        // cond_release_ins_st(cond) ::= <<
//        ST st = m_stg.getInstanceOf("cond_release_ins_st");
//        st.add("cond", node.m_cond.accept(this));
//        
//        return st;
//    }
//
//    @Override
//    public Object visit(CIMCAssert node) {
//        // mcassert_ins_st(pred) ::= <<
//        ST st = m_stg.getInstanceOf("mcassert_ins_st");
//        st.add("pred", node.m_localSrc.accept(this));
//        
//        return st;
//    }

}
