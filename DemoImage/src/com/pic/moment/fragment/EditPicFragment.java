package com.pic.moment.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

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
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.pic.moment.CustomMenu;
import com.pic.moment.EditPicCustomView;
import com.pic.moment.GPUImageFilterTools.FilterAdjuster;
import com.pic.moment.ImgEdit;
import com.pic.moment.R;
import com.pic.moment.utils.PopupProvider;
import com.pic.moment.utils.PopupProvider.frame;
import com.pic.moment.utils.PopupProvider.frameChild;
import com.pic.moment.utils.ScalingUtilities;
import com.pic.moment.utils.ScalingUtilities.ScalingLogic;
import com.pic.moment.utils.Util;

public class EditPicFragment extends BaseFragment{
private View homeFragmentView;
private Button editBack,editReset,editCurve,editFrame,editAdd,editEffect,editShare;
private final int RESULT_LOAD_IMAGE = 001;
private final int CAPTURE_IMAGE = 002;
private boolean isImageMode=false;
Uri imageUri= null;
LinearLayout linearLayout;
FrameLayout mainContainer;
EditPicCustomView editPicCustomView;
private GPUImageFilter mFilter;
private FilterAdjuster mFilterAdjuster;
private GPUImageView mGPUImageView;
protected String fileName;
 Uri uri;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeFragmentView = inflater.inflate(R.layout.editpicfragment, container, false);
		SetContent();
		
        return homeFragmentView;
		
		
		
	}
private void SetContent() {
linearLayout=(LinearLayout)homeFragmentView.findViewById(R.id.editDataContainer);

editCurve=(Button)homeFragmentView.findViewById(R.id.editCurve);
editCurve.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
});

editFrame=(Button)homeFragmentView.findViewById(R.id.editFrame);
editFrame.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
	linearLayout.removeAllViews();
	View view=PopupProvider.getBlands(picmomentActivity, new frame() {
		
		@Override
		public void frameClicked(int index) {
			
			
		}
	});
	linearLayout.addView(view);
	}
});
editAdd = (Button)homeFragmentView.findViewById(R.id.editAdd);
editAdd.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		showDialog();
	}
});

editEffect=(Button)homeFragmentView.findViewById(R.id.editEffect);
editEffect.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		 uri=Uri.fromFile(new File(saveImage()));
		/*new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				setGPUImageView(uri);
			}
		}, 5000);*/
		
		
	}
});
editShare=(Button)homeFragmentView.findViewById(R.id.editShare);
editShare.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
	//	setGPUImageView(uri);	
   // saveImage();
	
	}
});

editBack=(Button)homeFragmentView.findViewById(R.id.editBack);
editBack.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
});
editReset=(Button)homeFragmentView.findViewById(R.id.editReset);
editReset.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		editPicCustomView.unloadImages();
		
	}
});

mainContainer=(FrameLayout)homeFragmentView.findViewById(R.id.mainFrameContainer);
editPicCustomView = new EditPicCustomView(picmomentActivity);
mGPUImageView = (GPUImageView)homeFragmentView.findViewById(R.id.gpuimage);
mGPUImageView.setVisibility(View.GONE);
editPicCustomView.setBack(editBack);
editPicCustomView.setReset(editReset);
mainContainer.addView(editPicCustomView);


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
		Bitmap bitmap;
			
		switch (emoticonType) {
		case 0:
			 bitmap=ScalingUtilities.decodeResource(getResources(), PopupProvider.makeupBig[index],editPicCustomView.getWidth(),
		      			editPicCustomView.getHeight(), ScalingLogic.FIT);
				bitmap=ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),editPicCustomView.getHeight(), ScalingLogic.FIT);
				editPicCustomView.loadImages(picmomentActivity,new BitmapDrawable(getResources(),bitmap) ,rect,false);
		
			break;
		case 1:
			 bitmap=ScalingUtilities.decodeResource(getResources(), PopupProvider.textBig[index],editPicCustomView.getWidth(),
		      			editPicCustomView.getHeight(), ScalingLogic.FIT);
				bitmap=ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),editPicCustomView.getHeight(), ScalingLogic.FIT);
				editPicCustomView.loadImages(picmomentActivity,new BitmapDrawable(getResources(),bitmap) ,rect,false);
			break;
		case 2:
			 bitmap=ScalingUtilities.decodeResource(getResources(), PopupProvider.emocionsbig[index],editPicCustomView.getWidth(),
	      			editPicCustomView.getHeight(), ScalingLogic.FIT);
			bitmap=ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),editPicCustomView.getHeight(), ScalingLogic.FIT);
			editPicCustomView.loadImages(picmomentActivity,new BitmapDrawable(getResources(),bitmap) ,rect,false);
			break;
		case 3:
			 bitmap=ScalingUtilities.decodeResource(getResources(), PopupProvider.lightItemBig[index],editPicCustomView.getWidth(),
	      			editPicCustomView.getHeight(), ScalingLogic.FIT);
			bitmap=ScalingUtilities.createScaledBitmap(bitmap, editPicCustomView.getWidth(),editPicCustomView.getHeight(), ScalingLogic.FIT);
			editPicCustomView.loadImages(picmomentActivity,new BitmapDrawable(getResources(),bitmap) ,rect,false);

			break;
		default:
			break;
		}
	}
	private void showDialog() {
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
						dialog.dismiss();
						linearLayout.setVisibility(View.VISIBLE);
						addStrikerView();
					
					}
				});
				dialog.show();
				
				
	}
	private String saveImage() {

		fileName = Util.temPath+"/"+"PicMoment" + System.currentTimeMillis()+".jpg";
		Bitmap returnedBitmap = Bitmap.createBitmap(editPicCustomView.getWidth(),
				editPicCustomView.getHeight(), Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(returnedBitmap);
				Drawable bgDrawable = editPicCustomView.getBackground();
				if (bgDrawable != null)
					bgDrawable.draw(canvas);
				else
					canvas.drawColor(Color.WHITE);
				editPicCustomView.draw(canvas);
		        try {
		        	File file = new File(Util.temPath);
			            if (!file.exists())
			            {
			            	file.mkdirs();
			            }

					returnedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(fileName));
					Toast.makeText(picmomentActivity, "saved in "+fileName, 1).show();
					setGPUImageView(returnedBitmap);
					
					/* ShareFragment shareFragment=new ShareFragment();
					 Bundle bundle=new Bundle();
						bundle.putString("filename", fileName);
						shareFragment.setArguments(bundle);
						
						picmomentActivity.pushFragments(shareFragment, true, true);*/
		        
		        
		        } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(picmomentActivity, "saved faild", 1).show();
	 				
					
				}
				return fileName;
	
	}

	private void setGPUImageView(final Bitmap imagePath) {
		editPicCustomView.setVisibility(View.GONE);
		mGPUImageView.setVisibility(View.VISIBLE);
		mGPUImageView.setImage(imagePath);
		
	}

	private void setEditPicCustomView() {
		mGPUImageView.setVisibility(View.GONE);
		editPicCustomView.setVisibility(View.VISIBLE);

	}
}
