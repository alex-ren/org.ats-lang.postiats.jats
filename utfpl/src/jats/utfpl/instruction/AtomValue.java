package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.Map;

public class AtomValue implements ValPrim {
    private String m_obj;
    
    public AtomValue(String obj) {
        m_obj = obj;
    }
    
    public String toString() {
        return m_obj;
    }
    
}
