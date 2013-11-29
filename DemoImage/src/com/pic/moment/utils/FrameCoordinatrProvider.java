package com.pic.moment.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class FrameCoordinatrProvider {

    private static final int margin=10;
  
	public static List<FrameCoordinete> getFremeCoordinate(Resources res,int frameType) {
		DisplayMetrics metrics = res.getDisplayMetrics();
		 int height,blockHeight;
		 int width,blockWidth;
	     int displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
			.max(metrics.widthPixels, metrics.heightPixels) : Math.min(
			metrics.widthPixels, metrics.heightPixels);
	     int displayHeight =res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
			.min(metrics.widthPixels, metrics.heightPixels) : Math.max(
			metrics.widthPixels, metrics.heightPixels)-20;
			displayHeight=displayHeight-50;
			List<FrameCoordinete> frameCoordinetes = null ;
			FrameCoordinete frameCoordinete;
		
		switch (frameType) {
		
		case 0:
			
			
		 break;
		case 1:
			     frameCoordinetes = new ArrayList<FrameCoordinete>();
			     height=(displayHeight-margin*4)/3;
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
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-margin*4)/3;
	      	 frameCoordinete= new FrameCoordinete(margin,margin,displayWidth-margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,displayWidth-margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,2*(height+margin)+margin,displayWidth-margin,3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			break;

		case 5:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-3*margin)/2;
	         width=(displayWidth-3*margin)/2;
	      	 frameCoordinete= new FrameCoordinete(margin,margin,width+margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			break;

		case 6:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-3*margin)/2;
	         width=(displayWidth-3*margin)/2;
	      	 frameCoordinete= new FrameCoordinete(margin,margin,width+margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,width+margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			break;
		case 7:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-3*margin)/2;
	         width=(displayWidth-3*margin)/2;
	      	 frameCoordinete= new FrameCoordinete(margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,width+margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 frameCoordinete= new FrameCoordinete(width+2*margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			break;
		case 8:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-3*margin)/2;
	         width=(displayWidth-3*margin)/2;
	         frameCoordinete= new FrameCoordinete(margin,margin,width+margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 break;
		case 9:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	     
	         width=(displayWidth-4*margin)/3;
	         frameCoordinete= new FrameCoordinete(margin,margin,width+margin,displayHeight-2*margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),displayHeight-2*margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(2*(width+margin)+margin,margin,3*(width+margin),displayHeight-2*margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
		
			break;
		case 10:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-5*margin)/4;
	         frameCoordinete= new FrameCoordinete(margin,margin,displayWidth-margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,displayWidth-margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,2*height+3*margin,displayWidth-margin,3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,3*height+4*margin,displayWidth-margin,4*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
		
			break;
		case 11:
			frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-3*margin)/2;
	         width=(displayWidth-3*margin)/2;
	         frameCoordinete= new FrameCoordinete(margin,margin,width+margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,width+margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 frameCoordinete= new FrameCoordinete(width+2*margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
		
			break;
		case 12:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-margin*4)/3;
	         width=(displayWidth-3*margin)/2;
	         frameCoordinete= new FrameCoordinete(margin,margin,width+margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 
	    	 frameCoordinete= new FrameCoordinete(margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 
			 frameCoordinete= new FrameCoordinete(margin,2*height+3*margin,width+margin,3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 frameCoordinete= new FrameCoordinete(width+2*margin,2*height+3*margin,2*(width+margin),3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
		
			break;
		case 13:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-margin*4)/3;
	         width=(displayWidth-3*margin)/2;
			 frameCoordinete= new FrameCoordinete(margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,width+margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 
			 frameCoordinete= new FrameCoordinete(margin,2*height+3*margin,width+margin,3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 frameCoordinete= new FrameCoordinete(width+2*margin,2*height+3*margin,2*(width+margin),3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
		
			break;
		case 14:
			 frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-margin*4)/3;
	         width=(displayWidth-3*margin)/2;
	         frameCoordinete= new FrameCoordinete(margin,margin,width+margin,height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,margin,2*(width+margin),height+margin,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 
			 frameCoordinete= new FrameCoordinete(margin,height+2*margin,width+margin,2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 frameCoordinete= new FrameCoordinete(width+2*margin,height+2*margin,2*(width+margin),2*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 
			 frameCoordinete= new FrameCoordinete(margin,2*height+3*margin,width+margin,3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 frameCoordinete= new FrameCoordinete(width+2*margin,2*height+3*margin,2*(width+margin),3*(height+margin),0,0);	
			 frameCoordinetes.add(frameCoordinete);
			break;
		case 15:
			frameCoordinetes = new ArrayList<FrameCoordinete>();
	         height=(displayHeight-margin*4)/3;
	         width=(displayWidth-4*margin)/3;
	         blockHeight=height+margin;
	         blockWidth=width+margin;
	         //1
	         frameCoordinete= new FrameCoordinete(margin,margin,blockWidth,blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 //2
			 frameCoordinete= new FrameCoordinete(blockWidth+margin,margin,2*blockWidth,blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 //3
			 frameCoordinete= new FrameCoordinete(2*blockWidth+margin,margin,3*blockWidth-margin,blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 //4
			 frameCoordinete= new FrameCoordinete(margin,blockHeight+margin,blockWidth,2*blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 //5
			 frameCoordinete= new FrameCoordinete(blockWidth+margin,blockHeight+margin,2*blockWidth,2*blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 //6
			 frameCoordinete= new FrameCoordinete(2*blockWidth+margin,blockHeight+margin,3*blockWidth,2*blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);	
			 //7
			 frameCoordinete= new FrameCoordinete(margin,2*blockHeight+margin,blockWidth,3*blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 //8
			 frameCoordinete= new FrameCoordinete(blockWidth+margin,2*blockHeight+margin,2*blockWidth,3*blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);
			 //9
			 frameCoordinete= new FrameCoordinete(2*blockWidth+margin,2*blockHeight+margin,3*blockWidth,3*blockHeight,0,0);	
			 frameCoordinetes.add(frameCoordinete);
	         
			break;
		default:
		}
		return frameCoordinetes;

	}
}
