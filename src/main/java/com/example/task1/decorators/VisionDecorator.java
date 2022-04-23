package com.example.task1.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class VisionDecorator implements Note{

    @Inject @Delegate @Any Note note;

    @Override
    public String displayNote() {
        return "Decorated " + note.displayNote();
    }
}
