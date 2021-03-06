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
package de.jandrotek.android.aspectra.plottest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by jan on 04.08.15.
 */
public class ButtonHolderFragment extends Fragment {

    OnButtonClickListener mCallback;

    private Button mButtonMoveLeft;
    private Button mButtonMoveRight;
    private Button mButtonStretch;
    private Button mButtonSqueeze;
    private Button mButtonSingle;
    private Button mButtonAddPlot;
    private Button mButtonClearPlot;
    private Button mButtonAutoPlot;

    public static final int eButtonMoveLeft = 0;
    public static final int eButtonMoveRight = 1;
    public static final int eButtonStretch = 2;
    public static final int eButtonSqueeze = 3;
    public static final int eButtonSingle = 4;
    public static final int eButtonAdd = 5;
    public static final int eButtonClear = 6;
    public static final int eButtonAuto = 7;

    PlotTestModelController mController = null;

    public static ButtonHolderFragment newInstance(MainActivity context) {
        ButtonHolderFragment  fragment = new ButtonHolderFragment();


        fragment.mCallback = (OnButtonClickListener)context;
        fragment.mController = context.mModelController;
        return fragment;
    }

    public ButtonHolderFragment() {
    }

    public interface OnButtonClickListener {
    // TODO: Update argument type and name
    void onButtonClickListener(int _buttonId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View masterView = inflater.inflate(R.layout.fragment_button_holder, container, false);

        mButtonMoveLeft = (Button)masterView.findViewById(R.id.buttonMoveLeft);
        mButtonMoveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mCallback.onButtonClickListener(eButtonMoveLeft);
            }
        });

        mButtonMoveRight = (Button)masterView.findViewById(R.id.buttonMoveRight);
        mButtonMoveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mCallback.onButtonClickListener(eButtonMoveRight);
            }
        });

        mButtonStretch = (Button)masterView.findViewById(R.id.buttonStretch);
        mButtonStretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mCallback.onButtonClickListener(eButtonStretch);
            }
        });

        mButtonSqueeze = (Button)masterView.findViewById(R.id.buttonSqueeze);
        mButtonSqueeze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mCallback.onButtonClickListener(eButtonSqueeze);
            }
        });

        mButtonSingle = (Button)masterView.findViewById(R.id.bt_single);
        mButtonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onButtonClickListener(eButtonSingle);
            }
        });
        mButtonAddPlot = (Button)masterView.findViewById(R.id.bt_add);
        mButtonAddPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onButtonClickListener(eButtonAdd);
            }
        });
        mButtonClearPlot = (Button)masterView.findViewById(R.id.bt_clear);
        mButtonClearPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onButtonClickListener(eButtonClear);
            }
        });
        mButtonAutoPlot = (Button)masterView.findViewById(R.id.bt_auto);
        mButtonAutoPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onButtonClickListener(eButtonAuto);
            }
        });

        return masterView;
    }

}
