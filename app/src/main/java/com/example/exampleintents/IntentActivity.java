package com.example.exampleintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {

    private TextView txtIntentData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIntentData = findViewById(R.id.txt_intent_data);

        try{
            intent = getIntent();

            if(intent != null) {
                String path = intent.getData().getPath();
                Log.i(getString(R.string.app_name), path);
                txtIntentData.setText("PATH: " + path);
            }
        }catch (Exception e){
            Log.e(getString(R.string.app_name), ":" + e);
        }

    }
}
