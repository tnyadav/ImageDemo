package com.pic.moment.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pic.moment.R;
import com.pic.moment.R.drawable;
import com.pic.moment.R.id;
import com.pic.moment.R.layout;
import com.pic.moment.activity.PicmomentActivity;
import com.pic.moment.beans.FrameInfo;

public class PopupProvider {
	private static View view;

	private static FrameInfo frameItemes[] = {
			new FrameInfo(1, R.drawable.frame_0_cv),
			new FrameInfo(2, R.drawable.frame_1_cv),
			new FrameInfo(2, R.drawable.frame_2_cv),
			new FrameInfo(2, R.drawable.frame_3_cv),
			new FrameInfo(3, R.drawable.frame_4_cv),
			new FrameInfo(3, R.drawable.frame_5_cv),
			new FrameInfo(3, R.drawable.frame_6_cv),
			new FrameInfo(3, R.drawable.frame_7_cv),
			new FrameInfo(3, R.drawable.frame_8_cv),
			new FrameInfo(3, R.drawable.frame_9_cv),
			new FrameInfo(4, R.drawable.frame_10_cv),
			new FrameInfo(4, R.drawable.frame_11_cv),
			new FrameInfo(5, R.drawable.frame_12_cv),
			new FrameInfo(5, R.drawable.frame_13_cv),
			new FrameInfo(6, R.drawable.frame_14_cv),
			new FrameInfo(9, R.drawable.frame_15_cv) };
	
	private static int[] emoticonsParrentButton = { R.drawable.makeup,
			R.drawable.text, R.drawable.emoticon, R.drawable.color };
	
	
	private static int[] emocions = { R.drawable.emoticon2_1_tn,
			R.drawable.emoticon2_2_tn, R.drawable.emoticon2_3_tn,
			R.drawable.emoticon2_4_tn, R.drawable.emoticon2_5_tn,
			R.drawable.emoticon2_6_tn, R.drawable.emoticon2_7_tn,
			R.drawable.emoticon2_8_tn, R.drawable.emoticon2_9_tn/*,
			R.drawable.emoticon2_10_tn */};
	public static int[] emocionsbig = { R.drawable.emoticon_b_2_1,
			R.drawable.emoticon_b_2_2, R.drawable.emoticon_b_2_3,
			R.drawable.emoticon_b_2_4, R.drawable.emoticon_b_2_5,
			R.drawable.emoticon_b_2_6, R.drawable.emoticon_b_2_7,
			R.drawable.emoticon_b_2_8, R.drawable.emoticon_b_2_9 };
	
	
	
	public static int[] bland = { R.drawable.thn_citynight,
			R.drawable.thn_coldwhite, R.drawable.thn_coolsummer,
			R.drawable.thn_darkstreet, R.drawable.thn_foliage,
			R.drawable.thn_frezzingblue, R.drawable.thn_frezzingblue,
			R.drawable.thn_greenboost, R.drawable.thn_greenfodder,
			R.drawable.thn_greenlandscape, R.drawable.thn_lovegreen,
			R.drawable.thn_magentaleaves, R.drawable.thn_magicalcyan,
			R.drawable.thn_original, R.drawable.thn_romanticpink,
			R.drawable.thn_sexyred, R.drawable.thn_softwhite,
			R.drawable.thn_specialblue, R.drawable.thn_summerforest,
			R.drawable.thn_violetcoffee };

