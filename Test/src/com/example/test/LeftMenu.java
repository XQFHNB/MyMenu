package com.example.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class LeftMenu extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.left, container, false);
		/**
		 * 注意这里也是在屏幕上的点击可能会与我们之前在判断是滑动还是点击的事件有冲突，冲突的情况下就是无论我们如何点击按钮都是不会触发的，
		 * 因此我们需要在view中对点击事件进行重新的分发
		 */
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("hahh");
			}
		});
		return v;
	}

}
