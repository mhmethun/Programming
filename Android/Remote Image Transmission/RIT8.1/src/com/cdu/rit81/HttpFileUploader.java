package com.cdu.rit81;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class HttpFileUploader extends AsyncTask<String, Long, Object>{

	URL connectURL;
	String params;
	String responseString;
	String fileName;
	String filePath;
	byte[] dataToServer;
	FileInputStream fileInputStream = null;
	boolean error;

	HttpFileUploader(String urlString, String params, String fileName, FileInputStream stream, String filePath) {
		try {
			connectURL = new URL(urlString);
		} catch (Exception ex) {
			Log.i("URL FORMATION", "MALFORMATED URL");
		}
		this.params = params + "=";
		this.fileName = fileName;
		fileInputStream = stream;
		this.filePath = filePath;
		
		this.error = false;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String returnStr = "";
		String exsistingFileName = this.fileName;

		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		String Tag = "HttpFileUploader";
		
		try {
			// ------------------ CLIENT REQUEST

			Log.d(Tag, "Starting to bad things");
			// Open a HTTP connection to the URL

			HttpURLConnection conn = (HttpURLConnection) connectURL
					.openConnection();

			// Allow Inputs
			conn.setDoInput(true);

			// Allow Outputs
			conn.setDoOutput(true);

			// Don't use a cached copy.
			conn.setUseCaches(false);

			// Use a post method.
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Connection", "Keep-Alive");

			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);

			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
					+ exsistingFileName + "\"" + lineEnd);
			dos.writeBytes(lineEnd);

			Log.d(Tag, "Headers are written");

			// create a buffer of maximum size

			int bytesAvailable = fileInputStream.available();
			Log.d(Tag, "Available Byte: " + bytesAvailable);
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];

			// read file and write it into form...

			int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			// send multipart form data necesssary after file data...

			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			// close streams
			Log.d(Tag, "File is written");
			returnStr = "File is written";
			
			fileInputStream.close();
			dos.flush();

			InputStream is = conn.getInputStream();
			// retrieve the response from server
			int ch;

			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			String s = b.toString();
			dos.close();
			
			returnStr = s;
			Log.d("Response From server", returnStr);
			
			if(returnStr.contains("error")){
				this.error = true;
			}

		} catch (MalformedURLException ex) {
			Log.e(Tag, "error: " + ex.getMessage(), ex);
			returnStr = ex.getMessage();
			this.error = true;
		}
		catch (IOException ioe) {
			Log.e(Tag, "error: " + ioe.getMessage(), ioe);
			returnStr = ioe.getMessage();
			this.error = true;
		}
		finally{
			if(!this.error){
				File deleteFile = new File(this.filePath);
				
				if(deleteFile.exists()){
					deleteFile.delete();
				}
			}
		}
		
		return returnStr;
	}

}