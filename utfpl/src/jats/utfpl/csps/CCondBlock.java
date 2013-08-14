package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CCondBlock implements CGroup {
    private ValPrim m_vp; // condition
    private List<CGroup> m_tb; // true branch
    private List<CGroup> m_fb; // false branch
    private TID m_holder;

    public CCondBlock(ValPrim vp, List<CGroup> tb, List<CGroup> fb, TID holder) {
        m_vp = vp;
        m_tb = tb;
        m_fb = fb;
        m_holder = holder;
    }
}
