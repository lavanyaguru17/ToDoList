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

        
        System.out.print("\nEnter number of traders: ");
        int numTraders = Integer.parseInt(scanner.nextLine());

        Trader[] traders = new Trader[numTraders];
        for (int i = 0; i < numTraders; i++) {
            String traderName;
            do {
                System.out.print("Enter name for Trader " + (i + 1) + ": ");
                traderName = scanner.nextLine().trim();
            } while (traderName.isEmpty());

            traders[i] = new Trader(i + 1, traderName, market);
        }


        for (Trader trader : traders) {
            trader.start();
        }

        for (Trader trader : traders) {
            trader.join();
        }
        
        
        for (Trader trader : traders) {
            System.out.println("\n\t\t\tTrader " + trader.getTraderName() + " (ID: " + trader.getTraderId() + ")");

            while (true) {
                System.out.println("1. BUY");
                System.out.println("2. SELL");
                System.out.println("3. EXIT");
                System.out.print("Enter choice: ");

                String choice = scanner.nextLine().trim();

                if (choice.equals("3")) {
                    break;  
                }

                System.out.print("Enter Stock ID: ");
                String stockId = scanner.nextLine().trim().toUpperCase();

                System.out.print("Enter Quantity: ");
                int quantity;
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Try again.");
                    continue;
                }

                switch (choice) {
                    case "1":
                        trader.buyStock(stockId, quantity);
                        break;
                    case "2":
                        trader.sellStock(stockId, quantity);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }
        }


        
        
        
        System.out.println("\n\t\t\tFinal Trader Portfolios\t\t\t");
        for (Trader trader : traders) {
            trader.printPortfolio();
        }

        market.printMarketStatus();
        scanner.close();
    }
}

