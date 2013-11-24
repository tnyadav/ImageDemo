package com.pic.moment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ShareFragment extends BaseFragment{
private View shareFragment;
ImageView imageView;
private Bitmap bitmap;
public Bitmap getBitmap() {
	return bitmap;
}
public void setBitmap(Bitmap bitmap) {
	this.bitmap = bitmap;
}
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
	bitmap=(Bitmap)(getArguments().getParcelable("image"));
	Toast.makeText(picmomentActivity, bitmap.getWidth()+" "+bitmap.getHeight(), 1).show();
	imageView.setImageBitmap(bitmap);
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
