package com.jobsity.bowling.model;

import java.io.Serializable;

public class Sheet implements Serializable {

	private static final int TOTAL_FRAMES = 10;

	private String player;
	//since it always going to be 10, no need to use List.
	private final Frame[] frames;
	
	private int crt = 0;

	public Sheet(){
		frames = new Frame[TOTAL_FRAMES];
		for(int i =0; i < frames.length; i++){
			frames[i] = new Frame(i + 1);
		}
	}
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public void append(String val){
		if(frames[crt].isReady()){
			crt += 1;
		}
		
		frames[crt].append(val);
	}
}
