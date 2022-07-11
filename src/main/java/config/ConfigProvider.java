package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private static final String CONFIG_DIR = "src/test/resources/config/";
    public static final DriverConfigProps CONFIG_PROPS;

    static {
        Environment env = Environment.getByCommandLineProp();
        switch (env) {
            case amazon:
                ConfigFactory.setProperty("env.config.path", CONFIG_DIR + "/" + env + ".properties");
                break;
            default:
                throw new RuntimeException("Provide implementation for '" + env + "' environment");
        }
        if(System.getProperty("config") == null){
            throw new RuntimeException("Config file is not provided. Please find config file in test/java/resources/config");
        }
        ConfigFactory.setProperty("common.config.path", CONFIG_DIR + System.getProperty("config") + ".properties");
        CONFIG_PROPS = ConfigFactory.create(DriverConfigProps.class, System.getProperties());
    }
}
