package com.ankur.photostream.presentation.presenter;

import com.ankur.photostream.common.QueryParams;
import com.ankur.photostream.domain.interactor.GetItemsUseCase;
import com.ankur.photostream.domain.usecase.Callback;
import com.ankur.photostream.executor.ExecutorFactory;
import com.ankur.photostream.presentation.view.CollectionView;
import com.ankur.photostream.utils.LogUtils;

import java.util.Collection;

public class ItemPresenter<I> extends Presenter<CollectionView<I>> {

    private static final String LOG_TAG  = "ITEM_PRESENTER";

    private GetItemsUseCase<I>  mGetItemsUseCase;

    private Callback            mGetCollectionCallback;

    private boolean             isPaused = true;

    public ItemPresenter(GetItemsUseCase<I> getItemsUseCase) {
        mGetItemsUseCase = getItemsUseCase;
        mGetCollectionCallback = new Callback<Collection<I>>() {
            @Override
            public void onSuccess(Collection<I> item) {
                handleItemUpdate(item);
            }

            @Override
            public void onError(Exception exception) {
                handleError(exception);
            }
        };
    }

    /**
     * Handles the collection fetched
     */
    private void handleItemUpdate(Collection<I> item) {
        hideViewLoading();
        showItemInView(item);
    }

    /**
     * Handles the error occurred while fetching the collection
     */
    private void handleError(Exception ex) {
        hideViewLoading();
        showErrorMessage(ex);
        showViewRetry();
    }

    @Override
    public void resume() {
        if (LogUtils.isDebugLogEnabled()) {
            LogUtils.debugLog(LOG_TAG, this.getClass().getSimpleName() + ": resume()");
        }
        isPaused = false;
    }

    @Override
    public void pause() {
        if (LogUtils.isDebugLogEnabled()) {
            LogUtils.debugLog(LOG_TAG, this.getClass().getSimpleName() + ": pause()");
        }
        isPaused = true;
    }

    public void init(QueryParams queryParams) {
        loadItem(queryParams);
    }

    public void loadItem(QueryParams queryParams) {
        hideViewRetry();
        showViewLoading();
        getItem(queryParams);
    }

    private void getItem(QueryParams queryParams) {
        mGetItemsUseCase.getItem(queryParams, ExecutorFactory.getPostExecutionThreadInstance(), mGetCollectionCallback,
                true, true);
    }

    void showItemInView(Collection<I> item) {
        if (isPaused) {
            return;
        }
        getView().renderCollection(item);
    }

    private void showViewLoading() {
        try {
            getView().showLoading();
        } catch (Exception e) {

        }
    }

    private void hideViewLoading() {
        try {
            getView().hideLoading();
        } catch (Exception e) {

        }
    }

    private void showViewRetry() {
        try {
            getView().showRetry();
        } catch (Exception e) {

        }
    }

    private void hideViewRetry() {
        try {
            getView().hideRetry();
        } catch (Exception e) {

        }
    }

    public void onItemClicked(I item) {
        try {
            getView().viewItem(item);
        } catch (Exception e) {

        }
    }

    private void showErrorMessage(Exception ex) {
        if (ex != null) {
            getView().showError(ex.toString());
        } else {
            getView().showError("NULL ERROR");
        }
    }

}
