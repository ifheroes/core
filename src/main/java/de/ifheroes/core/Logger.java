package de.ifheroes.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public enum LogLevel {
        INFO, DEBUG, WARNING, ERROR
    }

    private LogLevel currentLogLevel;

    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Private constructor (Singleton pattern)
    public Logger(LogLevel logLevel) {
        this.currentLogLevel = logLevel;
    }

    private void log(LogLevel level, String message) {
        if (level.ordinal() >= currentLogLevel.ordinal()) {
            System.out.println(formatLog(level, message));
        }
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void warn(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private String formatLog(LogLevel level, String message) {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%s [%s] %s", dtFormatter.format(now), level, message);
    }
    
    public void setLogLevel(LogLevel logLevel) {
        this.currentLogLevel = logLevel;
    }
	
}
