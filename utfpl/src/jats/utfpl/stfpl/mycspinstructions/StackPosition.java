package jats.utfpl.stfpl.mycspinstructions;

public class StackPosition {
    private int m_offset;
//    private int m_frame; // unused now
    
    private StackPosition(int frame, int offset) {
        if (frame > 0) {
            throw new Error("useless information");
        } else if (frame < 0) {
            throw new Error("shouldn't see closure here");
            // all closures should have been eliminated by ClosureConverter.
        }
        m_offset = offset;
        // m_frame = frame;
    }
    
    public int getOffset() {
        return m_offset;
    }
    
//    public int getFrame() {
//        return m_frame;
//    }
    
    public static StackPosition createDef(String name, String id, int offset) {
    	System.out.println("allocate stack for " + name + ", id is " + id + ", offset is " + offset);
        return new StackPosition(0, offset);
    }
    
//    public static StackPosition createUsage(int frame, int offset) {
//        return new StackPosition(frame, offset);
//    }
    
    @Override
    public boolean equals(Object o) {
        throw new Error("not supported");
    }
}
