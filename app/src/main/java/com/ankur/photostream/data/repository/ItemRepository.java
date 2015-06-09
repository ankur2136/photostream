package com.ankur.photostream.data.repository;

import com.ankur.photostream.data.datasource.CloudStore;
import com.ankur.photostream.data.datasource.ItemDataSource;
import com.ankur.photostream.domain.dto.PhotoItem;

import java.util.List;

public class ItemRepository implements ContentRepository<PhotoItem> {

    private static final String LOG_TAG = "ITEM_REPOSITORY";

    ItemDataSource              mCloud  = new CloudStore();

    @Override
    public List<PhotoItem> getPhotos() {
        return mCloud.getPhotos();
    }

    @Override
    public List<PhotoItem> getAlbums() {
        return mCloud.getAlbums();
    }

    @Override
    public List<PhotoItem> getVideos() {
        return mCloud.getVideos();
    }
}
