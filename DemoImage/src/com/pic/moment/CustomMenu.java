package com.pic.moment;


import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;





/**
 * Menu
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class CustomMenu {

	/**
	 * Opened menu
	 */
	private static volatile CustomMenu mMenu = null;
   
	private Activity mContext = null;
	private static volatile PopupWindow mPopupWindow = null;
	private static boolean mIsShowing = false;
	


	public static boolean isShowing() {
		return mIsShowing;
	}

	/**
	 * Constructor
	 * 
	 * @param activity
	 *            Activity where the menu will be shown
	 * @param OnMenuItemSelectedListener
	 *            listener Listener
	 * @param LayoutInflater
	 *            items Items list
	 * @return void
	 */
	public CustomMenu(Activity activity,View v) {
	mContext = activity;
		
	}


	public void show(View v) {

		// The menu is shown
		mIsShowing = true;
		

		if (mPopupWindow != null)
			return; // The menu is opened

		// Display settings
	//	Display display = ((WindowManager) mContext
	//			.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	// Create popup window to show
		mPopupWindow = new PopupWindow(v, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, false);
		mPopupWindow.setAnimationStyle(R.style.Animations_MenuAnimation);
		
		mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);

		
		
	}

	/**
	 * Hide your menu.
	 * 
	 * @return void
	 */
	public static void hide() {
		mIsShowing = false;
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
			mPopupWindow = null;
			mMenu = null;
		}
		return;
	}

	public static synchronized void show(Activity context,View v) {

		if (mMenu != null && CustomMenu.isShowing()) {

			hide();
			return;

		}

		CustomMenu menu = new CustomMenu(context,v);
        mMenu = menu;
		menu.show(v);
		

	}


}
