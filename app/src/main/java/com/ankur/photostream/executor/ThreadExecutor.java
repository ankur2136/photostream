package com.ankur.photostream.executor;

public interface ThreadExecutor {
    java.util.concurrent.Future<?> execute(final Runnable runnable);
}
