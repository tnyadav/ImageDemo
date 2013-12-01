package com.pic.moment.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.pic.moment.CollagePicCustomView;
import com.pic.moment.CustomMenu;
import com.pic.moment.ImgCollage;
import com.pic.moment.R;
import com.pic.moment.activity.MultiPhotoSelectActivity;
import com.pic.moment.activity.PicmomentActivity;
import com.pic.moment.beans.FrameCoordinete;
import com.pic.moment.utils.FrameCoordinateProvider;
import com.pic.moment.utils.PopupProvider;
import com.pic.moment.utils.SaveFileTask;
import com.pic.moment.utils.ScalingUtilities;
import com.pic.moment.utils.PopupProvider.frame;
import com.pic.moment.utils.PopupProvider.frameChild;
import com.pic.moment.utils.ScalingUtilities.ScalingLogic;
import com.pic.moment.utils.Util;

public class CollagePicFragment extends BaseFragment{
private View homeFragmentView;
private CollagePicCustomView photoSorter;
private Button collageBack,collageDelete;
private final int RESULT_LOAD_IMAGE = 001;
private final int CAPTURE_IMAGE = 002;
private FrameLayout frameLayout;
Dialog addDialog;
Uri imageUri= null;
boolean isPresent=false;
protected String fileName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.collagepicfragment, container, false);
		SetContent();
		
        return homeFragmentView;
		
		
		
	}
