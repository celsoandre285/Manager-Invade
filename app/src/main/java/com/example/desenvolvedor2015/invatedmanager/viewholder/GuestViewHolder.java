package com.example.desenvolvedor2015.invatedmanager.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.listener.OnGuestListenerInteractionListener;


public class GuestViewHolder extends RecyclerView.ViewHolder {

    TextView mTextName;

    public GuestViewHolder(View itemView) {
        super(itemView);

        mTextName = (TextView) itemView.findViewById(R.id.txtName);

    }

    public void bindData(final GuestEntity entity, final OnGuestListenerInteractionListener listener) {
        this.mTextName.setText(entity.getName());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnListClick(entity.getId());
            }
        });

    }
}
