package testcase.testBatchAndOnce;

import com.game.context.SpringHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * test Batch
 * Created by xiewen on 2016/8/26.
 */
public class TestSqlPrint {

    private static Logger logger = LogManager.getLogger(TestSqlPrint.class);

    public static void main(String[] args) {
        IHelpDaos obj = (IHelpDaos) SpringHandler.loadSpring().getSpringContext().getBean("testDaos");

        logger.debug("before query");
        obj.select();
        logger.debug("succeed to query");
    }
}