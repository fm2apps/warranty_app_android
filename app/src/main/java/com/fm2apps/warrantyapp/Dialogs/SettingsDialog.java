package com.fm2apps.warrantyapp.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.fm2apps.warrantyapp.CheckCodeActivity;
import com.fm2apps.warrantyapp.Controls.FontButton;
import com.fm2apps.warrantyapp.R;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Helpers.Globals;

/**
 * Created by heat on 8/2/2017.
 */

public class SettingsDialog extends DialogFragment {

    FontButton scan_btn;
    FontButton receipt_btn;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.settings_dialog, null);
        scan_btn = (FontButton)v.findViewById(R.id.scan_btn);
        receipt_btn = (FontButton)v.findViewById(R.id.receipt_btn);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(v);
                //.setTitle("Configuration Settings");

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckCodeActivity.class);
                startActivity(intent);
            }
        });

        receipt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImagePicker(Globals.CAPTURE_RECEIPT_PHOTO_REQUEST_CODE,
                        Globals.ATTATCH_RECEIPT_PHOTO_REQUEST_CODE, Globals.FORM_TYPE + "_" + Globals.PHOTO1_NAME,
                        ((BasePresenterActivity) getActivity())).show();
            }
        });

        return builder.create();
    }
}
