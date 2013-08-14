package jats.utfpl.csps;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CIMove implements CInstruction {
    private TID m_holder;
    private ValPrim m_vp;

    public CIMove(TID holder, ValPrim vp) {
        m_holder = holder;
        m_vp = vp;
    }
}
