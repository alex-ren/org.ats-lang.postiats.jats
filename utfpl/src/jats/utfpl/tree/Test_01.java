package jats.utfpl.tree;

import jats.utfpl.ccomp.CCompUtils;

import jats.utfpl.instruction.TID;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.stfpl.UtfplPrinter;
import jats.utfpl.stfpl.UtfplProgramParserJson;
import jats.utfpl.stfpl.UtfplProgramProcessor;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;


public class Test_01 {

    /**
     * @param args
     * @throws IOException 
     * @throws RecognitionException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, RecognitionException, InterruptedException {
        String [] paths = {
//                "test/src_mutfpl/17_extcode.mutfpl"
//                "test/src_ats/demo_mc_dyn.dats"
//                "test/src_ats/51_2_4_slots.dats"
//                "test/test_temp.utfpl"
                "test/src_ats/54_peterson.dats"

        };

        for (String strPath: paths) {
        	
            System.out.println("==Processing file " + strPath + "==========");
            System.out.println("");
            
            File path = new File(strPath);
            ProgramTree prog = null;
            
            if (FilenameUtils.isATS(path)) {
            	path = FilenameUtils.toJson(path);
            	
            	String cmd = "patsopt -o " + path.getPath() + " --jsonize-2 -d " + strPath;
            	System.out.println("cmd is " + cmd);
            	Process child = Runtime.getRuntime().exec(cmd);
            	int returnCode = child.waitFor();
            	System.out.println("returnCode is " + returnCode);
            	if (0 == returnCode) {
                    FileReader fReader = new FileReader(path);

                    UtfplProgramParserJson utfplParser = new UtfplProgramParserJson();
                    ProgramUtfpl uProg = utfplParser.trans(fReader);

                    UtfplPrinter uPrinter = new UtfplPrinter();
                    String outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code (layer 01) is ==========================");
                    
                    System.out.println(outputUTFPL);
                    
                    UtfplProgramProcessor processor = new UtfplProgramProcessor();
                    uProg = processor.removeProof(uProg);
                    outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code (layer 02) is ==========================");
                    
                    System.out.println(outputUTFPL);
                    
                    FileWriter fwUTFPL = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cUTFPL));
                    BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
                    bwUTFPL.write(outputUTFPL);
                    bwUTFPL.close();
                    
                    TreeFromUtfpl treeV = new TreeFromUtfpl();
                    
                    prog = treeV.trans(uProg);
            	} else {
            		String line;
            		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
            		while ((line = reader.readLine()) != null) {
            			System.err.println(line);
            		}
            		return;            		
            	}

            } else {
                ANTLRFileStream fileStream = new ANTLRFileStream(path.getPath());
                
                /* ******** ******** */
                // lexing
                UtfplLexer lexer = new UtfplLexer(fileStream);
                TokenStream tokenStream = new CommonTokenStream(lexer);
                // System.out.println(tokenStream.toString());
                
                /* ******** ******** */
                // parsing
                UtfplParser parser = new UtfplParser(tokenStream);  // create worker
                UtfplParser.rule_return parser_ret = parser.rule();  // worker works
                CommonTree tree = (CommonTree)parser_ret.getTree();
                CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
                
                /* ******** ******** */
                // tree parsing
                Utfpl_tree walker = new Utfpl_tree(nodes);  // create worker

                prog = walker.rule();  // worker works
            }
     
            
            /* ***************** ****************** */
            // naming construction
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            /* ***************** ****************** */
            // print tree
            TreePrinter tp = new TreePrinter();  // create worker
            String output1 = tp.print(prog);  // worker works
            
            System.out.println("==program is ==========================");
            System.out.println(output1);

            System.out.println("\n" + "==" + strPath + " is O.K. " + " ==============================================================================\n");
        }

    }

}
