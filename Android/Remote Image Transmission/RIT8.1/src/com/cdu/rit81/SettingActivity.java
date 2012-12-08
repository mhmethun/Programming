package com.cdu.rit81;

import java.util.ArrayList;
import java.util.List;

import com.cdu.rit81.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity {

	public static int INTERVAL = 10;
	public static int TYPE = 0;	//{0=Sec, 1=Min, 2=Hour}
	public static long IMAGE_CAPTURE_INTERVAL = 10000;
	public static byte[] IMAGE_DATA;
	public static String IMAGE_PATH;
	public static String IMAGE_NAME = "";
	public static boolean WIFI_DETECTED = false;
	public static int DEST_TYPE = 0; //{0=SD card, 1=Server}
	public static boolean HTTP_PROTOCOL = true;
	public static String HTTP_URL = "";
	public static String HOST_ADDRESS = "eromanbd.5gbfree.com";
	public static String PORT_ADDRESS = "80";
	public static String LOCATION_ADDRESS = "index.php";
	public static CameraResolution CAMERA_RESOLUTION;
	public static int SELECTED_RESOLUTION_INDEX = 0;
	
	private Spinner spinner;
	private Spinner timeSpinner;
	private Spinner protocolSpinner;
	private Spinner destSpinner;
	private Spinner resolutionSpinner;
	private EditText host;
	private EditText port;
	private EditText location;
	
	private TextView txtProtocol;
	private TextView txtHost;
	private TextView txtPort;
	private TextView txtLocation;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_setting);
        
        this.setTitle(R.string.setting);
        final Vibrator vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        
        this.txtProtocol = (TextView)findViewById(R.id.textView5);
        this.txtHost = (TextView)findViewById(R.id.textView2);
        this.txtPort = (TextView)findViewById(R.id.textView3);
        this.txtLocation = (TextView)findViewById(R.id.textView4);
        
        this.host = (EditText)findViewById(R.id.txtHost);
        this.port = (EditText)findViewById(R.id.txtPort);
        this.location = (EditText)findViewById(R.id.txtLocation);
        
        this.host.setText(HOST_ADDRESS);
        this.port.setText(PORT_ADDRESS);
        this.location.setText(LOCATION_ADDRESS);
        
        this.spinner = (Spinner) findViewById(R.id.spFreq);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frequencylist, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.spinner.setAdapter(adapter);
		// Set the position that was selected last time
		this.spinner.setSelection(adapter.getPosition("" + INTERVAL));
		// Change selection and store that 
		this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	
		    	vibrate.vibrate(75);
		    	
		        Object item = parent.getItemAtPosition(pos);
		        INTERVAL = Integer.parseInt("" + item.toString());
		        Log.d("Settings activity", "Time interval selected: " + INTERVAL);
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		this.timeSpinner = (Spinner) findViewById(R.id.spTime);
		ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.timelist, android.R.layout.simple_spinner_item);
		timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.timeSpinner.setAdapter(timeAdapter);
		// Set the position that was selected last time 
		this.timeSpinner.setSelection(TYPE);
		// Change selection and store that 
		this.timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	
		    	vibrate.vibrate(75);
		    	
		        Object item = parent.getItemAtPosition(pos);
		        TYPE = pos;
		        Log.d("Settings activity", "Unit selected: " + item.toString());
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		this.protocolSpinner = (Spinner) findViewById(R.id.spProtocol);
		ArrayAdapter<CharSequence> protocolAdapter = ArrayAdapter.createFromResource(this, R.array.protocollist, android.R.layout.simple_spinner_item);
		protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.protocolSpinner.setAdapter(protocolAdapter);
		// Set the position that was selected last time 
		this.protocolSpinner.setSelected(HTTP_PROTOCOL);
		// Change selection and store that 
		this.protocolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	
		    	vibrate.vibrate(75);
		    	
		        Object item = parent.getItemAtPosition(pos);
		        if(pos == 0)
		        	HTTP_PROTOCOL = true;
		        else
		        	HTTP_PROTOCOL = false;
		        Log.d("Settings activity", "Protocol selected: " + item.toString());
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		this.destSpinner = (Spinner) findViewById(R.id.spDestination);
		ArrayAdapter<CharSequence> destAdapter = ArrayAdapter.createFromResource(this, R.array.destinationlist, android.R.layout.simple_spinner_item);
		destAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.destSpinner.setAdapter(destAdapter);
		// Set the position that was selected last time 
		this.destSpinner.setSelection(DEST_TYPE);
		//Make the http input fields visible or invisible based on destination selection
		if(DEST_TYPE == 1)
        	this.hideHttpOptions(0);
        else
        	this.hideHttpOptions(8);
		// Change selection and store that 
		this.destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	
		    	vibrate.vibrate(75);
		    	
		        Object item = parent.getItemAtPosition(pos);
		        DEST_TYPE = pos;
		        
		        if(pos == 1)
		        	hideHttpOptions(0);
		        else
		        	hideHttpOptions(8);
		        
		        Log.d("Settings activity", "Destination selected: " + item.toString());
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		
		this.resolutionSpinner = (Spinner) findViewById(R.id.spResolution);
		ArrayList<String> resolutionList = this.getSupportedResolution();
		ArrayAdapter<String> resolutionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resolutionList);
		resolutionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.resolutionSpinner.setAdapter(resolutionAdapter);
		// Set the position that was selected last time
		this.resolutionSpinner.setSelection(SELECTED_RESOLUTION_INDEX);
		// Change selection and store that 
		this.resolutionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	
		    	vibrate.vibrate(75);
		    	
		    	SELECTED_RESOLUTION_INDEX = pos;
		        Log.d("Settings activity", "resolution selected: " + CAMERA_RESOLUTION.getResolution(SELECTED_RESOLUTION_INDEX).getWidth() + " X " + CAMERA_RESOLUTION.getResolution(SELECTED_RESOLUTION_INDEX).getHeight());
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		Button selectNetwork = (Button)findViewById(R.id.btnFreq);
		selectNetwork.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				vibrate.vibrate(75);
				
				Intent networkIntent = new Intent(v.getContext(), NetworkActivity.class);
		        startActivityForResult(networkIntent, 0);
			}
		});
		
    }
    
    public static void updateSupportedResolution(){
    	Camera c = null;
    	
		try {
			c = Camera.open(); // attempt to get a Camera instance
			if(c != null){
				Camera.Parameters params = c.getParameters();
				List<Size> picSize = params.getSupportedPictureSizes();
				
				if(CAMERA_RESOLUTION == null)
					CAMERA_RESOLUTION = new CameraResolution();
				else
					CAMERA_RESOLUTION.clearAllResolutions();
				
		        for (int i=0;i<picSize.size();i++) {
		        	CAMERA_RESOLUTION.addResolution(picSize.get(i).width, picSize.get(i).height);
		            Log.d("Camera activity", i + ": "  + "width: " + picSize.get(i).width + " height: " + picSize.get(i).height);
		        }
			}
	        
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
			Log.d("Setting Activity", "Error: " + e);
		}
		finally{
			if (c != null) {
				c.release();
				c = null;
				
				Log.d("Setting Activity", "Free Camera object");
			}
		}
    }
    
    private ArrayList<String> getSupportedResolution(){
    	ArrayList<String> resolution = new ArrayList<String>();
    	
    	if(CAMERA_RESOLUTION == null || CAMERA_RESOLUTION.getTotalResolution() == 0)
    		updateSupportedResolution();
    	
    	for(int i = 0; i<CAMERA_RESOLUTION.getTotalResolution(); i++){
    		String str = "" + CAMERA_RESOLUTION.getResolution(i).getWidth() + " X " + CAMERA_RESOLUTION.getResolution(i).getHeight();
    		
    		resolution.add(str);
    	}
    	
    	return resolution;
    }
    
    public void hideHttpOptions(int isHide){
    	this.txtProtocol.setVisibility(isHide);
    	this.txtHost.setVisibility(isHide);
    	this.txtPort.setVisibility(isHide);
    	this.txtLocation.setVisibility(isHide);
    	
    	this.protocolSpinner.setVisibility(isHide);
    	this.host.setVisibility(isHide);
    	this.port.setVisibility(isHide);
    	this.location.setVisibility(isHide);
    }
	
    public void onBackPressed() {
    	Log.d("Settings activity", "Back button pressed");
    	
    	if(TYPE == 0){
    		// 0 means sec, 1 Sec = 1000 Milisec
    		IMAGE_CAPTURE_INTERVAL = INTERVAL*1000;
    	}else if(TYPE == 1){
    		// 1 means Min, 1 min = 60 Sec = 60*1000 Milisec
    		IMAGE_CAPTURE_INTERVAL = INTERVAL*1000*60;
    	}else{
    		// 2 Means Hour, 1 Hour=60Min=60*60Sec = 60*60*1000 Milisec
    		IMAGE_CAPTURE_INTERVAL = INTERVAL*1000*60*60;
    	}
    	
    	Log.d("Settings activity", "Image capture interval: " + IMAGE_CAPTURE_INTERVAL);
    	
    	String protocol = "";
    	String port = "80";
    	
    	if(this.port.getText() != null){
    		port = this.port.getText().toString();
    	}
    	
    	if(SettingActivity.HTTP_PROTOCOL)
    		protocol = "http://";
    	else
    		protocol = "https://";
    	
    	if(this.host.getText() != null){
    		HOST_ADDRESS = this.host.getText().toString();
    	}
    	
    	if(port.length() > 0){
    		PORT_ADDRESS = port;
    	}
    	
    	if(this.location.getText() != null){
    		LOCATION_ADDRESS = this.location.getText().toString();
    	}
    	
    	if(HOST_ADDRESS.length() > 0 && PORT_ADDRESS.length() > 0 && LOCATION_ADDRESS.length() > 0){
    		SettingActivity.HTTP_URL = protocol + HOST_ADDRESS + ":" + PORT_ADDRESS + "/" + LOCATION_ADDRESS;
    	}
    	else if(DEST_TYPE == 1){
    		Toast.makeText(this, "Please fulfill Host, Port and Location fields to send image properly.", Toast.LENGTH_LONG).show();
    	}
    	
    	if(DEST_TYPE == 0 || (HOST_ADDRESS.length() > 0 && PORT_ADDRESS.length() > 0 && LOCATION_ADDRESS.length() > 0)){
	    	Log.d("Settings activity", "Upload URL: " + SettingActivity.HTTP_URL);
	    	
	    	Intent intent = new Intent();
	        setResult(RESULT_OK, intent);
	        finish();
    	}
    }
}
