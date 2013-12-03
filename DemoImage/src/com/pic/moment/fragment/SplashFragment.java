package com.pic.moment.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pic.moment.R;

public class SplashFragment extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.splashfragment, container, false);
		
      
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
				 HomeFragment homeFragment = new HomeFragment();
			      picmomentActivity.pushFragments(homeFragment, true, true);
			}
		}, 0);
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
