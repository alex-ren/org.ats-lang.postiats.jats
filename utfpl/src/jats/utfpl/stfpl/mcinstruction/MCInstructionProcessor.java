package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Logger;

/*
 * Effect analysis.
 * Processing "if" branch with effect.
 * 
 */
public class MCInstructionProcessor {
    private ProgramMCIns m_prog;
    private MCSIdFactory m_fac;
    
    public MCInstructionProcessor(ProgramMCIns prog, MCSIdFactory fac) {
        m_prog = prog;
        m_fac = fac;
    }
    
    public ProgramMCIns process() {
        
        // ===========================================
        
        analyzeEffectTemporary();

        // ============================================
        
        // Mark effects for if.
        for (MCDefFunGroup grp: m_prog.m_defs) {
            for (MCDefFun fun: grp.m_funs) {
                analyzeEffectIF(fun.m_inss);
            }
        }
        analyzeEffectIF(m_prog.m_main_inss);
        
        // ============================================
        
        // Substitution
        
        List<MCDefFunGroup> defs = new ArrayList<MCDefFunGroup>();
        
        for (MCDefFunGroup grp: m_prog.m_defs) {
            List<MCDefFun> funs = new ArrayList<MCDefFun>();
            
            for (MCDefFun fun: grp.m_funs) {
                MCDefFun nfun = processFunction(fun);
                funs.add(nfun);
            }
            
            MCDefFunGroup ngrp = new MCDefFunGroup(grp.m_knd, funs);
            defs.add(ngrp);
        }
        
        List<IMCInstruction> main_inss = processBranchIns(m_prog.m_main_inss, null);
        
        // ============================================

        // Update self.
        m_prog.m_defs = defs;
        m_prog.m_main_inss = main_inss;

        return m_prog;
    }
    
    private MCDefFun processFunction(MCDefFun fun) {
        List<IMCInstruction> inss = processBranchIns(fun.m_inss, null);
        
        MCDefFun nfun = 
                new MCDefFun(
                        fun.m_loc
                      , fun.m_name
                      , fun.m_lin
                      , fun.m_paras
//                      , fun.m_env_name
                      , inss
                      );
        
        return nfun;

    }

    // Effect analysis
    /*
     * 
     * todo: Currently, all user defined functions are marked as having effect.
     */
    private void analyzeEffectTemporary() {
        for (MCDefFunGroup grp: m_prog.m_defs) {
            for (MCDefFun fun: grp.m_funs) {
                fun.m_name.setEffect(true);  
//                System.out.println("++++++++++ name is " + fun.m_name.toStringMCIns());
            }
        }
    }
    
