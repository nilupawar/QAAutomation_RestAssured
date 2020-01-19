package com.automation.qa.examples.logger;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example_Logback {
    private static final Logger LOGGER = LoggerFactory.getLogger(Example_CreateJSONObject.class);

    public static void main(String[] args){
        //printLogbackConfigStatus();
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");

        Example_GetClassPath example_getClassPath = new Example_GetClassPath();
        example_getClassPath.loggerTest();
    }


    //---------------------------------------------------------------------------
    // This method will print the status of the logback configuration
    private static void printLogbackConfigStatus(){
        // assume SLF4J is bound to logback in the current environment
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
        StatusPrinter.print(lc);
    }


}
