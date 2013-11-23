package com.pic.moment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.homefragment, container, false);
		
      
        return homeFragmentView;
		
		
		
	}

	@Override
	public void onPause() {
		
		super.onPause();
	}

	@Override
	public void onResume() {
		
		super.onResume();
new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				 HomeFragment1 homeFragment = new HomeFragment1();
			      picmomentActivity.pushFragments(homeFragment, true, true);
			}
		}, 3000);
	}

	@Override
	public void onStart() {
		
		super.onStart();
	}

	@Override
	public void onStop() {
		
		super.onStop();
	}
	
	

}
