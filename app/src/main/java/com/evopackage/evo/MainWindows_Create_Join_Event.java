package com.evopackage.evo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainWindows_Create_Join_Event extends AppCompatActivity implements create_event_popup.DialogListener, View.OnClickListener {


    private FirebaseAuth auth;
    private FirebaseDatabase search;
    private FirebaseUser user;
    private int Camera_Permission_Request = 1;
    private String[] perm_ = {Manifest.permission.CAMERA};//add permitions to this array
    private boolean permission_granted;
    private ImageButton btn;
    private ImageButton evtBtn;
    private ImageButton qr,settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_windows_create_join_event);
        qr = findViewById(R.id.qr_main_id);
        btn = findViewById(R.id.profile_picture_Main_id);
        settings= findViewById(R.id.settings_Main_Id);
        evtBtn = findViewById(R.id.calendar_id);
        evtBtn.setOnClickListener(v -> openDialog());
        qr.setOnClickListener(this);
        btn.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    private void openDialog() {
        create_event_popup evtPopUp = new create_event_popup();
        evtPopUp.show(getSupportFragmentManager(), "EventDialog");
    }

    @Override
    public void applyTexts(String _evtName, String _evtDate, String _evtAdder) {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btn.getId()) {

            Intent car = new Intent(this, Profile_Page.class);
            startActivity(car);

        }
        if (v.getId() == qr.getId()) {


            RequestCameraPermission();


        }
        if(v.getId()==R.id.settings_Main_Id){

            Intent car = new Intent(this, Event_Page.class);
            startActivity(car);
        }
    }


    private void RequestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            new AlertDialog.Builder(this).setTitle("Permission need it").setMessage("To be able to scan QR code \n you will need the permissions ")
                    .setPositiveButton("Allow", (dialog, which) -> {


                        Intent car = new Intent(MainWindows_Create_Join_Event.this, Qr_code_scanner.class);
                        startActivity(car);



                    }).setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                    .create()
                    .show();


        }   if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){


            Intent car = new Intent(this, Qr_code_scanner.class);
            startActivity(car);



        }


    }

    @Override
    public void applyTexts(String _evtName, String _evtDate, String _evtAddr, String _evtTheme) {

    }
}
