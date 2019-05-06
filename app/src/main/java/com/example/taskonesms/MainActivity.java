package com.example.taskonesms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

    EditText phn_edt, msg_edt;
    Button send_btn, call_btn, mail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phn_edt = (EditText) findViewById(R.id.phn_edt);
        msg_edt = (EditText) findViewById(R.id.msg_edt);
        send_btn = (Button) findViewById(R.id.send_btn);
        call_btn = (Button) findViewById(R.id.call_btn);
        mail_btn = (Button) findViewById(R.id.mail_btn);
        call_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + phn_edt.getText().toString()));
                startActivity(i);
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhon = phn_edt.getText().toString().trim();
                String strMessg = msg_edt.getText().toString().trim();

                if (strPhon.equals("")) {
                    phn_edt.setError("please enter the phone number");
                } else if (strMessg.equals("")) {
                    msg_edt.setError("please enter the message");
                } else {

                    /*Uri uriSms = Uri.parse("smsto:"+strPhon);
                    Intent intentSMS = new Intent(Intent.ACTION_SENDTO, uriSms);
                    intentSMS.putExtra("sms_body", strMessg);
                    startActivity(intentSMS);*/

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(strPhon, null, strMessg, null, null);

                    Toast.makeText(getApplicationContext(), "Message sent..!", Toast.LENGTH_SHORT).show();
                    Log.i("IS ONCLICK:-> ", "YES");
                }
            }
        });
    }
        public void SecondActivity(View v) {
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
}