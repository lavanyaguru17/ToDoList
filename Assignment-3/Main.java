package com.TelecomCustomerManagementSystem;
public class Main {
    public static void main(String[] args) {
        TelecomSystem telecomsystem = new TelecomSystem();
        telecomsystem.addCustomer("C001", "Lavanya");

        Customer Lavanya = telecomsystem.getCustomer("C001");
        Lavanya.recordCall("1234567890", 5);
        Lavanya.subscribeVAS("Caller Tune");
        Lavanya.fileComplaint("Network issue in area.");
        Lavanya.viewComplaints();
        telecomsystem.displayAllCustomersSummary();

        System.out.println("---------------------------");
        System.out.println("Full Details for Lavanya: ");
        Lavanya.printFullDetails();
    }
}

