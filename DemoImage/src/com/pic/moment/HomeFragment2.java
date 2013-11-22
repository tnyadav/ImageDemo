package com.pic.moment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment2 extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.homefragment2, container, false);
		 Button  new1Done = (Button) homeFragmentView.findViewById(R.id.new1Done);
		 new1Done.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					picmomentActivity.popFragments();
				}
			});
	/*	 ImageView  new1Left = (ImageView) homeFragmentView.findViewById(R.id.new1Left);
		 new1Left.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					picmomentActivity.popFragments();
				}
			});*/
		 ImageView  new1Right = (ImageView) homeFragmentView.findViewById(R.id.new1Right);
		 new1Right.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					HomeFragment3 homeFragment = new HomeFragment3();
				      picmomentActivity.pushFragments(homeFragment, true, true);
				}
			});
        return homeFragmentView;
		
		
		
	}

	@Override
	public void onPause() {
		
		super.onPause();
	}

	@Override
	public void onResume() {
		
		super.onResume();
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
