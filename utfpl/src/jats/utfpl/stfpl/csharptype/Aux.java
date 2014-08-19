package jats.utfpl.stfpl.csharptype;

import jats.utfpl.stfpl.Ilabel;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Aux {
    
    
    static public CSTBookingRecord findBookingRec(Set<ICSTypeBooking> track, List<Ilabel> labs) {
        for (ICSTypeBooking tybook: track) {
            if (tybook instanceof CSTBookingRecord) {
                CSTBookingRecord recbook = (CSTBookingRecord)tybook;
                if (recbook.m_labs.size() == labs.size()) {
                    ListIterator<Ilabel> liter = recbook.m_labs.listIterator();
                    ListIterator<Ilabel> riter = labs.listIterator();
                    boolean mismatched = false;
                    while (liter.hasNext()) {
                        if (!liter.next().equals(riter.next())) {
                            mismatched = true;
                            break;
                        }
                    }
                    if (!mismatched) {
                        return recbook;
                    }
                }
            }
        }
        return null;
    }
    
    static public CSTBookingFun findBookingFun(Set<ICSTypeBooking> track, int para_size, boolean is_void) {
        for (ICSTypeBooking tybook: track) {
            if (tybook instanceof CSTBookingFun) {
                if (((CSTBookingFun)tybook).m_para_size == para_size && ((CSTBookingFun)tybook).m_is_void == is_void) {
                    return (CSTBookingFun)tybook;
                }
            }
        }
        return null;
    }

}
