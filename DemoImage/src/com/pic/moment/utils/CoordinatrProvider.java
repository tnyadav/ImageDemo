package com.pic.moment.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class CoordinatrProvider {

    private static final int margin=10;
  
	public static List<FrameCoordinete> getFremeCoordinate(Resources res,int frameType) {
		DisplayMetrics metrics = res.getDisplayMetrics();
		 int height;
		 int width;
	     int displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
			.max(metrics.widthPixels, metrics.heightPixels) : Math.min(
			metrics.widthPixels, metrics.heightPixels);
	     int displayHeight =res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
			.min(metrics.widthPixels, metrics.heightPixels) : Math.max(
			metrics.widthPixels, metrics.heightPixels)-20;
			List<FrameCoordinete> frameCoordinetes = null ;
			FrameCoordinete frameCoordinete;
		
		switch (frameType) {
		
		case 0:
			
			frameCoordinetes = new ArrayList<FrameCoordinete>();
        height=(displayHeight-margin*4)/3;
       // int width=(displayWidth-margin*2)/2;
		 frameCoordinete= new FrameCoordinete(margin,margin,displayWidth-margin,height+margin,0,0);	
		 frameCoordinetes.add(frameCoordinete);	
		 frameCoordinete= new FrameCoordinete(margin,height+2*margin,displayWidth-margin,2*(height+margin),0,0);	
		 frameCoordinetes.add(frameCoordinete);	
		 frameCoordinete= new FrameCoordinete(margin,2*(height+margin)+margin,displayWidth-margin,3*(height+margin),0,0);	
		 frameCoordinetes.add(frameCoordinete);	
		 break;
		case 1:
			frameCoordinetes = new ArrayList<FrameCoordinete>();
			     height=(displayHeight-margin*4)/3;
		         // int width=(displayWidth-margin*2)/2;
				 frameCoordinete= new FrameCoordinete(margin,margin,displayWidth-margin,height+margin,0,0);	
				 frameCoordinetes.add(frameCoordinete);	
				 frameCoordinete= new FrameCoordinete(margin,height+2*margin,displayWidth-margin,3*(height+margin),0,0);	
				 frameCoordinetes.add(frameCoordinete);	
				 
			break;

		case 2:
			frameCoordinetes = new ArrayList<FrameCoordinete>();
			 height=(displayHeight-margin*4)/3;
			 frameCoordinete= new FrameCoordinete(margin,margin,displayWidth-margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,2*(height+margin)+margin,displayWidth-margin,3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			break;

		case 3:
			frameCoordinetes = new ArrayList<FrameCoordinete>();
			 height=(displayHeight-margin*3)/2;
			 frameCoordinete= new FrameCoordinete(margin,margin,displayWidth-margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,displayWidth-margin,displayHeight-2*margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			break;

		case 4:

			break;

		case 5:

			break;

		case 6:

			break;

		default:
			break;
		}
		return frameCoordinetes;

	}
}
