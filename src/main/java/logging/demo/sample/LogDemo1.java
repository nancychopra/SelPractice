package logging.demo.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogDemo1 {

    private static Logger logger = LogManager.getLogger(logging.demo.sample.LogDemo1.class.getName());

    public static void main(String[] args) {
        logger.info("I am log demo1");

        logger.info("This is info");
        logger.error("This is error");
        logger.fatal("This is fatal");
        logger.warn("This is warn");
        logger.trace("This is trace");

    }
}
