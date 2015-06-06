package com.ankur.photostream.executor;

public interface PostExecutionThread {
    /**
     * Causes the {@link Runnable} to be added to the message queue of the Main
     * UI Thread of the application.
     *
     * @param runnable
     *            {@link Runnable} to be executed.
     */
    void post(Runnable runnable);
}