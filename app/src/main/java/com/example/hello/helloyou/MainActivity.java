package com.example.hello.helloyou;

import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.Html;
//import android.text.method.LinkMovementMethod;
//import android.util.AndroidException;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nfcText = (TextView) findViewById(R.id.nfcMessage);
        Switch nfcSwitch = (Switch) findViewById(R.id.nfcSwitch);
        TextView nfcActiveText = (TextView) findViewById(R.id.nfcActiveText);
        ImageView availabilityImage = (ImageView) findViewById(R.id.availabilityImage);
        NfcManager manager = (NfcManager) getApplicationContext().getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();

        //boolean flag = isDeviceSupportCamera();
        nfcActiveText.setText(R.string.txt_nfc_not_active);
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {

            //NFC is available
            nfcSwitch.setEnabled(true);
            availabilityImage.setImageResource(R.drawable.ok1);
            //titre.setFont(new java.awt.Font("Serif", 1, 20));

            if (adapter != null && adapter.isEnabled()) {
                // adapter exists and is enabled.
                nfcSwitch.setChecked(true);
                nfcActiveText.setText(R.string.txt_nfc_active);

            }
            else
            {

                nfcSwitch.setChecked(false);
                nfcActiveText.setText(R.string.txt_nfc_not_active);

            }

            nfcText.setText(R.string.txt_nfc_ok);

            //nfcText.setText(Html.fromHtml(getString( R.string.nfc_wikipedia)));
            //link.setMovementMethod(LinkMovementMethod.getInstance());

        }
        else
        {
            nfcText.setText(R.string.txt_nfc_non);
            nfcActiveText.setText(R.string.txt_nfc_not_active);
            availabilityImage.setImageResource(R.drawable.non1);
            nfcActiveText.setEnabled(false);
            nfcSwitch.setEnabled(false);
            nfcSwitch.setChecked(false);
        }
    }
}
