package web.engine;

import config.ConfigProvider;

import java.util.Arrays;

public enum ExecutionType {
    local, docker_grid;

    public static ExecutionType getFromConfig() {
        String executionType = ConfigProvider.CONFIG_PROPS.executionType();
        if(executionType == null || executionType.isEmpty()){
            throw  new IllegalArgumentException("Execution type is not provided or config file is not correct");
        }
        return Arrays.stream(ExecutionType.values())
                .filter(it -> it.name().equals(executionType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provided execution type is not supported\n" +
                        "Execution Type: " + executionType));
    }
}
