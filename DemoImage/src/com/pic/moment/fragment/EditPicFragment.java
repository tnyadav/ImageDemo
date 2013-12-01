package com.pic.moment.fragment;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.pic.moment.CustomMenu;
import com.pic.moment.EditPicCustomView;
import com.pic.moment.R;
import com.pic.moment.utils.PopupProvider;
import com.pic.moment.utils.PopupProvider.frameChild;
import com.pic.moment.utils.ScalingUtilities;
import com.pic.moment.utils.PopupProvider.frame;
import com.pic.moment.utils.ScalingUtilities.ScalingLogic;
import com.pic.moment.utils.Util;

public class EditPicFragment extends BaseFragment{
private View homeFragmentView;
private Button collageBack,collageDelete,editAdd,collageShare;
private final int RESULT_LOAD_IMAGE = 001;
private final int CAPTURE_IMAGE = 002;
private boolean isImageMode=false;
private ImageView imageView;
Uri imageUri= null;
LinearLayout linearLayout;
FrameLayout mainContainer;
EditPicCustomView editPicCustomView;
private String temPath= Environment.getExternalStorageDirectory()+"/PicMomentsTemp";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.editpicfragment, container, false);
		SetContent();
		
        return homeFragmentView;
		
		
		
	}
private void SetContent() {
linearLayout=(LinearLayout)homeFragmentView.findViewById(R.id.editDataContainer);
linearLayout.setVisibility(View.GONE);
linearLayout.addView(PopupProvider.getBlands(picmomentActivity, new frame() {
	
	@Override
	public void frameClicked(int index) {
		// TODO Auto-generated method stub
		
	}
}));
editAdd = (Button)homeFragmentView.findViewById(R.id.editAdd);
editAdd.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {// custom dialog
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
				
			}
		});
	    Button dialogAddPhoto = (Button)dialog.findViewById(R.id.dialogAddPhoto);
	    dialogAddPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
						Intent i = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(i, RESULT_LOAD_IMAGE);
						dialog.dismiss();
			}
		});
	    Button dialogAddText = (Button)dialog.findViewById(R.id.dialogAddText);
	    dialogAddText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();

				
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
					Rect rect = new Rect();
					rect.right=Util.getScreenWidth(picmomentActivity);
					rect.bottom=Util.getScreenHeight(picmomentActivity);
					//editPicCustomView.getGlobalVisibleRect(rect);
					editPicCustomView.loadImages(picmomentActivity,
							 new BitmapDrawable(
									picmomentActivity.getResources(),
									Util.getBitmapFromView(et)),rect,isImageMode);
					CustomMenu.hide();
				
				}
			});
			  
				dialog.show();
				
			}
		});
	    Button dialogAddSticker = (Button)dialog.findViewById(R.id.dialogAddSticker);
	    dialogAddSticker.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				linearLayout.setVisibility(View.VISIBLE);
				addStrikerView();
			/*	
				View	navigationViewContainer = PopupProvider.getEmocione(picmomentActivity, new frame() {
					
					@Override
					public void frameClicked(int index) {
						if (index==9) {
							
							
						
						}else {
							Rect rect = new Rect();
							rect.right=Util.getScreenWidth(picmomentActivity);
							rect.bottom=Util.getScreenHeight(picmomentActivity);
							Bitmap bitmap=ScalingUtilities.decodeResource(getResources(), PopupProvider.emocionsbig[index],editPicCustomView.getWidth(),
	                      			editPicCustomView.getHeight(), ScalingLogic.FIT);
							bitmap=ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),editPicCustomView.getHeight(), ScalingLogic.FIT);
							//Drawable drawable=getResources().getDrawable(PopupProvider.emocionsbig[index]);
							editPicCustomView.loadImages(picmomentActivity,new BitmapDrawable(getResources(),bitmap) ,rect,false);
							
						}
						
					}
				});	*/
			
			}
		});
		dialog.show();
		
		}
});

