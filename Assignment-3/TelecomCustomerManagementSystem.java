package com.TelecomCustomerManagementSystem;
import java.util.*;

class Call {
    private String numberDialed;
    private int durationInMinutes;

    public Call(String numberDialed, int durationInMinutes) {
        this.numberDialed = numberDialed;
        this.durationInMinutes = durationInMinutes;
    }

    public String toString() {
        return "\tCalled: " + numberDialed + " | Duration: " + durationInMinutes + " mins";
    }
}


class Complaint {
    private String complaintText;
    private Date complaintDate;

    public Complaint(String complaintText) {
        this.complaintText = complaintText;
        this.complaintDate = new Date();
    }

    public String toString() {
        return "\tComplaint on " + complaintDate + ": " + complaintText;
    }
}


class Customer {
    private String customerId;
    private String name;

    private List<Call> callHistory;
    private Set<String> subscribedVAS;
    private List<Complaint> complaints;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.callHistory = new ArrayList<>();
        this.subscribedVAS = new HashSet<>();
        this.complaints = new ArrayList<>();
    }

    public void recordCall(String number, int duration) {
        callHistory.add(new Call(number, duration));
    }

    public void subscribeVAS(String service) {
        subscribedVAS.add(service);
    }

    public void unsubscribeVAS(String service) {
        subscribedVAS.remove(service);
    }

    public void fileComplaint(String complaintText) {
        complaints.add(new Complaint(complaintText));
    }

    public void viewComplaints() {
        System.out.println("Complaints for " + name + ":");
        for (Complaint c : complaints) {
            System.out.println(c);
        }
    }



    public String toString() {
        return  "\tCustomer ID   : " + customerId + "\n" +
                "\tName          : " + name + "\n" +
                "\tCalls Made    : " + callHistory.size() + "\n" +
                "\tVAS Subscribed: " + (subscribedVAS.isEmpty() ? "None" : subscribedVAS) + "\n" +
                "\tComplaints    : " + complaints.size();
    }


    public void printFullDetails() {
        System.out.println(this);
        System.out.println("Call History: ");
        for (Call call : callHistory) {
            System.out.println(call);
        }

        System.out.println("Complaints: ");
        for (Complaint c : complaints) {
            System.out.println(c);
        }
    }
}


class TelecomSystem {
    private Map<String, Customer> customers;

    public TelecomSystem() {
        customers = new HashMap<>();
    }

    public void addCustomer(String customerId, String name) {
        if (!customers.containsKey(customerId)) {
            customers.put(customerId, new Customer(customerId, name));
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Customer ID already exists.");
        }
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }

    public void displayAllCustomersSummary() {
        System.out.println("Customer Summary: ");
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
    }
}

