package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

public class TupleValue implements ValPrim {
    static public TupleValue cNone = new TupleValue();
    
    private List<ValPrim> m_eleLst;
    
    public TupleValue() {
        m_eleLst = new ArrayList<ValPrim>();
    }

}
