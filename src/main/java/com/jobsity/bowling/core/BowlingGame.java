package com.jobsity.bowling.core;

import com.jobsity.bowling.model.Sheet;

import static com.jobsity.bowling.utils.AppUtils.*;

public class BowlingGame {
	
	private final Sheet[] sheets;
	
	public BowlingGame(int players){
		checkParamIsPositive("players", players);
		sheets = new Sheet[players];
		for(int i =0; i < sheets.length; i++){
			sheets[i] = new Sheet();
		}
	}
	
	public void setPlayer(int idx, String name){
		checkParamBetween("idx", 0, sheets.length-1, idx);
		checkParamNotEmpty("name", name);
		sheets[idx].setPlayer(name);
	}
}
