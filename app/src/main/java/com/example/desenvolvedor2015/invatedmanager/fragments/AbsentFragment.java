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
import android.widget.Toast;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.activities.GuestFormActivity;
import com.example.desenvolvedor2015.invatedmanager.adapters.GuestListAdapter;
import com.example.desenvolvedor2015.invatedmanager.business.GuestBusiness;
import com.example.desenvolvedor2015.invatedmanager.constants.GuestConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.listener.OnGuestListenerInteractionListener;

import java.util.List;


public class AbsentFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    private GuestBusiness guestBusiness;
    private OnGuestListenerInteractionListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getContext();

        guestBusiness = new GuestBusiness(context);


        View view = inflater.inflate(R.layout.fragment_absent, container, false);

        //
        this.mViewHolder.recyclerAbsent = view.findViewById(R.id.rcv_absent);

        this.listener = new OnGuestListenerInteractionListener() {
            @Override
            public void OnListClick(int position) {

                Bundle mBundle = new Bundle();
                mBundle.putInt(GuestConstants.BundleConstants.GUEST_ID, position);
                Intent intent = new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(mBundle);

                startActivity(intent);


            }

            @Override
            public void OnDeleteClick(int id) {
                if(guestBusiness.removeItem(id)){


                    loadGuests();

                    Toast.makeText(getContext(), "Item removido com sucesso!", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getContext(), "Item nao removido!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void OnClickImage(int id) {

            }
        };


        // setando layout
        this.mViewHolder.recyclerAbsent.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.loadGuests();


    }

    private void loadGuests() {

        List<GuestEntity> list = guestBusiness.getAbsent();

        //setando o adapter
        GuestListAdapter adapter = new GuestListAdapter(list, listener);
        this.mViewHolder.recyclerAbsent.setAdapter(adapter);

    }

    private static class ViewHolder {
        RecyclerView recyclerAbsent;
    }


}
