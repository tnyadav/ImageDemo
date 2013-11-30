package com.pic.moment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pic.moment.activity.PicmomentActivity;

public abstract class BaseFragment extends Fragment {
	public PicmomentActivity picmomentActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		picmomentActivity = (PicmomentActivity) getActivity();

	}
public boolean onBackPressed(){
		
		return false;
	}
}