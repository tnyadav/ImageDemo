package com.pic.moment.utils;

import java.io.File;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.pic.moment.activity.PicmomentActivity;
import com.pic.moment.fragment.ShareFragment;

public class SaveFileTask extends AsyncTask<Void, Void, Boolean> {
Context context;

protected String fileName;
private ProgressDialog Dialog;
	public SaveFileTask() {
	super();
	this.context = context;
	
	this.fileName = fileName;
}

	

	
	@Override
	protected Boolean doInBackground(Void... arg0) {

	

			try {
				File file = new File(Util.temPath);
		            if (!file.exists())
		            {
		            	file.mkdirs();
		            }
		 
		       return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

			
		
	}
	
	
	

}
