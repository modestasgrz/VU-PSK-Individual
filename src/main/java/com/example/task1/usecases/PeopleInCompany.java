package com.example.task1.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import com.example.task1.persistence.PeopleDAO;
import com.example.task1.persistence.CompaniesDAO;
import com.example.task1.entities.Person;
import com.example.task1.entities.Company;

@Model
public class PeopleInCompany implements Serializable {

    @Inject
    private CompaniesDAO companiesDAO;

    @Inject
    private PeopleDAO peopleDAO;

    @Getter @Setter
    private Company company;

    @Getter @Setter
    private Double salary;

    @Getter @Setter
    private Person personToCreate = new Person();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer companyId = Integer.parseInt(requestParameters.get("companyId"));
        this.company = companiesDAO.findOne(companyId);
    }

    @Transactional
    public void createPerson() {
        personToCreate.setCompany(this.company);
        peopleDAO.persist(personToCreate);
    }
}
