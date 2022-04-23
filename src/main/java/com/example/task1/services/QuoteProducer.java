package com.example.task1.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class QuoteProducer implements Serializable {

    private List<String> quoteLibrary = Arrays.asList(
            "Today is a good day",
            "We will need a bigger boat",
            "You either die being a hero or live long enough to see yourself become the villain",
            "I'm gonna make him an offer he can't refuse",
            "May the Force be with you"
    );

    public String produceQuote() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer index = new Random().nextInt(5);
        return quoteLibrary.get(index);
    }
}