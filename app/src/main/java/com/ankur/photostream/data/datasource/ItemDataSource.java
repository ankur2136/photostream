package com.ankur.photostream.data.datasource;

import java.util.List;


public interface ItemDataSource<T> {

    List<T> getPhotos();
}
