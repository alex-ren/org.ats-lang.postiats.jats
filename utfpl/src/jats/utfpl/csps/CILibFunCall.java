package jats.utfpl.csps;

import jats.utfpl.tree.TID;

import java.util.List;

public class CILibFunCall implements CInstruction {
    private CTempID m_funlab;
    private List<CTemp> m_args;
    private CTempID m_ret;

}
