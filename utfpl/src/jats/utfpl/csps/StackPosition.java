package jats.utfpl.csps;

public class StackPosition {
    private int m_offset;
    private int m_frame;
    
    private StackPosition(int frame, int offset) {
        if (frame > 0) {
            throw new Error("useless information");
        } else if (frame < 0) {
            throw new Error("shouldn't see closure here");
            // all closures should have been eliminated by ClosureConverter.
        }
        m_offset = offset;
        m_frame = frame;
    }
    
    public int getOffset() {
        return m_offset;
    }
    
    public int getFrame() {
        return m_frame;
    }
    
    public static StackPosition createDef(int offset) {
        return new StackPosition(0, offset);
    }
    
    // For non-closure, frame is just 0.
    public static StackPosition createUsage(int frame, int offset) {
        return new StackPosition(frame, offset);
    }
    
}
