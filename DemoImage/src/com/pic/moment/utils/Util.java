package com.pic.moment.utils;

import java.util.ArrayList;
import java.util.List;

import com.pic.moment.Img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

public class Util {
	public static Bitmap getBitmapFromView(View view) {

		// Define a bitmap with the same size as the view
		view.setBackgroundColor(Color.TRANSPARENT);
		view.setDrawingCacheEnabled(true);
		view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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
	public List<Img> filterList(List<Img> img) {
		
       List<Img> imList =new ArrayList<Img>();
	       for (Img thingy : img) {
	        if (!thingy.isDeleted()) {
	        	imList.add(thingy);
	        }            
	    }
	    return imList;
	}
}
