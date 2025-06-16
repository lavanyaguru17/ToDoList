package com.TradingSystem;

public class InsufficientSharesException extends Exception {
    public InsufficientSharesException(String message) {
        super(message);
    }
}
