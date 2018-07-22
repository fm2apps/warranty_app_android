package com.fm2apps.warrantyapp.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.R;
import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 04/07/2016.
 */
public class ReqFragment2 extends Fragment {

    public ReqFragment2() {
        // Required empty public constructor
    }

    CustomCalendarView calendarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.req2_fragment, container, false);
        Button button_next = rootView.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager _mViewPager = (ViewPager)getActivity().findViewById(R.id.requestviewPager);
                _mViewPager.setCurrentItem(2);
//
//
//                Fragment currentFragment = getFragmentManager().findFragmentByTag("FRAGMENT");
//                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
//                fragTransaction.detach(currentFragment);
//                fragTransaction.attach(currentFragment);
//                fragTransaction.commit();
            }
        });

        calendarView = (CustomCalendarView)rootView.findViewById(R.id.calendar_view);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                if(Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setExpiryDate(date);
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }



    @Override
    public void onDestroy() {
        System.gc();
        super.onDestroy();
    }

}
