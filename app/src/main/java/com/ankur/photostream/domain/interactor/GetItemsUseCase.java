package com.ankur.photostream.domain.interactor;


import com.ankur.photostream.common.QueryParams;
import com.ankur.photostream.domain.usecase.Callback;
import com.ankur.photostream.executor.PostExecutionThread;

public interface GetItemsUseCase<T> extends Interactor {

    void getItem(QueryParams queryParams, PostExecutionThread postExecutionThread, Callback<T> callback, boolean async,
            boolean applyUserState);

}
