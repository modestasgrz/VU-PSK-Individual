package com.example.task1.usecases;


import lombok.Getter;
import lombok.Setter;
import com.example.task1.persistence.PeopleDAO;
import com.example.task1.entities.Person;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdatePersonDetails implements Serializable {

    private Person person;

    @Inject
    private PeopleDAO peopleDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdatePersonDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("personId"));
        this.person = peopleDAO.findOne(playerId);
    }

    @Transactional
    public String updatePersonSalary() {
        try{
            peopleDAO.update(this.person);
        } catch (OptimisticLockException e) {
            return "/personDetails.xhtml?faces-redirect=true&playerId=" + this.person.getId() + "&error=optimistic-lock-exception";
        }
        return "people.xhtml?teamId=" + this.person.getCompany().getId() + "&faces-redirect=true";
    }
}
