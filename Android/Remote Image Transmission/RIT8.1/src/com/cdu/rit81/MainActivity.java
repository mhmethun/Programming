package com.cdu.rit81;


import com.cdu.rit81.R;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends Activity {
	
	private AlertDialog alertDialog = null;
	static String ABOUT = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        
        final Vibrator vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        
        this.setTitle(R.string.home);
        
        Button cameraButton = (Button)findViewById(R.id.btnCamera);
        Button settingButton = (Button)findViewById(R.id.btnSetting);
        Button aboutButton = (Button)findViewById(R.id.btnAbout);
         
        cameraButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				vibrate.vibrate(75);
				
				Intent cameraControlIntent = new Intent(v.getContext(), CameraControlActivity.class);
		        startActivityForResult(cameraControlIntent, 0);
			}
		});
        
        settingButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				vibrate.vibrate(75);
				
				Intent settingIntent = new Intent(v.getContext(), SettingActivity.class);
		        startActivityForResult(settingIntent, 0);
				
			}
		});
        
        ABOUT = "Image capturing is one of the easiest jobs now but there has a big revolution on the image capturing devices and technology. " + 
        	"This application not only emphesis on the image capturing technology but also focus on a new methodology of image transmission technology. " + 
        	"We have focused on WiFi as data or image transmission media.\n\nSetting page holds all the setting information of this application. " + 
        	"One can set the image capture interval from here. You can configure the destination of the captured image. Destination can be either SD card or remote server. " + 
        	"If you select server as a destination then you have to fulfill other server related information like protocol, host, port, page location.\n\nAt " + 
        	"setting page there has a network button which opens network page. This page informs you the status of wifi network at your mobile device. " + 
        	"If you enable wifi then here you will see a message that WiFi is enabled. Otherwise you will show a message called WiFi disabled. " + 
        	"If wifi has disabled then you can not send image to server. So. you must have to enable wifi at your device to get the facility of transferring image to server.\n\n" + 
        	"Image capturing is the prominent feature here. After starting camera image has taken after a defined interval. " + 
        	"This interval has to configure at setting page. But by default it is 10 sec interval. After capturing image app transmit image to SD card or remote server based on setting. " + 
        	"After saving image camera automatically prepare to take next image. User does not need to start camera again. If you select the destination as server but does not make available " + 
        	"wifi at your device they after capturign image you will notify to enable wifi. At the same time application stores that image to SD card.\n\n"; 
        
        aboutButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				vibrate.vibrate(75);
				
				showAlertDialog(v.getContext(), "About this app", ABOUT);
			}
		});
        
        SettingActivity.updateSupportedResolution();
        
    }
    
    public void showAlertDialog(Context context, String title, String msg){
		this.alertDialog = new AlertDialog.Builder(context).create();
		this.alertDialog.setTitle(title);
		this.alertDialog.setMessage(msg);
		this.alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dismissAlertDialog();
			}
		});
		this.alertDialog.show();
	}
    
    public void dismissAlertDialog(){
    	this.alertDialog.dismiss();
    }

}
