package me.vinfer.executor.isolated;

import lombok.Data;

/**
 * 业务模块自动隔离的线程池配置清单。
 *
 * <p> 每个业务模块构建自己的业务线程池，当有来自业务模块内的异步任务提交执行时，
 * 委派执行器读取提交调用时的栈帧，并根据 {@link #executor#getModuleTag()}
 * 来匹配对应的业务线程池隔离地执行这些任务。
 *
 * @author vinfer
 * @date 2023-07-04 15:00
 */
public class AutoIsolatedExecutorProperties {

    public static final String REJECTION_POLICY_CALL_RUNNER = "CALL_RUNNER";

    public static final String REJECTION_POLICY_ABORT = "ABORT";

    public static final String REJECTION_POLICY_DISCARD = "DISCARD";

    public static final String REJECTION_POLICY_DISCARD_OLDEST = "DISCARD_OLDEST";

    private ModuleExecutorProperties executor;

    public String getExecutorName() {
        if (null == executor) {
            return null;
        }
        return executor.getName();
    }

    public Integer getCorePoolSize() {
        if (null == executor) {
            return null;
        }
        return executor.getCorePoolSize();
    }

    public Integer getMaxPoolSize() {
        if (null == executor) {
            return null;
        }
        return executor.getMaxPoolSize();
    }

    public Integer getQueueCapacity() {
        if (null == executor) {
            return null;
        }
        return executor.getQueueCapacity();
    }

    public Long getKeepAliveMill() {
        if (null == executor) {
            return null;
        }
        return executor.getKeepAliveMill();
    }

    public String getThreadNamePrefix() {
        if (null == executor) {
            return null;
        }
        return executor.getThreadNamePrefix();
    }

    public String getRejectionPolicy() {
        if (null == executor) {
            return null;
        }
        return executor.getRejectionPolicy();
    }

    public String getModuleTag() {
        if (null == executor) {
            return null;
        }
        return executor.getModuleTag();
    }

    @Data
    public static class ModuleExecutorProperties {

        private String name;

        private Integer corePoolSize;

        private Integer maxPoolSize;

        private Integer queueCapacity;

        private Long keepAliveMill = 0L;

        private String threadNamePrefix;

        private String rejectionPolicy = REJECTION_POLICY_CALL_RUNNER;

        /**
         * 线程池所属业务顶级包名，用于业务线程池的自动隔离。
         * <p> 例如：com.liquido.commerce.order，
         * 那么只要是该包下的类去调用 AutoIsolatedExecutor 执行任务，都会使用该tag对应的业务线程池
         */
        private String moduleTag;

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

}
