package jats.utfpl.instruction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClosureConverter {
	private List<UtfplInstruction> m_body;
	public ClosureConverter(List<UtfplInstruction> body) {
		m_body = body;
	}
	
	public List<UtfplInstruction> convert() {
		Set<TID> inScopeNames = new HashSet<TID>();
		Set<TID> escNames = new HashSet<TID>();
		Map<TID, Set<TID>> funMap = new HashMap<TID, Set<TID>>();
		Pass1 pa1 = new Pass1(inScopeNames, escNames, funMap);
		pa1.process(m_body);
		
		inScopeNames = new HashSet<TID>();
		escNames = new HashSet<TID>();
		Pass2 pa2 = new Pass2(inScopeNames, escNames, funMap);
		pa2.process(m_body);
		
		// todo pass3
		
		return null;
		
	}

	// escaped value caused by invocation of closure is not taken into consideration.
	static class Pass1 implements InsVisitor {
		protected Set<TID> m_inScopeNames;  // Names collected in the current scope so far
		protected Set<TID> m_escNames;  // out-of-scope names used, new names
		protected Map<TID, Set<TID>> m_funMap;  // function name, escaped names
		
		public Pass1(Set<TID> inScopeNames, Set<TID> escNames, Map<TID, Set<TID>> funMap) {
			m_inScopeNames = inScopeNames;
			m_escNames = escNames;
			m_funMap = funMap;
		}
		
		public void process(List<UtfplInstruction> body) {
			for (UtfplInstruction ins: body) {
				ins.accept(this);
			}
		}

		@Override
		public Object visit(CondIns ins) {
			if (ins.m_cond instanceof TID) {
				TID cond = (TID)(ins.m_cond);
				if (!cond.isGlobal() && !m_inScopeNames.contains(cond)) {  // cond is not in the scope
					m_escNames.add(cond);
				}
			}
			Pass1 leftPass = new Pass1(new HashSet<TID>(m_inScopeNames), m_escNames, m_funMap);
			leftPass.process(ins.m_btrue);
			Pass1 rightPass = new Pass1(new HashSet<TID>(m_inScopeNames), m_escNames, m_funMap);
			rightPass.process(ins.m_bfalse);
			
			// CondIns must be the last instruction.
			return null;
		}

		@Override
		public Object visit(FuncCallIns ins) {
			for (ValPrim arg: ins.m_args) {
				if (arg instanceof TID) {
					TID tArg = (TID)arg;
					if (!tArg.isGlobal() && !m_inScopeNames.contains(tArg)) {
						m_escNames.add(tArg);
					}
				}
			}
			
			if (!ins.m_holder.isGlobal()) {
				m_inScopeNames.add(ins.m_holder);  // new name			
			}
			
			// The escaped value of the function is handled in next pass.
			return null;
		}

		@Override
		public Object visit(FuncDefIns ins) {
			Set<TID> inScopeNames = new HashSet<TID>();
			Set<TID> escNames = new HashSet<TID>();
			
			for (TID para: ins.m_paralst) {
				inScopeNames.add(para);
			}
			Pass1 processor = new Pass1(inScopeNames, escNames, m_funMap);
			processor.process(ins.m_body);
			m_funMap.put(ins.m_name, escNames);  // add one more function
			escNames.removeAll(m_inScopeNames);  // escaped names caused by the function def
			m_escNames.addAll(escNames);  // escaped names at this level
			
			return null;
		}

		@Override
		public Object visit(MoveIns ins) {
			if (ins.m_vp instanceof TID) {
				TID name = (TID)(ins.m_vp);
				if (!name.isGlobal() && !m_inScopeNames.contains(name)) {  // name is not in the scope
					m_escNames.add(name);
				}
			}
			if (!ins.m_holder.isGlobal()) {
				m_inScopeNames.add(ins.m_holder);  // new name			
			}
			return null;
		}

		@Override
        public Object visit(FuncGroupIns ins) {
	        for (FuncDefIns fundef: ins.m_funLst) {
	        	fundef.accept(this);
	        }
	        return null;
        }
	}
	

	static class Pass2 extends Pass1 {
		
		public Pass2(Set<TID> inScopeNames, Set<TID> escNames, Map<TID, Set<TID>> funMap) {
			super(inScopeNames, escNames, funMap);
		}

		@Override
		public Object visit(FuncCallIns ins) {
			if (!ins.m_funlab.isLibFun()) {  // Not library function
				Set<TID> escNames = m_funMap.get(ins.m_funlab);
				for (TID escName: escNames) {
					if (!m_inScopeNames.contains(escName)) {
						m_escNames.add(escName);
					}
				}
			}
			// The escaped value of the function is handled in next pass.
			return null;
		}

		@Override
        public Object visit(FuncGroupIns ins) {
			Set<TID> allNames = new HashSet<TID>();
	        for (FuncDefIns fundef: ins.m_funLst) {
	        	Set<TID> nameSet = m_funMap.get(fundef.m_name);
	        	allNames.addAll(nameSet);
	        }
	        
	        for (FuncDefIns fundef: ins.m_funLst) {
	        	m_funMap.put(fundef.m_name, allNames);
	        }
	        
	        for (FuncDefIns fundef: ins.m_funLst) {
	        	fundef.accept(this);
	        }
	        return null;
        }
	}

}
