package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cp3at;
import jats.utfpl.utfpl.staexp.Ifunclo;

import java.util.List;

public class InsFunDef {
    public IVarName m_name;
    public int m_lin;  // something to do with linear type?
    public List<Cp3at> m_p3ts;
    public Ifunclo m_funclo;
    public List<IStfplInstruction> m_ins;  // body of the function
    public List<IVarName> m_env;
}

