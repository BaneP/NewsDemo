package com.zero.newsdemo;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zero.newsdemo.beans.News;

public class NewsAdapter extends BaseAdapter {
	private ArrayList<News> mNewsList;

	public NewsAdapter(ArrayList<News> mNewsList) {
		this.mNewsList = mNewsList;
	}

	@Override
	public int getCount() {
		return mNewsList == null ? 0 : mNewsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.list_news_item, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Set data to views
		News news = mNewsList.get(position);
		if (news.isUseLocalImage()) {
			viewHolder.image.setImageResource(news.getImageResourceId());
		} else {
			Picasso.with(parent.getContext()).load(news.getImageUrl())
					.into(viewHolder.image);
		}
		return convertView;
	}

	private class ViewHolder {
		ImageView image;

		public ViewHolder(View convertView) {
			image = (ImageView) convertView.findViewById(R.id.news_image);
		}
	}

}
