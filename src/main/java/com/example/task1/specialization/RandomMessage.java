package com.example.task1.specialization;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RandomMessage {
    public String loadMessage() {
        return "message from " + this.getClass().getName();
    }
}
