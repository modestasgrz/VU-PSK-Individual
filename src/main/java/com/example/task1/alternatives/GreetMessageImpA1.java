package com.example.task1.alternatives;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class GreetMessageImpA1 implements GreetMessage{
    @Override
    public String loadMessage() {
        return "Greet Message from alternative 1";
    }
}
