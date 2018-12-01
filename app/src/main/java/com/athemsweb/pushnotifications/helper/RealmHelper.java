package com.athemsweb.pushnotifications.helper;



import com.athemsweb.pushnotifications.NotificationsModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class RealmHelper {

    Realm realm;
    RealmResults<NotificationsModel> notificationsModels;
    Boolean saved;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // SAVE NOTIFICATION

    public boolean saveNotification(final NotificationsModel notificationsModel) {

        if (notificationsModel == null) {
            saved = false;

        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        NotificationsModel model = realm.copyToRealm(notificationsModel);
                        saved = true;
                    } catch (RealmException e) {
                        e.printStackTrace();
                        saved = false;
                    }

                }
            });
        }

        return saved;
    }

    public boolean clearNotifications() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        realm.where(NotificationsModel.class).findAll().deleteAllFromRealm();
       /* realm.beginTransaction();
        realm.where(NotificationsModel.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();*/
        return true;
    }

    //check if Book.class is empty
    public boolean hasNotifications() {

        return !realm.where(NotificationsModel.class).findAll().isEmpty();
    }


    // RETRIEVE NOTIFICATION

    public boolean retrieveNotifications() {
        notificationsModels = realm.where(NotificationsModel.class).findAll();
        return notificationsModels != null;
    }

    public long notificationsCount() {
        return realm.where(NotificationsModel.class).count();
    }

    // REFRESH NOTIFICATION

    public ArrayList<NotificationsModel> refreshNotifications() {

        ArrayList<NotificationsModel> latest = new ArrayList<>();
        for (NotificationsModel model : notificationsModels) {

            latest.add(model);
        }

        return latest;
    }
}
