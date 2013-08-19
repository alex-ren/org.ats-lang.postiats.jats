package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.tree.TID;

public class CProcess {
    private TID m_name;
    private List<CTempID> m_paras;
    private List<CBlock> m_body;
    
    public CProcess(TID name, List<CTempID> paras, List<CBlock> body) {
        m_name = name;
        m_paras = paras;
        m_body = body;
    }
}
