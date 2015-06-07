package com.ankur.photostream.data.repository;

public class RepositoryFactory {

    private static ContentRepository sPhotoRepository;

    public static synchronized ContentRepository getPhotosRepositoryInstance() {
        if (sPhotoRepository == null) {
            sPhotoRepository = new ItemRepository();
        }
        return sPhotoRepository;
    }
}
