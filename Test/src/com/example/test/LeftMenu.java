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
		 * ע������Ҳ������Ļ�ϵĵ�����ܻ�������֮ǰ���ж��ǻ������ǵ�����¼��г�ͻ����ͻ������¾�������������ε����ť���ǲ��ᴥ���ģ�
		 * ���������Ҫ��view�жԵ���¼��������µķַ�
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