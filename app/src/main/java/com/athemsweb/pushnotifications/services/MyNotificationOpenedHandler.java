package com.athemsweb.pushnotifications.services;

import android.content.Intent;

import com.athemsweb.pushnotifications.MainActivity;
import com.athemsweb.pushnotifications.utils.MyApplication;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

    private static String TAG = MyNotificationOpenedHandler.class.getSimpleName();

    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;

        //While sending a Push notification from OneSignal dashboard
        // you can send an addtional data named "activityToBeOpened" and retrieve the value of it and do necessary operation
        //If key is "activityToBeOpened" and value is "AnotherActivity", then when a user clicks
        //on the notification, AnotherActivity will be opened.
        //Else, if we have not set any additional data MainActivity is opened.
       /* if (data != null) {
            Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
            MyApplication.getContext().startActivity(intent);
        }*/

        Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);


    }
}