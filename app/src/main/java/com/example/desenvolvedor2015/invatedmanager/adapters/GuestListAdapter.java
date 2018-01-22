package com.example.desenvolvedor2015.invatedmanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.listener.OnGuestListenerInteractionListener;
import com.example.desenvolvedor2015.invatedmanager.viewholder.GuestViewHolder;

import java.util.List;


public class GuestListAdapter extends RecyclerView.Adapter<GuestViewHolder> {

    private List<GuestEntity>entityList;
    private OnGuestListenerInteractionListener listener;

    public GuestListAdapter(List<GuestEntity>entityList, OnGuestListenerInteractionListener listener){
        this.entityList = entityList;
        this.listener = listener;

    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context mContext = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_guest, parent, false);


        return new GuestViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        GuestEntity entity = entityList.get(position);

        holder.bindData(entity, listener);

    }

    @Override
    public int getItemCount() {
        return this.entityList.size();
    }
}
