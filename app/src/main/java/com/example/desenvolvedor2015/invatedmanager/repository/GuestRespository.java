package com.example.desenvolvedor2015.invatedmanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desenvolvedor2015.invatedmanager.constants.DataBaseConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;

import java.util.ArrayList;
import java.util.List;


public class GuestRespository {



    private static GuestRespository INSTANCE;
    private GuestDataBaseHelper mGuestDataBaseHelper;

    public final String TAG = "GuestRespository";

    private GuestRespository(Context mContext){
        mGuestDataBaseHelper = new GuestDataBaseHelper(mContext);
    }

    public static synchronized GuestRespository getINSTANCE(Context mContext){

        if (INSTANCE == null){
            INSTANCE = new GuestRespository(mContext);
        }

        return INSTANCE;
    }


    public Boolean insert(GuestEntity guestEntity) {
        try {
            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getReadableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guestEntity.getName());
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guestEntity.getConfirmed());

            sqLiteDatabase.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues);

            Log.i(TAG, "insert: Salvo com sucesso" );

            return true;


        }catch (Exception e){
            Log.e(TAG, "insert: "+ e.getMessage() );
            e.printStackTrace();

            return false;
        }
    }


    public List<GuestEntity> getGuestsByQuery(String query) {
        List<GuestEntity>list = new ArrayList<>();

        try{
            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null && cursor.getCount()>0) {

                while (cursor.moveToNext()) {
                    GuestEntity entity = new GuestEntity();
                    entity.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)));
                    entity.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)));
                    entity.setConfirmed(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)));

                    list.add(entity);

                }
            }

            if (cursor != null){
                cursor.close();
            }

        }catch (Exception e){
            e.printStackTrace();
            return list;
        }

        return list;
    }

    public GuestEntity load(int mGuestId) {
        GuestEntity entity = new GuestEntity();

        try {

            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getReadableDatabase();

            String tableName = DataBaseConstants.GUEST.TABLE_NAME;

            String[] projection = {
                    DataBaseConstants.GUEST.COLUMNS.ID,
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE
            };

            String selection = DataBaseConstants.GUEST.COLUMNS.ID + " + ? ";
            String[] selectionArgs = {String.valueOf(mGuestId)};

            Cursor cursor = sqLiteDatabase.query(tableName, projection, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0 ) {

                cursor.moveToFirst();

                entity.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)));
                entity.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)));
                entity.setConfirmed(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)));
            }

            if (cursor != null){
                cursor.close();
            }

            return entity;

        }catch (Exception e){
            Log.e(TAG, "load: "+ e.getMessage() );
            e.printStackTrace();
            return entity;
        }

    }
}
