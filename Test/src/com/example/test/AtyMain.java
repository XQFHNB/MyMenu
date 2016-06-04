package com.example.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AtyMain extends FragmentActivity {
	private MainUI mainui;
	private LeftMenu leftMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainui = new MainUI(this);
		setContentView(mainui);
		leftMenu = new LeftMenu();
		getSupportFragmentManager().beginTransaction().add(MainUI.Left_ID, leftMenu).commit();
	}

}
