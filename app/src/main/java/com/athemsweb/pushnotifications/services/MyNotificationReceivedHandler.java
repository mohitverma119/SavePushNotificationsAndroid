package com.athemsweb.pushnotifications.services;

import android.util.Log;

import com.athemsweb.pushnotifications.NotificationsModel;
import com.athemsweb.pushnotifications.helper.RealmHelper;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import io.realm.Realm;


public class MyNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    private static String TAG = MyNotificationReceivedHandler.class.getSimpleName();
    private Realm realm;
    private RealmHelper helper;

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        Log.i(TAG, "notificationReceived: " + data);
        String customKey;

        realm = Realm.getDefaultInstance();

        NotificationsModel notificationsModel = new NotificationsModel();
        helper = new RealmHelper(realm);


        String title, message;

        if (notification.payload.title == null) {
            title = "PushNotificationDemo";
        } else {
            title = notification.payload.title;
        }
        if (notification.payload.body == null) {
            message = "";
        } else {
            message = notification.payload.body;
        }


        notificationsModel.setTitle(title);
        notificationsModel.setMessage(message);
        helper.saveNotification(notificationsModel);


        if (data != null) {
            //While sending a Push notification from OneSignal dashboard
            // you can send an addtional data named "customkey" and retrieve the value of it and do necessary operation
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }
    }
}