package com.itheima.zhihutest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sszz on 2017/2/7.
 */

public class MyAdapter extends RecyclerView.Adapter {
	private List<String> datas;

	public MyAdapter(List<String> datas) {
		this.datas = datas;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null, false);
		MyViewHolder holder = new MyViewHolder(itemView);
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		MyViewHolder mVH = (MyViewHolder) holder;
		mVH.tv.setText(datas.get(position));
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	private class MyViewHolder extends RecyclerView.ViewHolder {

		private TextView tv;

		public MyViewHolder(View itemView) {
			super(itemView);
			tv = (TextView) itemView.findViewById(android.R.id.text1);
		}
	}
}
