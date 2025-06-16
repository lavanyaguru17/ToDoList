package com.TradingSystem;

public class Stock {
    private String stockId;
    private String stockName;
    private double pricePerShare;
    private int availableShares;

    public Stock(String stockId, String stockName, double pricePerShare, int availableShares) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
        this.availableShares = availableShares;
    }

    public String getStockId() { return stockId; }
    public String getStockName() { return stockName; }
    public double getPricePerShare() { return pricePerShare; }

    public synchronized int getAvailableShares() {
        return availableShares;
    }

    public synchronized void increaseShares(int quantity) {
        this.availableShares += quantity;
    }

    public synchronized void decreaseShares(int quantity) {
        this.availableShares -= quantity;
    }
}

