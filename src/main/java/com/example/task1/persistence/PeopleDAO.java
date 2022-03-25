package com.example.task1.persistence;

import com.example.task1.entities.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PeopleDAO {

    @Inject
    private EntityManager em;

    public void persist(Person person){
        this.em.persist(person);
    }

    public Person findOne(Integer id){
        return em.find(Person.class, id);
    }

    public Person update(Person person){
        return em.merge(person);
    }
}
