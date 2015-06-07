package com.ankur.photostream.presentation.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.URLUtil;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ankur.photostream.R;
import com.ankur.photostream.utils.LogUtils;
import com.ankur.photostream.utils.VolleyLib;

/**
 * The Volley's {@link NetworkImageView} does not allow using
 * setImageBitmap(Bitmap) method for assigning an image without url. This is
 * because in its loadImageIfNecessary() method it calls setImageBitmap(null).
 * This version enables setting local Bitmap by using setLocalImageBitmap().
 * 
 */
public class NetworkImageViewPlus extends NetworkImageView {

    private static final String LOG_TAG = "NETWORK_IMAGE_VIEW_PLUS";

    private int                 mImageTypeId;

    private int                 mImageSizeId;

    private Bitmap              mLocalImageBitmap;

    public NetworkImageViewPlus(Context context) {
        this(context, null);
        init(context, null);
    }

    public NetworkImageViewPlus(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public NetworkImageViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

//        mImageTypeId = ImageType.REGULAR.getId();
//        mImageSizeId = ImageSize.GRID_ITEM.getId();

        if (attrs != null) {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.NetworkImageViewPlus);
//            mImageTypeId = styledAttrs.getInt(R.styleable.NetworkImageViewPlus_imageType, ImageType.REGULAR.getId());
//            mImageSizeId = styledAttrs.getInt(R.styleable.NetworkImageViewPlus_imageSize, ImageSize.GRID_ITEM.getId());
            styledAttrs.recycle();
        }
    }

    public void setLocalImageBitmap(Bitmap bitmap) {
        mLocalImageBitmap = bitmap;
        setDefaultImageResId(0);
        setErrorImageResId(0);
        setImageUrl(null, VolleyLib.getImageLoader());
    }

    public void setLocalImageResId(int resId) {
        clearLocalImageBitmap();
        setDefaultImageResId(resId);
        setErrorImageResId(resId);
        setImageUrl(null, VolleyLib.getImageLoader());
    }

    @Override
    public void setImageResource(int resId) {
        try {
            super.setImageResource(resId);
        } catch (OutOfMemoryError e) {
            LogUtils.errorLog(LOG_TAG, "Caught OOM in setImageResource \n" + e);
        }
    }

    @Override
    public void setImageUrl(String url, ImageLoader imageLoader) {
        if (!TextUtils.isEmpty(url)) {
            clearLocalImageBitmap();
        }
        setDefaultImageResId(R.drawable.background_tab);
        setErrorImageResId(R.drawable.background_tab);
        if (URLUtil.isNetworkUrl(url)) {
            super.setImageUrl(url, imageLoader);
        } else {
            super.setImageUrl(url, imageLoader);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        clearLocalImageBitmap();
        super.onDetachedFromWindow();
    }

    public void clearLocalImageBitmap() {
        if (mLocalImageBitmap != null) {
            setImageBitmap(null);
            // mLocalImageBitmap.recycle();
            mLocalImageBitmap = null;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (bm == null && mLocalImageBitmap != null) {
            LogUtils.infoLog(LOG_TAG, "image attr: " + mLocalImageBitmap.getHeight());
            super.setImageBitmap(mLocalImageBitmap);
        } else {
            if (bm != null)
                LogUtils.infoLog(LOG_TAG, "image atrr: " + bm.getHeight());
            super.setImageBitmap(bm);
        }
    }
}
