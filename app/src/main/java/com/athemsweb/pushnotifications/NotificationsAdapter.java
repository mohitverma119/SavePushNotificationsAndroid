package com.athemsweb.pushnotifications;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    public static final String TAG = "DataViewHolder";
    private Context context;
    private ArrayList<NotificationsModel> notificationsModelList;

    public NotificationsAdapter(Context context, ArrayList<NotificationsModel> notificationsModelList) {
        this.context = context;
        this.notificationsModelList = notificationsModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_layout, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationsModel notificationsModel = notificationsModelList.get(position);
        holder.title_news.setText(notificationsModel.getTitle());
        holder.notif_message.setText(notificationsModel.getMessage());

    }

    @Override
    public int getItemCount() {
        return notificationsModelList == null ? 0 : notificationsModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //Views
        // public NetworkImageView imageView;
        TextView notif_message, title_news;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            title_news = (TextView) itemView.findViewById(R.id.title_news);
            notif_message = (TextView) itemView.findViewById(R.id.notif_message);

        }
    }
}
