package com.pic.moment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment1 extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.homefragment1, container, false);
		ImageView  StartEditButton = (ImageView) homeFragmentView.findViewById(R.id.startEditButton);
        StartEditButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				 EditFragment colageFragment = new EditFragment();
			      picmomentActivity.pushFragments(colageFragment, true, true);
			}
		});
        ImageView  CreateCollageButton = (ImageView) homeFragmentView.findViewById(R.id.createCollageButton);
        CreateCollageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				  CollageFragment colageFragment = new CollageFragment();
			      picmomentActivity.pushFragments(colageFragment, true, true);
			}
		});
        Button  NewOneButton = (Button) homeFragmentView.findViewById(R.id.newOneButton);
        NewOneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				HomeFragment2 homeFragment = new HomeFragment2();
			      picmomentActivity.pushFragments(homeFragment, true, true);
			}
		});
        Button  SettingButton = (Button) homeFragmentView.findViewById(R.id.settingButton);
        SettingButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				SettingFragment1 settingFragment1 = new SettingFragment1();
			      picmomentActivity.pushFragments(settingFragment1, true, true);
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
