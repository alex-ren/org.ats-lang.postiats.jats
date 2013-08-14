package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jats.utfpl.instruction.CondIns;
import jats.utfpl.instruction.FuncCallIns;
import jats.utfpl.instruction.FuncDefIns;
import jats.utfpl.instruction.MoveIns;
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.tree.TID;

public class InstructionProcessor {
    
    static public List<FuncDefIns> getAllFunctions(List<UtfplInstruction> inslst) {
        // Assume here that there is no function inside function for simplicity.
        List<FuncDefIns> funlst = new ArrayList<FuncDefIns>();
        for (UtfplInstruction ins: inslst) {
            if (ins instanceof FuncDefIns) {
                funlst.add((FuncDefIns) ins);
            }
        }
        return funlst;
    }
    
    static public Map<FuncDefIns, Boolean> markSideEffectFunLst(List<FuncDefIns> funlst) {
        // todo
        Map<FuncDefIns, Boolean> fmap = new HashMap<FuncDefIns, Boolean>();
        for (FuncDefIns funDec: funlst) {
            // assume that all user-defined functions have side effect.
            fmap.put(funDec, true);
            funDec.flagSideEffect();
        }
        
        return fmap;
        
    }
    
    // Keep the input list unmodified.
    static public List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> inslst) {
        // todo
        return null;
        
    }
    
    static class InsGroup {
        private List<UtfplInstruction> m_inslst;
        private int m_stackSize;
        private InsGroup m_father;
        
        public InsGroup() {
            m_inslst = new ArrayList<UtfplInstruction>();
            m_stackSize = 0;
        }
        
        public void add(UtfplInstruction ins) {
            m_inslst.add(ins);
        }
        
    }
    
    
    // insLoc will be used to store the pos of the group in the list for each instruction.
    static List<CGroup> transInsList(List<UtfplInstruction> inslst) {
        List<CGroup> insGroupList = new ArrayList<CGroup>();
        CBlock cblock = new CBlock();
        
        for (UtfplInstruction ins: inslst) {
            if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                cblock.add(new CIMove(aIns.m_holder, aIns.m_vp));
                if (ins.hasSideEffect()) {
                    insGroupList.add(cblock);
                    cblock = new CBlock();
                } else {
                    continue;
                }
            } else if (ins instanceof FuncCallIns) {
                FuncCallIns aIns = (FuncCallIns)ins;
                if (ins.hasSideEffect()) {
                    CProcessCallBlock cprocess = new CProcessCallBlock(aIns.m_funlab, aIns.m_args, aIns.m_holder);
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CBlock();
                    }
                    insGroupList.add(cprocess);
                } else {
                    cblock.add(new CIFunCall(aIns.m_funlab, aIns.m_args, aIns.m_holder));
                }
            } else if (ins instanceof CondIns) {
                CondIns aIns = (CondIns)ins;
                if (ins.hasSideEffect()) {
                    List<CGroup> btrue = transInsList(aIns.m_btrue);
                    List<CGroup> bfalse = transInsList(aIns.m_bfalse);
                    
                    CCondBlock ccond = new CCondBlock(aIns.m_cond, btrue, bfalse, aIns.m_holder);
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CBlock();
                    }
                    insGroupList.add(ccond);
                    
                } else {
                    throw new Error("todo");
                }
            } else {
                // simply neglect
            }
        }
        return insGroupList;
    }
    
    static public TIDUsageAnalyze(List<CGroup> cgs) {
        
    }
    
    static public void stackAnalyze() {
        // todo
        
    }

}
