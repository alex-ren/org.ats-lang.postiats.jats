package jats.utfpl.csps;

public class StackLocation {
    public int m_offset;
    public int m_frame;
    
    public StackLocation(int frame, int offset) {
        m_offset = offset;
        m_frame = frame;
    }
    
    public int getOffset() {
        return m_offset;
    }
    
    public int getFrame() {
        return m_frame;
    }
    
}
