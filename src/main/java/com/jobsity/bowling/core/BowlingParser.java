package com.jobsity.bowling.core;

import com.jobsity.bowling.model.PlayerEntry;

public interface BowlingParser extends AutoCloseable {
	
	public void init();
	
	public PlayerEntry next();
}
