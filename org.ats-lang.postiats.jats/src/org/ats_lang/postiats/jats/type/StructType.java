package org.ats_lang.postiats.jats.type;
import java.util.ArrayList;
import java.util.List;

import org.ats_lang.postiats.jats.tree.DefinitionNode;

public class StructType implements ATSType {
    private List<DefinitionNode> m_members;
    
    public StructType() {
        m_members = new ArrayList<DefinitionNode>();
    }
    
    public void addMember(DefinitionNode mem) {
        m_members.add(mem);
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

}
