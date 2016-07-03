package org.darioaxel.util;

import org.apache.logging.log4j.LogManager;

public class Logger {

	private final org.apache.logging.log4j.Logger	logger;

	public Logger(final Class<?> clazz) {
		logger = LogManager.getLogger(clazz);
	}

	public void unsupported(final Object arg0) {
		logger.warn("UNSUPPORTED: " + arg0);
	}

	public void info(final Object arg0) {
		logger.info(arg0);
	}

	public void warning(final Object arg0) {
		logger.warn(arg0);
	}

	public void fine(final Object arg0) {
		logger.debug(arg0);
	}

	public void fatal(final Object arg0) {
		logger.fatal(arg0);
	}

	public void skip(final Object arg0) {
		logger.warn("Skipped (unsupported): " + arg0);
	}

	public void finer(final Object arg0) {
		logger.trace(arg0);
	}

	public void error(final Throwable t) {
		String msg;
		
		msg = (t.getLocalizedMessage() != null) ? t.getLocalizedMessage() : "No exception message available.";
		logger.error(msg, t);
	}

	public void error(final String msg) {
		logger.error(msg);
	}

	public void error(final String msg, final Throwable t) {
		logger.error(msg + "\n\t" + t.getLocalizedMessage(), t);
	}

}
