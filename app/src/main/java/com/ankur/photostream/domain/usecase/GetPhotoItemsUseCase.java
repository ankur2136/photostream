package com.ankur.photostream.domain.usecase;

import com.ankur.photostream.common.QueryParams;
import com.ankur.photostream.data.repository.ContentRepository;
import com.ankur.photostream.domain.dto.PhotoItem;
import com.ankur.photostream.domain.interactor.GetItemsUseCase;
import com.ankur.photostream.executor.PostExecutionThread;
import com.ankur.photostream.executor.ThreadExecutor;
import com.ankur.photostream.utils.LogUtils;

import java.util.List;

public class GetPhotoItemsUseCase extends BaseUseCase implements GetItemsUseCase<List<PhotoItem>> {

    private static final String     LOG_TAG = "GET_PHOTO_ITEMS_USE_CASE";

    private final ContentRepository mContentRepository;

    private Integer mId;

    public GetPhotoItemsUseCase(ContentRepository contentRepository, ThreadExecutor executor) {
        mContentRepository = contentRepository;
        mThreadExecutor = executor;
    }

    @Override
    public void getItem(QueryParams queryParams, PostExecutionThread postExecutionThread, Callback callback,
            boolean async, boolean applyUserState) {
//        mId = queryParams.getId();
        mCallback = callback;
        mPostExecutionThread = postExecutionThread;
        mAsync = async;
        mApplyUserState = applyUserState;
        getData();
    }

    @Override
    public void run() {
        try {
            List<PhotoItem> result = mContentRepository.getPhotos();
            notifyOnSuccess(result);
        } catch (Exception e) {
            LogUtils.errorLog(LOG_TAG, "Exception on background thread... ", e);
            notifyOnError(e);
        }
    }

    private void getData() {
        if (mAsync) {
            if (!isTaskRunning()) {
                mFuture = mThreadExecutor.execute(this);
            }
        } else {
            run();
        }
    }
}
