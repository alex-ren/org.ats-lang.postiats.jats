package jats.utfpl.utils;

public interface Scope<T> {

    public void addValue(String id, T v);
    
    public void addValueWithModule(String id, T v, String moduleName);
    
    public T getValue(String id);
    
    public Entry<T> getEntry(String id);

    public Scope<T> getParent();
    
    // Caution: No constraints that v.newScope().getParent() == v
    public Scope<T> newScope();
    
    static public class Entry<T> {
        public String m_moduleName;
        public T m_val;
        
        public Entry(T v, String moduleName) {
            m_moduleName = moduleName;
            m_val = v;
        }
    }

}

