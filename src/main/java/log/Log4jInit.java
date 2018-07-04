package log;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;

public class Log4jInit {

    /**
     *
     */
    public static void initConfig(){
        try {
            String path = LogUtil.class.getClassLoader().getResource("config/").getPath()+"log4j2.xml";

            File file = new File(path);
            ConfigurationSource source = new ConfigurationSource(new FileInputStream(file), file);
            Configurator.initialize(LogUtil.class.getClassLoader(), source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
