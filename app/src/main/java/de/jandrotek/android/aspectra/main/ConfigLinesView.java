/**
 * This file is part of Aspectra.
 *
 * Aspectra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Aspectra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aspectra.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 *
 * Copyright Jan Debiec
 */
package de.jandrotek.android.aspectra.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import de.jandrotek.android.aspectra.core.ConfigViewSettings;

//import static de.jandrotek.android.aspectra.core.ConfigViewSettings;

/**
 * Created by jan on 12.01.15.
 * changed 30.05.2016
 * we have dimensions and settings :
 * X : spectrum length, Y spectrum width
 * and
 * width: camera longer side, height: camera shorter side.
 * For SpectrumLanscapeOrientation:
 * X == width, Y == height
 * For SpectrumPortraitOrientation
 * X == height, Y = width
 */
public class ConfigLinesView extends View {
    private static final String TAG = "ConfigView";

    public void setSpectrumOrientationLandscape(boolean spectrumOrientationLandscape) {
        mSpectrumOrientationLandscape = spectrumOrientationLandscape;
        initializeLines();
    }

    private boolean mSpectrumOrientationLandscape = true;
    private ConfigViewSettings mViewSettings = null;

    private float mConfigWidthX;
    private float mConfigHeightY;


    private float[] mCrossPointsW = new float[4];
    private float[] mCrossPointsH = new float[4];

    private final Paint mLinePaint0 = new Paint();
    private final Paint mLinePaint1 = new Paint();
    private final Paint mLinePaint2 = new Paint();
    private final Paint mLinePaint3 = new Paint();
    private final Paint mLinePaint4 = new Paint();
    private final Paint mLinePaint5 = new Paint();
    private final Paint mLinePaint6 = new Paint();
    private final Paint mLinePaint7 = new Paint();
    private final Paint mLinePaint8 = new Paint();

    private final Path mPath0 = new Path();
    private final Path mPath1 = new Path();
    private final Path mPath2 = new Path();
    private final Path mPath3 = new Path();
    private final Path mPath4 = new Path();
    private final Path mPath5 = new Path();
    private final Path mPath6 = new Path();
    private final Path mPath7 = new Path();
    private final Path mPath8 = new Path();

