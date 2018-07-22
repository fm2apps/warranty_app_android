package com.fm2apps.warrantyapp.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.fm2apps.warrantyapp.Applications.onlineApp;
import com.fm2apps.warrantyapp.BaseActivities.BaseActivity;
import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Dialogs.SettingsDialog;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.Notification;
import com.fm2apps.warrantyapp.Helpers.Models.Product;
import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.Helpers.Models.User;
import com.fm2apps.warrantyapp.Helpers.Utilities;
import com.fm2apps.warrantyapp.MainActivity;
import com.fm2apps.warrantyapp.MyProductsActivity;
import com.fm2apps.warrantyapp.R;
import com.fm2apps.warrantyapp.RealmStorage.RealmController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import io.realm.RealmResults;

import static com.fm2apps.warrantyapp.R.id.email_txt;
import static com.fm2apps.warrantyapp.R.id.name_txt;
import static com.fm2apps.warrantyapp.R.id.number_txt;

/**
 * Created by heat on 11/9/2017.
 */

public class ReminderService extends Service {

    //Boolean exists = false;
    Timer t;
    private final IBinder iBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      if (((BasePresenterActivity)((onlineApp)getApplicationContext()).getCurrentActivity()) != null) {
                                          Query queryRef = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).products_ref.orderByKey();
                                          queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(DataSnapshot dataSnapshot) {
                                                  for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                                                      final Product product = singleSnapshot.getValue(Product.class);
                                                      if (Globals.loggedUser != null)
                                                      {
                                                          if (product.userId.equals(Globals.loggedUser.Id)) {





//
//
//                                                              try {
//                                                                  Query queryRefNotif = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref
//                                                                          .orderByChild("userId").equalTo(Globals.loggedUser.Id);
//
//
//                                                                  queryRefNotif.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                                      @Override
//                                                                      public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                                                          Query queryRefNotif2 = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.orderByChild("productId").equalTo(product.Id);
//                                                                          queryRefNotif2.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                                              @Override
//                                                                              public void onDataChange(DataSnapshot dataSnapshot2) {
//                                                                                  if (dataSnapshot2.exists()) {
//                                                                                      Log.v("c", "All Exit");
//
//                                                                                      for (DataSnapshot singleSnapshotNotif : dataSnapshot2.getChildren()) {
//                                                                                          Notification notification = singleSnapshotNotif.getValue(Notification.class);
//
//                                                                                          String notificationId = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.push().getKey();
//                                                                                          Notification notificationNew = new Notification(notificationId, product.Id, Globals.loggedUser.Id, Globals.loggedUser.name, product.name, new Date(), product.expiryDate);
//
//                                                                                          if (!notificationNew.productId.equals(notification.productId) && !notificationNew.userId.equals(notification.userId))
//                                                                                          {
//                                                                                              setNotificationBuilder(product);
//                                                                                              ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.child(notificationId).setValue(notificationNew);
//                                                                                          }
//                                                                                      }
//                                                                                  }
//                                                                                  else
//                                                                                  {
//                                                                                      String notificationId = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.push().getKey();
//                                                                                      Notification notificationNew = new Notification(notificationId, product.Id, Globals.loggedUser.Id, Globals.loggedUser.name, product.name, new Date(), product.expiryDate);
//
//                                                                                      setNotificationBuilder(product);
//                                                                                      ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.child(notificationId).setValue(notificationNew);
//                                                                                  }
//                                                                              }
//
//                                                                              @Override
//                                                                              public void onCancelled(DatabaseError databaseError2) {
//
//                                                                              }
//                                                                          });
//
//
//                                                                      }
//
//                                                                      @Override
//                                                                      public void onCancelled(DatabaseError databaseError) {
//
//                                                                      }
//                                                                  });
//
//                                                              }
//                                                              catch (Exception ex)
//                                                              {
//                                                                  String ss = ex.getMessage();
//                                                              }
















                                                              long diff = Utilities.getDifference(Calendar.getInstance().getTime(), product.expiryDate);
                                                              if (diff <= 80 && diff >= 0) {

//


                                                                  try {
                                                                      Query queryRefNotif = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref
                                                                              .orderByChild("userId").equalTo(Globals.loggedUser.Id);


                                                                      queryRefNotif.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                          @Override
                                                                          public void onDataChange(DataSnapshot dataSnapshot) {

                                                                              Query queryRefNotif2 = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.orderByChild("productId").equalTo(product.Id);
                                                                              queryRefNotif2.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                  @Override
                                                                                  public void onDataChange(DataSnapshot dataSnapshot2) {
                                                                                      if (dataSnapshot2.exists()) {
                                                                                          Log.v("c", "All Exit");

                                                                                          for (DataSnapshot singleSnapshotNotif : dataSnapshot2.getChildren()) {
                                                                                              Notification notification = singleSnapshotNotif.getValue(Notification.class);

                                                                                              String notificationId = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.push().getKey();
                                                                                              Notification notificationNew = new Notification(notificationId, product.Id, Globals.loggedUser.Id, product.name, Globals.loggedUser.name, new Date(), product.expiryDate);

                                                                                              if (!notificationNew.productId.equals(notification.productId) && !notificationNew.userId.equals(notification.userId))
                                                                                              {
                                                                                                  setNotificationBuilder(product);
                                                                                                  ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.child(notificationId).setValue(notificationNew);
                                                                                              }
                                                                                          }
                                                                                      }
                                                                                      else
                                                                                      {
                                                                                          String notificationId = ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.push().getKey();
                                                                                          Notification notificationNew = new Notification(notificationId, product.Id, Globals.loggedUser.Id, product.name, Globals.loggedUser.name, new Date(), product.expiryDate);

                                                                                          setNotificationBuilder(product);
                                                                                          ((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()).notifications_ref.child(notificationId).setValue(notificationNew);
                                                                                      }
                                                                                  }

                                                                                  @Override
                                                                                  public void onCancelled(DatabaseError databaseError2) {

                                                                                  }
                                                                              });


                                                                          }

                                                                          @Override
                                                                          public void onCancelled(DatabaseError databaseError) {

                                                                          }
                                                                      });

                                                                  }
                                                                  catch (Exception ex)
                                                                  {
                                                                      String ss = ex.getMessage();
                                                                  }

                                                                  Intent intent = new Intent(((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()), MyProductsActivity.class);
                                                                  PendingIntent pendingIntent = PendingIntent.getActivity(((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()), 0 /* Request code */, intent,
                                                                          PendingIntent.FLAG_ONE_SHOT);
                                                                  Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                                  NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                                                                          .setSmallIcon(R.mipmap.ic_launcher)
                                                                          .setContentTitle(String.format("%s warranty is about to expire", product.name))
                                                                          .setContentText(String.format("%s warranty is about to expire", product.name))
                                                                          .setAutoCancel(true)
                                                                          .setSound(defaultSoundUri)
                                                                          .setContentIntent(pendingIntent);

                                                                  NotificationManager notificationManager =
                                                                          (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                                                                  notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
                                                              }
                                                          }
                                                      }



                                                  }


                                              }

                                              @Override
                                              public void onCancelled(DatabaseError databaseError) {
                                                  //Log.e(TAG, "onCancelled", databaseError.toException());
                                              }
                                          });
                                      }


//                                      RealmResults<ProductModel> realmResults = RealmController.with(ReminderService.this).getProducts();
//
//                                      if (realmResults.size() > 0)
//                                      {
//                                          ProductModel b = realmResults.get(0);
//                                          String title = b.getName();
//                                          SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
//
//                                          long diff = Utilities.getDifference(Calendar.getInstance().getTime(), b.getDateCreated());
//                                          long diff2 = Utilities.getDifference(Calendar.getInstance().getTime(), b.getExpiryDate());
//                                          String date = df.format(b.getDateCreated());
//                                      }


                                  }

                              },
                0,
                60000);
    }

    void setNotificationBuilder(Product product)
    {
        Intent intent = new Intent(((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()), MyProductsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(((BasePresenterActivity) ((onlineApp) getApplicationContext()).getCurrentActivity()), 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(String.format("%s warranty is about to expire", product.name))
                .setContentText(String.format("%s warranty is about to expire", product.name))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public class LocalBinder extends Binder
    {
        public ReminderService getService()
        {
            return ReminderService.this;
        }
    }

}
