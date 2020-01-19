package com.automation.qa.examples.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.net.URLClassLoader;

public class Example_GetClassPath {

    private static final Logger LOGGER = LoggerFactory.getLogger(Example_GetClassPath.class);
    public static void main(String[] args){

            ClassLoader cl = ClassLoader.getSystemClassLoader();

            URL[] urls = ((URLClassLoader)cl).getURLs();

            for(URL url: urls){
                LOGGER.info(url.getFile());
            }
    }

    public void loggerTest(){
        LOGGER.info("Info log from Example_GetClassPath class");
    }
}
