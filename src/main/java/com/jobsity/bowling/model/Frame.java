package com.jobsity.bowling.model;

import com.jobsity.bowling.utils.InvalidScoreException;
import com.jobsity.bowling.utils.InvalidStateException;

import java.io.Serializable;

import static com.jobsity.bowling.utils.AppUtils.*;
import static com.jobsity.bowling.utils.Constants.*;

public class Frame implements Serializable {
	
	
	
	private final int num;
	private Integer first;
	private Integer second;
	private Integer last; //used only in the 10th frame.
	
	public Frame(int num) {
		checkParamBetween("num", 1, 10, num);
		this.num = num;
	}
	
	private void checkSum(int pinfalls) {
		int sum = first + pinfalls;
		if (sum > TOTAL_PIN) {
			throw new InvalidScoreException("The sum of the pin falls for the first and " +
					"second shots, can not exceed " + TOTAL_PIN + ". First: " + first + "," +
					" second: " + pinfalls + ".");
		}
	}
	
	private void unsupported(PinfallType type) {
		throw new InvalidScoreException("Unsupported score type " + type + ".");
	}
	
	public int getNum() {
		return num;
	}
	
	public Integer getFirst() {
		return first;
	}
	
	public void setFirst(String val) {
		PinfallType type = PinfallType.parse(val);
		switch (type) {
			case SPARE:
				throw new InvalidScoreException("The first shot of a frame can not be a spare " +
						"(/)" +
						".");
			case NUMBER:
				int num = Integer.parseInt(val);
				first = num;
				break;
			case STRIKE:
				first = STRIKE_VALUE;
				break;
			default:
				unsupported(type);
		}
		
		second = null;
		last = null;
	}
	
	public Integer getSecond() {
		return second;
	}
	
	public void setSecond(String val) {
		
		if (first == null) {
			throw new InvalidScoreException("The first score must be set before assigning the " +
					"value for the second shot.");
		}
		PinfallType type = PinfallType.parse(val);
		switch (type) {
			case SPARE:
				second = TOTAL_PIN - first;
				break;
			case NUMBER:
				int pinfalls = Integer.parseInt(val);
				checkSum(pinfalls);
				second = pinfalls;
				break;
			case STRIKE:
				if(num < 10){
					throw new InvalidScoreException("For this frame (" + num+ ") the strike can only happen in the first shot.");
				}
				
				if(first < TOTAL_PIN){
					throw new InvalidScoreException("The first shot was not a strike (" + first +").");
				}
				second = STRIKE_VALUE;
				break;
			default:
				unsupported(type);
		}
		last = null;
	}
	
	public Integer getLast() {
		return last;
	}
	
	public void setLast(String val) {
		if (first == null || second == null) {
			throw new InvalidScoreException("The first and second score must be set before assigning the " +
					"value for the last shot.");
		}
		PinfallType type = PinfallType.parse(val);
		switch (type) {
			case SPARE:
				throw new InvalidScoreException("Can not set an spare for the extra shot in the 10th frame.");
			case NUMBER:
				int num = Integer.parseInt(val);
				last = num;
				break;
			case STRIKE:
				last = STRIKE_VALUE;
				break;
			default:
				unsupported(type);
		}
	}
	
	public boolean isReady() {
		if (num < 10) {
			if(first == null){
				return false;
			}
			
			if(first == STRIKE_VALUE){
				return true;
			}
			
			return second != null;
		}
		
		if(first == null || second == null){
			return false;
		}
		
		
		
		//A strike on the 10'th frame gets 2 more shots
		if(first == STRIKE_VALUE){
			return second != null && last != null;
		}
		
		//If the second is a spare, then the user get's one extra shot
		if(first + second == TOTAL_PIN){
			return last != null;
		}
		
		return true;
	}
	
	public int directSum(){
		int ans = 0;
		
		ans += (first == null)?0:first;
		ans += (second == null)?0:second;
		if(num == 10){
			//the 10th frame has the special case
			ans += (last == null)?0:last;
		}
		
		return ans;
	}
	
	public void append(String val){
		if(first == null){
			setFirst(val);
			return;
		}
		
		if(second == null){
			setSecond(val);
			return;
		}
		
		if(last == null){
			setLast(val);
			return;
		}
		throw new InvalidStateException("Can not append another score to this frame.");
	}
	
	@Override
	public String toString() {
		return "Frame{" +
				"num=" + num +
				", first=" + first +
				", second=" + second +
				", last=" + last +
				'}';
	}
}
