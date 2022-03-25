package com.example.task1.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class EmployeeNumberGenerator implements Serializable {

    public Integer generateSalary() {
        Integer emploeeNumber = new Random().nextInt();
        return emploeeNumber;
    }
}