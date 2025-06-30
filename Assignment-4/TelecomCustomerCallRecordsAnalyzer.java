package com.TelecomCustomerCallRecordsAnalyzer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class TelecomCustomerCallRecordsAnalyzer {
    public static void main(String[] args) {
        CallDataStore dataStore = new CallDataStore();
        CallAnalyzer analyzer = new CallAnalyzer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Telecom Call Records CLI");
        while (true) {
            System.out.println("\n1. Add call record");
            System.out.println("2. Show longest calls");
            System.out.println("3. Filter by minimum duration");
            System.out.println("4. Frequent callers");
            System.out.println("5. Group by operator");
            System.out.println("6. Group by location");
            System.out.println("7. Show longest single call");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Caller: ");
                    String caller = scanner.nextLine();
                    System.out.print("Receiver: ");
                    String receiver = scanner.nextLine();
                    System.out.print("Start Time (yyyy-MM-ddTHH:mm): ");
                    LocalDateTime start = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("End Time (yyyy-MM-ddTHH:mm): ");
                    LocalDateTime end = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Operator: ");
                    String operator = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();

                    CallRecord record = new CallRecord(caller, receiver, start, end, operator, location);
                    dataStore.addRecord(record);
                    System.out.println("Record added!");
                    break;

                case 2:
                    List<CallRecord> topCalls = analyzer.getLongestCalls(dataStore.getRecords(), 5);
                    if (topCalls.isEmpty()) {
                        System.out.println("No call records found.");
                    } else {
                        topCalls.forEach(System.out::println);
                    }
                    break;


                case 3:
                    System.out.print("Enter min duration (in seconds): ");
                    long minDuration = scanner.nextLong();
                    List<CallRecord> filtered = analyzer.filterByDuration(dataStore.getRecords(), minDuration);
                    filtered.forEach(System.out::println);
                    break;

                case 4:
                    Map<String, Long> freq = analyzer.getFrequentCallers(dataStore.getRecords());
                    if (freq.isEmpty()) {
                        System.out.println("No call records found.");
                    } else {
                        freq.entrySet().stream()
                                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " calls"));
                    }
                    break;


                case 5:
                    Map<String, List<CallRecord>> byOperator = analyzer.groupByOperator(dataStore.getRecords());
                    if (byOperator.isEmpty()) {
                        System.out.println("No call records found.");
                    } else {
                        byOperator.forEach((op, recs) -> {
                            System.out.println("Operator: " + op);
                            recs.forEach(r -> System.out.println("  " + r));
                        });
                    }
                    break;

                case 6:
                    Map<String, List<CallRecord>> byLocation = analyzer.groupByLocation(dataStore.getRecords());
                    if (byLocation.isEmpty()) {
                        System.out.println("No call records found.");
                    } else {
                        byLocation.forEach((loc, recs) -> {
                            System.out.println("Location: " + loc);
                            recs.forEach(r -> System.out.println("  " + r));
                        });
                    }
                    break;

                case 7:
                    Optional<CallRecord> longest = analyzer.getLongestCall(dataStore.getRecords());
                    longest.ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("No calls found.")
                    );
                    break;

                case 0:
                    System.out.println("Exiting. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

