package com.example.desenvolvedor2015.invatedmanager.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


public class PresentFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context mContext = getContext();

        View view = inflater.inflate(R.layout.fragment_present, container, false);

        this.mGuestBusiness = new GuestBusiness(mContext);
        List<GuestEntity> guestEntityList =this.mGuestBusiness.getPresent();

        //
        this.mViewHolder.mRecyclePresent = (RecyclerView) view.findViewById(R.id.rcv_present);

        OnGuestListenerInteractionListener listener = new OnGuestListenerInteractionListener() {
            @Override
            public void OnListClick(int position) {

                Bundle mBundle = new Bundle();
                mBundle.putInt(GuestConstants.BundleConstants.GUEST_ID, position);
                Intent intent  =  new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(mBundle);

                startActivity(intent);


            }

            @Override
            public void OnDeleteClick(int position) {

            }
        };

        //
        GuestListAdapter adapter = new GuestListAdapter(guestEntityList, listener);
        this.mViewHolder.mRecyclePresent.setAdapter(adapter);

        //
        this.mViewHolder.mRecyclePresent.setLayoutManager(new LinearLayoutManager(mContext));

        return view;
    }

    private static class ViewHolder{
        RecyclerView mRecyclePresent;
    }


}
