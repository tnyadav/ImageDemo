package com.pic.moment;

import java.io.File;
import java.io.FileOutputStream;

import com.pic.moment.PopupProvider.frame;

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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
private Button collageBack,collageDelete,collageFrame,collageAdd,collageShare,frameBotton;
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
		
		
	/*	
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

					b.compress(CompressFormat.JPEG, 95, new FileOutputStream(temPath+"/"+time+".jpeg"));
					Toast.makeText(picmomentActivity, "saved in "+temPath+"/"+time+".jpeg", 1).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("t", e.toString());
					Toast.makeText(picmomentActivity, "faild", 1).show();
				}
				
			dialog.dismiss();
			picmomentActivity.popFragments();
				
			}
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
	  
		dialog.show();*/
	}
});
collageDelete = (Button)homeFragmentView.findViewById(R.id.collageDelete);
collageDelete.setClickable(false);
collageFrame = (Button)homeFragmentView.findViewById(R.id.collageFrame);
collageFrame.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		View	navigationViewContainer = PopupProvider.getFrame(picmomentActivity, new frame() {
			
			@Override
			public void frameClicked(int index) {
				Toast.makeText(picmomentActivity, ""+index, 1).show();
			}
		});
		CustomMenu.show(picmomentActivity,navigationViewContainer);
		
	}
});

collageAdd = (Button)homeFragmentView.findViewById(R.id.collageAdd);
collageAdd.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		
	/*	LayoutInflater layoutInflater=picmomentActivity.getLayoutInflater();;
		View	navigationViewContainer = layoutInflater.inflate(
					R.layout.dialogback, null);
		CustomMenu.show(picmomentActivity,navigationViewContainer);*/
		
		
		
		
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
				
				
			}
		});
	    Button dialogAddSticker = (Button)dialog.findViewById(R.id.dialogAddSticker);
	    dialogAddSticker.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View	navigationViewContainer = PopupProvider.getEmocione(picmomentActivity, new frame() {
					
					@Override
					public void frameClicked(int index) {
						Toast.makeText(picmomentActivity, ""+index, 1).show();
					}
				});
				CustomMenu.show(picmomentActivity,navigationViewContainer);
			}
		});
		dialog.show();
		
		}
});
collageShare = (Button)homeFragmentView.findViewById(R.id.collageShare);
collageShare.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {}
});

/*frameBotton=(Button)homeFragmentView.findViewById(R.id.collageFrame);
frameBotton.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
	}
});
*/

frameLayout=(FrameLayout)homeFragmentView.findViewById(R.id.mainFrameContainer);
photoSorter = new PhotoSortrView(picmomentActivity);
int []rectf=new int[2];
collageFrame.getLocationOnScreen(rectf);

photoSorter.setRectf(rectf);
photoSorter.setDelete(collageDelete);/*
photoSorter.setOnTouchListener(new OnTouchListener() {
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		 switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
				if (dialog!=null) 
					dialog.dismiss();
		   
		      break;
		    case MotionEvent.ACTION_UP:
		    	Img i=photoSorter.mImages.get(0);
				if (i.getMinX() <= event.getX()&&event.getX()<=i.getMaxX()&&i.getMinY() <= event.getY()&&event.getY()<=i.getMaxY()) {
					
				}else {
		
						dialog = new Dialog(picmomentActivity,R.style.custom_dialog_theme_back);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setCanceledOnTouchOutside(true);
						Window window = dialog.getWindow();
						WindowManager.LayoutParams wlp = window.getAttributes();
						//wlp.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
						wlp.height=LayoutParams.WRAP_CONTENT;
						wlp.width=LayoutParams.WRAP_CONTENT;
						wlp.x=(int) event.getX()-300;
						wlp.y=(int) event.getY()-300;
						//wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
						window.setAttributes(wlp);
						//window.clearFlags(LayoutParams.FLAG_DIM_BEHIND);
						dialog.setCancelable(true);
						dialog.setContentView(R.layout.dialogback);
					    Button save = (Button)dialog.findViewById(R.id.save);
					    save.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
							
				                
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

									b.compress(CompressFormat.JPEG, 95, new FileOutputStream(temPath+"/"+time+".jpeg"));
									Toast.makeText(picmomentActivity, "saved in "+temPath+"/"+time+".jpeg", 1).show();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Log.e("t", e.toString());
									Toast.makeText(picmomentActivity, "faild", 1).show();
								}
								
							dialog.dismiss();
							picmomentActivity.popFragments();
								
							}
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
		      break;
		 }
		
		return false;
	}
});*/
frameLayout.addView(photoSorter);
collageDelete.setVisibility(View.GONE);
collageFrame.setVisibility(View.GONE);
collageShare.setVisibility(View.GONE);



