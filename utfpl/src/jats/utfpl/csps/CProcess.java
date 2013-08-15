package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.tree.TID;

public class CProcess {
    private TID m_name;
    private List<CTempID> m_paras;
    private List<CGroup> m_body;
    private TID m_ret;
    
    public CProcess(TID name, List<CTempID> paras, List<CGroup> body, TID ret) {
        m_name = name;
        m_paras = paras;
        m_body = body;
        m_ret = ret;
    }
}
