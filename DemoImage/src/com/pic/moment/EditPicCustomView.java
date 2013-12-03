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

import android.app.Dialog;
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

import com.pic.moment.MultiTouchController.MultiTouchObjectCanvas;
import com.pic.moment.MultiTouchController.PointInfo;
import com.pic.moment.MultiTouchController.PositionAndScale;

public class EditPicCustomView extends View implements
		MultiTouchObjectCanvas<ImgEdit> {

	
	public ArrayList<ImgEdit> mImages = new ArrayList<ImgEdit>();
	public ArrayList<ImgEdit> mImages1 = new ArrayList<ImgEdit>();
	public static boolean saveclicked = false;
	private Context context;
	private Resources resources;
	private Dialog dialog;
	private Button back,reset;
	

	public void setBack(Button back) {
		this.back = back;
	}

	public void setReset(Button reset) {
		this.reset = reset;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}



	private MultiTouchController<ImgEdit> multiTouchController/* = new MultiTouchController<Img>(
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

	public EditPicCustomView(Context context) {
		this(context, null);
		this.context = context;
		 multiTouchController = new MultiTouchController<ImgEdit>(
					this,context);
		 saveclicked=false;

	}

	public EditPicCustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		 multiTouchController = new MultiTouchController<ImgEdit>(
					this, context);
		 saveclicked=false;
	}

	public EditPicCustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		 multiTouchController = new MultiTouchController<ImgEdit>(
					this, context);
		init(context);
	}

	private void init(Context context) {
		 
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
	public void loadImages(Context context, Drawable IMAGES,Rect rect,boolean isImage) {
		Resources res = context.getResources();
		ImgEdit img = new ImgEdit(IMAGES, res,isImage);
			     img.setImage(isImage);
			     if (isImage) {
			    	 mImages.clear();
				}
		          mImages.add(img);
		         img.load(res,rect);
		
	
	}

	/**
	 * Called by activity's onPause() method to free memory used for loading the
	 * images
	 */
	public void unloadImages() {
		int n = mImages.size();
		if (n>=1) {
			for (int i = 1; i < n; i++)
				
					 mImages.get(i).unload();
			
		}
		 
		 
	}

	// ---------------------------------------------------------------------------------------------------

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (mImages.size() == 0) {
			/*float spacing = mLinePaintTouchPointCircle.getFontSpacing();
			float totHeight = spacing * infoLines.length;
			for (int i = 0; i < infoLines.length; i++)
				paintText(canvas, infoLines[i],
						(canvas.getHeight() - totHeight) * .5f + i * spacing);
*/
		} else {
			if (saveclicked) {

				for (int i = 0; i < mImages.size(); i++)
					if (!mImages.get(i).isDeleted())

						mImages.get(i).draw(canvas);

			} else {

				for (int i = 0; i < mImages.size(); i++)

					mImages.get(i).draw(canvas);

			}

		}
		invalidate();
		back.bringToFront();
		reset.bringToFront();
		

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

			if (mImages.size() > 0) {
				
			//	gestureDetector.onTouchEvent(event);
				
			}
			
		return multiTouchController.onTouchEvent(event);
		
		

	}

	SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener()
 {
		private static final int SWIPE_MIN_DISTANCE = 50;

		private static final int SWIPE_THRESHOLD_VELOCITY = 500;
		boolean b;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				// delete.setBackgroundResource(R.drawable.cl_deleteopen);
				final ImgEdit img = getDraggableObjectAtPoint(currTouchPoint);
				if (img != null) {
					img.setDeleted(true) ;
					img.setMinX(img.getDisplayWidth() - 100);
					img.setMaxX(img.getDisplayWidth());
					img.setMinY(0);
					img.setMaxY(100);

					/*
					 * cx = e1.getX(); cy= e1.getY();
					 */
					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							

						}
					}, 100);
					b = true;
				} else {
					b = false;
				}

			} else {
				b = false;
			}
			return b;
		}

	};

	@SuppressWarnings("deprecation")
	GestureDetector gestureDetector = new GestureDetector(
			simpleOnGestureListener);

	/**
	 * Get the image that is under the single-touch point, or return null
	 * (canceling the drag op) if none
	 */
	public ImgEdit getDraggableObjectAtPoint(PointInfo pt) {
		float x = pt.getX(), y = pt.getY();
		int n = mImages.size();
		for (int i = n - 1; i >= 0; i--) {
			ImgEdit im = mImages.get(i);
			if (im.containsPoint(x, y) && !im.isImage())
				
				return im;
		}
		
		return null;
	}

	/**
	 * Select an object for dragging. Called whenever an object is found to be
	 * under the point (non-null is returned by getDraggableObjectAtPoint()) and
	 * a drag operation is starting. Called with null when drag op ends.
	 */
	public void selectObject(ImgEdit img, PointInfo touchPoint) {
		currTouchPoint.set(touchPoint);
		if (img != null) {
			// Move image to the top of the stack when selected
			
			mImages.remove(img);
			mImages.add(img);
			img.setDeleted(false);
	
		} else {
			// Called with img == null when drag stops.
		}
		//invalidate();
	}

	/**
	 * Get the current position and scale of the selected image. Called whenever
	 * a drag starts or is reset.
	 */
	public void getPositionAndScale(ImgEdit img, PositionAndScale objPosAndScaleOut) {
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
	public boolean setPositionAndScale(ImgEdit img,
			PositionAndScale newImgPosAndScale, PointInfo touchPoint) {
		currTouchPoint.set(touchPoint);
		boolean ok = img.setPos(newImgPosAndScale, mUIMode, UI_MODE_ANISOTROPIC_SCALE);
		if (ok)
			invalidate();
		return ok;
	}

	@Override
	public void shoeAddDialog(PointInfo mCurrPt) {
		/*Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();
    	wlp.height=LayoutParams.WRAP_CONTENT;
		wlp.width=LayoutParams.WRAP_CONTENT;
		
		int height=window.getDecorView().getHeight()/2;
		int width=window.getDecorView().getWidth()/2;
		if (height==0 && width == 0) {
			wlp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
		}else {
			wlp.gravity = Gravity.TOP | Gravity.LEFT;
			wlp.x=(int) mCurrPt.getX()-width;
			wlp.y=(int) mCurrPt.getY()-height;	
		}
		
		wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		window.setAttributes(wlp);
		dialog.show();*/
	}

	
	
}
