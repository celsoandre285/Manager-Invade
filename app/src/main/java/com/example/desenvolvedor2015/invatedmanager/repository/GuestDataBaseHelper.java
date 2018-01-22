package com.example.desenvolvedor2015.invatedmanager.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.desenvolvedor2015.invatedmanager.constants.DataBaseConstants;


/**
 * Created by desenvolvedor on 18/01/18.
 */

public class GuestDataBaseHelper extends SQLiteOpenHelper {

    private final String TAG = "GuestDataBaseHelper";

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "meusconvidados.db";

    private static final String SQL_CREATE_TABLE_GUEST =
            "create table "+ DataBaseConstants.GUEST.TABLE_NAME +" ("
            + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement ,"
            + DataBaseConstants.GUEST.COLUMNS.NAME + " text ,"
            + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer );";

    private static final String DROP_TABLE_GUESTS = "drop table if exists " + DataBaseConstants.GUEST.TABLE_NAME;



    public GuestDataBaseHelper(Context context) {
        //super(context, this.DATABASE_NAME, null, this.DATABASE_VERSION);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate table");

        db.execSQL(SQL_CREATE_TABLE_GUEST);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int update) {
        Log.i(TAG, "onUpgrade: : v"+ i +"for  v  "+update);

        //db.execSQL(DROP_TABLE_GUESTS);
        //db.execSQL(SQL_CREATE_TABLE_GUEST);

        switch (update){
            case 1:
                Log.i(TAG, "onUpgrade: ok");

            case 2:
                String alterTable = "alter table "+DataBaseConstants.GUEST.TABLE_NAME + " add "+ DataBaseConstants.GUEST.COLUMNS.DOCUMENTATION + " text" ;

                db.execSQL(alterTable);

                break;
            default:

        }

    }
}
