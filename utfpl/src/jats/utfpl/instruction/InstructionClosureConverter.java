package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// turn closure into normal function by add more parameters
public class InstructionClosureConverter {
    
    private ProgramInstruction m_iProg;
//    private Set<TID> m_baseScope;
    
    public InstructionClosureConverter(ProgramInstruction iProg) {
        m_iProg = iProg;
    }
    
    // The return value shall not
    private Set<TID> createBaseScope() {
        Set<TID> baseScope = new HashSet<TID>();
        for (GlobalEntity ge: m_iProg.getGlobalEntities()) {
//            System.out.println("tid is " + ge.getTID());
            baseScope.add(ge.getTID());
        }
        
        return baseScope;
    }

	public ProgramInstruction convert() {
	    
	    // pass 1
	    Set<TID> inScopeNames = createBaseScope();	    
	    Set<TID> escNames = new HashSet<TID>();
	    Map<TID, Set<TID>> funMap = new HashMap<TID, Set<TID>>();
	    Pass1 pa1 = new Pass1(inScopeNames, escNames, funMap);  // fill the funMap
	    pa1.process(m_iProg.getInsLst());

	    // pass 2
        inScopeNames = createBaseScope();
        escNames = new HashSet<TID>();
        Pass2 pa2 = new Pass2(inScopeNames, escNames, funMap);  // modify the funMap
        pa2.process(m_iProg.getInsLst());

        // pass 3
        Pass3 pa3 = new Pass3(funMap, new HashMap<TID, TID>());
        List<UtfplInstruction> nInsLst = pa3.process(m_iProg.getInsLst());
        List<FunctionInstruction> nFuncLst = pa3.getFuncLst();
        
        return new ProgramInstruction(
                m_iProg.getGlobalEntities(), nInsLst, nFuncLst, m_iProg.getExtCodeLst());
	}

	// escaped value caused by invocation of closure is not taken into consideration.
	class Pass1 implements InsVisitor {
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
		public Object visit(InsCond ins) {
			if (ins.m_cond instanceof TID) {
				TID cond = (TID)(ins.m_cond);
				if (!m_inScopeNames.contains(cond)) {  // cond is not in the scope
					m_escNames.add(cond);
				}
			}
			
			Pass1 leftPass = new Pass1(new HashSet<TID>(m_inScopeNames), m_escNames, m_funMap);
			leftPass.process(ins.m_btrue);
			Pass1 rightPass = new Pass1(new HashSet<TID>(m_inScopeNames), m_escNames, m_funMap);
			rightPass.process(ins.m_bfalse);
			
			m_inScopeNames.add(ins.m_holder);

			return null;
		}

		@Override
		public Object visit(InsFuncDef ins) {
			Set<TID> inScopeNames = createBaseScope();
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
        public Object visit(InsFuncGroup ins) {
	        for (InsFuncDef fundef: ins.m_funLst) {
//	            System.out.println("============= ClosureConverter.converter.visit");
	        	fundef.accept(this);
	        }
	        return null;
        }

        @Override
        public Object visit(InsCall ins) {
            for (ValPrim arg: ins.m_args) {
                if (arg instanceof TID) {
                    TID tArg = (TID)arg;
                    if (!m_inScopeNames.contains(tArg)) {
//                      System.out.println("pass1 ====== arg " + tArg);
                        m_escNames.add(tArg);
                    }
                }
            }
            
            if (!ins.m_holder.isGlobalVariable()) {
//              System.out.println("pass1 ====== " + ins.m_holder);
                 m_inScopeNames.add(ins.m_holder);  // new name
            } else {
            	throw new Error("shall not happen and holder is " + ins.m_holder);
            }
            
            // The escaped value of the function is handled in next pass.
            return null;
        }

        @Override
        public Object visit(InsStoreArray ins) {
            if (ins.m_localValue instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localValue)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localValue);
            }
            
