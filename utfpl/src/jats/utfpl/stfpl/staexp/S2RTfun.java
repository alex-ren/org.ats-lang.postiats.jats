package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.stype.ESort;

import java.util.List;

public class S2RTfun implements Is2rt {
    public List<Is2rt> m_args;
    public Is2rt m_res;

    public S2RTfun(List<Is2rt> args, Is2rt res) {
        m_args = args;
        m_res = res;
    }

    @Override
    public ESort simplify() {
        return ESort.advance;
    }

    @Override
    public boolean isType() {
        return false;
    }


}
