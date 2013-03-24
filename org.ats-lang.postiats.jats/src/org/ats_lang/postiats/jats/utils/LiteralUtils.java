package org.ats_lang.postiats.jats.utils;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.ats_lang.postiats.jats.parser.ATSILLexer;

public class LiteralUtils {
    public static String getStringEcsaped(String input) {
        CharStream stream = new ANTLRStringStream(input);
        ATSILLexer lexer0 = new ATSILLexer(stream);
        try {
            lexer0.mSTRING();
        } catch (RecognitionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lexer0.getEscaped();
    }
    
    public static String getCharEcsaped(String input) {
//        System.out.println("getCharEcsaped, input is " + input);
//        System.out.println("getCharEcsaped, input2 is " + '\000');
        CharStream stream = new ANTLRStringStream(input);
        ATSILLexer lexer0 = new ATSILLexer(stream);
        try {
            lexer0.mCHAR();
        } catch (RecognitionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lexer0.getEscaped();
    }
    
    public static void main(String [] argv) {
        System.out.println("sss = " + String.valueOf((char)1));
    }
    
}
