package com.cdu.rit81;

import java.util.ArrayList;

public class CameraResolution {
	ArrayList<Resolution> cameraResolution;
	
	public CameraResolution(){
		this.cameraResolution = new ArrayList<Resolution>();
	}
	
	public void clearAllResolutions(){
		this.cameraResolution.clear();
	}
	
	public void addResolution(int w, int h){
		this.cameraResolution.add(new Resolution(w, h));
	}
	
	public int getTotalResolution(){
		return this.cameraResolution.size();
	}
	
	public Resolution getResolution(int pos){
		if(pos >= 0 && pos < this.cameraResolution.size()){
			return this.cameraResolution.get(pos);
		}
		
		return null;
	}
}

class Resolution{
	private int width;
	private int height;
	
	public Resolution(int w, int h){
		this.width = w;
		this.height = h;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
}
