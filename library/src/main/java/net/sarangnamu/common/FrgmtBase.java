/*
 * FrgmtBase.java
 * Copyright 2013 Burke Choi All rights reserved.
 *             http://www.sarangnamu.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sarangnamu.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * @author <a href="mailto:aucd29@gmail.com">Burke Choi</a>
 */
public abstract class FrgmtBase extends Fragment {
    protected ViewGroup mBaseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int id = getLayoutId();
        if (id == 0) {
            mBaseView = makeDefaultView();
        } else {
            mBaseView = inflate(getLayoutId(), container);
        }

        mBaseView.setBackgroundColor(0xffffffff);
        mBaseView.setClickable(true);

        ButterKnife.bind(this, mBaseView);
        initLayout();

        return mBaseView;
    }

    protected int dpToPixelInt(int dp) {
        return DimTool.dpToPixelInt(getActivity(), dp);
    }

    protected float dpToPixel(float dp) {
        return DimTool.dpToPixel(getActivity(), dp);
    }

    protected ViewGroup inflate(@LayoutRes int resid, ViewGroup container) {
        return (ViewGroup) LayoutInflater.from(getContext()).inflate(resid, container, false);
    }

    /**
     * getLayoutId 의 값이 invalid 하면 현재 클래스 명을 가운데로 출력하는 형태로 만들어주자
     * @return
     */
    protected ViewGroup makeDefaultView() {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        layout.setGravity(Gravity.CENTER);

        TextView text = new TextView(getActivity());
        text.setText(getDefalutViewMessage());
        text.setGravity(Gravity.CENTER);

        layout.addView(text);

        return layout;
    }

    protected String getDefalutViewMessage() {
        return getClass().getSimpleName();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // ABSTRACT
    //
    ////////////////////////////////////////////////////////////////////////////////////

    protected abstract int getLayoutId();

    protected abstract void initLayout();
}
