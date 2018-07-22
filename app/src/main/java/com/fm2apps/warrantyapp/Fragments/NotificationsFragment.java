package com.fm2apps.warrantyapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fm2apps.warrantyapp.Adapters.NotificationsAdapter;
import com.fm2apps.warrantyapp.Adapters.ProductsAdapter;
import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.Notification;
import com.fm2apps.warrantyapp.Helpers.Models.Product;
import com.fm2apps.warrantyapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heat on 3/12/2017.
 */

public class NotificationsFragment extends Fragment {

    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.products_fragment, container, false);
        rv = (RecyclerView)rootView.findViewById(R.id.products_list);
        progressBar = (ProgressBar) rootView.findViewById(R.id.main_progress);
        assert rv != null;

        linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());


        Query queryRef = ((BasePresenterActivity)getActivity()).notifications_ref.orderByKey();
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Notification> notifications = new ArrayList<Notification>();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Notification notification = singleSnapshot.getValue(Notification.class);
                    if (notification.userId.equals(Globals.loggedUser.Id))
                        notifications.add(notification);
                }

                NotificationsAdapter notificationsAdapter = new NotificationsAdapter(getActivity());
                notificationsAdapter.setLst(notifications);
                rv.setAdapter(notificationsAdapter);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });

        return rootView;
    }
}