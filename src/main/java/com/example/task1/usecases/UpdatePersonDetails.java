package com.example.task1.usecases;


import com.example.task1.entities.Person;
import com.example.task1.interceptors.Log;
import com.example.task1.persistence.PeopleDAO;
import lombok.Getter;
import lombok.Setter;

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
        System.out.println("UpdatePlayerDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer personId = Integer.parseInt(requestParameters.get("personId"));
        this.person = peopleDAO.findOne(personId);
    }

    @Transactional
    @Log
    public String updatePersonSalary() {
        try{
            peopleDAO.update(this.person);
        } catch (OptimisticLockException e) {
            return "/personDetails.xhtml?faces-redirect=true&personId=" + this.person.getId() + "&error=optimistic-lock-exception";
        }
        return "people.xhtml?companyId=" + this.person.getCompany().getId() + "&faces-redirect=true";
    }
}
