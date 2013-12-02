package com.pic.moment.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
	
//makeup	
	
	private static int[] makeup={ R.drawable.makeup_1_tn,
		R.drawable.makeup_2_tn, R.drawable.makeup_3_tn,
		R.drawable.makeup_4_tn, R.drawable.makeup_5_tn,
		R.drawable.makeup_6_tn, R.drawable.makeup_7_tn,
		R.drawable.makeup_8_tn, R.drawable.makeup_9_tn,
		R.drawable.makeup_10_tn, R.drawable.makeup_11_tn,
		R.drawable.makeup_12_tn, R.drawable.makeup_13_tn,
		R.drawable.makeup_14_tn, R.drawable.makeup_15_tn,
		R.drawable.makeup_16_tn, R.drawable.makeup_17_tn,
		R.drawable.makeup_18_tn, R.drawable.makeup_19_tn,
		R.drawable.makeup_20_tn, R.drawable.makeup_21_tn,
		R.drawable.makeup_22_tn, R.drawable.makeup_23_tn,
		R.drawable.makeup_24_tn, R.drawable.makeup_25_tn,
		R.drawable.makeup_26_tn, R.drawable.makeup_27_tn,
		R.drawable.makeup_28_tn, R.drawable.makeup_29_tn,
		R.drawable.makeup_30_tn, R.drawable.makeup_31_tn,
		
	};
	
	public static int[] makeupBig={ R.drawable.makeup_big1_tn,
		R.drawable.makeup_big2_tn, R.drawable.makeup_big3_tn,
		R.drawable.makeup_big4_tn, R.drawable.makeup_big5_tn,
		R.drawable.makeup_big6_tn, R.drawable.makeup_big7_tn,
		R.drawable.makeup_big8_tn, R.drawable.makeup_big9_tn,
		R.drawable.makeup_big10_tn, R.drawable.makeup_big11_tn,
		R.drawable.makeup_big12_tn, R.drawable.makeup_big13_tn,
		R.drawable.makeup_big14_tn, R.drawable.makeup_big15_tn,
		R.drawable.makeup_big16_tn, R.drawable.makeup_big17_tn,
		R.drawable.makeup_big18_tn, R.drawable.makeup_big19_tn,
		R.drawable.makeup_big20_tn, R.drawable.makeup_big21_tn,
		R.drawable.makeup_big22_tn, R.drawable.makeup_big23_tn,
		R.drawable.makeup_big24_tn, R.drawable.makeup_big25_tn,
		R.drawable.makeup_big26_tn, R.drawable.makeup_big27_tn,
		R.drawable.makeup_big28_tn, R.drawable.makeup_big29_tn,
		R.drawable.makeup_big30_tn, R.drawable.makeup_big31_tn,
		
	};
//text
	
	private static int[] text={ R.drawable.tn_textbox1,
		R.drawable.tn_textbox2, R.drawable.tn_textbox3,
		R.drawable.tn_textbox4, R.drawable.tn_textbox5,
		R.drawable.tn_textbox6, R.drawable.tn_textbox7,
		R.drawable.tn_textbox8, R.drawable.tn_textbox9,
		R.drawable.tn_textbox10, R.drawable.tn_textbox11,
		R.drawable.tn_textbox12, R.drawable.tn_textbox13,
		 R.drawable.tn_textbox14};
	
	public static int[] textBig={ R.drawable.tn_textbox_big1,
		R.drawable.tn_textbox_big2, R.drawable.tn_textbox_big3,
		R.drawable.tn_textbox_big4, R.drawable.tn_textbox_big5,
		R.drawable.tn_textbox_big6, R.drawable.tn_textbox_big7,
		R.drawable.tn_textbox_big8, R.drawable.tn_textbox_big9,
		R.drawable.tn_textbox_big10, R.drawable.tn_textbox_big11,
		R.drawable.tn_textbox_big12, R.drawable.tn_textbox_big13,
		 R.drawable.tn_textbox_big14};
	
