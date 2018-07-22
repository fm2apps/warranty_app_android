package com.fm2apps.warrantyapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fm2apps.warrantyapp.Helpers.Models.CountryModel;
import com.fm2apps.warrantyapp.R;

import java.util.List;

/**
 * Created by heat on 12/19/2017.
 */

public class CountriesAdapter extends ArrayAdapter<CountryModel> {
    private List<CountryModel> objects;
    private Context context;

    public CountriesAdapter(Context context, int resourceId,
                            List<CountryModel> objects) {
        super(context, resourceId, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView label = (TextView)row.findViewById(R.id.txt1);
        label.setText(objects.get(position).Name);

//        if (position == 0) {//Special style for dropdown header
//            label.setTextColor(context.getResources().getColor(R.color.Red));
//        }
        label.setTextColor(context.getResources().getColor(R.color.grey2));
//        else
//        {
//            label.setTextColor(context.getResources().getColor(R.color.white));
//        }

        return row;
    }
}
