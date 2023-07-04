package me.vinfer.executor.core;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author vinfer
 * @date 2023-07-04 15:14
 */
public class ScalableThreadPoolExecutor extends AnalyzablePoolExecutor {

    public ScalableThreadPoolExecutor(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      Integer capacity,
                                      ThreadFactory threadFactory,
                                      RejectedExecutionHandler handler) {
        super(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                createTaskQueue(capacity),
                threadFactory,
                handler
        );
    }

    static BlockingQueue<Runnable> createTaskQueue(Integer capacity) {
        return new ScalableExecutorTaskQueue(capacity);
    }

    static class ScalableExecutorTaskQueue extends LinkedBlockingQueue<Runnable> {
        private ThreadPoolExecutor executor;

        public ScalableExecutorTaskQueue(Integer capacity) {
            super(Objects.requireNonNullElse(capacity, Integer.MAX_VALUE));
        }

        @Override
        public boolean offer(Runnable runnable) {
            int currentPoolSize = executor.getPoolSize();
            int maximumPoolSize = executor.getMaximumPoolSize();

            // 当前线程数小于最大线程数，不放入队列而是让线程池去创建非核心线程来执行
            if (currentPoolSize < maximumPoolSize) {
                return false;
            }

            return super.offer(runnable);
        }

        public void setExecutor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }
    }

}
