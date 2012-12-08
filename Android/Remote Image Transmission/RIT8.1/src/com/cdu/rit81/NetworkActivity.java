package com.cdu.rit81;

import java.util.List;

import com.cdu.rit81.R;
import com.cdu.rit81.R.id;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NetworkActivity extends Activity {
	WifiManager wifi;
	BroadcastReceiver receiver;

	TextView textStatus;
	ProgressBar searchProgress;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_network);
        
        this.setTitle(R.string.network);
        
        textStatus = (TextView) findViewById(R.id.textStatus);
		searchProgress = (ProgressBar)findViewById(id.barNetProgress);
        
		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		if(wifi.isWifiEnabled()){
			textStatus.append("WiFi Enabled.");
			SettingActivity.WIFI_DETECTED = true;
		}
		else{
			textStatus.append("WiFi Disabled.");
			SettingActivity.WIFI_DETECTED = false;
		}

		// Get WiFi status
		WifiInfo info = wifi.getConnectionInfo();
		textStatus.append("\n\nWiFi Status: " + info.toString());

		// List available networks
		List<WifiConfiguration> configs = wifi.getConfiguredNetworks();
		for (WifiConfiguration config : configs) {
			textStatus.append("\n\n" + config.toString());
		}
		
		searchProgress.setVisibility(4);
	}

    public void onBackPressed() {
    	Log.d("Network activity", "Back button pressed");
    	
    	Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
