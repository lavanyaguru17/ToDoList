package com.TradingSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class Trader extends Thread implements Tradeable {
    private int traderId;
    private String name;
    private Market market;
    private Map<String, Integer> portfolio = new HashMap<>();
    private List<String> transactionHistory = new ArrayList<>();


    public Trader(int traderId, String name, Market market) {
        this.traderId = traderId;
        this.name = name;
        this.market = market;
    }

    
    public int getTraderId() {
        return traderId;
    }
    
    
    public String getTraderName() {
        return name;
    }

    @Override
    public void buyStock(String stockId, int quantity) {
        try {
            market.buyStock(stockId, quantity);
            portfolio.put(stockId, portfolio.getOrDefault(stockId, 0) + quantity);
            String record = "BOUGHT " + quantity + " of " + stockId;
            transactionHistory.add(record);
            System.out.println(name + " " + record);
        } catch (Exception e) {
            System.err.println(name + " BUY ERROR: " + e.getMessage());
        }
    }

    @Override
    public void sellStock(String stockId, int quantity) {
        try {
            if (!portfolio.containsKey(stockId) || portfolio.get(stockId) < quantity) {
                throw new InsufficientSharesException(name + " does not own enough shares of " + stockId);
            }
            market.sellStock(stockId, quantity);
            portfolio.put(stockId, portfolio.get(stockId) - quantity);
            String record = "SOLD " + quantity + " of " + stockId;
            transactionHistory.add(record);
            System.out.println(name + " " + record);
        } catch (Exception e) {
            System.err.println(name + " SELL ERROR: " + e.getMessage());
        }
    }
    
    public void printPortfolio() {
        System.out.println("\nTrader: " + name + " (ID: " + traderId + ")");
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            System.out.println("  Stock: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }

        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("  No transactions performed.");
        } else {
            for (String record : transactionHistory) {
                System.out.println("  " + record);
            }
        }
    }

}
