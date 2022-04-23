package com.example.task1.decorators;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Vision implements Note {

    @Override
    public String displayNote() {
        return "Vision of a company";
    }
}
