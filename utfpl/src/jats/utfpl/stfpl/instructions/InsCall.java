package jats.utfpl.stfpl.instructions;

import java.util.List;

public class InsCall implements IStfplInstruction {
    public SId m_holder;
    public IValPrim m_fun;
    public List<IValPrim> m_args;
    public CallType m_call_type;
    
    static enum CallType {
    	eFun,
    	eCloObj,
    	eCloEnv  // calling an enclosing function
    }
    
    public InsCall(SId holder, IValPrim fun, List<IValPrim> args, CallType call_type) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
        m_call_type = call_type;
    }
}
