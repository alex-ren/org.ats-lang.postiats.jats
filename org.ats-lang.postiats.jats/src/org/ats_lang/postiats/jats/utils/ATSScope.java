package org.ats_lang.postiats.jats.utils;

public interface ATSScope<T> {

    public void addValue(String id, T v);
    
    public T getValue(String id);

    public ATSScope<T> getParent();
    
    // Caution: No constraints that v.newScope().getParent() == v
    public ATSScope<T> newScope();

}

