package web.engine;

import config.ConfigProvider;

import java.util.Arrays;

public enum DriverType {
    chrome, remote;

    public static DriverType getFromConfig() {
        String driverType = ConfigProvider.CONFIG_PROPS.driverType();
        if(driverType == null || driverType.isEmpty()){
           throw  new IllegalArgumentException("Driver type is not provided or config file is not correct");
        }
        return Arrays.stream(DriverType.values())
                .filter(it -> it.name().equals(driverType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provided driver type is not supported\n" +
                        "Driver Type: " + driverType));
    }
}