            if (ins.m_localIndex instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localIndex)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localIndex);
            }
            
            return null;
        }

        @Override
        public Object visit(InsStore ins) {
            if (ins.m_localSrc instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localSrc)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localSrc);
            }
            
            return null;
        }

        @Override
        public Object visit(InsRet ins) {
            if (ins.m_v instanceof TID && 
                    !m_inScopeNames.contains(ins.m_v)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_v);
            }
            
            return null;
        }

        @Override
        public Object visit(InsLoadArray ins) {
            if (ins.m_localIndex instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localIndex)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localIndex);
            }
            
            m_inScopeNames.add(ins.m_localHolder);
            return null;
        }

        @Override
        public Object visit(InsMove ins) {
            if (ins.m_vp instanceof TID) {
                TID name = (TID)(ins.m_vp);
                if (!m_inScopeNames.contains(name)) {  // name is not in the scope
                    m_escNames.add(name);
                }
            }
            
            m_inScopeNames.add(ins.m_holder);  // new name 

            return null;
        }

        @Override
        public Object visit(InsLoad ins) {
            m_inScopeNames.add(ins.m_localHolder);
            return null;
        }

		@Override
        public Object visit(InsThreadCreate ins) {
            if (ins.m_tid instanceof TID && 
                    !m_inScopeNames.contains(ins.m_tid)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_tid);
            }
            
            if (ins.m_args instanceof TID && 
                    !m_inScopeNames.contains(ins.m_args)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_args);
            }
            
            return null;
        }

        @Override
        public Object visit(InsMutexAlloc ins) {
        	m_inScopeNames.add(ins.m_holder);  // new name      
            return null;
        }

		@Override
        public Object visit(InsMutexRelease ins) {
            if (ins.m_mutex instanceof TID && 
                    !m_inScopeNames.contains(ins.m_mutex)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_mutex);
            }
	        return null;
        }
		
        @Override
        public Object visit(InsCondAlloc ins) {
        	m_inScopeNames.add(ins.m_holder);  // new name      
            return null;
        }

		@Override
        public Object visit(InsCondRelease ins) {
            if (ins.m_cond instanceof TID && 
                    !m_inScopeNames.contains(ins.m_cond)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_cond);
            }
	        return null;
        }

        @Override
        public Object visit(InsMCAssert ins) {
            if (ins.m_localSrc instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localSrc)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localSrc);
            }
            
            return null;
        }
	}

	// Pass2 is similar to Pass1 except that escaped value caused 
	// by invocation of closure is now taken into consideration.
	class Pass2 implements InsVisitor {
		
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
        public Object visit(InsCond ins) {
            if (ins.m_cond instanceof TID) {
                TID cond = (TID) (ins.m_cond);
                if (!m_inScopeNames.contains(cond)) { // cond is not in the scope
                    m_escNames.add(cond);
                }
            }
            Pass2 leftPass = new Pass2(new HashSet<TID>(m_inScopeNames),
                    m_escNames, m_funMap);
            leftPass.process(ins.m_btrue);
            Pass2 rightPass = new Pass2(new HashSet<TID>(m_inScopeNames),
                    m_escNames, m_funMap);
            rightPass.process(ins.m_bfalse);

            m_inScopeNames.add(ins.m_holder);
            
            return null;
        }

		// similar to Pass1
        @Override
        public Object visit(InsFuncDef ins) {
            Set<TID> inScopeNames = createBaseScope();
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

		@Override
        public Object visit(InsFuncGroup ins) {
		    // merge escaped names from all mutually recursive functions, which are
		    // generated at pass 1.
			Set<TID> allNames = new HashSet<TID>();
	        for (InsFuncDef fundef: ins.m_funLst) {
	        	Set<TID> nameSet = m_funMap.get(fundef.m_name);
	        	allNames.addAll(nameSet);
	        }
	        
	        for (InsFuncDef fundef: ins.m_funLst) {
	        	m_funMap.put(fundef.m_name, allNames);
	        }
	        
	        for (InsFuncDef fundef: ins.m_funLst) {
	        	fundef.accept(this);
	        }
	        return null;
        }

        @Override
        public Object visit(InsCall ins) {
            for (ValPrim arg : ins.m_args) {
                if (arg instanceof TID) {
                    TID tArg = (TID) arg;
                    if (!m_inScopeNames.contains(tArg)) {
//                        System.out.println("pass1 ====== arg " + tArg);
                        m_escNames.add(tArg);
                    }
                }
            }

            if (!ins.m_funlab.isLibFun()) {  // Not library function
                Set<TID> escNames = m_funMap.get(ins.m_funlab);
                if (null == escNames) {
                    throw new Error("Function " + ins.m_funlab.getID() + " is not defined.");
                }
                for (TID escName: escNames) {
                    if (!m_inScopeNames.contains(escName)) {
                        m_escNames.add(escName);
                    }
                }
            }

            m_inScopeNames.add(ins.m_holder);  // new name
            
            return null;
        }


        @Override
        public Object visit(InsStoreArray ins) {
            if (ins.m_localValue instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localValue)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localValue);
            }
            
            if (ins.m_localIndex instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localIndex)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localIndex);
            }
            
            return null;
        }

        @Override
        public Object visit(InsStore ins) {
            if (ins.m_localSrc instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localSrc)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localSrc);
            }
            
            return null;
        }

        @Override
        public Object visit(InsRet ins) {
            if (ins.m_v instanceof TID && 
                    !m_inScopeNames.contains(ins.m_v)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_v);
            }
            
            return null;
        }

        @Override
        public Object visit(InsLoadArray ins) {
            if (ins.m_localIndex instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localIndex)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localIndex);
            }
            
            m_inScopeNames.add(ins.m_localHolder);
            return null;
        }

        @Override
        public Object visit(InsMove ins) {
            if (ins.m_vp instanceof TID) {
                TID name = (TID) (ins.m_vp);
                if (!m_inScopeNames.contains(name)) { // name is not in the scope
                    m_escNames.add(name);
                }
            }
            m_inScopeNames.add(ins.m_holder); // new name
            return null;
        }

        @Override
        public Object visit(InsLoad ins) {
            m_inScopeNames.add(ins.m_localHolder);
            return null;
        }

		@Override
        public Object visit(InsThreadCreate ins) {
            if (ins.m_tid instanceof TID && 
                    !m_inScopeNames.contains(ins.m_tid)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_tid);
            }
            
            if (ins.m_args instanceof TID && 
                    !m_inScopeNames.contains(ins.m_args)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_args);
            }
            
            return null;
        }

        @Override
        public Object visit(InsMutexAlloc ins) {
            m_inScopeNames.add(ins.m_holder);  // new name          
            return null;
        }

		@Override
        public Object visit(InsMutexRelease ins) {
            if (ins.m_mutex instanceof TID && 
                    !m_inScopeNames.contains(ins.m_mutex)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_mutex);
            }
	        return null;
        }
		
        @Override
        public Object visit(InsCondAlloc ins) {
        	m_inScopeNames.add(ins.m_holder);  // new name      
            return null;
        }

		@Override
        public Object visit(InsCondRelease ins) {
            if (ins.m_cond instanceof TID && 
                    !m_inScopeNames.contains(ins.m_cond)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_cond);
            }
	        return null;
        }

        @Override
        public Object visit(InsMCAssert ins) {
            if (ins.m_localSrc instanceof TID && 
                    !m_inScopeNames.contains(ins.m_localSrc)) {  // name is not in the scope
                m_escNames.add((TID)ins.m_localSrc);
            }
            
            return null;
        }
	}
	
	// handle function definition and invocation
	// add parameters and arguments
	static class Pass3 implements InsVisitor {
	    final private Map<TID, TID> m_map;  // the map won't be changed once initialized
	    final private Map<TID, Set<TID>> m_escNames;  // function name to escaped name
	                                                  // the map won't be changed
	    
	    private List<FunctionInstruction> m_funcLst;
	    private List<UtfplInstruction> m_insLst;
	    
        public Pass3(Map<TID, Set<TID>> escNames, Map<TID, TID> map) {
            m_map = map;
            m_escNames = escNames;
            
            m_funcLst = new ArrayList<FunctionInstruction>();
            m_insLst = new ArrayList<UtfplInstruction>();
        }
        
        public List<FunctionInstruction> getFuncLst() {
            return m_funcLst;
        }
        
        public List<UtfplInstruction> getInsLst() {
            return m_insLst;
        }
        
        // Create new list of instructions
        public List<UtfplInstruction> process(List<UtfplInstruction> body) {
            for (UtfplInstruction ins: body) {
                ins.accept(this);
            }
            
            return m_insLst;
        }

        @Override
        public Object visit(InsCond ins) {
            ValPrim nCond = InstructionProgramProcessor.subsVP(ins.m_cond, m_map);
            
            Pass3 leftPass = new Pass3(m_escNames, m_map);
            List<UtfplInstruction> leftInsLst = leftPass.process(ins.m_btrue);
            m_funcLst.addAll(leftPass.getFuncLst());
            
            Pass3 rightPass = new Pass3(m_escNames, m_map);
            List<UtfplInstruction> rightInsLst = rightPass.process(ins.m_bfalse);
            m_funcLst.addAll(rightPass.getFuncLst());
            
            InsCond nIns = new InsCond(ins.m_holder, nCond, leftInsLst, rightInsLst, ins.hasSideEffect());
            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(InsFuncDef ins) {
            List<TID> escParaLst = new ArrayList<TID>();
            Set<TID> escNameSet = m_escNames.get(ins.m_name);
            
            Map<TID, TID> map = new HashMap<TID, TID>();
            for (TID escName: escNameSet) {
                TID newPara = TID.createPara(escName.getID() + "_esc", null);
                escParaLst.add(newPara);
                map.put(escName, newPara);
            }
            
            Pass3 pass = new Pass3(m_escNames,map);
            List<UtfplInstruction> newBody = pass.process(ins.m_body);
            
            // function instruction is removed from the instruction list
            
            FunctionInstruction nFunc = new FunctionInstruction(ins.m_name, ins.m_paralst, escParaLst, newBody);
            // InsFuncDef is removed from the instruction list
            m_funcLst.add(nFunc);
            m_funcLst.addAll(pass.getFuncLst());

            return m_insLst;
        }

        @Override
        public Object visit(InsFuncGroup ins) {
            // InsFuncGroup is removed from the instruction list
            for (InsFuncDef fundef: ins.m_funLst) {
                fundef.accept(this);
            }
            return m_insLst;
        }

        @Override
        public Object visit(InsCall ins) {
            List<ValPrim> newArgLst = InstructionProgramProcessor.subsVPLst(ins.m_args, m_map);
            
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
            
            InsCall nIns = new InsCall(ins.m_holder, ins.m_funlab, newArgLst, ins.m_isTailCall);
            
            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(InsStoreArray ins) {
            ValPrim nLocalIndex = InstructionProgramProcessor.subsVP(ins.m_localIndex, m_map);
            ValPrim nLocalValue = InstructionProgramProcessor.subsVP(ins.m_localValue, m_map);
            InsStoreArray nIns = new InsStoreArray(nLocalValue, ins.m_globalVar, nLocalIndex);
            m_insLst.add(nIns);
            
            return m_insLst;
        }

        @Override
        public Object visit(InsStore ins) {
            ValPrim nLocalSrc = InstructionProgramProcessor.subsVP(ins.m_localSrc, m_map);
            InsStore nIns = new InsStore(nLocalSrc, ins.m_globalDest);
            m_insLst.add(nIns);
            
            return m_insLst;
        }

        @Override
        public Object visit(InsRet ins) {
            ValPrim nV = InstructionProgramProcessor.subsVP(ins.m_v, m_map);
            InsRet nIns = new InsRet(nV);
            m_insLst.add(nIns);
            
            return m_insLst; 
        }

        @Override
        public Object visit(InsLoadArray ins) {
            ValPrim nLocalIndex = InstructionProgramProcessor.subsVP(ins.m_localIndex, m_map);
            
            InsLoadArray nIns = new InsLoadArray(ins.m_globalVar, nLocalIndex, ins.m_localHolder);
            m_insLst.add(nIns);
            
            return m_insLst; 
        }

        @Override
        public Object visit(InsMove ins) {
            ValPrim nSrc = InstructionProgramProcessor.subsVP(ins.m_vp, m_map);
            
            InsMove nIns = new InsMove(nSrc, ins.m_holder);

            m_insLst.add(nIns);
            return m_insLst;
        }

        @Override
        public Object visit(InsLoad ins) {
            m_insLst.add(ins);
            return m_insLst;
        }

		@Override
        public Object visit(InsThreadCreate ins) {
	        ValPrim nTid = InstructionProgramProcessor.subsVP(ins.m_tid, m_map);
	        ValPrim nArgs = InstructionProgramProcessor.subsVP(ins.m_args, m_map);
	        InsThreadCreate nIns = new InsThreadCreate(nTid, ins.m_funlab, nArgs);
	        
	        m_insLst.add(nIns);
	        return m_insLst;
        }

        @Override
        public Object visit(InsMutexAlloc ins) {
            m_insLst.add(ins);
            return m_insLst;
        }

		@Override
        public Object visit(InsMutexRelease ins) {
			ValPrim nMutex = InstructionProgramProcessor.subsVP(ins.m_mutex, m_map);
			InsMutexRelease nIns = new InsMutexRelease(nMutex);
	        m_insLst.add(nIns);
	        return m_insLst;
        }
		
        @Override
        public Object visit(InsCondAlloc ins) {
            m_insLst.add(ins);
            return m_insLst;
        }

		@Override
        public Object visit(InsCondRelease ins) {
			ValPrim nCond = InstructionProgramProcessor.subsVP(ins.m_cond, m_map);
			InsCondRelease nIns = new InsCondRelease(nCond);
	        m_insLst.add(nIns);
	        return m_insLst;
        }

        @Override
        public Object visit(InsMCAssert ins) {
            ValPrim nLocalSrc = InstructionProgramProcessor.subsVP(ins.m_localSrc, m_map);
            InsMCAssert nIns = new InsMCAssert(nLocalSrc);
            m_insLst.add(nIns);
            
            return m_insLst;
        }
	    
	}

}





