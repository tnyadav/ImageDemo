package com.pic.moment.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.pic.moment.ImgCollage;

public class Util {
	
	private static Resources res ;
	private static DisplayMetrics metrics;
	public static Bitmap getBitmapFromView(View view) {

		// Define a bitmap with the same size as the view
		view.setBackgroundColor(Color.TRANSPARENT);
		view.setDrawingCacheEnabled(true);
		view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
		Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
				view.getHeight(), Bitmap.Config.ARGB_8888);
		// Bind a canvas to it
		Canvas canvas = new Canvas(returnedBitmap);
		canvas.drawColor(Color.TRANSPARENT);
		canvas.setDensity(240);
		// draw the view on the canvas
		view.draw(canvas);
		// return the bitmap
		return returnedBitmap;
	}
	
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		
		if (null != activeNetwork && activeNetwork.isConnected()) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
				System.out.println("wifi");
				NetworkInfo activeNetwork1=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (activeNetwork1.getState()== NetworkInfo.State.CONNECTED) {
					Toast.makeText(context, "true", 1).show();
					return true;
					
				}
				
			}
			if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
				System.out.println("mobile");
				return true;
			}
		}
		return false;
	}
	public static List<ImgCollage> filterList(List<ImgCollage> img) {
		
       List<ImgCollage> imList =new ArrayList<ImgCollage>();
	       for (ImgCollage thingy : img) {
	        if (!thingy.isDeleted()) {
	        	imList.add(thingy);
	        }            
	    }
	    return imList;
	}
public static int getScreenWidth(Context context) {
	 res = context.getResources();
	 metrics = res.getDisplayMetrics();
	int displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
			.max(metrics.widthPixels, metrics.heightPixels) : Math.min(
			metrics.widthPixels, metrics.heightPixels);
	return displayWidth;
	
}
public static int getScreenHeight(Context context) {
	 res = context.getResources();
	 metrics = res.getDisplayMetrics();
	
	int displayHeight = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
			.min(metrics.widthPixels, metrics.heightPixels) : Math.max(
			metrics.widthPixels, metrics.heightPixels);
	return displayHeight;
}
}
