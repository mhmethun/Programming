package com.cdu.rit81;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.cdu.rit81.R;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/*
 * This class controls camera action and again loading camera periodically
 */
public class CameraControlActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_control);
        
        this.setTitle("Camera Loading...");
        
        CameraActivity.backBtnPressed = false;
        
        this.startCameraView(this);
    }

    private void startCameraView(Context context){
    	Intent cameraIntent = new Intent(context, CameraActivity.class);
        startActivityForResult(cameraIntent, 0);
    }
    
    /*
     * This method calls when mobile back button pressed
     */
    public void onBackPressed() {
    	Log.d("Camera Control activity", "Back button pressed");
    	Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
    
	/*
	 * Them method calls when view loads again It detects that if came to this
	 * page by pressing back button then leave this page also Otherwise load the
	 * camera again
	 */
	public void onRestart() {
		super.onRestart();

		if (SettingActivity.IMAGE_DATA != null
				&& SettingActivity.IMAGE_DATA.length > 0) {
			this.checkWifi();

			/*
			 * if(SettingActivity.DEST_TYPE == 0) this.emailImage(); else
			 */

			if (SettingActivity.DEST_TYPE == 1) {

				if (SettingActivity.WIFI_DETECTED) {
					this.uploadFile();
				} else {
					Toast.makeText(this,
							"Please enable WiFi to send image to server.",
							Toast.LENGTH_LONG).show();
				}
			}
		}

		if (!CameraActivity.backBtnPressed) {
			this.startCameraView(this);
		} else {
			this.onBackPressed();
		}
	}
    
    private void checkWifi(){
    	WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		if(wifi.isWifiEnabled()){
			SettingActivity.WIFI_DETECTED = true;
		}
		else{
			SettingActivity.WIFI_DETECTED = false;
		}
    }
    
    /*private void emailImage(){
    	Intent sendIntent;
    	Log.d("Camera Control activity", "send image of path: " + SettingActivity.IMAGE_PATH);
    	sendIntent = new Intent(Intent.ACTION_SEND);
    	sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Subject");
    	sendIntent.putExtra(Intent.EXTRA_TEXT, "Test Text");
    	sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + SettingActivity.IMAGE_PATH));
    	sendIntent.setType("image/jpeg");

    	startActivity(Intent.createChooser(sendIntent, "Send Mail"));
    }*/
    
    private void uploadFile(){
    	if(SettingActivity.HTTP_URL.length() > 0){
	    	try {
	    		File file = new File(SettingActivity.IMAGE_PATH);
	    		FileInputStream fis = new FileInputStream(file);
	    		
	    		HttpFileUploader uploader = new HttpFileUploader(SettingActivity.HTTP_URL, "noparamshere", SettingActivity.IMAGE_NAME, fis, SettingActivity.IMAGE_PATH);
	    		uploader.execute();
	    	} catch (IOException e) {
		    	// TODO Auto-generated catch block
	    		Log.d("Camera Control activity", "File upload error: " + e.getMessage());
	    	}
	    	catch (IllegalStateException e) {
		    	// TODO Auto-generated catch block
	    		Log.d("Camera Control activity", "Async uploader error: " + e.getMessage());
	    	}
    	}
    	else{
    		Toast.makeText(this, "Please configure server at setting page", Toast.LENGTH_LONG).show();
    	}
    }	
}
