package kg.gruzovoz.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kg.gruzovoz.BaseActivity;
import kg.gruzovoz.R;

public class CallActivity extends AppCompatActivity {

    private static final int  REQUEST_CALL = 1;

    Button callButton;
    Button allOrdersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call2);

        callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

        allOrdersButton = findViewById(R.id.allOrdersButton);
        allOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allOrdersIntent  = new Intent(CallActivity.this, BaseActivity.class);
                startActivity(allOrdersIntent);
                finish();
            }
        });
    }


    public void makePhoneCall(){
        //get phone number from api , now let in be string var
        String phoneNumber = " ";
        if (phoneNumber.trim().length() >0){
            if (ContextCompat.checkSelfPermission(CallActivity.this,
                    Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(CallActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            }else {
                String dail  = "tel:" + phoneNumber;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));
                finish();
            }

        }else {
            Toast.makeText(CallActivity.this,"Невозможно позвонить, пожалуйста свяжитесь с диспетчером",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL){

            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(CallActivity.this,"Невозможно позвонить, пожалуйста свяжитесь с диспетчером",Toast.LENGTH_LONG).show();

            }
        }
    }


}