    /*
     * Update the effect of "if" instruction.
     */
    static private boolean analyzeEffectIF(List<IMCInstruction> insLst) {
        boolean ret = false;
        for (IMCInstruction ins: insLst) {
            if (ins instanceof MCInsCond) {
                boolean btrue = analyzeEffectIF(((MCInsCond)ins).m_btrue);
                boolean bfalse = analyzeEffectIF(((MCInsCond)ins).m_bfalse);
                if (btrue || bfalse) {
                    ret = true;
                    ((MCInsCond)ins).setEffectFlag(true);
                } else {
                    ((MCInsCond)ins).setEffectFlag(false);
                }
            } else {
                if (ins.hasSideEffect()) {
                    ret = true;
                }
            }
        }
        return ret;
    }
    

    
    /*
     * Create a new list and the old list is unchanged.
     * The purpose of this function is to process if-branch -- turn the merged
     * "if" into branches.
     * 
     * subMap means two things if it's not null:
     *   1. we need to generate new TID for dest.
     *   2. we need to substitute for the src.
     * 
     * If it's null, then do thing.
     * 
     */
    private List<IMCInstruction> processBranchIns(
            List<IMCInstruction> insLst, 
            Map<MCSId, MCSId> subMap) {
        List<IMCInstruction> nList = new ArrayList<IMCInstruction>();
        ListIterator<IMCInstruction> iter = insLst.listIterator();
        
        MCInsSubstitutor processor = new MCInsSubstitutor(subMap, m_fac);
        
        while (iter.hasNext()) {
            IMCInstruction ins = iter.next();
            if (ins instanceof MCInsCond) {
                MCInsCond cIns = (MCInsCond)ins;
                List<IMCInstruction> restList = insLst.subList(iter.nextIndex(), insLst.size());
                
                if (cIns.hasSideEffect()) {
                    if (null == subMap) {
                        List<IMCInstruction> trueList = new ArrayList<IMCInstruction>(cIns.m_btrue);
                        trueList.addAll(restList);
                        List<IMCInstruction> trueBranch = processBranchIns(trueList, null);
                        
                        Map<MCSId, MCSId> subMapFalse = new HashMap<MCSId, MCSId>();
                        List<IMCInstruction> falseList = new ArrayList<IMCInstruction>(cIns.m_bfalse);
                        falseList.addAll(restList);
                        List<IMCInstruction> falseBranch = processBranchIns(falseList, subMapFalse);
                        
                        MCInsCond nCondIns = new MCInsCond(null, cIns.m_cond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                        return nList;  // We can return since "if" is the last instruction.
                    } else {
                        Map<MCSId, MCSId> subMapTrue = new HashMap<MCSId, MCSId>(subMap);
                        List<IMCInstruction> trueList = new ArrayList<IMCInstruction>(cIns.m_btrue);
                        trueList.addAll(restList);
                        List<IMCInstruction> trueBranch = processBranchIns(trueList, subMapTrue);
                        
                        Map<MCSId, MCSId> subMapFalse = new HashMap<MCSId, MCSId>(subMap);
                        List<IMCInstruction> falseList = new ArrayList<IMCInstruction>(cIns.m_bfalse);
                        falseList.addAll(restList);
                        List<IMCInstruction> falseBranch = processBranchIns(falseList, subMapFalse);
                        
                        IMCValPrim nCond = subsVP(cIns.m_cond, subMap);
                        MCInsCond nCondIns = new MCInsCond(null, nCond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                        return nList;  // We can return since "if" is the last instruction.
                    }
                } else {
                    if (null == subMap) {
                        List<IMCInstruction> trueBranch = processBranchIns(cIns.m_btrue, null);
                        List<IMCInstruction> falseBranch = processBranchIns(cIns.m_bfalse, null);
                        MCInsCond nCondIns = new MCInsCond(cIns.m_holder, cIns.m_cond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                    } else {
                    	
                    	IMCValPrim nCond = subsVP(cIns.m_cond, subMap);
                        Map<MCSId, MCSId> subMap2 = new HashMap<MCSId, MCSId>(subMap);
                        List<IMCInstruction> trueBranch = processBranchIns(cIns.m_btrue, subMap2);
                        List<IMCInstruction> falseBranch = processBranchIns(cIns.m_bfalse, subMap2);
                        MCInsCond nCondIns = new MCInsCond(cIns.m_holder, nCond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                    }
                }
            } else {
                Object retIns = ins.accept(processor);
                if (null != retIns) {
                    IMCInstruction nIns = (IMCInstruction)retIns;
                    nList.add(nIns);
                } else {
                    Log.log4j.warn("Check this.");
                }
            }
        }
        return nList;
    }
    
    
    static private MCSId subsVP(MCSId vp, Map<MCSId, MCSId> subMap) {
        MCSId nid = subMap.get((MCSId)vp);
        if (null == nid) {
            return vp;  // no need to substitute
        } else {
            return nid;
        }
    }
    
    static private IMCValPrim subsVP(IMCValPrim vp, Map<MCSId, MCSId> subMap) {
        if (vp instanceof MCAtomValue) {
            return vp;
        } else if (vp instanceof MCSId) {
            MCSId nid = subMap.get((MCSId)vp);
            if (null == nid) {
                return vp;  // no need to substitute
            } else {
                return nid;
            }
        } else {
            throw new Error(vp + " not supported");
        }
    }
    
    static private List<IMCValPrim> subsVPLst(List<IMCValPrim> vpLst, Map<MCSId, MCSId> subMap) {
        List<IMCValPrim> newVpLst = new ArrayList<IMCValPrim>();
        for (IMCValPrim vp: vpLst) {
            IMCValPrim newVp = subsVP(vp, subMap);
            newVpLst.add(newVp);
        }
        return newVpLst;
    }
    
    static private List<MCSId> subsIdLst(List<MCSId> vpSet, Map<MCSId, MCSId> subMap) {
    	List<MCSId> newVpSet = new ArrayList<MCSId>();
        for (MCSId vp: vpSet) {
            MCSId newVp = subsVP(vp, subMap);
            newVpSet.add(newVp);
        }
        return newVpSet;
    }
    
    // This visitor is for changing the TID. It is used to support
    // the conversion of "if".
    private static class MCInsSubstitutor implements IMCInsVisitor {
        private Map<MCSId, MCSId> m_sub;
        private MCSIdFactory m_fac;
        
        public MCInsSubstitutor(Map<MCSId, MCSId> sub, MCSIdFactory fac) {
            m_sub = sub;
            m_fac = fac;
        }

        private MCSId subsHolder(MCSId holder) {
            SId sid = holder.getSId();
            if (sid.isGlobalValue()) {
                return holder;  // no substitution for global value
            }
            
            if (sid.isUserFun()) {
                throw new Error("This should not happen. holder is " + holder);
            }
            if (sid.isConstant()) {
                throw new Error("Check this.. holder is " + holder);
            }

            MCSId nholder = m_fac.duplicate(holder);
            m_sub.put(holder, nholder);
            return nholder;
            
        }
        
        @Override
        public MCInsFormEnv visit(MCInsFormEnv ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                List<MCSId> env = subsIdLst(ins.m_env, m_sub);   
                return new MCInsFormEnv(holder, env);
            }
        }

        @Override
        public MCInsTuple visit(MCInsTuple ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                
                List<IMCValPrim> elements = subsVPLst(ins.m_elements, m_sub);         
                
                return new MCInsTuple(elements, holder);
            }
        }

        @Override
        public MCInsPatLabDecompose visit(MCInsPatLabDecompose ins) {
            if (null == m_sub) {
                return ins;
            } else {
            	Log.log4j.warn("MCInsPatLabDecompose holder is " + ins.m_holder.toStringMCIns());
                MCSId holder = subsHolder(ins.m_holder);
                Log.log4j.warn("MCInsPatLabDecompose holder new is " + holder.toStringMCIns());
                
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsPatLabDecompose(ins.m_lab, ins.m_index, holder, vp);
            }
        }

        @Override
        public MCInsCond visit(MCInsCond ins) {
            throw new Error("shall not happen");
        }

        @Override
        public MCInsMove visit(MCInsMove ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                return new MCInsMove(vp, holder);
            }
        }

        @Override
        public MCInsCall visit(MCInsCall ins) {
            if (null == m_sub) {
                return ins;
            } else {

                MCSId holder = subsHolder(ins.m_holder);
                
                MCSId fun = subsVP(ins.m_fun, m_sub);
                List<IMCValPrim> args = subsVPLst(ins.m_args, m_sub);
//                MCSId env = subsVP(ins.m_env, m_sub);
                
                return new MCInsCall(holder, fun, args);
            }
        }

        @Override
        public MCInsGetEleFromEnv visit(MCInsGetEleFromEnv ins) {
            if (null == m_sub) {
                return ins;
            } else {
                
                MCSId holder = subsHolder(ins.m_holder);
                
                MCSId env = subsVP(ins.m_env, m_sub);
                return new MCInsGetEleFromEnv(holder, env, ins.m_tag, ins.m_index);
            }
        }

        @Override
        public MCInsClosure visit(MCInsClosure ins) {
            if (null == m_sub) {
                return ins;
            } else {
                
                MCSId holder = subsHolder(ins.m_holder);
                
                MCSId fun = subsVP(ins.m_fun, m_sub);
                MCSId env = subsVP(ins.m_env, m_sub);
                
                return new MCInsClosure(holder, fun, env);
            }
        }

        @Override
        public MCInsThreadCreate visit(MCInsThreadCreate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId funlab = subsVP(ins.m_funlab, m_sub);
                IMCValPrim arg = subsVP(ins.m_arg, m_sub);
                IMCValPrim tid = subsVP(ins.m_tid, m_sub);

                return new MCInsThreadCreate(funlab, arg, tid, ins.m_isret);
            }

        }

        @Override
        public MCInsAtomRefCreate visit(MCInsAtomRefCreate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsAtomRefCreate(holder, vp);
            }
        }

