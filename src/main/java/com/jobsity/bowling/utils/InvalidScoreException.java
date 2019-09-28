package com.jobsity.bowling.utils;

public class InvalidScoreException extends RuntimeException {

	public InvalidScoreException() {
	}

	public InvalidScoreException(String message) {
		super(message);
	}

	public InvalidScoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidScoreException(Throwable cause) {
		super(cause);
	}

	public InvalidScoreException(String message, Throwable cause, boolean enableSuppression,
								 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
