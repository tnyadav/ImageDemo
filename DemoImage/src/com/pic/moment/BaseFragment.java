package com.pic.moment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
	public PicmomentActivity picmomentActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		picmomentActivity = (PicmomentActivity) this.getActivity();

	}
public boolean onBackPressed(){
		
		return false;
	}
}