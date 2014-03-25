package jats.utfpl.tree;

import java.util.List;


public class PatRecord extends IPat {


    static public class PatLabItem {
        
        static public interface Ilabel {}
        static public class PatLabInt implements Ilabel {
            public int m_i;
            public PatLabInt(int i) {
                m_i = i;
            }
        }
        
        static public class PatLabSym implements Ilabel {
            public String m_sym;
            public PatLabSym(String sym) {
                m_sym = sym;
            }
        }
        
        public Ilabel m_lab;  // The label for the item.
                             // The label can be integer if the record is actually a list.
        public IPat m_pat;
        
        public PatLabItem(Ilabel lab, IPat pat) {
            m_lab = lab;
            m_pat = pat;
        }

    }
    
    /* ********** *********** */
    
    public List<PatLabItem> m_labs;

    protected PatRecord(Location loc, List<PatLabItem> labs) {
        super(loc);
        m_labs = labs;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
