package jats.utfpl.utfpl.staexp;


public class S2RT implements Is2rt {
    public String m_srt;
    
    public S2RT(String srt) {
        m_srt = srt;
    }

    @Override
    public boolean isType() {
        if (m_srt.equals("type") || m_srt.equals("t@ype")) {
            return true;
        } else {
            return false;
        }
    }

}
