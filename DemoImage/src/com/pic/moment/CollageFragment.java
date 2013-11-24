package com.pic.moment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.pic.moment.PopupProvider.frame;
import com.pic.moment.multipleselection.MultiPhotoSelectActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class CollageFragment extends BaseFragment{
private View homeFragmentView;
private PhotoSortrView photoSorter;
private Button collageBack,collageDelete;
private final int RESULT_LOAD_IMAGE = 001;
private final int CAPTURE_IMAGE = 002;
private FrameLayout frameLayout;
Dialog dialog;
Uri imageUri= null;
boolean isPresent=false;
private String temPath= Environment.getExternalStorageDirectory()+"/PicMomentsTemp";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.collagefragment, container, false);
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
photoSorter = new PhotoSortrView(picmomentActivity);
/*int []rectf=new int[2];
collageFrame.getLocationOnScreen(rectf);

photoSorter.setRectf(rectf);*/
photoSorter.setDelete(collageDelete);

if (picmomentActivity.mImages.size()!=0) {
photoSorter.mImages=picmomentActivity.mImages;

collageDelete.setVisibility(View.VISIBLE);
}else {

collageDelete.setVisibility(View.GONE);

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
           
            Drawable []drawable=new Drawable[urls.length];
            for (int i = 0; i < drawable.length; i++) {
				Drawable drawable2=new BitmapDrawable(getResources(),addWhiteBorder(BitmapFactory.decodeFile(urls[i]), 10));
				drawable[i]=drawable2; 
            }
              
             return drawable;
         }

		protected void onPostExecute(Drawable[] drawable) {
			Dialog.dismiss();
			photoSorter.loadImages(picmomentActivity, drawable);
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
 				View	navigationViewContainer =PopupProvider.getFrame(picmomentActivity, new frame() {
 					
 					@Override
 					public void frameClicked(int index) {
 						Toast.makeText(picmomentActivity, ""+index, 1).show();
 					}
 				},onClickListener);
 				CustomMenu.show(picmomentActivity,navigationViewContainer);
 				
 			}
 		});
 		Button collageAdd = (Button)view.findViewById(R.id.collageAdd);
 		collageAdd.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View arg0) {
 				
 				addImage();
 				
 				}
 		});	
 		Button collageShButton = (Button)view.findViewById(R.id.collageShare);
 		collageShButton.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View arg0) {
 				photoSorter.saveclicked=true;
 				photoSorter.setDrawingCacheEnabled(true);
				Bitmap b = Bitmap.createBitmap(photoSorter.getWidth(),
						photoSorter.getHeight(), Bitmap.Config.ARGB_8888);
				Fragment shareFragment=new ShareFragment();
				Bundle bundle=new Bundle();
				bundle.putParcelable("image", getBitmapFromView(photoSorter));
				shareFragment.setArguments(bundle);
				picmomentActivity.pushFragments(shareFragment, true, true);
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
		
		
		
		private void addImage() {
			// custom dialog
			final Dialog dialog = new Dialog(picmomentActivity,R.style.custom_dialog_theme);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCanceledOnTouchOutside(true);
			Window window = dialog.getWindow();
			WindowManager.LayoutParams wlp = window.getAttributes();
			wlp.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
			wlp.height=LayoutParams.WRAP_CONTENT;
			wlp.width=LayoutParams.WRAP_CONTENT;
			//wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
			window.setAttributes(wlp);
			//window.clearFlags(LayoutParams.FLAG_DIM_BEHIND);
			dialog.setCancelable(true);
			dialog.setContentView(R.layout.dialog);
		    Button dialogUseCamera = (Button)dialog.findViewById(R.id.dialogUseCamera);
		    dialogUseCamera.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					takePicture();
					dialog.dismiss();
					CustomMenu.hide();
					
				}
			});
		    Button dialogAddPhoto = (Button)dialog.findViewById(R.id.dialogAddPhoto);
		    dialogAddPhoto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
						/*	Intent i = new Intent(
									Intent.ACTION_PICK,
									android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);*/
					Intent i = new Intent(picmomentActivity,MultiPhotoSelectActivity.class);
							startActivityForResult(i, RESULT_LOAD_IMAGE);
							dialog.dismiss();
							CustomMenu.hide();
				}
			});
		    Button dialogAddText = (Button)dialog.findViewById(R.id.dialogAddText);
		    dialogAddText.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
				}
			});
		    Button dialogAddSticker = (Button)dialog.findViewById(R.id.dialogAddSticker);
		    dialogAddSticker.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					CustomMenu.hide();
					View	navigationViewContainer = PopupProvider.getEmocione(picmomentActivity, new frame() {
						
						@Override
						public void frameClicked(int index) {
							if (index==9) {
								CustomMenu.hide();
								showBottomBar();
							
							}else {
								Drawable drawable=getResources().getDrawable(PopupProvider.emocionsbig[index]);
								  photoSorter.loadImages(picmomentActivity,new Drawable[] {drawable});
								
							}
								
								
							
							
						}
					});
					dialog.dismiss();
					CustomMenu.show(picmomentActivity,navigationViewContainer);
				}
			});
			dialog.show();
		}
		private OnClickListener onClickListener= new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			CustomMenu.hide();
			showBottomBar();
				
			}
		};
		public Bitmap getBitmapFromView(View view) {
	        //Define a bitmap with the same size as the view
			view.setDrawingCacheEnabled(true);
	        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
	        //Bind a canvas to it
	        Canvas canvas = new Canvas(returnedBitmap);
	        //Get the view's background
	        Drawable bgDrawable =view.getBackground();
	        if (bgDrawable!=null) 
	            //has background drawable, then draw it on the canvas
	            bgDrawable.draw(canvas);
	        else 
	            //does not have background drawable, then draw white background on the canvas
	            canvas.drawColor(Color.TRANSPARENT);
	        // draw the view on the canvas
	        view.draw(canvas);
	        //return the bitmap
	        return returnedBitmap;
	    }
}
