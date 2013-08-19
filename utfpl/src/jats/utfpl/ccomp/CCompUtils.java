package jats.utfpl.ccomp;

import jats.utfpl.tree.TID;
import jats.utfpl.utils.MapScope;

public class CCompUtils {
    private static String [] m_funcs = new String[] {
        "add", "sub", "mul", "div"
        , "lt", "lte", "gt", "gte", "eq"
        , "hello", "mojo"
        , "printx"
        , "createEvt"
        };

    public static void populateAllFunctions(MapScope<TID> scope) {
        for (String func: m_funcs) {
            scope.addValue(func, TID.createLibFun(func));
        }
        return;
    }
}
