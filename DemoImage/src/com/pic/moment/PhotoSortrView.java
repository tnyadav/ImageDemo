/**
 * PhotoSorterView.java
 * 
 * (c) Luke Hutchison (luke.hutch@mit.edu)
 * 
 * TODO: Add OpenGL acceleration.
 * 
 * --
 * 
 * Released under the MIT license (but please notify me if you use this code, so that I can give your project credit at
 * http://code.google.com/p/android-multitouch-controller ).
 * 
 * MIT license: http://www.opensource.org/licenses/MIT
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package com.pic.moment;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pic.moment.MultiTouchController.MultiTouchObjectCanvas;
import com.pic.moment.MultiTouchController.PointInfo;
import com.pic.moment.MultiTouchController.PositionAndScale;

public class PhotoSortrView extends View implements
		MultiTouchObjectCanvas<Img> {

	// private static final int[] IMAGES = { R.drawable.m74hubble,
	// R.drawable.catarina, R.drawable.tahiti, R.drawable.sunset,
	// R.drawable.lake };
	private static final String[] infoLines = { "Touch the screen",
			"with one or more", "fingers to test", "multitouch",
			"characteristics" };

	ArrayList<Img> mImages = new ArrayList<Img>();
	//private static int counter = 0;
	public static boolean saveclicked = false;
	private Context context;
	private boolean touch_disabled=false;
	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	private Button delete;
	// --

	private MultiTouchController<Img> multiTouchController/* = new MultiTouchController<Img>(
			this)*/;

	// --

	private PointInfo currTouchPoint = new PointInfo();

	private boolean mShowDebugInfo = true;

	public static final int UI_MODE_ROTATE = 1, UI_MODE_ANISOTROPIC_SCALE = 2;

	public int mUIMode = UI_MODE_ROTATE;

	// --
	int[] rectf;

	public int[] getRectf() {
		return rectf;
	}

	public void setRectf(int[] rectf) {
		this.rectf = rectf;
	}

	private Paint mLinePaintTouchPointCircle = new Paint();

	// ---------------------------------------------------------------------------------------------------

	public PhotoSortrView(Context context) {
		this(context, null);
		this.context = context;
		 multiTouchController = new MultiTouchController<Img>(
					this,context);

	}

	public PhotoSortrView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		 multiTouchController = new MultiTouchController<Img>(
					this, context);
	}

	public PhotoSortrView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		 multiTouchController = new MultiTouchController<Img>(
					this, context);
		init(context);
	}

	private void init(Context context) {
		// Resources res = context.getResources();
		/*
		 * for (int i = 0; i < IMAGES.length; i++) mImages.add(new
		 * Img(IMAGES[i], res));
		 */

		mLinePaintTouchPointCircle.setColor(Color.BLUE);
		mLinePaintTouchPointCircle.setStrokeWidth(25);
		mLinePaintTouchPointCircle.setTypeface(Typeface.DEFAULT_BOLD);
		// mLinePaintTouchPointCircle.setStyle(Style.STROKE);
		mLinePaintTouchPointCircle.setAntiAlias(true);

		/*
		 * mTouchTheScreenLabelPaint.setColor(Color.GRAY);
		 * mTouchTheScreenLabelPaint.setTextSize(24);
		 * mTouchTheScreenLabelPaint.setTypeface(Typeface.DEFAULT_BOLD);
		 * mTouchTheScreenLabelPaint.setAntiAlias(true);
		 */

		// setBackgroundColor(Color.YELLOW);
	}

	/** Called by activity's onResume() method to load the images */
	public void loadImages(Context context, Drawable []IMAGES) {
		Resources res = context.getResources();
		 for (int i = 0; i < IMAGES.length; i++)
		 {
			 Img img = new Img(IMAGES[i], res);
		     mImages.add(img);
		     img.load(res);
		 }
	
	}

	/**
	 * Called by activity's onPause() method to free memory used for loading the
	 * images
	 */
	public void unloadImages(int index) {
		int n = mImages.size();
		// for (int i = 0; i < n; i++)
		mImages.get(index).unload();
	}

	// ---------------------------------------------------------------------------------------------------

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// delete.setBackgroundResource(R.drawable.cl_deleteoff);
	/*	if (getDraggableObjectAtPoint(currTouchPoint)==null && currTouchPoint      .getNumTouchPoints()==1) {
			Toast.makeText(context, "outside", 1).show();
		}
		*/
		
		if (mImages.size() == 0) {
			float spacing = mLinePaintTouchPointCircle.getFontSpacing();
			float totHeight = spacing * infoLines.length;
			for (int i = 0; i < infoLines.length; i++)
				paintText(canvas, infoLines[i],
						(canvas.getHeight() - totHeight) * .5f + i * spacing);

		} else {
			if (saveclicked) {
				
				for (int i = 0; i <  mImages.size(); i++)
					if (mImages.get(i).deleted) {

					} else {
						mImages.get(i).draw(canvas);
					}

			} else {
			
				for (int i = 0; i <  mImages.size(); i++)
				
						mImages.get(i).draw(canvas);
				
					
			}

		}
		invalidate();
		delete.bringToFront();
		

	}

	private void paintText(Canvas canvas, String msg, float vPos) {
		Rect bounds = new Rect();
		int msgLen = msg.length();
		mLinePaintTouchPointCircle.getTextBounds(msg, 0, msgLen, bounds);
		canvas.drawText(msg, (canvas.getWidth() - bounds.width()) * .5f, vPos,
				mLinePaintTouchPointCircle);
	}

	// ---------------------------------------------------------------------------------------------------

	public void trackballClicked() {
		mUIMode = (mUIMode + 1) % 3;
		invalidate();
	}

	// ---------------------------------------------------------------------------------------------------

	/** Pass touch events to the MT controller */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*if (touch_disabled) {
			multiTouchController.onTouchEvent(event);
			return false;
		}else {*/
			if (mImages.size() > 0) {
				/*if (getDraggableObjectAtPoint(currTouchPoint)==null) {
					Toast.makeText(context, "outside", 1).show();
				}*/
				gestureDetector.onTouchEvent(event);
				
			}
			
		return multiTouchController.onTouchEvent(event);
		//}
		

	}

	SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener()
	{
		private static final int SWIPE_MIN_DISTANCE = 50;
		
		private static final int SWIPE_THRESHOLD_VELOCITY = 500;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
			//	delete.setBackgroundResource(R.drawable.cl_deleteopen);
				//final Img img = multiTouchController.selectedObject;
				final Img img =getDraggableObjectAtPoint(currTouchPoint);
	
				img.deleted = true;
				img.minX = img.displayWidth - 100;
				img.maxX = img.displayWidth;
				img.minY = 0;
				img.maxY = 100;
				
				
				
			/*	cx = e1.getX();
				cy= e1.getY();
*/
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						delete.setBackgroundResource(R.drawable.cl_deleteoff);
						
					}
				}, 100);
				return true;
			}
			return true;
		}

	};

	@SuppressWarnings("deprecation")
	GestureDetector gestureDetector = new GestureDetector(
			simpleOnGestureListener);

	/**
	 * Get the image that is under the single-touch point, or return null
	 * (canceling the drag op) if none
	 */
	public Img getDraggableObjectAtPoint(PointInfo pt) {
		float x = pt.getX(), y = pt.getY();
		int n = mImages.size();
		for (int i = n - 1; i >= 0; i--) {
			Img im = mImages.get(i);
			if (im.containsPoint(x, y))
				
				return im;
		}
		
		return null;
	}

	/**
	 * Select an object for dragging. Called whenever an object is found to be
	 * under the point (non-null is returned by getDraggableObjectAtPoint()) and
	 * a drag operation is starting. Called with null when drag op ends.
	 */
	public void selectObject(Img img, PointInfo touchPoint) {
		currTouchPoint.set(touchPoint);
		if (img != null) {
			// Move image to the top of the stack when selected
			
			mImages.remove(img);
			mImages.add(img);
			img.deleted = false;
	
		} else {
			// Called with img == null when drag stops.
		}
		//invalidate();
	}

	/**
	 * Get the current position and scale of the selected image. Called whenever
	 * a drag starts or is reset.
	 */
	public void getPositionAndScale(Img img, PositionAndScale objPosAndScaleOut) {
		// FIXME affine-izem (and fix the fact that the anisotropic_scale part
		// requires averaging the two scale factors)
		objPosAndScaleOut.set(img.getCenterX(), img.getCenterY(),
				(mUIMode & UI_MODE_ANISOTROPIC_SCALE) == 0,
				(img.getScaleX() + img.getScaleY()) / 2,
				(mUIMode & UI_MODE_ANISOTROPIC_SCALE) != 0, img.getScaleX(),
				img.getScaleY(), (mUIMode & UI_MODE_ROTATE) != 0,
				img.getAngle());
	}

	/** Set the position and scale of the dragged/stretched image. */
	public boolean setPositionAndScale(Img img,
			PositionAndScale newImgPosAndScale, PointInfo touchPoint) {
		currTouchPoint.set(touchPoint);
		boolean ok = img.setPos(newImgPosAndScale, mUIMode, UI_MODE_ANISOTROPIC_SCALE);
		if (ok)
			invalidate();
		return ok;
	}

	@Override
	public void select(Img obj) {
		Toast.makeText(context, "1", 1).show();
		
	}

	
	
}
