package jats.utfpl.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public static final Logger log4j = LogManager.getLogger(Log.class
            .getName());
    
    public static void test() {
        log4j.trace("This is a trace message."); 
        log4j.debug("This is  a debug message."); 
        log4j.info("This is an info message."); 
        log4j.error("This is an error message");
    }
}
