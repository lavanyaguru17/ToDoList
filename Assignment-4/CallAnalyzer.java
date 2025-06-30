package com.TelecomCustomerCallRecordsAnalyzer;

import java.util.*;
import java.util.stream.Collectors;

public class CallAnalyzer {

    public List<CallRecord> getLongestCalls(List<CallRecord> records, int topN) {
        return records.stream()
                .sorted(Comparator.comparingLong(CallRecord::getDurationSeconds).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<CallRecord> filterByDuration(List<CallRecord> records, long minDurationSec) {
        return records.stream()
                .filter(r -> r.getDurationSeconds() >= minDurationSec)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getFrequentCallers(List<CallRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(CallRecord::getCaller, Collectors.counting()));
    }

    public Map<String, List<CallRecord>> groupByOperator(List<CallRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(CallRecord::getOperator));
    }

    public Map<String, List<CallRecord>> groupByLocation(List<CallRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(CallRecord::getLocation));
    }

    public Optional<CallRecord> getLongestCall(List<CallRecord> records) {
        return records.stream()
                .max(Comparator.comparingLong(CallRecord::getDurationSeconds));
    }
}

