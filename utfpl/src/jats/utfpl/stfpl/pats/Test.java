package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.ModelGenerater;
import jats.utfpl.utils.SystemEnv;

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

//		"src/jats/utfpl/stfpl/test/01_funcall_return_void.dats",
//				"src/jats/utfpl/stfpl/test/02_closure_call.dats",
//				"src/jats/utfpl/stfpl/test/02_2_closure_level.dats",
////				"src/jats/utfpl/stfpl/test/02_3_closure_return.dats", // This is not working, since S2EFun has no info of closure.
//				"src/jats/utfpl/stfpl/test/03_symbol_opr.dats",
//				"src/jats/utfpl/stfpl/test/04_if_function.dats",
//        		"src/jats/utfpl/stfpl/test/04_2_if_function_tag.dats",
//				"src/jats/utfpl/stfpl/test/05_use_ref.dats",
//				"src/jats/utfpl/stfpl/test/06_mc_assert.dats",
//				"src/jats/utfpl/stfpl/test/07_use_array.dats",
//				"src/jats/utfpl/stfpl/test/08_use_mutex.dats",
//				"src/jats/utfpl/stfpl/test/09_tuple.dats",
//				"src/jats/utfpl/stfpl/test/10_create_thread.dats",
//				"src/jats/utfpl/stfpl/test/11_fact_fun.dats",
//				"src/jats/utfpl/stfpl/test/12_fact_closure.dats",
//				"src/jats/utfpl/stfpl/test/13_if_noeffect.dats",
//				"src/jats/utfpl/stfpl/test/14_create_threads.dats",
//				"src/jats/utfpl/stfpl/test/15_thread_cond.dats",
//				"src/jats/utfpl/stfpl/test/15_2_thread_cond.dats",
//				"src/jats/utfpl/stfpl/test/16_producer_consumer.dats",
//				"src/jats/utfpl/stfpl/test/16_1_producer_consumer_m_1.dats",
//        		"src/jats/utfpl/stfpl/test/16_2_producer_consumer_m_1_2cond.dats",
//        		"src/jats/utfpl/stfpl/test/16_3_producer_consumer_m_m_signal.dats",
//        		"src/jats/utfpl/stfpl/test/16_4_producer_consumer_m_m_broadcast.dats",
//				"src/jats/utfpl/stfpl/test/17_mcset_mcget.dats",
//        		"src/jats/utfpl/stfpl/test/17_1_mcset_mcget_calc.dats",
//				"src/jats/utfpl/stfpl/test/18_atomic_opr.dats",
//				"src/jats/utfpl/stfpl/test/19_mc_view.dats",
//        		"src/jats/utfpl/stfpl/test/20_four_slot.dats",
//        		"src/jats/utfpl/stfpl/test/20_1_two_slot_acm.dats",
//        		"src/jats/utfpl/stfpl/test/20_2_three_slot_acm.dats",
//        		"src/jats/utfpl/stfpl/test/20_3_four_slot_acm.dats",
//        		"src/jats/utfpl/stfpl/test/21_global_val.dats",
//        		"src/jats/utfpl/stfpl/test/22_remove_proof.dats",
//        		"src/jats/utfpl/stfpl/test/23_use_condition.dats",
//        		"src/jats/utfpl/stfpl/test/24_global_ghost_variable.dats",
//         		"src/jats/utfpl/stfpl/test/temp.dats",
//        		"src/jats/utfpl/stfpl/test/25_scheduler.dats"
        		"src/jats/utfpl/stfpl/test/26_peterson_exclusion.dats"
        		
        };

        for (String strPath : paths) {
        	ModelGenerater mcGen = new ModelGenerater(strPath, null, SystemEnv.getPATPath(), 0);
            mcGen.generate(8);
        }

    }
}
