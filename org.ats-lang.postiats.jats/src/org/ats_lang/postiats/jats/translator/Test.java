
package org.ats_lang.postiats.jats.translator;

import org.stringtemplate.v4.*;

public class Test {

    public static void main(String[] args) {
        ST st = new ST("<items:{it|<it.id>: <it.lastName>, <it.firstName>\n}>");
        st.addAggr("items.{ firstName ,lastName, id }", "Ter", "Parr", 99); // add() uses varargs
        st.addAggr("items.{firstName, lastName ,id}", "Tom", "Burns", 34);
        System.out.println(st.render());
    }

}

