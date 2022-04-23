package com.example.task1.specialization;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;

@RequestScoped
@Specializes
public class SpecializedRandomMessage extends RandomMessage{
}
