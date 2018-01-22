package com.example.desenvolvedor2015.invatedmanager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.business.GuestBusiness;
import com.example.desenvolvedor2015.invatedmanager.constants.GuestConstants;
import com.example.desenvolvedor2015.invatedmanager.entities.GuestEntity;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;
    private int mGuestId = 0;
    private GuestEntity entityLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);


        this.mViewHolder.mEditText = this.findViewById(R.id.edtName);
        this.mViewHolder.mButton = this.findViewById(R.id.btnSave);
        this.mViewHolder.mRadioButtonAbsent = this.findViewById(R.id.radio_button_absent);
        this.mViewHolder.mRadioButtonNotConfirmed = this.findViewById(R.id.radio_button_not_confirmed);
        this.mViewHolder.mRadioButtonPresence = this.findViewById(R.id.radio_button_present);
        this.mGuestBusiness = new GuestBusiness(this);

        this.setListeners();

        this.loadDataFromActivity();

    }

    private void loadDataFromActivity() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            this.mGuestId =  bundle.getInt(GuestConstants.BundleConstants.GUEST_ID);

            entityLoad = this.mGuestBusiness.load(this.mGuestId);

            this.mViewHolder.mEditText.setText(entityLoad.getName());

            if (entityLoad.getConfirmed() == GuestConstants.CONFIRMATION.NOT_CONFIRMED){
                this.mViewHolder.mRadioButtonNotConfirmed.setChecked(true);
            }else if (entityLoad.getConfirmed() == GuestConstants.CONFIRMATION.PRESENT){
                this.mViewHolder.mRadioButtonPresence.setChecked(true);
            }else if (entityLoad.getConfirmed() == GuestConstants.CONFIRMATION.ABSENT){
                this.mViewHolder.mRadioButtonAbsent.setChecked(true);
            }

        }

    }

    private void setListeners() {
        this.mViewHolder.mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnSave){

            this.handleSave();

//            if (this.entityLoad == null) {
//                this.handleSave();
//            }else{
//                this.update();
//            }
        }

    }

    private void update() {

        if (!this.validationSalve()){
            return;
        }

        entityLoad.setName(this.mViewHolder.mEditText.getText().toString());

        if (this.mViewHolder.mRadioButtonNotConfirmed.isChecked()){
            entityLoad.setConfirmed(GuestConstants.CONFIRMATION.NOT_CONFIRMED);
        }else if (this.mViewHolder.mRadioButtonPresence.isChecked()){
            entityLoad.setConfirmed(GuestConstants.CONFIRMATION.PRESENT);
        }else if (this.mViewHolder.mRadioButtonAbsent.isChecked()) {
            entityLoad.setConfirmed(GuestConstants.CONFIRMATION.ABSENT);
        }

        if (this.mGuestBusiness.update(entityLoad)){
            Toast.makeText(this, R.string.msg_sucess_update, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.msg_unsucess_update, Toast.LENGTH_SHORT).show();
        }

        //finalizando
        finish();



    }

    private void handleSave() {

        if (!this.validationSalve()){
            return;
        }

        GuestEntity guestEntity = new GuestEntity();

        guestEntity.setName(this.mViewHolder.mEditText.getText().toString());

        if (this.mViewHolder.mRadioButtonNotConfirmed.isChecked()){
            guestEntity.setConfirmed(GuestConstants.CONFIRMATION.NOT_CONFIRMED);
        }else if (this.mViewHolder.mRadioButtonPresence.isChecked()){
            guestEntity.setConfirmed(GuestConstants.CONFIRMATION.PRESENT);
        }else if (this.mViewHolder.mRadioButtonAbsent.isChecked()) {
            guestEntity.setConfirmed(GuestConstants.CONFIRMATION.ABSENT);
        }

        if (this.mGuestId == 0){
            if(mGuestBusiness.insert(guestEntity)){

                Toast.makeText(this, getString(R.string.convidado_salvo), Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, getString(R.string.convidado_nao_salvo), Toast.LENGTH_SHORT).show();
            }

        }else{

            guestEntity.setId(this.mGuestId);

            if(mGuestBusiness.update(guestEntity)){

                Toast.makeText(this, getString(R.string.msg_sucess_update), Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, getString(R.string.msg_unsucess_update), Toast.LENGTH_SHORT).show();
            }

        }



        //finalizando activity
        finish();
    }

    private boolean validationSalve() {
        String name = this.mViewHolder.mEditText.getText().toString();
        if (name.equals("") || name.isEmpty()){
            this.mViewHolder.mEditText.setError(getString(R.string.nome_obrigatorio));
            return false;
        }
        return true;
    }


    public static class ViewHolder{
        EditText mEditText;
        Button mButton;
        RadioButton mRadioButtonAbsent;
        RadioButton mRadioButtonNotConfirmed;
        RadioButton mRadioButtonPresence;
    }
}