private void SetContent() {
	
	
collageBack = (Button)homeFragmentView.findViewById(R.id.collageBack);

collageBack.bringToFront();

collageBack.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		LayoutInflater layoutInflater=picmomentActivity.getLayoutInflater();;
		View	navigationViewContainer = layoutInflater.inflate(
					R.layout.dialogback, null);
		
		CustomMenu.show(picmomentActivity,navigationViewContainer);
		
		
	
		final Dialog dialog = new Dialog(picmomentActivity,R.style.custom_dialog_theme_back);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(true);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();
		wlp.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
		wlp.height=LayoutParams.WRAP_CONTENT;
		wlp.width=LayoutParams.WRAP_CONTENT;
		wlp.x=50;
		wlp.y=150;
		//wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		window.setAttributes(wlp);
		//window.clearFlags(LayoutParams.FLAG_DIM_BEHIND);
		dialog.setCancelable(true);
		dialog.setContentView(R.layout.dialogback);
	    Button save = (Button)dialog.findViewById(R.id.save);
	    save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				picmomentActivity.popFragments();
				/*
			
                
				photoSorter.setDrawingCacheEnabled(true);
				Bitmap b = Bitmap.createBitmap(photoSorter.getWidth(),
						photoSorter.getHeight(), Bitmap.Config.ARGB_8888);
				 Canvas canvas = new Canvas(b);
		         Drawable bgDrawable = photoSorter.getBackground();
		         if (bgDrawable != null)
		             bgDrawable.draw(canvas);
		         else
		             canvas.drawColor(Color.WHITE); 
		         photoSorter.draw(canvas);

			//	Toast.makeText(getApplicationContext(), saveToInternalSorage(b), 1).show();
				//photoSorter.unloadImages();
				try {
					File file = new File(temPath);
			            if (!file.exists())
			            {
			            	file.mkdirs();
			            }
			            
			        String time=""+System.currentTimeMillis();    

					b.compress(CompressFormat.JPEG, 100, new FileOutputStream(temPath+"/"+time+".jpeg"));
					Toast.makeText(picmomentActivity, "saved in "+temPath+"/"+time+".jpeg", 1).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("t", e.toString());
					Toast.makeText(picmomentActivity, "faild", 1).show();
				}
				
			dialog.dismiss();
			picmomentActivity.popFragments();
				
			*/}
		});
	    if (!isPresent) {
	    	save.setEnabled(false);
		}
	    Button back = (Button)dialog.findViewById(R.id.back);
	    back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.cancel();
				picmomentActivity.popFragments();
					
			}
		});
	    Button cancel = (Button)dialog.findViewById(R.id.cancel);
	    cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dialog.cancel();
			}
		});
	  
		dialog.show();
	}
});
collageDelete = (Button)homeFragmentView.findViewById(R.id.collageDelete);
collageDelete.setClickable(false);
frameLayout=(FrameLayout)homeFragmentView.findViewById(R.id.mainFrameContainer);
photoSorter = new CollagePicCustomView(picmomentActivity);
photoSorter.setResources(getResources());
photoSorter.setDelete(collageDelete);
photoSorter.setDialog(getDailog());
if (picmomentActivity.mImages.size()!=0) {
photoSorter.mImages=picmomentActivity.mImages;

collageDelete.setVisibility(View.VISIBLE);
}else {

//collageDelete.setVisibility(View.GONE);

}
showBottomBar();
frameLayout.addView(photoSorter);



}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
        	
          // ArrayList<String> selectedItems = data.getParcelableExtra("selectedItems");
           String[] selectedItems = data.getStringArrayExtra("selectedItems");
            new LoadImagesFromSDCard().execute(selectedItems);
            
         }
        if(requestCode==CAPTURE_IMAGE && resultCode==Activity.RESULT_OK )
        {
        	

             String[] filePathColumn = {MediaStore.Images.Media.DATA};
             Cursor cursor = picmomentActivity.getContentResolver().query(imageUri, filePathColumn, null, null, null);
             cursor.moveToFirst();
             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             String filePath = cursor.getString(columnIndex);
        	new LoadImagesFromSDCard().execute(new String[]{filePath});
        }
	}
        private Bitmap addWhiteBorder(Bitmap bmp, int borderSize) {
    	    Bitmap bmpWithBorder = Bitmap.createBitmap(bmp.getWidth() + borderSize * 2, bmp.getHeight() + borderSize * 2, bmp.getConfig());
    	    Canvas canvas = new Canvas(bmpWithBorder);
    	    canvas.drawColor(Color.WHITE);
    	    canvas.drawBitmap(bmp, borderSize, borderSize, null);
    	    return bmpWithBorder;
    	}
       
       
        public void takePicture() {
 		 
        	String fileName = "Camera_Example.jpg";
            
            // Create parameters for Intent with filename
             
            ContentValues values = new ContentValues();
             
            values.put(MediaStore.Images.Media.TITLE, fileName);
             
            values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
             
            // imageUri is the current activity attribute, define and save it for later usage 
             
            imageUri = picmomentActivity.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
             
            /**** EXTERNAL_CONTENT_URI : style URI for the "primary" external storage volume. ****/

             
            // Standard Intent action that can be sent to have the camera
            // application capture an image and return it. 
             
            Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
             
             intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
              
             //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
              
            startActivityForResult( intent, CAPTURE_IMAGE);
 		  }
        

	 public class LoadImagesFromSDCard  extends AsyncTask<String, Void, Drawable[]> {
         
         private ProgressDialog Dialog = new ProgressDialog(picmomentActivity);
         protected void onPreExecute() {
            Dialog.setMessage(" Loading image from Sdcard..");
            Dialog.show();
         }
     protected Drawable[] doInBackground(String...urls) {
    	 Resources res=picmomentActivity.getResources();
    	 DisplayMetrics metrics = res.getDisplayMetrics();
 		// The DisplayMetrics don't seem to always be updated on screen
 		// rotate, so we hard code a portrait
 		// screen orientation for the non-rotated screen here...
 		// this.displayWidth = metrics.widthPixels;
 		// this.displayHeight = metrics.heightPixels;
 		int displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
 				.max(metrics.widthPixels, metrics.heightPixels) : Math.min(
 				metrics.widthPixels, metrics.heightPixels);
 	    int displayHeight = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
 				.min(metrics.widthPixels, metrics.heightPixels) : Math.max(
 				metrics.widthPixels, metrics.heightPixels);
            Drawable []drawable=new Drawable[urls.length];
            for (int i = 0; i < drawable.length; i++) {
            	
            	Bitmap bitmap=BitmapFactory.decodeFile(urls[i]);
            	//bitmap = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.2), (int)(bitmap.getHeight()*0.2), true);
            	// option.inSampleSize = 4;
            	 bitmap = ScalingUtilities.createScaledBitmap(bitmap, displayWidth,
            			displayHeight, ScalingLogic.FIT);
            	bitmap=addWhiteBorder(bitmap, 10);
				Drawable drawable2=new BitmapDrawable(getResources(),bitmap);
				//bitmap.recycle();
				drawable[i]=drawable2; 
            }
              
             return drawable;
         }

		protected void onPostExecute(Drawable[] drawable) {
			Dialog.dismiss();
			photoSorter.loadImages(picmomentActivity, drawable,false);
			isPresent = true;
			showBottomBar();

		}  
	 }
		@Override
		public void onPause() {
			
			super.onPause();
			picmomentActivity.mImages=photoSorter.mImages;
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
		public void showBottomBar() { 
			
			 View view = picmomentActivity.layoutInflater.inflate(
 					R.layout.bottom_bar_frame, null);
 		
 		Button collageFrame = (Button)view.findViewById(R.id.collageFrame);
 		collageFrame.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View arg0) {
 				CustomMenu.hide();
 				 final List<ImgCollage> imgs=Util.filterList(photoSorter.mImages);
 				View	navigationViewContainer =PopupProvider.getFrame(picmomentActivity, new frame() {
 					
 					
 					@Override
 					public void frameClicked(int index) {
 						
 					List<FrameCoordinete> frameCoordinetes=	FrameCoordinateProvider.getFremeCoordinate(picmomentActivity.getResources(),index);
 				   
 					for (int i = 0; i < Math.min(frameCoordinetes.size(),imgs.size()); i++) {
						ImgCollage img = photoSorter.mImages.get(i);
						FrameCoordinete frameCoordinete=frameCoordinetes.get(i);
						img.setMinX(frameCoordinete.getMinX());
						img.setMaxX(frameCoordinete.getMaxX());
						img.setMinY(frameCoordinete.getMinY());
						img.setMaxY(frameCoordinete.getMaxY());
						img.setCollege(true);
						photoSorter.mImages.set(i, img);
						
					}
 					photoSorter.invalidate();
 					}
 				},onClickListener,imgs.size());
 				CustomMenu.show(picmomentActivity,navigationViewContainer);
 				
 			}
 		});
 		Button collageAdd = (Button)view.findViewById(R.id.collageAdd);
 		collageAdd.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View arg0) {
 				
 				showAddDialog().show();
 				
 				}
 		});	
 		Button collageShButton = (Button)view.findViewById(R.id.collageShare);
 		collageShButton.setOnClickListener(new OnClickListener() {
 			
 			@Override
			public void onClick(View arg0) {
 				fileName = "PicMoment" + System.currentTimeMillis();
 				final ProgressDialog Dialog = new ProgressDialog(picmomentActivity);
 				  Dialog.setMessage("Saving File");
 				  Dialog.show();
 			/*	new Handler().post(new Runnable() {
					
					@Override
					public void run() {*/
						Bitmap returnedBitmap = Bitmap.createBitmap(photoSorter.getWidth(),
		 						photoSorter.getHeight(), Bitmap.Config.ARGB_8888);
		 					Canvas canvas = new Canvas(returnedBitmap);
		 					Drawable bgDrawable = photoSorter.getBackground();
		 					if (bgDrawable != null)
		 						bgDrawable.draw(canvas);
		 					else
		 						canvas.drawColor(Color.WHITE);
		 					photoSorter.draw(canvas);
		 			        try {
		 						returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(Util.temPath+"/"+fileName+".jpeg"));
		 						Toast.makeText(picmomentActivity, "saved in "+Util.temPath+"/"+fileName+".jpeg", 1).show();
		 						 ShareFragment shareFragment=new ShareFragment();
		 						 Bundle bundle=new Bundle();
		 							bundle.putString("filename", fileName);
		 							shareFragment.setArguments(bundle);
		 							Dialog.dismiss();
		 							picmomentActivity.pushFragments(shareFragment, true, true);
		 			        
		 			        
		 			        } catch (FileNotFoundException e) {
		 						// TODO Auto-generated catch block
		 						e.printStackTrace();
		 						Dialog.dismiss();
		 					
		 					}
						
			/*		}
				});
 				*/
 				    

			}
 		});
 		
 		
 		if (photoSorter.mImages.size()==0) {
			collageFrame.setVisibility(View.GONE);
			collageShButton.setVisibility(View.GONE);
		}else {
			  collageDelete.setVisibility(View.VISIBLE);
		}
       CustomMenu.show(picmomentActivity, view);
		}
		

		private OnClickListener onClickListener= new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			CustomMenu.hide();
			showBottomBar();
				
			}
		};
	
		/**
		 *dialog for addding image/text customview
		 */
		private Dialog getDailog() {
			
			final Dialog dialog1 = new Dialog(picmomentActivity,R.style.custom_dialog_theme);
			dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog1.setCanceledOnTouchOutside(true);
			dialog1.setCancelable(true);
			dialog1.setContentView(R.layout.dialog);
			Button dialogUseCamera = (Button)dialog1.findViewById(R.id.dialogUseCamera);
			dialogUseCamera.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					takePicture();
					dialog1.dismiss();
					CustomMenu.hide();
					
				}
			});
			Button dialogAddPhoto = (Button)dialog1.findViewById(R.id.dialogAddPhoto);
			dialogAddPhoto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
						/*	Intent i = new Intent(
									Intent.ACTION_PICK,
									android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);*/
					Intent i = new Intent(picmomentActivity,MultiPhotoSelectActivity.class);
							startActivityForResult(i, RESULT_LOAD_IMAGE);
							dialog1.dismiss();
							CustomMenu.hide();
				}
			});
			Button dialogAddText = (Button)dialog1.findViewById(R.id.dialogAddText);
			dialogAddText.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					dialog1.dismiss();
					
					
					final Dialog dialog = new Dialog(picmomentActivity,R.style.custom_dialog_theme_back);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setCanceledOnTouchOutside(true);
					Window window = dialog.getWindow();
					WindowManager.LayoutParams wlp = window.getAttributes();
					wlp.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
					wlp.height=LayoutParams.WRAP_CONTENT;
					wlp.width=LayoutParams.WRAP_CONTENT;
					wlp.x=50;
					wlp.y=150;
					wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
					window.setAttributes(wlp);
					//window.clearFlags(LayoutParams.FLAG_DIM_BEHIND);
					dialog.setCancelable(true);
					dialog.setContentView(R.layout.add_text);
					final EditText et=(EditText)dialog.findViewById(R.id.et);
					et.setTextColor(Color.BLACK);
				    Button save = (Button)dialog.findViewById(R.id.done);
				    save.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						InputMethodManager imm = (InputMethodManager)picmomentActivity.getSystemService(
							      Context.INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
						dialog.dismiss();
						et.clearComposingText();
						et.setFocusable(false);
						et.setBackgroundColor(Color.TRANSPARENT);
						photoSorter.loadImages(picmomentActivity,
								new Drawable[] { new BitmapDrawable(
										picmomentActivity.getResources(),
										Util.getBitmapFromView(et)) },true);
						CustomMenu.hide();
						showBottomBar();
					}
				});
				  
					dialog.show();
					
				}
			});
			Button dialogAddSticker = (Button)dialog1.findViewById(R.id.dialogAddSticker);
			dialogAddSticker.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog1.dismiss();
					addStrikerView();
					
					
				}
			});
			return dialog1;
		}
		
		private Dialog showAddDialog() {
			Dialog dialog=getDailog();
			Window window = dialog.getWindow();
			WindowManager.LayoutParams wlp = window.getAttributes();
	    	wlp.height=LayoutParams.WRAP_CONTENT;
			wlp.width=LayoutParams.WRAP_CONTENT;
			wlp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
			wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
			window.setAttributes(wlp);
			return dialog;
		}

	private void addStrikerView() {

		// **** triker
		CustomMenu.hide();
		View stickerParent = PopupProvider.getEmoticone(picmomentActivity,
				new frame() {

					@Override
					public void frameClicked(int index) {
						CustomMenu.hide();
						if (index==4) {
							showBottomBar();
						}else {
							View stickerChild = PopupProvider.getEmoticonChild(
									picmomentActivity, new frameChild() {

										@Override
										public void frameChildClicked(
												int emoticonType, int index) {

											if (index == -1) {
												addStrikerView();
											} else {
												addStrikerToView(emoticonType,
														index);
											}

										}
									}, index);
							CustomMenu.show(picmomentActivity, stickerChild);
						}
						
						
						
					}
				});
		
		CustomMenu.show(picmomentActivity, stickerParent);

	}
