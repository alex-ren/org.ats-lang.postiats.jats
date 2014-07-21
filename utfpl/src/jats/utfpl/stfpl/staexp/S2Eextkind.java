package jats.utfpl.stfpl.staexp;



// example: t@ype: S2Eapp (s2rt_fun: g0int_t0ype, tkind: atstype_int)
// atstype_int is one S2Eextkind.
public class S2Eextkind implements Is2exp_node {
    public String m_kind;
    
    public S2Eextkind(String kind) {
        m_kind = kind;
    }

}
