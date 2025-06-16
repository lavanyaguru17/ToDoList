package com.TradingSystem;

import java.util.HashMap;
import java.util.Map;

public class Market {
    private Map<String, Stock> stocks = new HashMap<>();

    public synchronized void addStock(Stock stock) {
        stocks.put(stock.getStockId(), stock);
    }

    public synchronized Stock getStock(String stockId) throws StockNotFoundException {
        Stock stock = stocks.get(stockId);
        if (stock == null) {
            throw new StockNotFoundException("Stock ID " + stockId + " not found in the market.");
        }
        return stock;
    }

    public synchronized void buyStock(String stockId, int quantity) throws Exception {
        Stock stock = getStock(stockId);
        if (stock.getAvailableShares() < quantity) {
            throw new InsufficientMarketSharesException("Not enough shares available in the market.");
        }
        stock.decreaseShares(quantity);
    }

    public synchronized void sellStock(String stockId, int quantity) throws Exception {
        Stock stock = getStock(stockId);
        stock.increaseShares(quantity);
    }

    public void printMarketStatus() {
        System.out.println("\n\t\t\tMarket Stock Status\t\t\t");
        for (Stock stock : stocks.values()) {
            System.out.println(stock.getStockName() + " - Available: " + stock.getAvailableShares());
        }
    }
}
