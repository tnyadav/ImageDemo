package com.pic.moment;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.pic.moment.MultiTouchController.PositionAndScale;

public class Img {

	private int resId;

	private Drawable drawable;
	public Resources resources;
	boolean deleted=false;
	boolean bouncing=false;
	private int width, height;

	int displayWidth;

	private int displayHeight;

	float centerX;

	float centerY;

	private float scaleX;

	private float scaleY;

	private float angle;

	float minX;

	float maxX;

	float minY;

	float maxY;

	private static final float SCREEN_MARGIN = 100;

	public Img(Drawable resId, Resources res) {
		this.drawable = resId;
		

		this.resources = res;
		this.deleted = false;
		getMetrics(res);
	}

	private void getMetrics(Resources res) {
		DisplayMetrics metrics = res.getDisplayMetrics();
		// The DisplayMetrics don't seem to always be updated on screen
		// rotate, so we hard code a portrait
		// screen orientation for the non-rotated screen here...
		// this.displayWidth = metrics.widthPixels;
		// this.displayHeight = metrics.heightPixels;
		this.displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
				.max(metrics.widthPixels, metrics.heightPixels) : Math.min(
				metrics.widthPixels, metrics.heightPixels);
		this.displayHeight = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math
				.min(metrics.widthPixels, metrics.heightPixels) : Math.max(
				metrics.widthPixels, metrics.heightPixels);
	}

	/** Called by activity's onResume() method to load the images */
	public void load(Resources res) {
		getMetrics(res);
		this.width = drawable.getIntrinsicWidth();
		this.height = drawable.getIntrinsicHeight();
		
		
		
		
		float cx, cy, sx, sy;
		cx = (float) /* (Math.random() * */(displayWidth) / 2;
		cy = (float) /* (Math.random() * */(displayHeight) / 2;

		float sc = (float) (Math.max(displayWidth, displayHeight)
				/ (float) Math.max(width, height) * Math.random() * 0.3 + 0.2);
		sx = sy = sc;
		setPos(cx, cy, sx, sy, (float) (Math.random() * 0.5f + 0.0f));
		//setPos(cx, cy, 1, 1, 0);
	}

	/**
	 * Called by activity's onPause() method to free memory used for loading
	 * the images
	 */
	public void unload() {
		this.drawable = null;
	}

	/** Set the position and scale of an image in screen coordinates */
	public boolean setPos(PositionAndScale newImgPosAndScale,int mUIMode,int UI_MODE_ANISOTROPIC_SCALE ) {
		return setPos(
				newImgPosAndScale.getXOff(),
				newImgPosAndScale.getYOff(),
				(mUIMode & UI_MODE_ANISOTROPIC_SCALE) != 0 ? newImgPosAndScale
						.getScaleX() : newImgPosAndScale.getScale(),
				(mUIMode & UI_MODE_ANISOTROPIC_SCALE) != 0 ? newImgPosAndScale
						.getScaleY() : newImgPosAndScale.getScale(),
				newImgPosAndScale.getAngle());

	}

	/** Set the position and scale of an image in screen coordinates */
	private boolean setPos(float centerX, float centerY, float scaleX,
			float scaleY, float angle) {
		float ws = (width / 2) * scaleX, hs = (height / 2) * scaleY;
		float newMinX = centerX - ws, newMinY = centerY - hs, newMaxX = centerX
				+ ws, newMaxY = centerY + hs;
		if (newMinX > displayWidth - SCREEN_MARGIN
				|| newMaxX < SCREEN_MARGIN
				|| newMinY > displayHeight - SCREEN_MARGIN
				|| newMaxY < SCREEN_MARGIN)
			return false;
		this.centerX = centerX;
		this.centerY = centerY;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.angle = angle;
		this.minX = newMinX;
		this.minY = newMinY;
		this.maxX = newMaxX;
		this.maxY = newMaxY;

		return true;
	}

	/** Return whether or not the given screen coords are inside this image */
	public boolean containsPoint(float scrnX, float scrnY) {
		// FIXME: need to correctly account for image rotation
		return (scrnX >= minX && scrnX <= maxX && scrnY >= minY && scrnY <= maxY);
	}

	public void draw(Canvas canvas) {
		canvas.save();
		float dx = (maxX + minX) / 2;
		float dy = (maxY + minY) / 2;
		drawable.setBounds((int) minX, (int) minY, (int) maxX, (int) maxY);
		//drawable.setColorFilter( 0xffff0000, Mode.MULTIPLY );
		canvas.translate(dx, dy);
		canvas.rotate(angle * 180.0f / (float) Math.PI);
		canvas.translate(-dx, -dy);

		drawable.draw(canvas);

		canvas.restore();

	}

	/*
	 * int randomWithRange(int min, int max) { int range = (max - min) + 1;
	 * return (int)(Math.random() * range) + min; }
	 */
	public Drawable getDrawable() {
		return drawable;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getCenterX() {
		return centerX;
	}

	public float getCenterY() {
		return centerY;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getAngle() {
		return angle;
	}

	// FIXME: these need to be updated for rotation
	public float getMinX() {
		return minX;
	}

	public float getMaxX() {
		return maxX;
	}

	public float getMinY() {
		return minY;
	}

	public float getMaxY() {
		return maxY;
	}
	public void bounce() {
		if (!bouncing) {
			bouncing=true;
			minX = minX -10;
			maxX = maxX + 10;
			minY = minY - 10;
			maxY = maxY + 10;
		
			
			//multiTouchController.selectedObject=null;
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					minX = minX+10 ;
					maxX = maxX-10 ;
					minY = minY+10 ;
					maxY = maxY-10 ;
					bouncing=false;
					
			}
			}, 200);
		}
			
	
	}
}
