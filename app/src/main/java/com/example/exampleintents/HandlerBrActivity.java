package com.example.exampleintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HandlerBrActivity extends AppCompatActivity {

    static HandlerBrActivity instance = null;
    TextView txtMessage;
    EditText edtxtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_br);
        txtMessage = findViewById(R.id.txt_message_receiver);
        edtxtMessage = findViewById(R.id.edtxt_text_msj);

    }

    @Override
    protected void onStart() {
        super.onStart();

        instance = this;
    }


    static HandlerBrActivity getInstance(){
        return instance;
    }

    public void setCodigo(String s){
        txtMessage.setText(s);

    }


    public void onSendMessage(View view) {

        // Permisos
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.SEND_SMS,},1000);
        }

        // Mandando mensaje
        String phone = "5568983473";
        if(!edtxtMessage.getText().toString().isEmpty()) {
            try {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phone, null, edtxtMessage.getText().toString(), null, null);
                Log.e("SEND_MESSAGE", "Se envió el mensaje");
            }catch (Exception e){
                Log.e("SEND_MESSAGE_ERROR", e.getMessage());
            }

        } else{
            new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Debe de ingresar un mensaje")
                    .setPositiveButton("Aceptar",null)
                    .show();
        }
    }
}
