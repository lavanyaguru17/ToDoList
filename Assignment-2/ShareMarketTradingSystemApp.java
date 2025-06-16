package com.TradingSystem;

import java.util.Scanner;

public class ShareMarketTradingSystemApp {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();

        System.out.print("Enter number of stocks to add: ");
        int numStocks = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numStocks; i++) {
            System.out.print("\nEnter Stock ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Stock Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Stock Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter Quantity Available: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            market.addStock(new Stock(id, name, price, quantity));
        }

        Trader[] traders = new Trader[3];
        for (int i = 0; i < traders.length; i++) {
            System.out.print("\nEnter name for Trader " + (i + 1) + ": ");
            String name = scanner.nextLine();
            traders[i] = new Trader(i + 1, name, market);
        }

        for (Trader trader : traders) {
            trader.start();
        }

        for (Trader trader : traders) {
            trader.join();
        }

        System.out.println("\n\t\t\tFinal Trader Portfolios\t\t\t");
        for (Trader trader : traders) {
            trader.printPortfolio();
        }

        market.printMarketStatus();
        scanner.close();
    }
}

