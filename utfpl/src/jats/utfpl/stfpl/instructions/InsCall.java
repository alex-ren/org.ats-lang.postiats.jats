package jats.utfpl.stfpl.instructions;

import java.util.List;

public class InsCall implements IStfplInstruction {
    public SId m_holder;
    public SIdUser m_fun;
    public List<IValPrim> m_args;
    public ECallType m_call_type;  // this formation is redundant to m_fun
    
    static enum ECallType {
    	eFun,
    	eCloObj
//    	eCloEnv  // calling an enclosing function
    }
    
    public InsCall(SId holder, SIdUser fun, List<IValPrim> args, ECallType call_type) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
        m_call_type = call_type;
    }
}
