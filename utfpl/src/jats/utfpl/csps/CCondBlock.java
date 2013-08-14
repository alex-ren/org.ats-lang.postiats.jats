package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CCondBlock implements CGroup {
    private CTemp m_cond; // condition
    private List<CGroup> m_tb; // true branch
    private List<CGroup> m_fb; // false branch
    private CTempID m_holder;

    public CCondBlock(CTemp cond, List<CGroup> tb, List<CGroup> fb, CTempID holder) {
        m_cond = cond;
        m_tb = tb;
        m_fb = fb;
        m_holder = holder;
    }
}