//emoticon	
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
	
	
	//leight 
	private static int[] lightItem = { R.drawable.lightitem1_tn,
		R.drawable.lightitem2_tn, R.drawable.lightitem3_tn,
		R.drawable.lightitem4_tn, R.drawable.lightitem5_tn,
		R.drawable.lightitem6_tn, R.drawable.lightitem7_tn,
		R.drawable.lightitem8_tn, R.drawable.lightitem9_tn,
		R.drawable.lightitem10_tn, R.drawable.lightitem11_tn,
		R.drawable.lightitem12_tn, R.drawable.lightitem13_tn,
		R.drawable.lightitem14_tn, R.drawable.lightitem15_tn,
		R.drawable.lightitem16_tn, R.drawable.lightitem17_tn,
		R.drawable.lightitem18_tn, R.drawable.lightitem19_tn,
		R.drawable.lightitem20_tn, R.drawable.lightitem21_tn,
		R.drawable.lightitem22_tn, R.drawable.lightitem23_tn,
		R.drawable.lightitem24_tn, R.drawable.lightitem25_tn,
		R.drawable.lightitem26_tn, R.drawable.lightitem27_tn,
		R.drawable.lightitem28_tn, R.drawable.lightitem29_tn,
		R.drawable.lightitem30_tn, R.drawable.lightitem31_tn,
		R.drawable.lightitem32_tn, R.drawable.lightitem33_tn,
		R.drawable.lightitem34_tn, R.drawable.lightitem35_tn,
		R.drawable.lightitem35_tn, R.drawable.lightitem37_tn,
		R.drawable.lightitem38_tn, R.drawable.lightitem39_tn,};
	
	public static int[] lightItemBig = { R.drawable.lightitem1big_tn,
		R.drawable.lightitem2big_tn, R.drawable.lightitem3big_tn,
		R.drawable.lightitem4big_tn, R.drawable.lightitem5big_tn,
		R.drawable.lightitem6big_tn, R.drawable.lightitem7big_tn,
		R.drawable.lightitem8big_tn, R.drawable.lightitem9big_tn,
		R.drawable.lightitem10big_tn, R.drawable.lightitem11big_tn,
		R.drawable.lightitem12big_tn, R.drawable.lightitem13big_tn,
		R.drawable.lightitem14big_tn, R.drawable.lightitem15big_tn,
		R.drawable.lightitem16big_tn, R.drawable.lightitem17big_tn,
		R.drawable.lightitem18big_tn, R.drawable.lightitem19big_tn,
		R.drawable.lightitem20big_tn, R.drawable.lightitem21big_tn,
		R.drawable.lightitem22big_tn, R.drawable.lightitem23big_tn,
		R.drawable.lightitem24big_tn, R.drawable.lightitem25big_tn,
		R.drawable.lightitem26big_tn, R.drawable.lightitem27big_tn,
		R.drawable.lightitem28big_tn, R.drawable.lightitem29big_tn,
		R.drawable.lightitem30big_tn, R.drawable.lightitem31big_tn,
		R.drawable.lightitem32big_tn, R.drawable.lightitem33big_tn,
		R.drawable.lightitem34big_tn, R.drawable.lightitem35big_tn,
		R.drawable.lightitem35big_tn, R.drawable.lightitem37big_tn,
		R.drawable.lightitem38big_tn, R.drawable.lightitem39big_tn,};
	
	private static int[] bland = { R.drawable.thn_citynight,
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
	
	/*view= layoutInflater.inflate(R.layout.frame_popup, null);
	LinearLayout emoticonChildContainer = (LinearLayout) view
			.findViewById(R.id.popupFrameFrams);*/
	
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
			//Toast.makeText(picmomentActivity, makeup.length+"", 1).show();
			emoticonChildContainer.removeAllViews();
			for (int i = 0; i <makeup.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(makeup[i]);
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
			emoticonChildContainer.removeAllViews();
			for (int i = 0; i < text.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(text[i]);
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
			emoticonChildContainer.removeAllViews();
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
			emoticonChildContainer.removeAllViews();
			for (int i = 0; i < lightItem.length; i++) {
				final int tempI = i;
				ImageView imageView = new ImageView(picmomentActivity);

				imageView.setImageResource(lightItem[i]);
				final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				lp.setMargins(25, 0, 25, 0);
				imageView.setLayoutParams(lp);
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