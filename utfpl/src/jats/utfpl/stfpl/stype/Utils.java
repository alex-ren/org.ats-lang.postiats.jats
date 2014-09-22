package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.staexp.Ifunclo;

public class Utils {
    static public Ifunclo getCloInfo(ISType type) {
        if (type instanceof FunType) {
            return ((FunType)type).getFunClo();
        } else if (type instanceof PolyType) {
            return getCloInfo(((PolyType)type).m_body);
        } else {
            throw new Error(type + " is no way a closure.");
        }
    }
}
