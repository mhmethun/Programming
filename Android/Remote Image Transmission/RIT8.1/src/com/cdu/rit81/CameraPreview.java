package com.cdu.rit81;

import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*
 * This is a surface view type class because it extends SurfaceView class
 * It implements SerfaceHolder.Callback interface
 * It creates surface to hold the image
 * Each time before exit it destroy the surface
 * When camera is moving then surface changes regularly
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
	private static final String TAG = "CameraPreview";
	
	// Surface holder holds the image on the surface
	private SurfaceHolder mHolder;
	// Hardware camera object
    private Camera mCamera;
    
    // supported preview size
    List<Size> mSupportedPreviewSizes;
    
    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        
        if(mCamera != null){
        	List<Size> localSizes = mCamera.getParameters().getSupportedPreviewSizes();
            mSupportedPreviewSizes = localSizes;
            requestLayout();
        }

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        
        if(mHolder != null){
	        mHolder.addCallback(this);
	        
	        // deprecated setting, but required on Android versions prior to 3.0
	        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }
    
	/*
	 * This is an overriding method calls when surface is going to create at
	 * first time It sets the preview object to camera preview it starts camera
	 * preview. So, camera image is showing at mobile view surface. Pass a
	 * method as call back method This call back method store the image data to
	 * sd card
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the
		// preview.
		Log.d(TAG, "Surface Created");
	}
    
    /*
     * This is an overriding method that calls when a surface has destroyed
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    	Log.d(TAG, "Surface Destroyed");
    }
    
    public Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null) return null;

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for(Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if(Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if(Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if(optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Size size : sizes) {
                if(Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }

        return optimalSize;
    }
    
    
	/*
	 * This is an overriding method that calls when a surface has changed Stop
	 * the preview and start a new preview.
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.

		if (mHolder.getSurface() == null) {
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try {
			if (mCamera != null)
				mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
			Log.d(TAG, "Error in surface change: "
					+ e.getMessage());
		}

		// set preview size and make any resize, rotate or
		// reformatting changes here

		// start preview with new settings
		try {
			// Now that the size is known, set up the camera parameters and
			// begin
			// the preview.
			if (mCamera != null) {
				
				Camera.Parameters parameters = mCamera.getParameters();
				
				if(mSupportedPreviewSizes == null)
					mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
				
				Camera.Size mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, w, h);
				
				Log.d("CameraPreview.surfaceChanged", "Preview Size: ["
						+ mPreviewSize.width + "X" + mPreviewSize.height + "]");
				
				parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
				
				int index = SettingActivity.SELECTED_RESOLUTION_INDEX;
				int resWidth = SettingActivity.CAMERA_RESOLUTION.getResolution(index).getWidth();
				int resHeight = SettingActivity.CAMERA_RESOLUTION.getResolution(index).getHeight();
				
				Log.d("CameraPreview.surfaceChanged", "Picture Size: ["
						+ resWidth + " X " + resHeight + "]");
				
				parameters.setPictureSize(resWidth, resHeight);
				parameters.setJpegQuality(100);
				
				requestLayout();
				
				mCamera.setParameters(parameters);
				
				mCamera.setPreviewDisplay(mHolder);
				mCamera.startPreview();
			}

		} catch (Exception e) {
			Log.d(TAG, "Error in surface change: "
					+ e.getMessage());
		}
	}

}
