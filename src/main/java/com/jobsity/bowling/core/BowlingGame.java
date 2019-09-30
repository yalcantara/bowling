package com.jobsity.bowling.core;

import com.jobsity.bowling.model.Sheet;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jobsity.bowling.utils.AppUtils.*;
import static com.jobsity.bowling.utils.Constants.*;

public class BowlingGame {
	
	private final Map<String, Sheet> sheets;
	
	public BowlingGame(){
		sheets = new LinkedHashMap<>();
	}
	
	public void append(String player, String val){
		
		Sheet sheet = sheets.get(player);
		if(sheet == null){
			sheet = new Sheet();
			sheets.put(player, sheet);
		}
		
		sheet.append(val);
	}
	
	public void print(){
		PrintWriter pw = new PrintWriter(System.out);
		print(pw);
		pw.flush();
	}
	
	public void print(PrintWriter pw){
		pw.print("Frame\t\t");
		
		for(int i =1; i <= TOTAL_FRAMES; i++){
			pw.print(i);
			
			if(i  + 1 <= TOTAL_FRAMES){
				pw.print("\t\t");
			}
		}
		pw.println();
		for(Sheet sheet:sheets.values()){
			sheet.print(pw);
			pw.println();
		}
	}
}
