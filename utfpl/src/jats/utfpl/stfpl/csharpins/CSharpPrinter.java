package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.csharptype.CSTBookingFun;
import jats.utfpl.stfpl.csharptype.CSTBookingRecord;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;

import java.net.URL;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class CSharpPrinter {

    private STGroup m_stg;
    
    private List<CSDecGroup> m_decs;  // global declaration
    private List<D3Cextcode> m_exts;
    private List<CSDefFunGroup> m_defs;  // function definition
    private List<ICSInstruction> m_main_inss;  // global instructions
    private Set<ICSTypeBooking> m_track;  // type booking info
    
    public  CSharpPrinter(List<CSDecGroup> decs,
                          List<D3Cextcode> exts,
                          List<CSDefFunGroup> defs,
                          List<ICSInstruction> main_inss,
                          Set<ICSTypeBooking> track) {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/csharpins/csharp.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        m_decs = decs;
        m_exts = exts;
        m_defs = defs;
        m_main_inss = main_inss;
        m_track = track;

    }
    
    public String printCSharp() {
        ST st_booking = printBooking(m_track);
        
        
        return st_booking.render();
    }
    

    private ST printBooking(Set<ICSTypeBooking> bookings) {
    	// ICSTypeBooking_st(grecords, gdelegates) ::= <<
    	ST st = m_stg.getInstanceOf("booking_st");
	    for (ICSTypeBooking b: bookings) {
	    	if (b instanceof CSTBookingRecord) {
	    		ST stb = print((CSTBookingRecord)b);
	    		st.add("grecords", stb);
	    	} else if (b instanceof CSTBookingFun) {
	    		ST stb = print((CSTBookingFun)b);
	    		st.add("gdelegates", stb);
	    	} else {
	    		throw new Error(b + " is not supported");
	    	}
	    }
    }

	private ST print(CSTBookingRecord b) {
	    // TODO Auto-generated method stub
	    return null;
    }
    
}
