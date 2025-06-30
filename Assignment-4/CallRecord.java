package com.TelecomCustomerCallRecordsAnalyzer;

import java.time.Duration;
import java.time.LocalDateTime;

public class CallRecord {
    private String caller;
    private String receiver;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String operator;
    private String location;

    public CallRecord(String caller, String receiver, LocalDateTime startTime, LocalDateTime endTime, String operator, String location) {
        this.caller = caller;
        this.receiver = receiver;
        this.startTime = startTime;
        this.endTime = endTime;
        this.operator = operator;
        this.location = location;
    }

    public String getCaller() { return caller; }
    public String getReceiver() { return receiver; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public String getOperator() { return operator; }
    public String getLocation() { return location; }

    public long getDurationSeconds() {
        return Duration.between(startTime, endTime).getSeconds();
    }

    @Override
    public String toString() {
        return String.format("Caller: %s, Receiver: %s, Duration: %ds, Operator: %s, Location: %s",
                caller, receiver, getDurationSeconds(), operator, location);
    }
}

