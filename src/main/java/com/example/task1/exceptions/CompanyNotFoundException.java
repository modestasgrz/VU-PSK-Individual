package com.example.task1.exceptions;

public class CompanyNotFoundException extends Exception{
    public CompanyNotFoundException() {
        super();
    }
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
