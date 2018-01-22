package com.example.desenvolvedor2015.invatedmanager.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.listener.OnGuestListenerInteractionListener;


public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;
    private ImageView imgRow;
    private Context mContext;

    public GuestViewHolder(View itemView, Context mContext) {
        super(itemView);

        mTextName = (TextView) itemView.findViewById(R.id.txtName);
        imgRow = itemView.findViewById(R.id.imgGuest);
        this.mContext = mContext;

    }

    public void bindData(final GuestEntity entity, final OnGuestListenerInteractionListener listener) {
        this.mTextName.setText(entity.getName());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnListClick(entity.getId());
            }
        });

        this.mTextName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(mContext)
                        .setTitle("Remocao de convidado")
                        .setMessage("Deseja remover o convidado")
                        .setIcon(R.drawable.ic_menu_camera)
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.OnDeleteClick(entity.getId());
                            }
                        })
                        .setNeutralButton("Nao", null)
                        .show();



                return true;
            }
        });

        this.imgRow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Remocao de convidado")
                        .setMessage("Deseja remover o convidado")
                        .setIcon(R.drawable.ic_menu_camera)
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.OnDeleteClick(entity.getId());
                            }
                        })
                        .setNeutralButton("Nao", null)
                        .show();
            }
        });

    }
}
