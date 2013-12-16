package jats.utfpl.tree;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.instruction.TID;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.utfpl.UtfplPrinter;
import jats.utfpl.utfpl.UtfplProgram;
import jats.utfpl.utfpl.UtfplProgramParserJson;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Test_02_name_bind_UTFPL {

    /**
     * @param args
     * @throws IOException 
     * @throws RecognitionException 
     */
    public static void main(String[] args) throws IOException {
        String [] filenames = {
                "test/json/test01_dats.json"
                , "test/json/test02_dats.json"

        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            File file = new File(filename);
            String classname = FilenameUtils.removeExtension(file.getName());
            FileReader fReader = new FileReader(file);

            UtfplProgramParserJson utfplParser = new UtfplProgramParserJson();
            UtfplProgram uProg = utfplParser.trans(fReader);
            
            System.out.println("======= Generating Utfpl from JSON stream is finished ========== ");
            
            UtfplPrinter uPrinter = new UtfplPrinter();
            String output = uPrinter.print(uProg);
            
            System.out.println(output);
            
            FileWriter fwUTFPL = new FileWriter("test/json/" + classname
                    + ".myutfpl");
            BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
            bwUTFPL.write(output);
            bwUTFPL.close();
            
            System.out.println("======= Printing Utfpl is finished ========== ");
            
            TreeFromUtfpl treeGen = new TreeFromUtfpl();
            
            ProgramTree prog = treeGen.trans(uProg);
            
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            TreePrinter tp = new TreePrinter();
            output = tp.print(prog);
            
            System.out.println("==program is ==========================");
            System.out.println(output);
            
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }

    }

}
