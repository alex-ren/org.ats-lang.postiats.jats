package jats.utfpl.stfpl.mcinstruction;

public class MCInsGetEleFromEnv implements IMCInstruction {
	
	public MCSId m_holder;
	public MCSId m_env;
	public String m_tag;
	
	public MCInsGetEleFromEnv(MCSId holder, MCSId env, String tag) {
		m_holder = holder;
		m_env = env;
		m_tag = tag;
	}

	@Override
    public Object accept(IMCInsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public Boolean hasSideEffect() {
	    return false;
    }

}
