package com.TradingSystem;

public interface Tradeable {
    void buyStock(String stockId, int quantity) throws Exception;
    void sellStock(String stockId, int quantity) throws Exception;
}
