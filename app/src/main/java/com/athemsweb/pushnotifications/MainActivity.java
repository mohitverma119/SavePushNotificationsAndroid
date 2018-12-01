package com.athemsweb.pushnotifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.athemsweb.pushnotifications.helper.RealmHelper;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private Realm realm;
    private RealmChangeListener realmChangeListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter<NotificationsAdapter.ViewHolder> adapter;
    private DividerItemDecoration mDividerItemDecoration;
    private RealmHelper helper;
    private ImageView noNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noNotifications = (ImageView) findViewById(R.id.noNotifications);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotifications);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        //SETTING UP REALM

        realm = Realm.getDefaultInstance();

        helper = new RealmHelper(realm);

        helper.retrieveNotifications();

        checkNoNotifications();


        adapter = new NotificationsAdapter(MainActivity.this, helper.refreshNotifications());
        recyclerView.setAdapter(adapter);

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {

                checkNoNotifications();
                adapter = new NotificationsAdapter(MainActivity.this, helper.refreshNotifications());
                recyclerView.setAdapter(adapter);
            }
        };

        realm.addChangeListener(realmChangeListener);
    }

    public void clearNotifications() {

        checkNoNotifications();
        RealmHelper helper = new RealmHelper(realm);
        helper.clearNotifications();
    }

    private void checkNoNotifications() {
        if (!helper.hasNotifications()) {
            noNotifications.setVisibility(View.VISIBLE);
        } else {
            noNotifications.setVisibility(View.GONE);
        }

    }

}
