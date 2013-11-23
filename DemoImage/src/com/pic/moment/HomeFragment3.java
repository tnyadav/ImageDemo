package com.pic.moment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment3 extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.homefragment3, container, false);
		 Button  new2Done = (Button) homeFragmentView.findViewById(R.id.new2Done);
		 new2Done.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					picmomentActivity.popFragments();
					picmomentActivity.popFragments();
				}
			});
		 ImageView  new2Left = (ImageView) homeFragmentView.findViewById(R.id.new2Left);
		 new2Left.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					picmomentActivity.popFragments();
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
