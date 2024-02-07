package logging.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogDemo {

    private static Logger logger = LogManager.getLogger(LogDemo.class.getName());

    public static void main(String[] args) {

        logger.info("This is info");
        logger.error("This is error");
        logger.debug("This is debug");
        logger.fatal("This is fatal");
        logger.warn("This is warn");
        logger.trace("This is trace");

    }
}
