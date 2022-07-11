package config;

import java.util.Arrays;
import java.util.Objects;

public enum Environment {
    amazon;

    public static Environment getByCommandLineProp() {
        String env = System.getProperty("env");
        Objects.requireNonNull(env, "Environment is not defined. Specify it by '-Denv=<env>'");
        return Arrays.stream(values())
                .filter(it -> it.name().equals(env))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Couldn't find '" + env + "' environment. Values: "
                        + Arrays.toString(values())));
    }
}
