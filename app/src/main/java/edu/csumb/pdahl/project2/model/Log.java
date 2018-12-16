package edu.csumb.pdahl.project2.model;
/**
 * Title: Log.java
 * Abstract: File with constructor, getters and setters for logs.
 * Name: Pernille Dahl
 * Date: 2018-Dec-16
 */

public class Log {
    private String timestamp;
    private TransactionType type;
    private String log;

    public Log(String timestamp, TransactionType type, String log) {
        this.timestamp = timestamp;
        this.type = type;
        this.log = log;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

