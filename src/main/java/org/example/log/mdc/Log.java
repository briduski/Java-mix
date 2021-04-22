package org.example.log.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Log {

    private static Logger logger = LoggerFactory.getLogger(Log.class);

    public static void main(String[] args) {
        try {
            MDC.put("username", "XXXX");
            logger.debug("Debugging logging usin MDC");
            new Log2().main();
            try {
                Thread.sleep(1200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Log2().main();

        } finally {
//            MDC.remove("username");
            MDC.clear();
        }
            logger.info("App=0123;A=1;B=2");
    }

}
