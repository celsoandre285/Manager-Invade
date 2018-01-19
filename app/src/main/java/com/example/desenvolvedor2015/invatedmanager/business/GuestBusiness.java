package com.example.desenvolvedor2015.invatedmanager.business;

import android.content.Context;

import com.example.desenvolvedor2015.invatedmanager.constants.DataBaseConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;
import com.example.desenvolvedor2015.invatedmanager.repository.GuestRespository;

import java.util.List;

/**
 * Created by desenvolvedor on 18/01/18.
 */

public class GuestBusiness {
    private GuestRespository mGuestRespository;

    public GuestBusiness(Context mContext){
        mGuestRespository = GuestRespository.getINSTANCE(mContext);
    }

    public Boolean insert(GuestEntity guestEntity){

        return this.mGuestRespository.insert(guestEntity);
    }

    public List<GuestEntity> getInvated() {
        return this.mGuestRespository.getGuestsByQuery("select * from "+ DataBaseConstants.GUEST.TABLE_NAME);

    }

    public GuestEntity load(int mGuestId) {
        return this.mGuestRespository.load(mGuestId);
    }
}
