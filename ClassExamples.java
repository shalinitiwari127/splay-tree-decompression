package com.example.vision.myapplication;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.widget.Button;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.util.Log;
import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.io.IOException;

public class ClassExamples extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button sendSMS;
    private Button retSMS;
    String sms;
    String retr;
Decompress d= new Decompress("acb");

    public ClassExamples() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }
        final EditText smsText = (EditText) findViewById(R.id.message);
        final TextView Text = (TextView) findViewById(R.id.m);
        sendSMS = (Button) findViewById(R.id.sendSMS);
        retSMS = (Button) findViewById(R.id.retrive);
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms = smsText.getText().toString();
                String phoneNum = phoneNumber.getText().toString();
                if (!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                    if (checkPermission()) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNum, null, sms, null, null);
                    } else {
                        Toast.makeText(ClassExamples.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        try {
            d.decodeTree(retr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        retSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms = smsText.getText().toString();
                if (sms.contains("coz")) {
                   retr= sms.replace("coz", "cause");}
                   if (sms.contains("n")) {
                        retr= sms.replace("n", "nd");}
                        if (sms.contains("alt")) {
                            retr= sms.replace("alt", "altitude");}
                            if (sms.contains("jan")) {
                                retr= sms.replace("jan", "january");

                }
                Text.setText(retr);
                String phoneNum = phoneNumber.getText().toString();
                if (!TextUtils.isEmpty(retr) && !TextUtils.isEmpty(phoneNum)) {
                    if (checkPermission()) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNum, null, retr, null, null);
                    } else {
                        Toast.makeText(ClassExamples.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(ClassExamples.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ClassExamples.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ClassExamples.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    Button sendSMS = (Button) findViewById(R.id.sendSMS);
                    sendSMS.setEnabled(false);

                }
                break;
        }
    }

}