package com.fm2apps.warrantyapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.LoginActivity;
import com.fm2apps.warrantyapp.MyProductsActivity;
import com.fm2apps.warrantyapp.R;

import java.text.SimpleDateFormat;

import com.fm2apps.warrantyapp.Step1Activity;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.Product;
import com.fm2apps.warrantyapp.Helpers.Utilities;

public class ReqFragment3 extends Fragment {

    public ReqFragment3() {

    }

    Activity activity;
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
    }

    TextView warranty_txt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.req3_fragment, container, false);
        if(Globals.productModel != null)
        {
            TextView name_txt = rootView.findViewById(R.id.name_txt);
            name_txt.setText(Globals.productModel.getName());
            TextView desc_txt = rootView.findViewById(R.id.desc_txt);
            desc_txt.setText(Globals.productModel.getDesc());
            final TextView code_txt = rootView.findViewById(R.id.code_txt);
            Button send_btn = (Button)rootView.findViewById(R.id.send_btn);
            send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ViewPager _mViewPager = (ViewPager)getActivity().findViewById(R.id.requestviewPager);
                    if (Globals.productModel.getName() == null)
                    {
                        Toast.makeText(getActivity(), R.string.productname_required, Toast.LENGTH_LONG).show();
                        _mViewPager.setCurrentItem(0);
                        return;
                    }

                    if (Globals.productModel.getDesc() == null)
                    {
                        Toast.makeText(getActivity(), R.string.productdesc_required, Toast.LENGTH_LONG).show();
                        _mViewPager.setCurrentItem(0);
                        return;
                    }

                    if (Globals.productModel.getExpiryDate() == null)
                    {
                        Toast.makeText(getActivity(), R.string.productexpdate_required, Toast.LENGTH_LONG).show();
                        _mViewPager.setCurrentItem(1);
                        return;
                    }

                    if (Globals.productModel.getImage64() == null)
                    {
                        Toast.makeText(getActivity(), R.string.productimg_required, Toast.LENGTH_LONG).show();
                        _mViewPager.setCurrentItem(0);
                        return;
                    }

                    String productId = ((Step1Activity)getActivity()).products_ref.push().getKey();
                    Product product = new Product(Globals.productModel.getBarCode(), Globals.productModel.getExpiryDate()
                    , Globals.productModel.getName(), productId, Globals.productModel.getDesc()
                            ,Globals.productModel.getImage64(), Globals.productModel.getReceipt64()
                            , Globals.productModel.getDateCreated(), Globals.loggedUser.Id);
                    ((Step1Activity)getActivity()).products_ref.child(productId).setValue(product);

                    Toast.makeText(getActivity(), R.string.saved, Toast.LENGTH_LONG).show();

                    Globals.productModel = new ProductModel();

                    Intent intent = new Intent(getActivity(), MyProductsActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            code_txt.setText(Globals.productModel.getBarCode());
            warranty_txt = rootView.findViewById(R.id.warranty_txt);
            if (Globals.productModel.getExpiryDate() != null)
                warranty_txt.setText(df.format(Globals.productModel.getExpiryDate()));
            ImageView product_img = rootView.findViewById(R.id.product_img);

            if (Globals.productModel.getImage64() != null)
            {
                Bitmap decodedByte = Utilities.DecodeToBitmap(Globals.productModel.getImage64());
                product_img.setImageBitmap(decodedByte);
            }
        }

        return rootView;
    }

    public void setWarranty()
    {
        if (Globals.productModel != null)
        {
            if (Globals.productModel.getExpiryDate() != null)
                warranty_txt.setText(df.format(Globals.productModel.getExpiryDate()));
        }
    }

}
