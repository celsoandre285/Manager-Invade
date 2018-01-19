package com.example.desenvolvedor2015.invatedmanager.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.activities.GuestFormActivity;
import com.example.desenvolvedor2015.invatedmanager.adapters.GuestListAdapter;
import com.example.desenvolvedor2015.invatedmanager.business.GuestBusiness;
import com.example.desenvolvedor2015.invatedmanager.constants.GuestConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.listener.OnGuestListenerInteractionListener;

import java.util.List;

/**
 * Created by desenvolvedor on 18/01/18.
 */

public class AllInvatedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    private GuestBusiness mGuestBusiness;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all_invated, container, false);

        final Context context = view.getContext();

        //obter o RecycleView
        this.mViewHolder.mRecyclerAllInvated = (RecyclerView) view.findViewById(R.id.rcv_All_Invated);

        this.mGuestBusiness = new GuestBusiness(context);

        OnGuestListenerInteractionListener listener = new OnGuestListenerInteractionListener() {
            @Override
            public void OnListClick(int position) {

                Bundle mBundle = new Bundle();
                mBundle.putInt(GuestConstants.BundleConstants.GUEST_ID, position);
                Intent intent  =  new Intent(context, GuestFormActivity.class);
                intent.putExtras(mBundle);

                startActivity(intent);


            }

            @Override
            public void OnDeleteClick(int position) {

            }
        };

        List<GuestEntity> guestEntityList = this.mGuestBusiness.getInvated();

        //definir o adapter
        GuestListAdapter adapter = new GuestListAdapter(guestEntityList, listener);
        this.mViewHolder.mRecyclerAllInvated.setAdapter(adapter);

        //Definir Layout
        this.mViewHolder.mRecyclerAllInvated.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private static class ViewHolder{
        RecyclerView mRecyclerAllInvated;
    }


}
