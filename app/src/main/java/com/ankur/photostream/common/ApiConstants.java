package com.ankur.photostream.common;

import com.facebook.AccessToken;

public class ApiConstants {

    public interface Photo {
        String ID     = "id";
        String NAME   = "name";
        String SOURCE = "source";
        String FROM   = "from";
        String HEIGHT = "height";
        String WIDTH  = "width";
        String IMAGES = "images";
    }

    public static String getPhotoUrl() {
        return getPhotoUrl(5);
    }

    private static String getPhotoUrl(int pageSize) {
        AccessToken.getCurrentAccessToken().getToken();
        return "https://graph.facebook.com/v2.3/me/photos?fields=id,from,images&limit=" + pageSize + "&access_token="
                + AccessToken.getCurrentAccessToken().getToken();
    }

    public static String getAlbumUrl() {
        return getAlbumUrl(5);
    }

    private static String getAlbumUrl(int pageSize) {
        AccessToken.getCurrentAccessToken().getToken();
        return "https://graph.facebook.com/v2.3/me/albums?fields=id,from,images&limit=" + pageSize + "&access_token="
                + AccessToken.getCurrentAccessToken().getToken();
    }

}
