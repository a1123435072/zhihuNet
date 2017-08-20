package com.itheima.zhihutest;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private BottomSheetBehavior<View> bottomSheetBehavior;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolBar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		List<String> datas = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			datas.add("条目" + i);
		}
		recyclerView.setAdapter(new MyAdapter(datas));

		//找到BottomSheetBehavior
		//当FAB触发缩放的时候,让BottomSheetBehavior触发显示或隐藏
		bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet_layout));
		ScaleBehavior scaleBehavior = ScaleBehavior.from(findViewById(R.id.fab));
		scaleBehavior.setOnStateChangedListener(listener);
	}

	private ScaleBehavior.OnStateChangedListener listener = new ScaleBehavior.OnStateChangedListener() {
		@Override
		public void onChanged(boolean isShow) {
			bottomSheetBehavior.setState(isShow ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
		}
	};
}
