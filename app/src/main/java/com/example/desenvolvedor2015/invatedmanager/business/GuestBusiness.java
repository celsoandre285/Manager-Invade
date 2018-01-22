package com.example.desenvolvedor2015.invatedmanager.business;

import android.content.Context;

import com.example.desenvolvedor2015.invatedmanager.constants.DataBaseConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestCount;
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



    public boolean update(GuestEntity entityLoad) {

        return this.mGuestRespository.update(entityLoad);

    }

    public List<GuestEntity> getPresent() {
        return this.mGuestRespository.getGuestsPresents();
    }

    public List<GuestEntity> getAbsent() {
        return this.mGuestRespository.getGuestsAbsent();
    }

    public boolean removeItem(int id) {
        return this.mGuestRespository.removeItem(id);
    }

    public GuestCount loadDashBord() {
        return this.mGuestRespository.loadDashBord();
    }
}
