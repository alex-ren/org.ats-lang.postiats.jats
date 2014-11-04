package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

//import jats.utfpl.stfpl.csharptype.CSFunType;
//import jats.utfpl.stfpl.csharptype.CSVoidType;
//
//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;

public class MCInsCall implements IMCInstruction {
    public MCSId m_holder;
    public MCSId m_fun;
    public List<IMCValPrim> m_args;
    public MCSId m_env;
    
    public MCInsCall(MCSId holder, MCSId fun, List<IMCValPrim> args, MCSId env) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
        m_env = env;
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return m_fun.hasEffect();
    }
}
