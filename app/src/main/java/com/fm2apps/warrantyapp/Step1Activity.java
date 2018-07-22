package com.fm2apps.warrantyapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Helpers.Utilities;

public class Step1Activity extends BasePresenterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        Utilities.getPermissions(this);


//        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar);
//        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.txt_title);
//        //mTitle.setText("App1");
//        Typeface khandBold = Typeface.createFromAsset(getApplication().getAssets(), "DroidKufi-Bold.ttf");
//        mTitle.setTypeface(khandBold);

        //customizeToolbar(toolbarTop);

//        final ViewPager _mViewPager = (ViewPager)findViewById(R.id.requestviewPager);
//        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                _mViewPager.getAdapter().notifyDataSetChanged();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    public void customizeToolbar(Toolbar toolbar){
        // Save current title and subtitle
        final CharSequence originalTitle = toolbar.getTitle();
        final CharSequence originalSubtitle = toolbar.getSubtitle();

        // Temporarily modify title and subtitle to help detecting each
        toolbar.setTitle("title");
        toolbar.setSubtitle("subtitle");

        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);

            if(view instanceof TextView){
                TextView textView = (TextView) view;


                if(textView.getText().equals("title")){
                    // Customize title's TextView
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER_HORIZONTAL;
                    textView.setLayoutParams(params);

                    // Apply custom font using the Calligraphy library
                    Typeface typeface = Typeface.createFromAsset(getApplication().getAssets(), "DroidKufi-Bold.ttf");
                    textView.setTypeface(typeface);

                } else if(textView.getText().equals("subtitle")){
                    // Customize subtitle's TextView
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER_HORIZONTAL;
                    textView.setLayoutParams(params);

                    // Apply custom font using the Calligraphy library
                    Typeface typeface = Typeface.createFromAsset(getApplication().getAssets(), "DroidKufi-Bold.ttf");
                    textView.setTypeface(typeface);
                }
            }
        }

        // Restore title and subtitle
        toolbar.setTitle(originalTitle);
        toolbar.setSubtitle(originalSubtitle);
    }
}
