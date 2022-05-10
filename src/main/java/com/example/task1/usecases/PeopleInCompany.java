package com.example.task1.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.example.task1.decorators.Note;
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

    @Inject
    private Note note;

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

    public String loadNote() {
        return note.displayNote();
    }
}
