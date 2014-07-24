package jats.utfpl.stfpl.instructions;

import java.util.List;

public class InsTuple implements IStfplInstruction {
    public List<IValPrim> m_elements;
    public SId m_holder;
    
    public InsTuple(List<IValPrim> elements, SId holder) {
        m_elements = elements;
        m_holder = holder;
    }

}
