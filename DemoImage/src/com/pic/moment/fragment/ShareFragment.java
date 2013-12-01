package com.pic.moment.fragment;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pic.moment.R;
import com.pic.moment.R.id;
import com.pic.moment.R.layout;

public class ShareFragment extends BaseFragment{
private View shareFragment;
ImageView imageView;
private Bitmap bitmap;

private Button shareBack,shareFacebookButton,shareTwitterButton,shareTumbirButton,shareLibraryButton,shareEmailButton,shareFlikerButton;

Uri imageUri= null;
boolean isPresent=false;
private String temPath= Environment.getExternalStorageDirectory()+"/PicMomentsTemp";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		shareFragment = inflater.inflate(R.layout.share, container, false);
		SetContent();
		
        return shareFragment;
		
		
		
	}
private void SetContent() {
	imageView=(ImageView)shareFragment.findViewById(R.id.shareImage);
	//bitmap=(Bitmap)(getArguments().getParcelable("image"));
//	imageView.setImageBitmap(bitmap);
	shareBack=(Button)shareFragment.findViewById(R.id.shareBack);
	shareBack.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			picmomentActivity.popFragments();
		}
	});
	//**********************
	shareFacebookButton=(Button)shareFragment.findViewById(R.id.shareFacebookButton);
	shareFacebookButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
		}
	});
	shareTwitterButton=(Button)shareFragment.findViewById(R.id.shareTwitterButton);
	shareTwitterButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
		}
	});
	shareTumbirButton=(Button)shareFragment.findViewById(R.id.shareTumbirButton);
	shareTumbirButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
		}
	});
	
	//*************************
	shareLibraryButton=(Button)shareFragment.findViewById(R.id.shareLibraryButton);
	shareLibraryButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			try {
				File file = new File(temPath);
		            if (!file.exists())
		            {
		            	file.mkdirs();
		            }
		            
		        String time=""+System.currentTimeMillis();    

				bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(temPath+"/"+time+".jpeg"));
				Toast.makeText(picmomentActivity, "saved in "+temPath+"/"+time+".jpeg", 1).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("t", e.toString());
				Toast.makeText(picmomentActivity, "faild to save", 1).show();
			}
			
		}
	});
	
	shareEmailButton=(Button)shareFragment.findViewById(R.id.shareEmailButton);
	shareEmailButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
		}
	});
	shareFlikerButton=(Button)shareFragment.findViewById(R.id.shareFlikerButton);
	shareFlikerButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
		}
	});
	
	

}
}
