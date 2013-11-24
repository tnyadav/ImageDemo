package com.pic.moment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PopupProvider {
private static View view;
	private static int[] framsREsource = { R.drawable.frame_0_cv,
			R.drawable.frame_1_cv, R.drawable.frame_2_cv,
			R.drawable.frame_3_cv, R.drawable.frame_4_cv,
			R.drawable.frame_5_cv, R.drawable.frame_6_cv,
			R.drawable.frame_7_cv, R.drawable.frame_8_cv,
			R.drawable.frame_9_cv,R.drawable.frame_10_cv,
			R.drawable.frame_11_cv, R.drawable.frame_12_cv,
			R.drawable.frame_13_cv,R.drawable.frame_14_cv,
			R.drawable.frame_15_cv};
	private static int[] emocions = { R.drawable.emoticon2_1_tn,
			R.drawable.emoticon2_2_tn, R.drawable.emoticon2_3_tn,
			R.drawable.emoticon2_4_tn, R.drawable.emoticon2_5_tn,
			R.drawable.emoticon2_6_tn, R.drawable.emoticon2_7_tn,
			R.drawable.emoticon2_8_tn,R.drawable.emoticon2_9_tn,R.drawable.emoticon2_10_tn};
	public static int[] emocionsbig = { R.drawable.emoticon_b_2_1,
		R.drawable.emoticon_b_2_2, R.drawable.emoticon_b_2_3,
		R.drawable.emoticon_b_2_4, R.drawable.emoticon_b_2_5,
		R.drawable.emoticon_b_2_6, R.drawable.emoticon_b_2_7,
		R.drawable.emoticon_b_2_8,R.drawable.emoticon_b_2_9};

	public static View getFrame(final PicmomentActivity picmomentActivity,final frame frame,final OnClickListener onClickListener) {
		LayoutInflater layoutInflater=picmomentActivity.getLayoutInflater();;
		view = layoutInflater.inflate(
					R.layout.frame_popup, null);
		Button button=(Button)view.findViewById(R.id.popupFrameButton);
		button.setOnClickListener(onClickListener);
		LinearLayout popupFrameFrams=(LinearLayout)view.findViewById(R.id.popupFrameFrams);
		for (int i = 0; i < framsREsource.length; i++) {
			final int tempI=i; 
			ImageView imageView=new ImageView(picmomentActivity);
			imageView.setImageResource(framsREsource[i]);
			imageView.setPadding(25, 0, 25, 0);
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				frame.frameClicked(tempI);	
				}
			});
			popupFrameFrams.addView(imageView);
		}
		
		
		return view;
	}
	
	
	public static View getEmocione(PicmomentActivity picmomentActivity,final frame frame) {
		LayoutInflater layoutInflater=picmomentActivity.getLayoutInflater();;
		view = layoutInflater.inflate(
					R.layout.frame_emoticion_popup, null);
		
		LinearLayout popupFrameFrams=(LinearLayout)view.findViewById(R.id.popupFrameFrams);
		for (int i = 0; i < emocions.length; i++) {
			final int tempI=i; 
			ImageView imageView=new ImageView(picmomentActivity);
			
				imageView.setImageResource(emocions[i]);
				imageView.setPadding(25, 0, 25, 25);
				imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
					frame.frameClicked(tempI);	
					}
				});
				popupFrameFrams.addView(imageView);
			}
			
			
		
		
		
		return view;
	}
	
public	interface frame{
	
	public void frameClicked(int index);
}
}