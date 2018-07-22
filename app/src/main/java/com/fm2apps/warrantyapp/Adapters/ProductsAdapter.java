package com.fm2apps.warrantyapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.Product;
import com.fm2apps.warrantyapp.Helpers.Utilities;
import com.fm2apps.warrantyapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by heat on 11/7/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ListViewHolder> {
    //ProgressDialog dialog;
    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_desc;
        TextView tv_expdate;
        TextView tv_code;
        ImageView iv_product;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            tv_expdate = (TextView) itemView.findViewById(R.id.tv_expdate);
            tv_code = (TextView) itemView.findViewById(R.id.tv_code);
            iv_product = (ImageView) itemView.findViewById(R.id.iv_product);
        }
    }

    Context context;
    List<Product> dataList = new ArrayList<>();
    LayoutInflater inflater;
    //Listener listener;
    //Context con;

    public ProductsAdapter(Context context) {

        this.context = context;
        //this.con= context;
        inflater = LayoutInflater.from(context);
    }

    public void setLst(List<Product> lst)
    {
        this.dataList = lst;
    }

    public List<Product> getLst()
    {
        return this.dataList;
    }

    @Override
    public ProductsAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.oneproduct, parent, false);
        return new ProductsAdapter.ListViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.ListViewHolder holder, final int position) {
        if (dataList.get(position) != null)
        {
            Bitmap decodedByte = Utilities.DecodeToBitmap(dataList.get(position).img64);
            holder.iv_product.setImageBitmap(decodedByte);
            //holder.tv_Name.setText(dataList.get(position).Name);
//            String str = String.format("%s%s", Globals.coreImageUrl, dataList.get(position).img64);
//            Picasso.with(context).load(str).placeholder(R.mipmap.ic_launcher).fit().into(holder.iv_product,
//                    new Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//                        @Override
//                        public void onError() {
//
//                        }
//                    });
            if(dataList.get(position).name != null)
            {
                holder.tv_name.setText(String.format("Name: %s", dataList.get(position).name));
                holder.tv_desc.setText(String.format("Description: %s", dataList.get(position).desc));
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(dataList.get(position).expiryDate);
                holder.tv_expdate.setText(String.format("Expiration date: %s", timeStamp));
                holder.tv_code.setText(String.format("Code: %s", dataList.get(position).barCode));
            }

            Utilities.setScaleAnimation(holder.itemView);
        }
    }
    @Override
    public int getItemCount() {
        if(dataList == null)
            return 0;
        return dataList.size();
    }
}