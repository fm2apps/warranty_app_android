package com.fm2apps.warrantyapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fm2apps.warrantyapp.Helpers.Models.Notification;
import com.fm2apps.warrantyapp.Helpers.Utilities;
import com.fm2apps.warrantyapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heat on 11/7/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ListViewHolder> {
    //ProgressDialog dialog;
    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_expdate;
        TextView tv_name;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_expdate = (TextView) itemView.findViewById(R.id.tv_expdate);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    Context context;
    List<Notification> dataList = new ArrayList<>();
    LayoutInflater inflater;
    //Listener listener;
    //Context con;

    public NotificationsAdapter(Context context) {

        this.context = context;
        //this.con= context;
        inflater = LayoutInflater.from(context);
    }

    public void setLst(List<Notification> lst)
    {
        this.dataList = lst;
    }

    public List<Notification> getLst()
    {
        return this.dataList;
    }

    @Override
    public NotificationsAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.onenotification, parent, false);
        return new NotificationsAdapter.ListViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.ListViewHolder holder, final int position) {
        if (dataList.get(position) != null)
        {
            String timeStamp = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss aaa").format(dataList.get(position).expiryDate);
            String str = String.format("%s warranty will expire on %s", dataList.get(position).productName,
                    timeStamp);
            holder.tv_expdate.setText(String.format("Expiration Date: %s", timeStamp));
            holder.tv_name.setText(str);


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