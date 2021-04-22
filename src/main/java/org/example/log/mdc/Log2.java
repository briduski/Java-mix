package org.example.log.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;

public class Log2 {

    Logger logger = LoggerFactory.getLogger(Log2.class);

    public void main() {
        try {
            MDC.put("header1", "val1");
            MDC.put("ts", new Date().toString());
            logger.info("logging A ... ");
            try {
                Thread.sleep(1200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("logging B ...");
        } finally {
            MDC.remove("header1");
            MDC.remove("ts");
        }
        logger.info("logging done");
    }

}