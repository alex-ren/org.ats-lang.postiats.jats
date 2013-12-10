package jats.utfpl.utfpl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        String[] filenames = { 
                "test/json/test01_dats.json"

        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            File file = new File(filename);
            FileReader fReader = new FileReader(file);

            UtfplProgramParserJson utfplParser = new UtfplProgramParserJson();
            UtfplProgram uProg = utfplParser.trans(fReader);
            
            System.out.println("======= Generating Utfpl from JSON stream is finished ========== ");
            
            UtfplPrinter uPrinter = new UtfplPrinter();
            String output = uPrinter.print(uProg);
            
            System.out.println(output);
            
            System.out.println("======= Printing Utfpl is finished ========== ");
            

        }
    }

}
