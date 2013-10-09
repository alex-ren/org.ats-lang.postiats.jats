package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClosureConverter {
	public ClosureConverter() {
	}
	
	public List<UtfplInstruction> convert(List<UtfplInstruction> body) {
		Set<TID> inScopeNames = new HashSet<TID>();
		Set<TID> escNames = new HashSet<TID>();
		Map<TID, Set<TID>> funMap = new HashMap<TID, Set<TID>>();
		Pass1 pa1 = new Pass1(inScopeNames, escNames, funMap);
		pa1.process(body);
		
//		for (Map.Entry<TID, Set<TID>> entry: funMap.entrySet()) {
//		    System.out.println("==== " + entry.getKey());
//		    for (TID ele: entry.getValue()) {
//		        System.out.println("====== " + ele);
//		    }
//		}

		inScopeNames = new HashSet<TID>();
		escNames = new HashSet<TID>();
		Pass2 pa2 = new Pass2(inScopeNames, escNames, funMap);
		pa2.process(body);

		Pass3 pa3 = new Pass3(funMap, new HashMap<TID, TID>());
		return pa3.process(body);
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
		
		// populate funMap
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
//					    System.out.println("pass1 ====== arg " + tArg);
						m_escNames.add(tArg);
					}
				}
			}
			
			if (!ins.m_holder.isGlobal()) {
//			    System.out.println("pass1 ====== " + ins.m_holder);
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
			
//			System.out.println("+++++" + ins.m_name);
//			for (TID name: escNames) {
//			    System.out.println("+++++++++ " + name);
//			}
			
			for (TID escName: escNames) {
			    if (!m_inScopeNames.contains(escName)) {  // escaped names caused by the function def
			        m_escNames.add(escName);  // escaped names at this level
			    }
			}
			
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
//	            System.out.println("============= ClosureConverter.converter.visit");
	        	fundef.accept(this);
	        }
	        return null;
        }
	}
	

	static class Pass2 implements InsVisitor {
		
	    protected Set<TID> m_inScopeNames;  // Names collected in the current scope so far
	    protected Set<TID> m_escNames;  // out-of-scope names used, new names
	    protected Map<TID, Set<TID>> m_funMap;  // function name, escaped names
	        
        public Pass2(Set<TID> inScopeNames, Set<TID> escNames,
                Map<TID, Set<TID>> funMap) {
            m_inScopeNames = inScopeNames;
            m_escNames = escNames;
            m_funMap = funMap;
        }
	        
        // populate funMap
        public void process(List<UtfplInstruction> body) {
            for (UtfplInstruction ins : body) {
                ins.accept(this);
            }
        }
	    
        // similar to Pass1
        @Override
        public Object visit(CondIns ins) {
            if (ins.m_cond instanceof TID) {
                TID cond = (TID) (ins.m_cond);
                if (!cond.isGlobal() && !m_inScopeNames.contains(cond)) { // cond is not in the scope
                    m_escNames.add(cond);
                }
            }
            Pass2 leftPass = new Pass2(new HashSet<TID>(m_inScopeNames),
                    m_escNames, m_funMap);
            leftPass.process(ins.m_btrue);
            Pass2 rightPass = new Pass2(new HashSet<TID>(m_inScopeNames),
                    m_escNames, m_funMap);
            rightPass.process(ins.m_bfalse);

            // CondIns must be the last instruction.
            return null;
        }

		@Override
		public Object visit(FuncCallIns ins) {

            for (ValPrim arg : ins.m_args) {
                if (arg instanceof TID) {
                    TID tArg = (TID) arg;
                    if (!tArg.isGlobal() && !m_inScopeNames.contains(tArg)) {
//                        System.out.println("pass1 ====== arg " + tArg);
                        m_escNames.add(tArg);
                    }
                }
            }

			if (!ins.m_funlab.isLibFun()) {  // Not library function
				Set<TID> escNames = m_funMap.get(ins.m_funlab);
				for (TID escName: escNames) {
					if (!m_inScopeNames.contains(escName)) {
						m_escNames.add(escName);
					}
				}
			}
	         if (!ins.m_holder.isGlobal()) {
//	                System.out.println("pass1 ====== " + ins.m_holder);
	                m_inScopeNames.add(ins.m_holder);  // new name          
	            }
			return null;
		}

		// similar to Pass1
        @Override
        public Object visit(FuncDefIns ins) {
            Set<TID> inScopeNames = new HashSet<TID>();
            Set<TID> escNames = new HashSet<TID>();

            for (TID para : ins.m_paralst) {
                inScopeNames.add(para);
            }
            Pass2 processor = new Pass2(inScopeNames, escNames, m_funMap);
            processor.process(ins.m_body);
            m_funMap.put(ins.m_name, escNames); // update the function

            // System.out.println("+++++" + ins.m_name);
            // for (TID name: escNames) {
            // System.out.println("+++++++++ " + name);
            // }

            for (TID escName : escNames) {
                if (!m_inScopeNames.contains(escName)) { // escaped names caused
                                                         // by the function def
                    m_escNames.add(escName); // escaped names at this level
                }
            }

            return null;
        }

        // similar to Pass1
        @Override
        public Object visit(MoveIns ins) {
            if (ins.m_vp instanceof TID) {
                TID name = (TID) (ins.m_vp);
                if (!name.isGlobal() && !m_inScopeNames.contains(name)) { // name is not in the scope
                    m_escNames.add(name);
                }
            }
            if (!ins.m_holder.isGlobal()) {
                m_inScopeNames.add(ins.m_holder); // new name
            }
            return null;
        }
	      
		@Override
        public Object visit(FuncGroupIns ins) {
		    // merge escaped names from all mutually recursive functions, which are
		    // generated at pass 1.
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
	
	// handle function definition and invocation
	// add parameters and arguments
	static class Pass3 implements InsVisitor {
	    final private Map<TID, TID> m_map;  // the map won't be changed
	    final private Map<TID, Set<TID>> m_escNames;  // the map won't be changed
	    
	    private List<UtfplInstruction> m_insLst;
	    
        public Pass3(Map<TID, Set<TID>> escNames, Map<TID, TID> map) {
            m_map = map;
            m_escNames = escNames;
            
            m_insLst = new ArrayList<UtfplInstruction>();
        }
        
        // Create new list of instructions
        public List<UtfplInstruction> process(List<UtfplInstruction> body) {
            for (UtfplInstruction ins: body) {
                ins.accept(this);
            }
            
            return m_insLst;
        }

        @Override
        public Object visit(CondIns ins) {
            ValPrim nCond = null;
            if (ins.m_cond instanceof TID) {
                TID cond = (TID)(ins.m_cond);
                nCond = m_map.get(cond);
                if (nCond == null) {
                    nCond = cond;
                }
            } else {
                nCond = ins.m_cond;
            }
            Pass3 leftPass = new Pass3(m_escNames, m_map);
            List<UtfplInstruction> leftInsLst = leftPass.process(ins.m_btrue);
            
            Pass3 rightPass = new Pass3(m_escNames, m_map);
            List<UtfplInstruction> rightInsLst = rightPass.process(ins.m_bfalse);
            
            CondIns nIns = new CondIns(ins.m_holder, nCond, leftInsLst, rightInsLst);
            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(FuncCallIns ins) {
            List<ValPrim> newArgLst = new ArrayList<ValPrim>();
            
            for (ValPrim arg: ins.m_args) {
                if (arg instanceof TID) {
                    TID newArg = m_map.get(arg);
                    if (null == newArg) {
                        newArg = (TID)arg;
                    }
                    newArgLst.add(newArg);
                } else {
                    newArgLst.add(arg);
                }
            }
            
            // handle escaped names
            if (!ins.m_funlab.isLibFun()) {
                Set<TID> escSet = m_escNames.get(ins.m_funlab);
                
                for (TID escName: escSet) {
                    TID newArg = m_map.get(escName);
                    if (null == newArg) {
                        newArg = escName;
                    }
                    newArgLst.add(newArg);
                }
            }
            
            FuncCallIns nIns = new FuncCallIns(ins.m_holder, ins.m_funlab, newArgLst);
            
            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(FuncDefIns ins) {
            List<TID> newParaLst = new ArrayList<TID>(ins.m_paralst);
            Set<TID> escNameSet = m_escNames.get(ins.m_name);
            
            Map<TID, TID> map = new HashMap<TID, TID>();
            for (TID escName: escNameSet) {
                TID newPara = TID.createPara(escName.getID() + "_esc", false);
                newParaLst.add(newPara);
                map.put(escName, newPara);
            }
            
            Pass3 pass = new Pass3(m_escNames,map);
            List<UtfplInstruction> newBody = pass.process(ins.m_body);
            
            FuncDefIns nIns = new FuncDefIns(ins.m_name, newParaLst, newBody, ins.m_ret);
            
            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(MoveIns ins) {
            ValPrim nSrc = null;

            if (ins.m_vp instanceof TID) {
                nSrc = m_map.get(ins.m_vp);
                if (null == nSrc) {  // not an escaped name
                    nSrc = ins.m_vp;
                }
            } else {
                nSrc = ins.m_vp;
            }
            MoveIns nIns = new MoveIns(ins.m_holder, nSrc);
            
            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(FuncGroupIns ins) {
            // FuncGroupIns is removed from the instruction list
            for (FuncDefIns fundef: ins.m_funLst) {
                fundef.accept(this);
            }
            return m_insLst;
        }
	    
	}

}





