package jats.utfpl.stfpl.mcinstruction;


import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;


public class MCInstructionProcessor {
    private ProgramMCIns m_prog;
    
    public MCInstructionProcessor(ProgramMCIns prog) {
        m_prog = prog;
    }
    
    public void process() {
        
    }
    

    // Effect analysis
    /*
     * 
     * todo: Currently, all user defined functions are marked as having effect.
     */
    private void analyzeEffectTemporary() {
        for (MCDefFunGroup grp: m_prog.m_defs) {
            for (MCDefFun fun: grp.m_funs) {
                fun.m_name.updateEffect(true);  
            }
        }
    }
//
//    private boolean analyzeEffect(MCDefFun fun, Set<MCDefFun> accu) {
//        if (fun.m_name.hasEffect() != null) {
//            
//        } else {
//            accu.add(fun);
//            boolean ret = analyzeEffect(fun.m_lin, accu);
//            accu.remove(fun);
//            return ret;
//        }
//        
//    }
//
//    private boolean analyzeEffect(int m_lin, Set<MCDefFun> accu) {
//        // TODO Auto-generated method stub
//        return false;
//    }
    

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
    static private List<IMCInstruction> processBranchIns(
            List<IMCInstruction> insLst, 
            Map<MCSId, MCSId> subMap) {
        List<IMCInstruction> nList = new ArrayList<IMCInstruction>();
        ListIterator<IMCInstruction> iter = insLst.listIterator();
        
        MCInsSubstitutor processor = new MCInsSubstitutor(subMap);
        
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
                        Map<MCSId, MCSId> subMap2 = new HashMap<MCSId, MCSId>(subMap);
                        List<IMCInstruction> trueBranch = processBranchIns(cIns.m_btrue, subMap2);
                        List<IMCInstruction> falseBranch = processBranchIns(cIns.m_bfalse, subMap2);
                        MCInsCond nCondIns = new MCInsCond(cIns.m_holder, cIns.m_cond, trueBranch, falseBranch, cIns.hasSideEffect());
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
            return nid;  // no need to substitute
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
                return nid;  // no need to substitute
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
    
    static private Set<MCSId> subsVPSet(Set<MCSId> vpSet, Map<MCSId, MCSId> subMap) {
        Set<MCSId> newVpSet = new HashSet<MCSId>();
        for (MCSId vp: vpSet) {
            MCSId newVp = subsVP(vp, subMap);
            newVpSet.add(newVp);
        }
        return newVpSet;
    }
    
    // This visitor is for changing the TID. It is used to support
    // the conversion of "if".
    private static class MCInsSubstitutor implements IMCInsVisitor {
        Map<MCSId, MCSId> m_sub;
        
        public MCInsSubstitutor(Map<MCSId, MCSId> sub) {
            m_sub = sub;
        }

        @Override
        public Object visit(MCInsFormEnv ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = ins.m_holder.dup();
                m_sub.put(ins.m_holder, holder);
                Set<MCSId> env = subsVPSet(ins.m_env, m_sub);         
                
                return new MCInsFormEnv(holder, env);
            }
        }

        @Override
        public Object visit(MCInsTuple ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = ins.m_holder.dup();
                m_sub.put(ins.m_holder, holder);
                List<IMCValPrim> elements = subsVPLst(ins.m_elements, m_sub);         
                
                return new MCInsTuple(elements, holder);
            }
        }

        @Override
        public Object visit(MCInsPatLabDecompose ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = ins.m_holder.dup();
                m_sub.put(ins.m_holder, holder);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsPatLabDecompose(ins.m_lab, holder, vp);
            }
        }

        @Override
        public Object visit(MCInsCond ins) {
            throw new Error("shall not happen");
        }

        @Override
        public Object visit(MCInsClosure ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId name = ins.m_name.dup();
                m_sub.put(ins.m_name, name);
                MCSId env = subsVP(ins.m_env, m_sub);
                
                return new MCInsClosure(name, env);
            }
        }

        @Override
        public Object visit(MCInsMove ins) {
            if (null == m_sub) {
                return ins;
            } else {                
                MCSId holder = ins.m_holder.dup();
                m_sub.put(ins.m_holder, holder);
                IMCValPrim vp = subsVP(ins.m_vp, m_sub);
                
                return new MCInsMove(vp, holder);
                
            }
        }

        @Override
        public Object visit(MCInsCall ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId holder = ins.m_holder.dup();
                m_sub.put(ins.m_holder, holder);
                List<IMCValPrim> args = subsVPLst(ins.m_args, m_sub);         
                
                return new MCInsCall(holder, ins.m_fun, args);
            }
        }

        @Override
        public Object visit(MCInsStore ins) {
            if (null == m_sub) {
                return ins;
            } else {
                IMCValPrim l_src = subsVP(ins.m_l_src, m_sub);
                MCSId g_dst = subsVP(ins.m_g_dst, m_sub);
                
                return new MCInsStore(l_src, g_dst);
            }
        }

        @Override
        public Object visit(MCInsLoad ins) {
            if (null == m_sub) {
                return ins;
            } else {
                MCSId l_holder = ins.m_l_holder.dup();
                m_sub.put(ins.m_l_holder, l_holder);
                MCSId g_src = subsVP(ins.m_g_src, m_sub);
                
                return new MCInsLoad(g_src, l_holder);
            }
        }

        @Override
        public Object visit(MCInsMutexAlloc ins) {
            if (null == m_sub) {
                return ins;
            } else {
                x
            }
        }

        @Override
        public Object visit(MCInsMutexRelease ins) {
            if (null == m_sub) {
                return ins;
            } else {
                xx
            }
        }

        @Override
        public Object visit(MCInsCondAlloc ins) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object visit(MCInsCondRelease ins) {
            if (null == m_sub) {
                return ins;
            } else {
                xx
            }
        }

        @Override
        public Object visit(MCInsThreadCreate ins) {
            if (null == m_sub) {
                return ins;
            } else {
                xx
            }
        }

        @Override
        public Object visit(MCInsMCAssert ins) {
            if (null == m_sub) {
                return ins;
            } else {
                xx
            }
        }

        @Override
        public Object visit(MCInsMCGet insMCGet) {
            if (null == m_sub) {
                return ins;
            } else {
                xx
            }
        }

        @Override
        public Object visit(MCInsMCSet insMCSet) {
            if (null == m_sub) {
                return ins;
            } else {
                xx
            }
        }
    }
    

}
