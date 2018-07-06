package com.carlos.cat;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {


    private static ExecutorService executorService;

    static {
        initThreadPoolExecutor();
    }

    private static void initThreadPoolExecutor() {
        int corePoolSize = 0;
        int maxPoolSize = Integer.MAX_VALUE;
        long aliveTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;
        executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTime, unit,
                new SynchronousQueue<Runnable>());
    }

    public static void newTask(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static void shutdown() {
        executorService.shutdownNow();
    }
}
