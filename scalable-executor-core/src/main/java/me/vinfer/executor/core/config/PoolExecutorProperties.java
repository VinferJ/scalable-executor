package me.vinfer.executor.core.config;

import lombok.Data;

/**
 * @author vinfer
 * @date 2023-07-04 15:11
 */
@Data
public class PoolExecutorProperties {

    public static final String REJECTION_POLICY_CALL_RUNNER = "CALL_RUNNER";
    public static final String REJECTION_POLICY_ABORT = "ABORT";
    public static final String REJECTION_POLICY_DISCARD = "DISCARD";
    public static final String REJECTION_POLICY_DISCARD_OLDEST = "DISCARD_OLDEST";

    private String name;

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private Long keepAliveMill = 0L;

    private String threadNamePrefix;

    private String rejectionPolicy = REJECTION_POLICY_CALL_RUNNER;

    public String getThreadNamePrefix() {
        String prefix;

        if (null == threadNamePrefix) {
            prefix = getName();
        }else {
            prefix = threadNamePrefix;
        }

        return prefix + "-";
    }

}
