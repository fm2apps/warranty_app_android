package com.fm2apps.warrantyapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fm2apps.warrantyapp.Adapters.RequestViewPagerAdapter;
import com.fm2apps.warrantyapp.R;

/**
 * Created by Administrator on 04/07/2016.
 */
public class requestMainFragment extends Fragment {
    private ViewPager _mViewPager;
    private RequestViewPagerAdapter _adapter;
    private ImageView _btn1, _btn2, _btn3;
    public requestMainFragment() {
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        setTab();
        onCircleButtonClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.requestpager_fragment, container, false);
    }

    private void onCircleButtonClick() {

        _btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _btn1.setImageResource(R.drawable.paging3_selected);
                _btn2.setImageResource(R.drawable.paging2_selected2);
                _btn3.setImageResource(R.drawable.paging1_selected);
                _btn1.setImageResource(R.drawable.paging3_selected);
                _mViewPager.setCurrentItem(0);
            }
        });

        _btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btn1.setImageResource(R.drawable.paging3_selected);
                _btn3.setImageResource(R.drawable.paging1);
                _btn2.setImageResource(R.drawable.paging2_selected2);
                _mViewPager.setCurrentItem(1);
            }
        });
        _btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btn3.setImageResource(R.drawable.paging1);
                _btn2.setImageResource(R.drawable.paging2);
                _mViewPager.setCurrentItem(2);
            }
        });
    }

    private void setUpView() {
        _mViewPager = (ViewPager) getView().findViewById(R.id.requestviewPager);
        _adapter = new RequestViewPagerAdapter(getActivity(), getFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        initButton();
    }

    private void setTab() {
        /*_mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                _btn1.setImageResource(R.drawable.holo_circle);
                _btn2.setImageResource(R.drawable.holo_circle);
                _btn3.setImageResource(R.drawable.holo_circle);
                btnAction(position);
            }

        });*/
        _mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {

            }

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //_btn1.setImageResource(R.drawable.holo_circle);
                //_btn2.setImageResource(R.drawable.holo_circle);
                //_btn3.setImageResource(R.drawable.holo_circle);
                Fragment frag = _adapter.fragments[position];
                if (frag != null && frag instanceof ReqFragment3) {
                    ((ReqFragment3)frag).setWarranty();
                }
                btnAction(position);

            }

        });

    }

    private void btnAction(int action) {
        switch (action) {
            case 0:
//                _btn1.setImageResource(R.drawable.paging3);
//                _btn3.setImageResource(R.drawable.paging1);
//                _btn2.setImageResource(R.drawable.paging2);
                _btn1.setImageResource(R.drawable.paging3_selected);
                _btn3.setImageResource(R.drawable.paging1);
                _btn2.setImageResource(R.drawable.paging2);

                break;

            case 1:
                _btn1.setImageResource(R.drawable.paging3_selected);
                _btn3.setImageResource(R.drawable.paging1);
                _btn2.setImageResource(R.drawable.paging2_selected2);
               // _btn2.setImageResource(R.drawable.fill_circle);

                break;
            case 2:
//                _btn1.setImageResource(R.drawable.paging3_selected);
//                _btn2.setImageResource(R.drawable.paging2_selected2);
//                _btn3.setImageResource(R.drawable.paging1_selected);

                _btn1.setImageResource(R.drawable.paging3_selected);
                _btn2.setImageResource(R.drawable.paging2_selected2);
                _btn3.setImageResource(R.drawable.paging1_selected);
                break;
        }
    }

    private void initButton() {
        _btn1 = (ImageView) getView().findViewById(R.id.btn1);
        //_btn1.setImageResource(R.drawable.fill_circle);
        _btn2 = (ImageView) getView().findViewById(R.id.btn2);
        _btn3 = (ImageView) getView().findViewById(R.id.btn3);

    }

    private void setButton(Button btn, String text, int h, int w) {
        btn.setWidth(w);
        btn.setHeight(h);
        btn.setText(text);
    }
}