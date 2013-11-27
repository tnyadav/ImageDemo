package com.pic.moment;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.pic.moment.MultiTouchController.PositionAndScale;

public class Img {

	private int resId;
   

	private Drawable drawable;
	private Resources resources;
	private boolean deleted = false;
	private boolean bouncing = false;
	private boolean isText;
	
	private int width, height;
	private int displayWidth;
	private int displayHeight;
	
	private float centerX;
	private float centerY;
	private float scaleX;
	private float scaleY;
	
	private float angle;
	
	private float minX;
	private float maxX;
	private float minY;
	private float maxY;
	private boolean isCollege=false;
	private static final float SCREEN_MARGIN = 100;

	 public int getResId() {
			return resId;
		}

		public void setResId(int resId) {
			this.resId = resId;
		}

		public Drawable getDrawable() {
			return drawable;
		}

		public void setDrawable(Drawable drawable) {
			this.drawable = drawable;
		}

		public Resources getResources() {
			return resources;
		}

		public void setResources(Resources resources) {
			this.resources = resources;
		}

		public boolean isDeleted() {
			return deleted;
		}

		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}

		public boolean isBouncing() {
			return bouncing;
		}

		public void setBouncing(boolean bouncing) {
			this.bouncing = bouncing;
		}

		public boolean isText() {
			return isText;
		}

		public void setText(boolean isText) {
			this.isText = isText;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getDisplayWidth() {
			return displayWidth;
		}

		public void setDisplayWidth(int displayWidth) {
			this.displayWidth = displayWidth;
		}

		public int getDisplayHeight() {
			return displayHeight;
		}

		public void setDisplayHeight(int displayHeight) {
			this.displayHeight = displayHeight;
		}

		public float getCenterX() {
			return centerX;
		}

		public void setCenterX(float centerX) {
			this.centerX = centerX;
		}

		public float getCenterY() {
			return centerY;
		}

		public void setCenterY(float centerY) {
			this.centerY = centerY;
		}

		public float getScaleX() {
			return scaleX;
		}

		public void setScaleX(float scaleX) {
			this.scaleX = scaleX;
		}

		public float getScaleY() {
			return scaleY;
		}

		public void setScaleY(float scaleY) {
			this.scaleY = scaleY;
		}

		public float getAngle() {
			return angle;
		}

		public void setAngle(float angle) {
			this.angle = angle;
		}

		public float getMinX() {
			return minX;
		}

		public void setMinX(float minX) {
			this.minX = minX;
		}

		public float getMaxX() {
			return maxX;
		}

		public void setMaxX(float maxX) {
			this.maxX = maxX;
		}

		public float getMinY() {
			return minY;
		}

		public void setMinY(float minY) {
			this.minY = minY;
		}

		public float getMaxY() {
			return maxY;
		}

		public void setMaxY(float maxY) {
			this.maxY = maxY;
		}
	
	
	
	public boolean isCollege() {
			return isCollege;
		}

		public void setCollege(boolean isCollege) {
			this.isCollege = isCollege;
		}

	public Img(Drawable resId, Resources res, boolean isText) {
		this.drawable = resId;
		this.isText = isText;
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
		if (isText) {
			setPos(cx, cy, 1, 1, 0);
		} else {
			setPos(cx, cy, sx, sy, (float) (Math.random() * 0.5f + 0.0f));
		}

	}

	/**
	 * Called by activity's onPause() method to free memory used for loading the
	 * images
	 */
	public void unload() {
		this.drawable = null;
	}

	/** Set the position and scale of an image in screen coordinates */
	public boolean setPos(PositionAndScale newImgPosAndScale, int mUIMode,
			int UI_MODE_ANISOTROPIC_SCALE) {
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
		if (newMinX > displayWidth - SCREEN_MARGIN || newMaxX < SCREEN_MARGIN
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
		if (!isCollege) {
			canvas.translate(dx, dy);
			canvas.rotate(angle * 180.0f / (float) Math.PI);
			canvas.translate(-dx, -dy);
		}
		//drawable.setColorFilter(Color.parseColor("#41EAA8"), Mode.MULTIPLY);

		

		float[] matrix = { 0, 0, 0, 0.1f, 0, // red
				0,  0.1f, 0, 0, 0, // green
				0, 0, 0, 0.1f, 0, // blue
				0.1f, 0, 0, 0, 0 // alpha
		};
	//	 drawable.setColorFilter(new ColorMatrixColorFilter(matrix));

		drawable.draw(canvas);

		canvas.restore();

	}

	/*
	 * int randomWithRange(int min, int max) { int range = (max - min) + 1;
	 * return (int)(Math.random() * range) + min; }
	 */

	public void bounce() {
		if (!bouncing) {
			bouncing = true;
			minX = minX - 10;
			maxX = maxX + 10;
			minY = minY - 10;
			maxY = maxY + 10;

			// multiTouchController.selectedObject=null;
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					minX = minX + 10;
					maxX = maxX - 10;
					minY = minY + 10;
					maxY = maxY - 10;
					bouncing = false;

				}
			}, 200);
		}

	}
}
