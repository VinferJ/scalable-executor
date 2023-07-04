package me.vinfer.executor.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author vinfer
 * @date 2023-07-04 15:20
 */
public abstract class AnalyzablePoolExecutor extends ThreadPoolExecutor {
    public AnalyzablePoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> taskQueue,
                                  ThreadFactory threadFactory,
                                  RejectedExecutionHandler handler) {
        super(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                taskQueue,
                threadFactory,
                handler
        );
    }

}
