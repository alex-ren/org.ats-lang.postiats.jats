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
//                "test/ats2utfpl/test01.dats"
//                , "test/ats2utfpl/test02.dats"
//                "test/src_ats/51_2_4_slots.dats"
//                "test/src_ats/54_peterson.dats"
//                "src/jats/utfpl/utfpl/test/test01.dats",
//                "src/jats/utfpl/utfpl/test/test02.dats",
//                "src/jats/utfpl/utfpl/test/test03.dats"
//                "src/jats/utfpl/utfpl/test/test04.dats"
//                "src/jats/utfpl/stfpl/test/test05.dats"
//                "src/jats/utfpl/stfpl/test/test06.dats"
//                "src/jats/utfpl/stfpl/test/test07.dats"
//                "src/jats/utfpl/stfpl/test/test_helloworld.dats",
//                "src/jats/utfpl/stfpl/csharpins/test/01_tuple_op.dats"
//                "src/jats/utfpl/stfpl/csharpins/test/02_if_branch.dats"
//                "src/jats/utfpl/stfpl/csharpins/test/03_closure.dats"
//                "src/jats/utfpl/stfpl/csharpins/test/04_polymorphism.dats"
//                "test/src_ats/53_demo_mc_dyn.dats"
//                "src/jats/utfpl/stfpl/test/test08.dats"
//              , "src/jats/utfpl/stfpl/test/test_helloworld.dats"
        		  "src/jats/utfpl/stfpl/test/01_funcall_return_void.dats"
        		, "src/jats/utfpl/stfpl/test/02_closure_call.dats"
        		, "src/jats/utfpl/stfpl/test/03_symbol_opr.dats"
        		, "src/jats/utfpl/stfpl/test/04_if_function.dats"
        		, "src/jats/utfpl/stfpl/test/05_use_ref.dats"
        		, "src/jats/utfpl/stfpl/test/06_mc_assert.dats"
        		, "src/jats/utfpl/stfpl/test/07_use_array.dats"
        		
        };

        for (String strPath : paths) {
            ModelGenerater mcGen = new ModelGenerater(strPath);
            mcGen.generate(8);
        }

    }
}