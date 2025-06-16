package com.TradingSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Trader extends Thread implements Tradeable {
    private int traderId;
    private String name;
    private Market market;
    private Map<String, Integer> portfolio = new HashMap<>();

    public Trader(int traderId, String name, Market market) {
        this.traderId = traderId;
        this.name = name;
        this.market = market;
    }

    @Override
    public void buyStock(String stockId, int quantity) {
        try {
            market.buyStock(stockId, quantity);
            portfolio.put(stockId, portfolio.getOrDefault(stockId, 0) + quantity);
            System.out.println(name + " bought " + quantity + " of " + stockId);
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
            System.out.println(name + " sold " + quantity + " of " + stockId);
        } catch (Exception e) {
            System.err.println(name + " SELL ERROR: " + e.getMessage());
        }
    }

    public void printPortfolio() {
        System.out.println("\nTrader: " + name + " (ID: " + traderId + ")");
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            System.out.println("  Stock: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }

    @Override
    public void run() {
        String[] stockIds = {"S1", "S2", "S3"};
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            String stockId = stockIds[rand.nextInt(stockIds.length)];
            int quantity = rand.nextInt(5) + 1;
            boolean buy = rand.nextBoolean();

            if (buy) {
                buyStock(stockId, quantity);
            } else {
                sellStock(stockId, quantity);
            }

            try {
                Thread.sleep(100);  
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