private void addStrikerToView(int emoticonType, int index) {
		
	
		 Drawable drawable;	
		switch (emoticonType) {
		case 0:
			 drawable=getResources().getDrawable(PopupProvider.makeupBig[index]);
			  photoSorter.loadImages(picmomentActivity,new Drawable[] {drawable},false);
			break;
		case 1:
			 drawable=getResources().getDrawable(PopupProvider.textBig[index]);
			  photoSorter.loadImages(picmomentActivity,new Drawable[] {drawable},false);
			break;
		case 2:
		
		  drawable=getResources().getDrawable(PopupProvider.emocionsbig[index]);
								  photoSorter.loadImages(picmomentActivity,new Drawable[] {drawable},false);
		
				break;
		case 3:
		  drawable=getResources().getDrawable(PopupProvider.lightItemBig[index]);
			  photoSorter.loadImages(picmomentActivity,new Drawable[] {drawable},false);
			break;
		default:
			break;
		}
	}
class SaveFile extends  AsyncTask<Void, Void, Boolean> {


private ProgressDialog Dialog;

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		  Dialog = new ProgressDialog(picmomentActivity);
		  Dialog.setMessage("Saving File");
		  Dialog.show();
		 
	}
	@Override
	protected Boolean doInBackground(Void... arg0) {
   	Bitmap returnedBitmap = Bitmap.createBitmap(photoSorter.getWidth(),
			photoSorter.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnedBitmap);
		Drawable bgDrawable = photoSorter.getBackground();
		if (bgDrawable != null)
			bgDrawable.draw(canvas);
		else
			canvas.drawColor(Color.WHITE);
		photoSorter.draw(canvas);
        try {
			returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(Util.temPath+"/"+fileName+".jpeg"));
		return true;
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    
		
		
		
	
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Dialog.dismiss();
		if (result) {
			Toast.makeText(picmomentActivity, "saved in "+Util.temPath+"/"+fileName+".jpeg", 1).show();
			 ShareFragment shareFragment=new ShareFragment();
			 Bundle bundle=new Bundle();
				bundle.putString("filename", fileName);
				shareFragment.setArguments(bundle);
				picmomentActivity.pushFragments(shareFragment, true, true);
		}
		
	}


}
}
