package com.cdu.rit81;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
	// object of hardware camera
	private Camera mCamera;

	private CameraPreview mPreview;

	// 10 sec interval
	public long delay = SettingActivity.IMAGE_CAPTURE_INTERVAL;

	// hold the status of back button press
	// static so that I can get the value from another class
	public static boolean backBtnPressed = false;

	/*
	 * Timer is a auto trigger class that schedule the timer task Timer task
	 * hold the tasks
	 */
	private Timer timer = null;
	private TimerTask timerTask = null;

	/*
	 * This method calls at first time when view is loading
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make screen alive
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_camera);

		// Create an instance of Camera
		mCamera = getCameraInstance();
		
		if(mCamera != null){
			// get Camera parameters
	        Camera.Parameters params = mCamera.getParameters();
	        // set the focus mode
	        //params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
	        // set Camera parameters
	        mCamera.setParameters(params);
		

			// Create our Preview view and set it as the content of our activity.
			mPreview = new CameraPreview(this, mCamera);
	
			// Load the custom frame layout to an frame layout object
			// Add the camera preview object to this frame layout view
			// so that we can view the camera image on this layout
			FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
			preview.addView(mPreview);
	
			try {
				this.timerTask = new TimerTask() {
	
					public void run() {
						// TODO Auto-generated method stub
						if (mCamera != null)
							mCamera.takePicture(null, null, mPicture);
	
					}
				};
	
				this.timer = new Timer();
				this.timer.schedule(this.timerTask, delay);
			} catch (Exception ex) {
				Log.d("Camera activity", "Exception in timer: " + ex.getMessage());
			}
		}
		else{
			exitFromActivity();
		}
	}

	/*
	 * This method calls when device back button pressed Set the static variable
	 * value to indicate that back button has pressed
	 */
	public void onBackPressed() {
		backBtnPressed = true;
		exitFromActivity();
	}

	/*
	 * This method nullify the hardware camera object It nullify the timer and
	 * timer task object Return to previous view
	 */
	public void exitFromActivity() {
		Log.d("Camera activity", "Back button pressed");
		
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
			
			Log.d("CameraActivity", "Free Camera object");
		}

		if (this.timer != null && this.timerTask != null) {
			this.timerTask.cancel();
			Log.d("CameraActivity", "Free timertask object");
			this.timer.cancel();
			Log.d("CameraActivity", "Free timer object");
		}

		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
	}

	// A safe way to get an instance of the Camera object.
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance

		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
			Log.d("CameraActivity.getCameraInstance", "Error: " + e);
		}
		return c; // returns null if camera is unavailable
	}

	/*
	 * This is a call back method This method calls when a picture has taken
	 */
	private PictureCallback mPicture = new PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {

			try {
				String externalPath = Environment.getExternalStorageDirectory()
						.getPath() + "/CDU/";
				String filePath = String.format("%d.jpg",
						System.currentTimeMillis());

				File cduFileDir = new File(externalPath);

				if (!cduFileDir.exists())
					cduFileDir.mkdir();

				File pictureFile = new File(cduFileDir, filePath);

				Log.d("CameraActivity.PictureCallback", "External path: "
						+ pictureFile.getPath());

				FileOutputStream fos = new FileOutputStream(pictureFile);
				
				//Log.d("CameraActivity.PictureCallback", "Bitmap conversion starts");
				
				/*Bitmap bp = BitmapFactory.decodeByteArray(data, 0, data.length);
				Matrix matrix = new Matrix();
				matrix.setRotate(90);
				Bitmap nbp = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				nbp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] newData = stream.toByteArray();*/
				
				//Log.d("CameraActivity.PictureCallback", "Bitmap conversion ends");
				
				SettingActivity.IMAGE_DATA = data;
				SettingActivity.IMAGE_PATH = "" + pictureFile.getPath();
				SettingActivity.IMAGE_NAME = filePath;

				fos.write(data);
				fos.flush();
				fos.close();

			} catch (FileNotFoundException e) {
				Log.d("CameraActivity.PictureCallback",
						"File not found: " + e.getMessage());
			} catch (IOException e) {
				Log.d("CameraActivity.PictureCallback",
						"Error accessing file: " + e.getMessage());
			} finally {
				Log.d("CameraActivity.PictureCallback", "Image file length: "
						+ data.length);
				exitFromActivity();
			}
		}
	};

}