//frameLayout.addView(photoSorter);
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
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            new LoadImagesFromSDCard().execute(selectedImage);
            
          /*  String[] filePathColumn = { MediaStore.Images.Media.DATA };
       	 
            Cursor cursor = picmomentActivity.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             String picturePath = cursor.getString(columnIndex);
             
             cursor.close();
             
             if (photoSorter==null) {
             	
                 
                 
                 photoSorter.loadImages(picmomentActivity,new BitmapDrawable(getResources(),addWhiteBorder(BitmapFactory.decodeFile(picturePath), 10)));
 			}else {
 				photoSorter.loadImages(picmomentActivity,new BitmapDrawable(getResources(),addWhiteBorder(BitmapFactory.decodeFile(picturePath), 10)));
 			}
             if (collageDelete.getVisibility()==View.GONE && collageFrame.getVisibility() ==View.GONE && collageFrame.getVisibility() == View.GONE) {
             	collageDelete.setVisibility(View.VISIBLE);
                 collageFrame.setVisibility(View.VISIBLE);
                 collageShare.setVisibility(View.VISIBLE);	
 			}*/
             
         }
        if(requestCode==CAPTURE_IMAGE && resultCode==Activity.RESULT_OK )
        {
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
        

	 public class LoadImagesFromSDCard  extends AsyncTask<Uri, Void, String> {
         
         private ProgressDialog Dialog = new ProgressDialog(picmomentActivity);
          
        // Bitmap mBitmap;
        
          
         protected void onPreExecute() {
             /****** NOTE: You can call UI Element here. *****/
              
             // Progress Dialog
             Dialog.setMessage(" Loading image from Sdcard..");
             Dialog.show();
         }


         // Call after onPreExecute method
         protected String doInBackground(Uri... urls) {
              
        	 String picturePath = null ;
                  
                  
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
                 } catch (Exception e) {
                     // Error fetching image, try to recover
                      e.printStackTrace();
                     /********* Cancel execution of this task. **********/
                     cancel(true);
                 }
              
             return picturePath;
         }
          
          
         protected void onPostExecute(String picturePath) {
              
             // NOTE: You can call UI Element here.
                                                                                           
             // Close progress dialog
               Dialog.dismiss();
               if (photoSorter==null) {
                	
                   
                    
                    photoSorter.loadImages(picmomentActivity,new BitmapDrawable(getResources(),addWhiteBorder(BitmapFactory.decodeFile(picturePath), 10)));
    			}else {
    				photoSorter.loadImages(picmomentActivity,new BitmapDrawable(getResources(),addWhiteBorder(BitmapFactory.decodeFile(picturePath), 10)));
    			}
                if (collageDelete.getVisibility()==View.GONE && collageFrame.getVisibility() ==View.GONE && collageFrame.getVisibility() == View.GONE) {
                	collageDelete.setVisibility(View.VISIBLE);
                    collageFrame.setVisibility(View.VISIBLE);
                    collageShare.setVisibility(View.VISIBLE);	
    			}
                isPresent=true;
              
         }
          
     }  
	
}
