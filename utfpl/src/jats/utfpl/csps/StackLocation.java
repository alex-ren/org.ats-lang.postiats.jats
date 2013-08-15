package jats.utfpl.csps;

public class StackLocation {
    public int m_offset;
    public int m_frame;
    
    public StackLocation() {
        m_offset = 0;
        m_frame = 0;
    }
    
    public void incOffset() {
        m_offset++;
    }
    
    public void incFrame() {
        m_frame++;
        m_offset = 0;
    }
    
    public StackLocation clone() {
        StackLocation ret = new StackLocation();
        ret.m_offset = this.m_offset;
        ret.m_frame = this.m_frame;
        return ret;
    }
}
