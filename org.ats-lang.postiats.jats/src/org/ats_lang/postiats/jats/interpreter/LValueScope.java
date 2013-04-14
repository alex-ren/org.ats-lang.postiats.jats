package org.ats_lang.postiats.jats.interpreter;

import java.util.Map;

import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.utils.MapScope;
import org.ats_lang.postiats.jats.value.LValue;


/*
 * The update function takes different strategies for updating 
 * based on the type (LValue or not) of element stored in the scope.
 * 
 */
public class LValueScope implements ATSScope<Object> {

    private ATSScope<Object> m_scope;

    public LValueScope() {
        m_scope = new MapScope<Object>();
    }

    private LValueScope(ATSScope<Object> p) {
        m_scope = p;
    }

    @Override
    public void addValue(String id, Object v) {
      Object ele = m_scope.getValue(id);
      if (ele == null) {
          // initialize
          m_scope.addValue(id, v);
      } else {
          // two kinds of semantics
          if (ele instanceof LValue) {  // update
              ((LValue) ele).updateValue(v);
          }
          else if (ele instanceof Map<?, ?>) {
              // todo update the content of the Map
          }
          else {  // replace
              m_scope.addValue(id, v);
          }
      }
    }

    @Override
    public Object getValue(String id) {
        return m_scope.getValue(id);
    }

    @Override
    public LValueScope getParent() {
        ATSScope<Object> parent = m_scope.getParent();
        return new LValueScope(parent);

    }

    @Override
    // Caution: v.newScope().getParent() != v
    public LValueScope newScope() {
        return new LValueScope(m_scope.newScope());
    }
}
