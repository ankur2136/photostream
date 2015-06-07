package com.ankur.photostream.domain.usecase;

public interface Callback<T> {

    void onSuccess(T obj);

    void onError(Exception ex);
}
