package com.itheima.zhihutest;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

/**
 * Created by sszz on 2017/2/7.
 */

public class ScaleBehavior extends FloatingActionButton.Behavior {
	public ScaleBehavior(Context context, AttributeSet attrs) {
		super();
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
	}

	private boolean isAnimate = false;

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
		if (((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed > 0)) && !isAnimate) {
			//告诉BottomSheetBehavior隐藏
			if (onStateChangedListener != null) {
				onStateChangedListener.onChanged(false);
			}
			AnimatorUtil.scaleHide(child, listener);
		} else if (((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed < 0)) && !isAnimate) {
			//告诉BottomSheetBehavior显示
			if (onStateChangedListener != null) {
				onStateChangedListener.onChanged(true);
			}
			AnimatorUtil.scaleShow(child, listener);
		}
	}
	public static <V extends View> ScaleBehavior from(V view) {
		ViewGroup.LayoutParams params = view.getLayoutParams();
		if (!(params instanceof CoordinatorLayout.LayoutParams)) {
			throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
		}
		CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
				.getBehavior();
		if (!(behavior instanceof ScaleBehavior)) {
			throw new IllegalArgumentException(
					"The view is not associated with ScaleBehavior");
		}
		return (ScaleBehavior) behavior;
	}
	private OnStateChangedListener onStateChangedListener;

	public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
		this.onStateChangedListener = onStateChangedListener;
	}

	public interface OnStateChangedListener {
		void onChanged(boolean isShow);
	}

	private ViewPropertyAnimatorListener listener = new ViewPropertyAnimatorListener() {
		@Override
		public void onAnimationStart(View view) {
			isAnimate = true;
		}

		@Override
		public void onAnimationEnd(View view) {
			isAnimate = false;
		}

		@Override
		public void onAnimationCancel(View view) {
			isAnimate = false;
		}
	};
}
