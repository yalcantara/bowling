package com.jobsity.bowling;

import com.jobsity.bowling.model.Sheet;
import org.junit.Test;

import static org.junit.Assert.*;

public class SheetTest {
	
	
	@Test
	public void testScore1(){
		Sheet s = new Sheet();
		s.append("x"); //1st frame
		s.append("7"); //2nd frame
		s.append("/");
		s.append("9"); //3rd frame
		s.append("0");
		s.append("x"); //4th frame
		s.append("0"); //5th frame
		s.append("8");
		s.append("8"); //6th frame
		s.append("/");
		s.append("f"); //7th frame
		s.append("6");
		s.append("x"); //8th frame
		s.append("x"); //9th frame
		s.append("x"); //10th frame
		s.append("8");
		s.append("1");
		
		assertEquals(20, s.computeScore(1));
		assertEquals(39, s.computeScore(2));
		assertEquals(48, s.computeScore(3));
		assertEquals(66, s.computeScore(4));
		assertEquals(74, s.computeScore(5));
		assertEquals(84, s.computeScore(6));
		assertEquals(90, s.computeScore(7));
		assertEquals(120, s.computeScore(8));
		assertEquals(148, s.computeScore(9));
		assertEquals(167, s.computeScore(10));
	}
	
	@Test
	public void testScore2(){
		Sheet s = new Sheet();
		s.append("3"); //1st frame
		s.append("/");
		s.append("6"); //2nd frame
		s.append("3");
		s.append("x"); //3rd frame
		s.append("8"); //4th frame
		s.append("1");
		s.append("x"); //5th frame
		s.append("x"); //6th frame
		s.append("9"); //7th frame
		s.append("0");
		s.append("7"); //8th frame
		s.append("/");
		s.append("4"); //9th frame
		s.append("4");
		s.append("x"); //10th frame
		s.append("9");
		s.append("0");
		
		assertEquals(16, s.computeScore(1));
		assertEquals(25, s.computeScore(2));
		assertEquals(44, s.computeScore(3));
		assertEquals(53, s.computeScore(4));
		assertEquals(82, s.computeScore(5));
		assertEquals(101, s.computeScore(6));
		assertEquals(110, s.computeScore(7));
		assertEquals(124, s.computeScore(8));
		assertEquals(132, s.computeScore(9));
		assertEquals(151, s.computeScore(10));
	}
	
	@Test
	public void testFrameScore(){
		
		Sheet s = new Sheet();
		s.append("3"); // 1st frame
		assertEquals(false, s.isReady(1));
		s.append("2");
		assertEquals(true, s.isReady(1));
		int score = s.computeFrameScore(1);
		assertEquals(5, score);
		
		s.append("x"); //2nd frame
		assertEquals(true, s.isReady(2));
		//we need to set the next 2 balls
		s.append("x"); //3rd frame
		s.append("x"); //4th frame
		score = s.computeFrameScore(2);
		assertEquals(30, score);
		
		s.append("7"); //5th frame
		s.append("/");
		assertEquals(true, s.isReady(5));
		assertEquals(20, s.computeFrameScore(4));
		
		s.append("1"); //6th frame
		s.append("9");
		s.append("x"); //7th frame
		s.append("4"); //8th frame
		s.append("/");
		s.append("2"); //9th frame
		s.append("/");
		
		assertEquals(true, s.isReady(9));
		assertEquals(12, s.computeFrameScore(8));
		
		s.append("x");
		s.append("x");
		s.append("x");
		assertEquals(true, s.isReady(10));
		assertEquals(60, s.computeFrameScore(10));
	}
}
