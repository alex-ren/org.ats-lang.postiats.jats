package jats.utfpl.stfpl.mycspinstructions;


public class CIFormClosure extends MyCspInstruction {

    public MyCspTempID m_holder;
    public MyCspTempID m_fun_name;
    public MyCspTempID m_env_name;
    
    
    public CIFormClosure(MyCspTempID holder, MyCspTempID fun_name, MyCspTempID env_name, MyCspGroup blk, boolean effect) {
        super(blk, effect);

        m_holder = holder;
        m_env_name = env_name;
        m_fun_name = fun_name;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        m_env_name.updateForUsage();
        m_fun_name.updateForUsage();
        
        return offset;
    }


}
