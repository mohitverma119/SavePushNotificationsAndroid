package com.athemsweb.pushnotifications;

import io.realm.RealmObject;

public class NotificationsModel extends RealmObject {

    private String title, message;

    public NotificationsModel() {

    }

    public NotificationsModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