mainContainer=(FrameLayout)homeFragmentView.findViewById(R.id.mainFrameContainer);
editPicCustomView = new EditPicCustomView(picmomentActivity);
editPicCustomView.getBackground();
mainContainer.addView(editPicCustomView);


}

	@Override
	public void onPause() {
		
		super.onPause();
	}

	@Override
	public void onResume() {
		
		super.onResume();

//Log.e("frame", mainContainer.getHeight()+" w: "+mainContainer.getWidth());
//Log.e("editPicCustomView", editPicCustomView.getHeight()+" w: "+editPicCustomView.getWidth());
	}

	@Override
	public void onStart() {
		
		super.onStart();
	}

	@Override
	public void onStop() {
		
		super.onStop();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         isImageMode= true;
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
        	linearLayout.setVisibility(View.VISIBLE);
            Uri selectedImage = data.getData();
            new LoadImagesFromSDCard().execute(selectedImage);
            
           
             
         }
        if(requestCode==CAPTURE_IMAGE && resultCode==Activity.RESULT_OK )
        {
        	linearLayout.setVisibility(View.VISIBLE);
        	//Uri selectedImage = data.getData();
        	new LoadImagesFromSDCard().execute(imageUri);
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
        

	 public class LoadImagesFromSDCard  extends AsyncTask<Uri, Void, Drawable> {
         
         private ProgressDialog Dialog = new ProgressDialog(picmomentActivity);
         protected void onPreExecute() {
            
             Dialog.setMessage(" Loading image from Sdcard..");
             Dialog.show();
         }


         // Call after onPreExecute method
         protected Drawable doInBackground(Uri... urls) {
              
        	 String picturePath = null ;
             Drawable drawable=null;     
                  
                 try {
                      
                	 String[] filePathColumn = { MediaStore.Images.Media.DATA };
                	 
                     Cursor cursor = picmomentActivity.getContentResolver().query(urls[0],
                             filePathColumn, null, null, null);
                     cursor.moveToFirst();
          
                      int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                      picturePath = cursor.getString(columnIndex);
                      cursor.close();
                    
                      
                    /* if (bitmap != null) {
                          
                         *//********* Creates a new bitmap, scaled from an existing bitmap. ***********//*

                         newBitmap = Bitmap.createScaledBitmap(bitmap, 170, 170, true);
                          
                         bitmap.recycle();
                          
                         if (newBitmap != null) {
                              
                             mBitmap = newBitmap;
                         }
                     }*/
                   
                        	
                      	Bitmap bitmap=BitmapFactory.decodeFile(picturePath);
                      	//bitmap = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.2), (int)(bitmap.getHeight()*0.2), true);
                      	// option.inSampleSize = 4;
                      	 bitmap = ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),
                      			editPicCustomView.getHeight(), ScalingLogic.FIT);
                      	 Log.e("doin",  editPicCustomView.getWidth()+"   "+editPicCustomView.getHeight());
                      
                      	//bitmap=addWhiteBorder(bitmap, 10);
          			    drawable=new BitmapDrawable(getResources(),bitmap);
          			   
          			            
                 } catch (Exception e) {
                     // Error fetching image, try to recover
                      e.printStackTrace();
                     /********* Cancel execution of this task. **********/
                     cancel(true);
                 }
              
             return drawable;
         }
          
          
		protected void onPostExecute(Drawable drawable) {

			Dialog.dismiss();

			Rect rect = new Rect();
			rect.right= editPicCustomView.getWidth();
			rect.bottom=editPicCustomView.getHeight();
			Resources res = getResources();
			DisplayMetrics metrics = res.getDisplayMetrics();
			int displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
					.max(metrics.widthPixels, metrics.heightPixels) : Math.min(
					metrics.widthPixels, metrics.heightPixels);
			int displayHeight = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
					.min(metrics.widthPixels, metrics.heightPixels) : Math.max(
					metrics.widthPixels, metrics.heightPixels);
			Log.e("screen", displayWidth + " h: " + displayHeight);

			Log.e("Rect",
					"left: " + rect.left + " right: " + rect.right
							+ " bootom: " + rect.bottom + " top: " + rect.top
							+ " centerX:" + rect.centerX() + " centerY:"
							+ rect.centerY());
			editPicCustomView.loadImages(picmomentActivity, drawable,
					rect,isImageMode);
			isImageMode=false;
			editPicCustomView.invalidate();

		}
          
     }  
	private void addStrikerView() {
		View	stickerParent =PopupProvider.getEmoticone(picmomentActivity, new frame() {
			
			@Override
			public void frameClicked(int index) {
				if (index==4) {
					linearLayout.removeAllViews();
					//linearLayout.addView(null);
					
				}else {
					
					View stickerChild=PopupProvider.getEmoticonChild(picmomentActivity, new frameChild() {
						
						@Override
						public void frameChildClicked(int emoticonType, int index) {
						
							if (index==-1) {
								addStrikerView();
							}else {
								addStrikerToView(emoticonType, index);
							}
							
						}
					}, index);
					linearLayout.removeAllViews();
					linearLayout.addView(stickerChild);	
				}
			
			}
		});
		linearLayout.removeAllViews();
		linearLayout.addView(stickerParent);
	}
	private void addStrikerToView(int emoticonType, int index) {
		
		Rect rect = new Rect();
		rect.right=Util.getScreenWidth(picmomentActivity);
		rect.bottom=Util.getScreenHeight(picmomentActivity);
			
		switch (emoticonType) {
		case 0:

			break;
		case 1:

			break;
		case 2:
			Bitmap bitmap=ScalingUtilities.decodeResource(getResources(), PopupProvider.emocionsbig[index],editPicCustomView.getWidth(),
	      			editPicCustomView.getHeight(), ScalingLogic.FIT);
			bitmap=ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),editPicCustomView.getHeight(), ScalingLogic.FIT);
			editPicCustomView.loadImages(picmomentActivity,new BitmapDrawable(getResources(),bitmap) ,rect,false);
			break;
		case 3:
		
			break;
		default:
			break;
		}
	}
}
