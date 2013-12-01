package com.pic.moment.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pic.moment.ImgCollage;

public class Util {
	
	public static final String temPath= Environment.getExternalStorageDirectory()+"/PicMomentsTemp";
	
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
public static void saveImage(Context context,View view,String fileName) {
	Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
			view.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnedBitmap);
		Drawable bgDrawable = view.getBackground();
		if (bgDrawable != null)
			bgDrawable.draw(canvas);
		else
			canvas.drawColor(Color.WHITE);
		view.draw(canvas);

		try {
			File file = new File(temPath);
	            if (!file.exists())
	            {
	            	file.mkdirs();
	            }
	            
	       
	       returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(temPath+"/"+fileName+".jpeg"));
	       Toast.makeText(context, "saved in "+temPath+"/"+fileName+".jpeg", 1).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("t", e.toString());
			Toast.makeText(context, "faild to save", 1).show();
		}

		returnedBitmap.recycle();
}
class SaveFileTask extends AsyncTask<Void, Void, Boolean>{

	@Override
	protected Boolean doInBackground(Void... arg0) {
	
		return null;
	}
}
}
