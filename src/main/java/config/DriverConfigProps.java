package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:${env.config.path}",
        "file:${common.config.path}"
})
public interface DriverConfigProps extends Config {
    @Key("web.url")
    String webUrl();

    @Key("execution.type")
    String executionType();

    @Key("driver.type")
    String driverType();

    @Key("hub.url")
    String getHubUrl();

    @Key("driver.timeout")
    int driverTimeout();
}
