package com.jobsity.model;

import com.jobsity.utils.InvalidScoreException;

import static com.jobsity.utils.Constants.*;

public enum PinfallType {
	NUMBER,
	STRIKE,
	SPARE;


	public static PinfallType parse(String str){

		if("X".equalsIgnoreCase(str)){
			return STRIKE;
		}

		if("/".equals(str)){
			return SPARE;
		}

		try{
			int val = Integer.parseInt(str);
			
			if(val == TOTAL_PIN){
				return STRIKE;
			}
			
			if(val < 0 || val > TOTAL_PIN){
				throw new InvalidScoreException("The input '" + str + "' is not a valid score number.");
			}
			return NUMBER;
		}catch(NumberFormatException ex){
			throw new InvalidScoreException("Could not parse the input '" + str +"' into a valid score.");
		}
	}
}
