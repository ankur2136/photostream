package com.ankur.photostream.data.datasource;

import com.ankur.photostream.domain.dto.PhotoItem;
import com.ankur.photostream.presentation.activity.MyApplication;
import com.ankur.photostream.utils.RequestUtils;

import java.util.List;

public class CloudStore implements ItemDataSource<PhotoItem> {

    @Override
    public List<PhotoItem> getPhotos() {
        List<PhotoItem> results;
        results = RequestUtils.getPhotos(MyApplication.getMyApplicationContext());
        return results;
    }

    @Override
    public List<PhotoItem> getAlbums() {
        List<PhotoItem> results;
        results = RequestUtils.getAlbums(MyApplication.getMyApplicationContext());
        return results;
    }

    @Override
    public List<PhotoItem> getVideos() {
        List<PhotoItem> results;
        results = RequestUtils.getVideos(MyApplication.getMyApplicationContext());
        return results;
    }
}
