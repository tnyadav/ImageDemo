package com.pic.moment.activity;

import java.util.ArrayList;
import java.util.Stack;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.pic.moment.CustomMenu;
import com.pic.moment.ImgCollage;
import com.pic.moment.R;
import com.pic.moment.fragment.BaseFragment;
import com.pic.moment.fragment.SplashFragment;

public class PicmomentActivity extends FragmentActivity {

	public Stack<Fragment> stack;
	public LayoutInflater layoutInflater;
	public ArrayList<ImgCollage> mImages = new ArrayList<ImgCollage>();
	 String groupId= "com.getchute.android.sdk.v2";
	 String artifactId="chute-sdk-v2-android";
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		  setContentView(R.layout.activity_main);
		  stack= new Stack<Fragment>();
		  SplashFragment homeFragment = new SplashFragment();
	      pushFragments(homeFragment, false, false);
	      layoutInflater=getLayoutInflater();
	     
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public void pushFragments(Fragment fragment, boolean shouldAnimate,
			boolean shouldAdd) {
		CustomMenu.hide();
		if (shouldAdd)
			stack.push(fragment);
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		if (shouldAnimate)
			ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

		ft.replace(R.id.mainContainer, fragment);
		ft.commit();
	}

	public void popFragments() {
		CustomMenu.hide();
		Fragment fragment = stack.elementAt(stack.size() - 2);
		stack.pop();
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();

		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
		ft.replace(R.id.mainContainer, fragment);
		ft.commit();

	}

	@Override
	public void onBackPressed() {

		//popFragments();
		
		/* Builder dlg = new AlertDialog.Builder(this);
		 dlg.setCancelable(false); dlg.setTitle("Message");
		 dlg.setMessage("Do you want to exit Soccer");
		 dlg.setPositiveButton("No", new DialogInterface.OnClickListener(){
		 public void onClick(DialogInterface dialog, int which) {
		 
		 } }); dlg.setNegativeButton("Yes", new
		 DialogInterface.OnClickListener(){ public void
		 onClick(DialogInterface dialog,int which) { //finish();
		 System.exit(0); } }); dlg.show();*/
		 

    	
    	//Toast.makeText(MainActivity.this, ""+stack.size(), 1).show();
    	if (((BaseFragment) stack.lastElement()).onBackPressed() == false) {
    		 
	 if(stack.size() == 1){
    	
		 Builder dlg = new AlertDialog.Builder(this);
			dlg.setCancelable(false);
			dlg.setTitle("Message");
			dlg.setMessage("Do you want to exit PicMoments");
			dlg.setPositiveButton("No", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog,	int which) {
					
				}
			});
			dlg.setNegativeButton("Yes", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog,int which) {
					//finish();
					System.exit(0);
				}
			});
			dlg.show();
    		
		 
       		}else{
       			popFragments();
       		}
    
		}
    
	}

}
