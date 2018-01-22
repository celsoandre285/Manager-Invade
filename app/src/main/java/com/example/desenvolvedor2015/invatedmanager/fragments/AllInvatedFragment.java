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
import android.widget.TextView;
import android.widget.Toast;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.activities.GuestFormActivity;
import com.example.desenvolvedor2015.invatedmanager.adapters.GuestListAdapter;
import com.example.desenvolvedor2015.invatedmanager.business.GuestBusiness;
import com.example.desenvolvedor2015.invatedmanager.constants.GuestConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestCount;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.listener.OnGuestListenerInteractionListener;

import java.util.List;



public class AllInvatedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    private GuestBusiness mGuestBusiness;
    private GuestListAdapter adapter;
    private OnGuestListenerInteractionListener listener;


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

        //elementos
        this.mViewHolder.allInvated = view.findViewById(R.id.txt_all_invated);
        this.mViewHolder.absentCount = view.findViewById(R.id.txt_absent_count);
        this.mViewHolder.presentsCount = view.findViewById(R.id.txt_present_count);

        //obter o RecycleView
        this.mViewHolder.mRecyclerAllInvated = (RecyclerView) view.findViewById(R.id.rcv_All_Invated);

        this.mGuestBusiness = new GuestBusiness(getContext());

         listener = new OnGuestListenerInteractionListener() {
            @Override
            public void OnListClick(int id) {

                Bundle mBundle = new Bundle();
                mBundle.putInt(GuestConstants.BundleConstants.GUEST_ID, id);
                Intent intent  =  new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(mBundle);

                startActivity(intent);


            }

            @Override
            public void OnDeleteClick(int id) {
                if(mGuestBusiness.removeItem(id)){

                    loadDashBord();
                    loadGuests();

                    Toast.makeText(getContext(), "Item removido com sucesso!", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getContext(), "Item nao removido!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void OnClickImage(int id) {
                Toast.makeText(getContext(), "Test click na imagem", Toast.LENGTH_SHORT).show();
            }
        };



        //Definir Layout
        this.mViewHolder.mRecyclerAllInvated.setLayoutManager(new LinearLayoutManager(getContext()));

      




        return view;
    }

    private void loadDashBord() {
        GuestCount guestCount = this.mGuestBusiness.loadDashBord();

        this.mViewHolder.allInvated.setText(String.valueOf(guestCount.getAllInvatedCount()));
        this.mViewHolder.presentsCount.setText(String.valueOf(guestCount.getPresentCount()));
        this.mViewHolder.absentCount.setText(String.valueOf(guestCount.getAbsentCount()));
    }


    @Override
    public void onResume() {
        super.onResume();

        this.loadDashBord();
        
        this.loadGuests();



    }

    private void loadGuests() {

        List<GuestEntity> guestEntityList = this.mGuestBusiness.getInvated();

        //definir o adapter
        adapter = new GuestListAdapter(guestEntityList, listener);
        this.mViewHolder.mRecyclerAllInvated.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private static class ViewHolder{
        RecyclerView mRecyclerAllInvated;
        TextView allInvated;
        TextView absentCount;
        TextView presentsCount;
    }
}
