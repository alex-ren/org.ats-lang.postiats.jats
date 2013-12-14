package jats.utfpl.utfpl;

import jats.utfpl.utils.FilenameUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        String[] filenames = { 
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
            

        }
    }

}
