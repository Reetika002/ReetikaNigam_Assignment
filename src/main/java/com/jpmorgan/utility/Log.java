package com.jpmorgan.utility;

import org.apache.log4j.Logger;

public class Log {
	// Initialize Log4j logs
		private static Logger Log = Logger.getLogger(Log.class.getName());

	// This is to print log for the beginning of the test scenario
	public static void startTestScenario(String sTestScenarioName) {
		Log.info("*******************************************************************************************");
		Log.info("*******************************************************************************************");
		Log.info("$$  " + sTestScenarioName + "  $$");
		Log.info("*******************************************************************************************");
		Log.info("*******************************************************************************************");
	}

	// This is to print log for the ending of the test scenario
	public static void endTestScenario() {
		Log.info("X");
		Log.info("X");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		Log.info("X");
		Log.info("X");
	}

	// Need to create these methods, so that they can be called
	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}
}