	public static View getFrame(final PicmomentActivity picmomentActivity,
			final frame frame, final OnClickListener onClickListener,
			final int count) {
		LayoutInflater layoutInflater = picmomentActivity.getLayoutInflater();
		view = layoutInflater.inflate(R.layout.frame_popup, null);
		Button button = (Button) view.findViewById(R.id.popupFrameButton);
		button.setOnClickListener(onClickListener);
		LinearLayout popupFrameFrams = (LinearLayout) view
				.findViewById(R.id.popupFrameFrams);
		for (int i = 0; i < frameItemes.length; i++) {
			final int tempI = i;
			FrameInfo frameIteme = frameItemes[tempI];
			if (frameIteme.getCount() >= count) {
				ImageView imageView = new ImageView(picmomentActivity);
				imageView.setImageResource(frameIteme.getId());
				imageView.setPadding(25, 0, 25, 0);
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						frame.frameClicked(tempI);
					}
				});
				popupFrameFrams.addView(imageView);
			}

		}

		return view;
	}

	public static View getEmoticone(PicmomentActivity picmomentActivity,
			final frame frame) {
		LayoutInflater layoutInflater = picmomentActivity.getLayoutInflater();
	    view = layoutInflater.inflate(R.layout.emoticions, null);

		LinearLayout popupFrameFrams = (LinearLayout) view
				.findViewById(R.id.emoticonMainContainer);
		Button emoticonMakeup=(Button)view.findViewById(R.id.emoticonMakeup);
		emoticonMakeup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				frame.frameClicked(0);
				
			}
		});
		Button emoticonText=(Button)view.findViewById(R.id.emoticonText);
		emoticonText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				frame.frameClicked(1);
				
			}
		});
		Button emoticonEmoticon=(Button)view.findViewById(R.id.emoticonEmoticon);
		emoticonEmoticon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				frame.frameClicked(2);
				
			}
		});
		Button emoticonLight=(Button)view.findViewById(R.id.emoticonLight);
		emoticonLight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				frame.frameClicked(3);
				
			}
		});
		Button emoticonClose=(Button)view.findViewById(R.id.emoticonClose);
		emoticonClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				frame.frameClicked(4);
				
			}
		});
		
		return view;
	}
public static View getEmoticonChild(PicmomentActivity picmomentActivity,
		final frameChild frame,final int emoticonType) {
	
	LayoutInflater layoutInflater = picmomentActivity.getLayoutInflater();
	view= layoutInflater.inflate(R.layout.emoticon_child, null);
	LinearLayout emoticonChildContainer = (LinearLayout) view
			.findViewById(R.id.emoticonChildContainer);
	
	Button emoticonParrentButton=(Button)view.findViewById(R.id.emoticonParrentButton);
	emoticonParrentButton.setBackgroundResource(emoticonsParrentButton[emoticonType]);
	emoticonParrentButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			frame.frameChildClicked(emoticonType,-1);
			
		}
	});
		switch (emoticonType) {
		case 0:
			
			for (int i = 0; i < emocions.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(emocions[i]);
				imageView.setPadding(25, 0, 25, 25);
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						frame.frameChildClicked(emoticonType, tempI);
					}
				});
				emoticonChildContainer.addView(imageView);
			}
			break;
		case 1:

			for (int i = 0; i < emocions.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(emocions[i]);
				imageView.setPadding(25, 0, 25, 25);
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						frame.frameChildClicked(emoticonType,tempI);
					}
				});
				emoticonChildContainer.addView(imageView);
			}
			break;
		case 2:

			for (int i = 0; i < emocions.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(emocions[i]);
				imageView.setPadding(25, 0, 25, 25);
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						frame.frameChildClicked(emoticonType,tempI);
					}
				});
				emoticonChildContainer.addView(imageView);
			}
			break;
		case 3:

			for (int i = 0; i < emocions.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(emocions[i]);
				imageView.setPadding(25, 0, 25, 25);
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						frame.frameChildClicked(emoticonType,tempI);
					}
				});
				emoticonChildContainer.addView(imageView);
			}
			break;
		default:
			break;
		}
	
	

    return view;
}




	public static View getBlands(PicmomentActivity picmomentActivity,
			final frame frame) {
		LayoutInflater layoutInflater = picmomentActivity.getLayoutInflater();
		;
		view = layoutInflater.inflate(R.layout.bland, null);

		LinearLayout popupFrameFrams = (LinearLayout) view
				.findViewById(R.id.editBland);
		for (int i = 0; i < bland.length; i++) {
			final int tempI = i;
			final ImageView imageView = new ImageView(picmomentActivity);

			imageView.setImageResource(bland[i]);
			final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(25, 0, 25, 25);
			imageView.setLayoutParams(lp);

			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// lp.setMargins(25,0, 25, 50);
					// imageView.setLayoutParams(lp);

					frame.frameClicked(tempI);
				}
			});
			popupFrameFrams.addView(imageView);
		}

		return view;
	}

	public interface frame {

		public void frameClicked(int index);
	}
	public interface frameChild {

		public void frameChildClicked(int emoticonType,int index);
	}
}