package com.TelecomCustomerCallRecordsAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class CallDataStore {
    private List<CallRecord> records = new ArrayList<>();

    public void addRecord(CallRecord record) {
        records.add(record);
    }

    public List<CallRecord> getRecords() {
        return records;
    }
}

