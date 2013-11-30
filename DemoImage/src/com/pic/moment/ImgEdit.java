package com.pic.moment;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import com.pic.moment.MultiTouchController.PositionAndScale;

public class ImgEdit {



	@Override
	public String toString() {
		return "ImgEdit [width=" + width + ", height=" + height
				+ ", displayWidth=" + displayWidth + ", displayHeight="
				+ displayHeight + ", centerX=" + centerX + ", centerY="
				+ centerY + ", scaleX=" + scaleX + ", scaleY=" + scaleY
				+ ", angle=" + angle + ", minX=" + minX + ", maxX=" + maxX
				+ ", minY=" + minY + ", maxY=" + maxY + "]";
	}

	private int resId;
   

	private Drawable drawable;
	private Resources resources;
	private boolean deleted = false;
	private boolean bouncing = false;
	private boolean isImage;
	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

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
	
	private static final float SCREEN_MARGIN = 0;

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
	
	

	public ImgEdit(Drawable resId, Resources res,boolean isImage) {
		this.drawable = resId;
		this.isImage=isImage;
        this.resources = res;
		this.deleted = false;
		
	}

	

	/** Called by activity's onResume() method to load the images */
	public void load(Resources res,Rect rect) {
		this.displayWidth=rect.right;
		this.displayHeight=rect.bottom;
		this.width = drawable.getIntrinsicWidth();
		this.height = drawable.getIntrinsicHeight();

		float cx, cy, sx, sy,sc;
		cx = (float) /* (Math.random() * */(displayWidth) / 2;
		cy = (float) /* (Math.random() * */(displayHeight) / 2;
		setPos(cx, cy,0.98f,0.98f,0);
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
	
		float   newMinX = centerX - ws,
				newMinY = centerY - hs, 
				newMaxX = centerX+ ws, 
				newMaxY = centerY + hs;
	
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
		if (maxX-minX!=100 && maxY-minY!=100&&!bouncing) {
			setDeleted(false);
		}
		
		if (!isImage) {
			canvas.translate(dx, dy);
			canvas.rotate(angle * 180.0f / (float) Math.PI);
			canvas.translate(-dx, -dy);
		}
		
	
		
		drawable.draw(canvas);

		canvas.restore();

	}

	
	public void bounce() {

		if (!bouncing) {
			bouncing = true;
			minX = minX - 10;
			maxX = maxX + 10;
			minY = minY - 10;
			maxY = maxY + 10;
            
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
