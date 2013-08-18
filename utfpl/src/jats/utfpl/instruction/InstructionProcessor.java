package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class InstructionProcessor {
	
	/*
	 * Create a new list and the old list is unchanged.
	 */
	static public List<UtfplInstruction> InsLstProcess(
			List<UtfplInstruction> insLst, 
			Map<TID, TID> subMap,
			TID FuncLab,
			List<TID> paras,
			TID retHOlder) {
		
		List<UtfplInstruction> nList = new ArrayList<UtfplInstruction>();
		
		ListIterator<UtfplInstruction> iter = insLst.listIterator();
		while (iter.hasNext()) {
			UtfplInstruction ins = iter.next();
			if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                MoveIns nIns = aIns.createSubs(subMap);
                nList.add(nIns);   
			} else if (ins instanceof CondIns) {
				CondIns aIns = (CondIns)ins;
				List<UtfplInstruction> insLstTrue = InsLstProcess(aIns.m_btrue, subMap, FuncLab);
				List<UtfplInstruction> insLstFalse = InsLstProcess(aIns.m_bfalse, subMap, FuncLab);
				if (iter.hasNext()) {
					
					int nextInd = iter.nextIndex();
					List<UtfplInstruction> restLst = insLst.subList(nextInd, insLst.size());
					
					
					List<TID> newParas = new ArrayList<TID>();
					TID extraPara = TID.createPara(aIns.m_holder.getID());
					newParas.add(extraPara);

					subMap.put(aIns.m_holder, extraPara);
					
					InsLstProcess(restLst, subMap, yyynewname, )
					
					
				} else {
					
					CondIns nIns = aIns.createSubs(subMap);
				}
			} else if () {
				
			} else {
				
			}    
			
		}
		
	}
	
	static public FuncDefIns FuncDefInsPorcess(FuncDefIns ins, Map<TID, TID> subMap) {
		
		
	}

}