        @Override
        public MCInsAtomRefGet visit(MCInsAtomRefGet ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                
                MCSId ref = subsVP(ins.m_ref, m_sub);
                
                return new MCInsAtomRefGet(ref, holder);
            }

        }

        @Override
        public MCInsAtomRefUpdate visit(MCInsAtomRefUpdate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId ref = subsVP(ins.m_ref, m_sub);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsAtomRefUpdate(ref, vp, ins.m_isret);
            }
            
        }

        @Override
        public MCInsMutexCreate visit(MCInsMutexCreate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                
                return new MCInsMutexCreate(holder);
            }
        }

        @Override
        public MCInsMCAtomicStart visit(MCInsMCAtomicStart ins) {
            return ins;
        }
        

		@Override
		public Object visit(MCInsMCAtomicEnd ins) {
            return ins;
		}


        @Override
        public MCInsMCAssert visit(MCInsMCAssert ins) {
            if (null == m_sub) {
                return ins;
            } else {

                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsMCAssert(vp, ins.m_isret);
            }
        }

        @Override
        public MCInsMCGet visit(MCInsMCGet ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                
                MCSId src = subsVP(ins.m_src, m_sub);
                
                return new MCInsMCGet(holder, src);
            }
        }

        @Override
        public MCInsMCSet visit(MCInsMCSet ins) {
            if (null == m_sub) {
                return ins;
            } else {
                IMCValPrim src = subsVP(ins.m_src, m_sub);
                MCSId dst = subsVP(ins.m_dst, m_sub);
                
                return new MCInsMCSet(src, dst, ins.m_isret);
            }
        }

        @Override
        public MCInsMCVLockViewGet visit(MCInsMCVLockViewGet ins) {
            if (null == m_sub) {
                return ins;
            } else {

                List<IMCValPrim> args = subsVPLst(ins.m_args, m_sub);
                MCSId holder = subsHolder(ins.m_holder);
                
                return new MCInsMCVLockViewGet(args, holder);
            }

        }
        
        @Override
        public MCInsMCVLockViewPut visit(MCInsMCVLockViewPut ins) {
            if (null == m_sub) {
                return ins;
            } else {

            	MCSId v = subsVP(ins.m_v, m_sub);
                
                return new MCInsMCVLockViewPut(v, ins.m_isret);
            }
        }

		@Override
		public MCInsArrayRefCreate visit(MCInsArrayRefCreate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                IMCValPrim len = subsVP(ins.m_len, m_sub);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsArrayRefCreate(holder, len, vp);
            }
		}

		@Override
		public MCInsArrayRefUpdate visit(MCInsArrayRefUpdate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId ref = subsVP(ins.m_ref, m_sub);
                IMCValPrim pos = subsVP(ins.m_pos, m_sub);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsArrayRefUpdate(ref, pos, vp, ins.m_isret);
            }
		}

		@Override
		public MCInsArrayRefGet visit(MCInsArrayRefGet ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                IMCValPrim pos = subsVP(ins.m_pos, m_sub);
                MCSId ref = subsVP(ins.m_ref, m_sub);
                
                return new MCInsArrayRefGet(ref, pos, holder);
            }
		}

		@Override
		public Object visit(MCInsTIdAllocate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                
                return new MCInsTIdAllocate(holder);
            }
		}

		@Override
		public Object visit(MCInsSharedCreate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = subsHolder(ins.m_holder);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                IMCValPrim n = subsVP(ins.m_n, m_sub);
                
                return new MCInsSharedCreate(holder, vp, n);
            }
		}


    }
    

}
