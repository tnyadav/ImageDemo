package com.pic.moment.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.pic.moment.R;

public class HomeFragment extends BaseFragment{
private View homeFragmentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.homefragment, container, false);
		ImageView  StartEditButton = (ImageView) homeFragmentView.findViewById(R.id.startEditButton);
        StartEditButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				 EditPicFragment colageFragment = new EditPicFragment();
			      picmomentActivity.pushFragments(colageFragment, true, true);
			}
		});
        ImageView  CreateCollageButton = (ImageView) homeFragmentView.findViewById(R.id.createCollageButton);
        
        
        
       // CreateCollageButton.setImageDrawable(textAsBitmap("tnyadav", 50, Color.RED));
        CreateCollageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				  CollagePicFragment colageFragment = new CollagePicFragment();
			      picmomentActivity.pushFragments(colageFragment, true, true);
			}
		});
        Button  NewOneButton = (Button) homeFragmentView.findViewById(R.id.newOneButton);
        NewOneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				NewFeatureFragment1 homeFragment = new NewFeatureFragment1();
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
	private Drawable makeMarker(){
		 Paint paint = new Paint();
		 paint.setTextSize(10);
         paint.setTextScaleX(1.f);
         paint.setAlpha(0);
         paint.setAntiAlias(true);
		 paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC)); 
		   
		 
         Bitmap b = Bitmap.createBitmap(200, 200, Bitmap.Config.ALPHA_8);
         Canvas c = new Canvas(b);
         c.drawColor(Color.GREEN);
         c.drawRect(0, 0, 100, 100, paint);

         
         c.drawText("Your text", 30, 40, paint);
         paint.setColor(Color.TRANSPARENT);

         c.drawBitmap(b, 100,100, paint);
         Drawable drawable=new BitmapDrawable(picmomentActivity.getResources(), b);
         return drawable;
	}
	public Drawable textAsBitmap(String text, float textSize, int textColor) {
	    Paint paint = new Paint();
	    paint.setTextSize(textSize);
	    paint.setColor(textColor);
	    paint.setTextAlign(Paint.Align.CENTER);
	    int width = (int) (paint.measureText(text) + 0.5f); // round
	    float baseline = (int) (-paint.ascent() + 0.5f); // ascent() is negative
	    int height = (int) (baseline + paint.descent() + 0.5f);;
	    Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(image);
	    canvas.drawText(text, 0, baseline, paint);
	    Drawable drawable=new BitmapDrawable(picmomentActivity.getResources(), image);
	    return drawable;
	}


}
