/*
 * Copyright (c) 2010, Sony Ericsson Mobile Communication AB. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    * Redistributions of source code must retain the above copyright notice, this
 *      list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *    * Neither the name of the Sony Ericsson Mobile Communication AB nor the names
 *      of its contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.pic.moment;

import com.pic.moment.utils.ScalingUtilities;
import com.pic.moment.utils.ScalingUtilities.ScalingLogic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity implementation for decoding and scaling tutorial
 *
 * @author Andreas Agvard (andreas.agvard@sonyericsson.com)
 */
public class ScalingTutorialActivity extends Activity {

    /** Id of image resource to decode */
    private int mSourceId;

    /** Wanted width of decoded image */
    private int mDstWidth;

    /** Wanted height of decoded image */
    private int mDstHeight;

    /** Image view for presenting decoding result */
    private ImageView mImageView;

    /** Text view for presenting decoding statistics */
    private TextView mResultView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      

    }

    /**
     * Invoked when pressing button for showing result of the "Bad" decoding
     * method
     */
    protected void badButtonPressed() {
        final long startTime = SystemClock.uptimeMillis();

        // Part 1: Decode image
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(getResources(), mSourceId);

        // Part 2: Scale image
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap, mDstWidth, mDstHeight, true);
        unscaledBitmap.recycle();

        // Calculate memory usage and performance statistics
        final int memUsageKb = (unscaledBitmap.getRowBytes() * unscaledBitmap.getHeight()) / 1024;
        final long stopTime = SystemClock.uptimeMillis();

        // Publish results
        mResultView.setText("Time taken: " + (stopTime - startTime)
                + " ms. Memory used for scaling: " + memUsageKb + " kb.");
        mImageView.setImageBitmap(scaledBitmap);
    }

    /**
     * Invoked when pressing button for showing result of the "Fit" decoding
     * method
     */
    protected void fitButtonPressed() {
        final long startTime = SystemClock.uptimeMillis();

        // Part 1: Decode image
        Bitmap unscaledBitmap = ScalingUtilities.decodeResource(getResources(), mSourceId,
                mDstWidth, mDstHeight, ScalingLogic.FIT);

        // Part 2: Scale image
        Bitmap scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, mDstWidth,
                mDstHeight, ScalingLogic.FIT);
        unscaledBitmap.recycle();

        // Calculate memory usage and performance statistics
        final int memUsageKb = (unscaledBitmap.getRowBytes() * unscaledBitmap.getHeight()) / 1024;
        final long stopTime = SystemClock.uptimeMillis();

        // Publish results
        mResultView.setText("Time taken: " + (stopTime - startTime)
                + " ms. Memory used for scaling: " + memUsageKb + " kb.");
        mImageView.setImageBitmap(scaledBitmap);
    }

    /**
     * Invoked when pressing button for showing result of the "Crop" decoding
     * method
     */
    protected void cropButtonPressed() {
        final long startTime = SystemClock.uptimeMillis();

        // Part 1: Decode image
        Bitmap unscaledBitmap = ScalingUtilities.decodeResource(getResources(), mSourceId,
                mDstWidth, mDstHeight, ScalingLogic.CROP);

        // Part 2: Scale image
        Bitmap scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, mDstWidth,
                mDstHeight, ScalingLogic.CROP);
        unscaledBitmap.recycle();

        // Calculate memory usage and performance statistics
        final int memUsageKb = (unscaledBitmap.getRowBytes() * unscaledBitmap.getHeight()) / 1024;
        final long stopTime = SystemClock.uptimeMillis();

        // Publish results
        mResultView.setText("Time taken: " + (stopTime - startTime)
                + " ms. Memory used for scaling: " + memUsageKb + " kb.");
        mImageView.setImageBitmap(scaledBitmap);
    }

}
