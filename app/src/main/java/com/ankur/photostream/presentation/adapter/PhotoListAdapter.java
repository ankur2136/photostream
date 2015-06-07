package com.ankur.photostream.presentation.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;
import com.ankur.photostream.R;
import com.ankur.photostream.domain.dto.PhotoItem;
import com.ankur.photostream.presentation.view.component.NetworkImageViewPlus;
import com.ankur.photostream.utils.VolleyLib;

public class PhotoListAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;

    private List<PhotoItem>      mPhotos;

    private OnItemClickListener  mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View anchor, PhotoItem photoItem);
    }

    public PhotoListAdapter(Context context, Collection<PhotoItem> collection) {
        validateCollection(collection);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPhotos = new ArrayList<>(collection);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void validateCollection(Collection<PhotoItem> photoItems) {
        if (photoItems == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setCollection(Collection<PhotoItem> photoItems) {
        validateUsersCollection(photoItems);
        mPhotos = new ArrayList<>(photoItems);
        notifyDataSetChanged();
    }

    private void validateUsersCollection(Collection<PhotoItem> photoItems) {
        if (photoItems == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public int getCount() {
        if (mPhotos == null)
            return 0;
        return mPhotos.size();
    }

    @Override
    public Object getItem(int position) {
        if (mPhotos == null)
            return null;
        return mPhotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (mPhotos == null)
            return 0;
        return Long.parseLong(mPhotos.get(position).mId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoItem result = (PhotoItem) getItem(position);

        ViewHolder resultViewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.photo_row_item, parent, false);
        }

        if (convertView.getTag() == null) {
            resultViewHolder = new ViewHolder();
            resultViewHolder.instantiate(convertView);
            convertView.setTag(resultViewHolder);
        } else {
            resultViewHolder = (ViewHolder) convertView.getTag();
        }

        final PhotoItem photoItem = mPhotos.get(position);
        convertView.findViewById(R.id.ll_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(v, photoItem);
            }
        });

        resultViewHolder.bindViews(result);
        return convertView;
    }

    public static class ViewHolder {

        NetworkImageView mPhoto;

        public void instantiate(View view) {
            mPhoto = (NetworkImageView) view.findViewById(R.id.iv_image);
        }

        public void bindViews(PhotoItem item) {
            mPhoto.setImageUrl(item.mImages.get(0).source, VolleyLib.getImageLoader());
        }
    }

}
