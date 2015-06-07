package com.ankur.photostream.utils;

import android.content.Context;

import com.ankur.photostream.common.ApiConstants;
import com.ankur.photostream.common.ApiUtils;
import com.ankur.photostream.domain.dto.PhotoItem;
import com.ankur.photostream.entity.PhotosResponse;

import java.util.List;

public class RequestUtils extends ApiUtils {

    private static final String LOG_TAG = "REQUEST_UTILS";

    public static List<PhotoItem> getPhotos(Context context) {
        String url = ApiConstants.getPhotoUrl();
        final ResultWrapper resultWrapper = new ResultWrapper();
        makeSyncGetJsonRequest(new PhotosResponse(), context, url, 10000, new ApiResponseListener<PhotosResponse>() {
            @Override
            public void onResponse(PhotosResponse response) {
                LogUtils.debugLog(LOG_TAG, "Results received");
                resultWrapper.setResult(response.getResult());
            }

            @Override
            public void onError(Exception exception) {
                LogUtils.errorLog(LOG_TAG, "Failed to fetch page. API returned: " + exception);
            }

        });
        return (List<PhotoItem>) resultWrapper.getResult();
    }

    public static List<PhotoItem> getAlbums(Context context) {
        String url = ApiConstants.getAlbumUrl();
        final ResultWrapper resultWrapper = new ResultWrapper();
        makeSyncGetJsonRequest(new PhotosResponse(), context, url, 10000, new ApiResponseListener<PhotosResponse>() {
            @Override
            public void onResponse(PhotosResponse response) {
                LogUtils.debugLog(LOG_TAG, "Results received");
                resultWrapper.setResult(response.getResult());
            }

            @Override
            public void onError(Exception exception) {
                LogUtils.errorLog(LOG_TAG, "Failed to fetch page. API returned: " + exception);
            }

        });
        return (List<PhotoItem>) resultWrapper.getResult();
    }

}
