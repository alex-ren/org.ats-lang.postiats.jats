package org.ats_lang.postiats.jats.translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;

import org.ats_lang.postiats.jats.parser.ATSILPrepocessorLexer;
import org.ats_lang.postiats.jats.utils.FilenameUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class STTest {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {

        STGroup group = new STGroupFile("src/org/ats_lang/postiats/jats/translator/sttest.stg");
        System.out.println("==group template file loaded==============");
        
        ST st = group.getInstanceOf("decl");
        st.add("type", "int");
        st.add("name", "x");
        st.add("value", 0);
        String result = st.render(); // yields "int x = 0;"
        System.out.println(result);

        String[] files = { "test/test01_dats.c" , "test/f91_dats.c",
                                                 "test/fact_dats.c",
                                                 "test/fib_dats.c"};  // ,
                                             //    "test/atof_dats.c"};

//        // populate types and funcstions
//        Map<String, ATSType> types = CCompTypes.getLibTypes();
//
//        Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
//        CCompUtils.populateAllFuncs(funcs);

        for (String fileName : files) {
            System.out.println("Processing file " + fileName);

            ANTLRFileStream fileStream = new ANTLRFileStream(fileName);
            
            File file = new File(fileName);
            String classname = FilenameUtils.removeExtension(file.getName());
            
            System.out.println("eeeeeeeeeeeeeeeeeeeee");
        }

    }

}
