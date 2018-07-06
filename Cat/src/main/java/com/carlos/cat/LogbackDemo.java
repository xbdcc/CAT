package com.carlos.cat;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Carlos on 2018/6/27.
 */
public class LogbackDemo {

    private static final Logger logger = LoggerFactory.getLogger(LogbackDemo.class);

    public static void main(String[] args) {

        //这里强制类型转换时为了能设置 logger 的 Level : TRACE < DEBUG < INFO <  WARN < ERROR
//        ch.qos.logback.classic.Logger logback_logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
//        logback_logger.setLevel(Level.DEBUG);
//
//        logback_logger.error("logback_logger.error");
//        logback_logger.warn("logback_logger.warn");
//        logback_logger.info("logback_logger.info");
//        logback_logger.debug("logback_logger.debug");
//        logback_logger.trace("logback_logger.trace");

        logger.error("logger.error");
        logger.warn("logger.warn");
        logger.info("logger.info");
        logger.debug("logger.debug");
        logger.trace("logger.trace");
    }
}

