package com.k3kc.checknetworkstate;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ConnectivityManager mConnectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        Button btnStatus = findViewById(R.id.btn_status);
        Button btnType = findViewById(R.id.btn_type);

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkStatus();
            }
        });

        btnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkType();
            }
        });
    }

    public void checkNetworkType() {
        NetworkInfo mobileInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(mobileInfo != null && mobileInfo.isConnected()) {
            notify("3G is connected");
        } else if(wifiInfo != null && wifiInfo.isConnected()) {
            notify("WiFi is connected");
        } else {
            notify("No connection");
        }

    }

    public void checkNetworkStatus() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            notify("Network is connected");
        } else {
            notify("No network connection");
        }
    }

    public void notify(String message) {
        Snackbar.make(findViewById(R.id.root_view), message, Snackbar.LENGTH_SHORT).show();
    }

}
