package com.ankur.photostream.common;

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
        return getPhotoUrl(1, 5);
    }

    public static String getPhotoUrl(int page, int pageSize) {
        return "https://graph.facebook.com/v2.3/me/photos?fields=id,from,images&limit=2&access_token=CAAMYCMaGJZA0BAPMnqTqEQy2GsavxqlNUUci5xoDAqVMWMs5cLQbHPUrirHg88sXHMoudURLkiF5LKHmDkFpNk8mVMWGMKxt8tjkONLuqEcxHvnvu8PV0IMj6oz5qb1SamLRDrVMLcSLh3Bes5GmSZAsW6asdAkiYfig6kS71SfAy9cg5zsZBfuleYuAR7E5YZCLr67ATglbLTaQ57tU";
    }
}
