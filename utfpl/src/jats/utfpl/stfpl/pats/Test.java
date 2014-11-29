package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.ModelGenerater;

import java.io.IOException;

import org.antlr.runtime.RecognitionException;

public class Test {
    /**
     * @param args
     * @throws IOException 
     * @throws InterruptedException 
     * @throws RecognitionException 
     */
    public static void main(String[] args) throws IOException, InterruptedException, RecognitionException {
        String[] paths = { 

//        		  "src/jats/utfpl/stfpl/test/01_funcall_return_void.dats"
//        		, "src/jats/utfpl/stfpl/test/02_closure_call.dats"
//        		"src/jats/utfpl/stfpl/test/02_2_closure_level.dats"
//        		"src/jats/utfpl/stfpl/test/02_3_closure_return.dats"        		
//        		, "src/jats/utfpl/stfpl/test/03_symbol_opr.dats"
//        		, "src/jats/utfpl/stfpl/test/04_if_function.dats"
//        		"src/jats/utfpl/stfpl/test/05_use_ref.dats"
//        		, "src/jats/utfpl/stfpl/test/06_mc_assert.dats"
//        		, "src/jats/utfpl/stfpl/test/07_use_array.dats"
//        		"src/jats/utfpl/stfpl/test/08_use_mutex.dats"
//        		 "src/jats/utfpl/stfpl/test/09_tuple.dats"
//        		, "src/jats/utfpl/stfpl/test/10_create_thread.dats"
//        		"src/jats/utfpl/stfpl/test/11_fact_fun.dats"
//        		, "src/jats/utfpl/stfpl/test/12_fact_closure.dats"
//        		"src/jats/utfpl/stfpl/test/13_if_noeffect.dats"
//        		"src/jats/utfpl/stfpl/test/14_create_threads.dats"
//        		"src/jats/utfpl/stfpl/test/15_thread_cond.dats"
        		"src/jats/utfpl/stfpl/test/16_reader_writer.dats"


        		
        };

        for (String strPath : paths) {
            ModelGenerater mcGen = new ModelGenerater(strPath, null);
            mcGen.generate(8);
        }

    }
}
