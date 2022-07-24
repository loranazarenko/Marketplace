package com.project.marketplace.exception;

public class MoneyNotEnoughException extends RuntimeException {
    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
