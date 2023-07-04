package me.vinfer.executor.core.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.RequiredArgsConstructor;

/**
 * @author vinfer
 * @date 2023-07-04 15:26
 */
@RequiredArgsConstructor
public class PrefixNamingThreadFactory implements ThreadFactory {

    private final String namePrefix;

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, namePrefix + "-" + counter.getAndIncrement());
    }

}
