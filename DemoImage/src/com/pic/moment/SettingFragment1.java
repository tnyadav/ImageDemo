package com.pic.moment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SettingFragment1 extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.settingfragment1, container, false);
		 RelativeLayout  settingInfo = (RelativeLayout) homeFragmentView.findViewById(R.id.settingInfo);
		 settingInfo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					
				}
			});
		 Button  settingDone = (Button) homeFragmentView.findViewById(R.id.settingDone);
		 settingDone.setOnClickListener(new OnClickListener() {
				
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
