package com.example.exampleintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle intentExtras = intent.getExtras();

        if (intentExtras != null) {
            // Get Messages

            Object[] sms = (Object[]) intentExtras.get("pdus");

            assert sms != null;
            for (Object sm : sms) {

                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sm);

                //String phone = smsMessage.getOriginatingAddress();
                String message = smsMessage.getMessageBody();

                if (!message.isEmpty()) {
                    //this will update the UI with message
                    Log.e("ExampleIntents", message);
                    // La actividad debe de estar instanciada para manejar el mensaje
                    HandlerBrActivity inst = HandlerBrActivity.getInstance();
                    if (inst != null) {
                        // Validar que sean dígitos
                        inst.setCodigo(message);
                        System.out.println("Enviado!!!!!!!!!!!!");
                        System.out.println(message);

                    }
                }
            }
        }
    }
}
