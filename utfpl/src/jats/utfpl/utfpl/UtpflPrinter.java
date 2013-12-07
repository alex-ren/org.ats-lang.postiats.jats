package jats.utfpl.utfpl;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class UtpflPrinter {
    private STGroup m_stg;
    
    public  UtpflPrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/utfpl/utfpl.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');

    }
    
    public ST print(Cd2cst d2cst) {
        ST st = m_stg.getInstanceOf("d2cst_st");
        st.add("obj", d2cst);
        return st;
    }
    
}
