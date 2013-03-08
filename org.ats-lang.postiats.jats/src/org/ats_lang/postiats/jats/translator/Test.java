package org.ats_lang.postiats.jats.translator;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.ats_lang.postiats.jats.ccomp.CCompUtils;
import org.ats_lang.postiats.jats.parser.ATSILLexer;
import org.ats_lang.postiats.jats.parser.ATSILParser;
import org.ats_lang.postiats.jats.parser.ATSILPrepocessorLexer;
import org.ats_lang.postiats.jats.parser.ATSILPrepocessorParser;
import org.ats_lang.postiats.jats.type.ATSType;

public class Test {

    /**
     * @param args
     * @throws RecognitionException
     * @throws IOException
     */
    public static void main(String[] args) throws RecognitionException,
            IOException {
        
//        // little try using StringTemplate V4
//        STGroup group = new STGroupFile("org/ats_lang/postiats/jats/translator/ats_il_java.stg");
//        
//        ST st = group.getInstanceOf("classes_st");
//        String [] arr = {"xjsiejfwef;", "feifjeijfw;"};
//        st.add("classes", arr);
//        String result = st.render(); // yields "int x = 0;"
//        System.out.println(result);
        
        // load the group file ats_il_java.stg, put in templates
        FileReader groupFileR = new FileReader(
                "src/org/ats_lang/postiats/jats/translator/ats_il_java.stg");
        StringTemplateGroup templates = new StringTemplateGroup(groupFileR);
        groupFileR.close();
        System.out.println("==group template file loaded==============");

        String[] files = { "test/test01.txt" , "test/f91_dats.c",
                                                 "test/fact_dats.c",
                                                 "test/fib_dats.c",
                                                 "test/test_dats.c"};

//        // populate types and funcstions
//        Map<String, ATSType> types = CCompTypes.getLibTypes();
//
//        Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
//        CCompUtils.populateAllFuncs(funcs);

        for (String file : files) {
            System.out.println("Processing file " + file);

            ANTLRFileStream fileStream = new ANTLRFileStream(file);
            
            // preprocessing
            ATSILPrepocessorLexer lexer0 = new ATSILPrepocessorLexer(fileStream);
            TokenStream tokens = new TokenRewriteStream(lexer0);
            ATSILPrepocessorParser preparser = new ATSILPrepocessorParser(tokens);
            preparser.rule();
            
            System.out.println("==preprocessing finished==========================");
            
            /* ******** ******** */
            // lexing
            ANTLRStringStream stream = new ANTLRStringStream(tokens.toString());
            ATSILLexer lexer = new ATSILLexer(stream);
            TokenStream tokenStream = new CommonTokenStream(lexer);

            /* ******** ******** */
            // parsing
            ATSILParser parser = new ATSILParser(tokenStream);
            ATSILParser.rule_return parser_ret = parser.rule();
            CommonTree tree = (CommonTree) parser_ret.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);

            /* ******** ******** */
            // tree parsing
            ATSIL2JavaPass1 walker = new ATSIL2JavaPass1(nodes);
            walker.setTemplateLib(templates);
            
            // populate types and funcstions
            Map<String, ATSType> types = CCompUtils.getLibTypes();
            
//            Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
//            CCompUtils.populateAllFuncs(funcs);
            
            // collect the definition of all the structures
            ATSIL2JavaPass1.program_return ret = walker.program(types, file);
            // ATSNode prog = walker.program(types, funcs);

            /* ******** ******** */
            StringTemplate output = ret.st;

            /* ******** ******** */
            System.out.println(output.toString()); // render full template

            System.out.println(file + " is O.K.\n\n");
        }
    }

}
