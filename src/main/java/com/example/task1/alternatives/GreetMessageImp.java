package com.example.task1.alternatives;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GreetMessageImp implements GreetMessage{
    @Override
    public String loadMessage() {
        return "Standard greeting message";
    }
}