    public ConfigLinesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mViewSettings = ConfigViewSettings.getInstance();
        initialize();
    }

    public ConfigLinesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewSettings = ConfigViewSettings.getInstance();
        initialize(); // here we get
    }

    public ConfigLinesView(Context context) {
        super(context);
        mViewSettings = ConfigViewSettings.getInstance();
        initialize(); // here too
    }


    @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            //here you have the size of the view and you can do stuff
        mConfigHeightY = b - t;
        mConfigWidthX = r - l;
        if(mConfigWidthX > 0){
            if(BuildConfig.DEBUG) {
                Log.d(TAG, "width = " + mConfigWidthX + ", height = " + mConfigHeightY);
            }
            setConfigViewDimensions(mConfigWidthX, mConfigHeightY);
            mViewSettings.calcCrossPoints();
            initializeLines();

        }
    }

    public void setPreviewDimensions(int widthX, int heightY){
        //TODO hier is bug, these values are original camera values, not modified to view values
//        mViewSettings.setCameraPreviewDimensions(widthX, heightY);


//        mViewSettings.setSpectrumOrientationLandscape(mSpectrumOrientationLandscape);
        mViewSettings.calcCrossPoints();

        initializeLines();
    }

    private void setConfigViewDimensions(float widthX, float heightY) {
        mViewSettings.setConfigViewDimensions(widthX, heightY);
//        mViewSettings.setSpectrumOrientationLandscape(mSpectrumOrientationLandscape);
        initializeLines();
    }

    private void initialize() {

        mLinePaint0.setAntiAlias(true);
        mLinePaint0.setColor(Color.GREEN);
        mLinePaint0.setStrokeWidth(5);
        mLinePaint0.setStyle(Paint.Style.STROKE);

        mLinePaint1.setAntiAlias(true);
        mLinePaint1.setColor(Color.GREEN);
        mLinePaint1.setStrokeWidth(5);
        mLinePaint1.setStyle(Paint.Style.STROKE);

        mLinePaint2.setAntiAlias(true);
        mLinePaint2.setColor(Color.GREEN);
        mLinePaint2.setStrokeWidth(5);
        mLinePaint2.setStyle(Paint.Style.STROKE);

        mLinePaint3.setAntiAlias(true);
        mLinePaint3.setColor(Color.GREEN);
        mLinePaint3.setStrokeWidth(5);
        mLinePaint3.setStyle(Paint.Style.STROKE);

        mLinePaint4.setAntiAlias(true);
        mLinePaint4.setColor(Color.GREEN);
        mLinePaint4.setStrokeWidth(5);
        mLinePaint4.setStyle(Paint.Style.STROKE);

        mLinePaint5.setAntiAlias(true);
        mLinePaint5.setColor(Color.GREEN);
        mLinePaint5.setStrokeWidth(5);
        mLinePaint5.setStyle(Paint.Style.STROKE);

        mLinePaint6.setAntiAlias(true);
        mLinePaint6.setColor(Color.GREEN);
        mLinePaint6.setStrokeWidth(5);
        mLinePaint6.setStyle(Paint.Style.STROKE);

        mLinePaint7.setAntiAlias(true);
        mLinePaint7.setColor(Color.GREEN);
        mLinePaint7.setStrokeWidth(5);
        mLinePaint7.setStyle(Paint.Style.STROKE);

        mLinePaint8.setAntiAlias(true);
        mLinePaint8.setColor(Color.RED);
        mLinePaint8.setStrokeWidth(5);
        mLinePaint8.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        boolean newCrossPoints = mViewSettings.isNewCrossPoints();
        if (newCrossPoints) {
            initializeLines();
            mViewSettings.setNewCrossPoints(false);
        }
        canvas.drawPath(mPath0, mLinePaint0);
        canvas.drawPath(mPath1, mLinePaint1);
        canvas.drawPath(mPath2, mLinePaint2);
        canvas.drawPath(mPath3, mLinePaint3);
        canvas.drawPath(mPath4, mLinePaint4);
        canvas.drawPath(mPath5, mLinePaint5);
        canvas.drawPath(mPath6, mLinePaint6);
        canvas.drawPath(mPath7, mLinePaint7);
        canvas.drawPath(mPath8, mLinePaint8);
    }

    public void setPercent(float widthStartX, float widthEndX, float heightStartY, float deltaLinesY) {
        mViewSettings.setPercent(widthStartX, widthEndX, heightStartY,  deltaLinesY);
            initializeLines();
    }

    public void initializeLines() {
        if(mViewSettings.isConfigured()){
            mCrossPointsW = mViewSettings.getPointsW();
            mCrossPointsH = mViewSettings.getPointsH();
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "mCrossPointsW[1] = " + mCrossPointsW[1] + ", mCrossPointsW[2] = " + mCrossPointsW[2]);
                Log.d(TAG, "mCrossPointsH[1] = " + mCrossPointsH[1] + ", mCrossPointsH[2] = " + mCrossPointsH[2]);
            }

            mPath0.reset();
            mPath1.reset();
            mPath2.reset();
            mPath3.reset();
            mPath4.reset();
            mPath5.reset();
            mPath6.reset();
            mPath7.reset();
            mPath8.reset();


            mPath0.moveTo(mCrossPointsW[0], mCrossPointsH[1]);
            mPath0.lineTo(mCrossPointsW[1], mCrossPointsH[1]);

            mPath1.moveTo(mCrossPointsW[2], mCrossPointsH[1]);
            mPath1.lineTo(mCrossPointsW[3], mCrossPointsH[1]);

            mPath2.moveTo(mCrossPointsW[0], mCrossPointsH[2]);
            mPath2.lineTo(mCrossPointsW[1], mCrossPointsH[2]);

            mPath3.moveTo(mCrossPointsW[2], mCrossPointsH[2]);
            mPath3.lineTo(mCrossPointsW[3], mCrossPointsH[2]);

            mPath4.moveTo(mCrossPointsW[1], mCrossPointsH[0]);
            mPath4.lineTo(mCrossPointsW[1], mCrossPointsH[1]);

            mPath5.moveTo(mCrossPointsW[1], mCrossPointsH[2]);
            mPath5.lineTo(mCrossPointsW[1], mCrossPointsH[3]);

            mPath6.moveTo(mCrossPointsW[2], mCrossPointsH[0]);
            mPath6.lineTo(mCrossPointsW[2], mCrossPointsH[1]);

            mPath7.moveTo(mCrossPointsW[2], mCrossPointsH[2]);
            mPath7.lineTo(mCrossPointsW[2], mCrossPointsH[3]);

            mPath8.moveTo(mCrossPointsW[1], mCrossPointsH[1]);
            mPath8.lineTo(mCrossPointsW[2], mCrossPointsH[1]);
            mPath8.lineTo(mCrossPointsW[2], mCrossPointsH[2]);
            mPath8.lineTo(mCrossPointsW[1], mCrossPointsH[2]);
            mPath8.close();

            invalidate();
        }
    }
}
