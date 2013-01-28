package org.ats_lang.postiats.jats;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import org.ats_lang.postiats.jats.parser.*;


public class Test {

    /**
     * @param args
     * @throws RecognitionException 
     * @throws IOException 
     */
    public static void main(String[] args) throws RecognitionException, IOException {
        CharStream charStream = new ANTLRStringStream("\"ddd\" dd");
        // CharStream charStream1 = new ANTLRFileStream("test.iats");
        ATSILLexer lexer = new ATSILLexer(charStream );
        
        TokenStream tokenStream = new CommonTokenStream(lexer);
        ATSILParser parser = new ATSILParser(tokenStream );
        
        
        parser.rule();
        System.out.println("O.K.");
    }

}

