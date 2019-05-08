package com.developes.smssender;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText txt_pNumber, txt_Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Message = (EditText) findViewById(R.id.txt_phone_message);
        txt_pNumber = (EditText) findViewById(R.id.txt_phone_number);
    }

    public void btn_send(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void MyMessage() {

        String phoneNumber = txt_pNumber.getText().toString().trim();
        String Message = txt_Message.getText().toString().trim();

        if (!txt_pNumber.getText().toString().equals("") || !txt_Message.getText().toString().equals("")){
            SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

    else {
            Toast.makeText(this,"Please Enter Number and Message Both",Toast.LENGTH_SHORT).show();
        }
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 0:

                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    MyMessage();
                }
                else {
                    Toast.makeText(this,"You don't have Required Permission",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
