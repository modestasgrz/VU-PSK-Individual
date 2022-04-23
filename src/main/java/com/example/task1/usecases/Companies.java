package com.example.task1.usecases;

import com.example.task1.alternatives.GreetMessage;
import com.example.task1.specialization.RandomMessage;
import lombok.Getter;
import lombok.Setter;
import com.example.task1.persistence.CompaniesDAO;
import com.example.task1.entities.Company;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Companies {

    @Inject
    private CompaniesDAO companiesDAO;

    @Inject
    private GreetMessage greetMessage;

    @Inject
    private RandomMessage randomMessage;

    @Getter @Setter
    private Company companyToCreate = new Company();

    @Getter
    private List<Company> allCompanies;

    @Getter @Setter
    private int numOfCompanies;

    @PostConstruct
    public void init(){
        loadAllCompanies();
    }

    @Transactional
    public void createCompany(){
        this.companiesDAO.persist(companyToCreate);
    }

    private void loadAllCompanies(){

        this.allCompanies = companiesDAO.loadAll();
        this.numOfCompanies = this.allCompanies.size();
    }

    public String loadGreetMessage() {
        return greetMessage.loadMessage();
    }

    public String loadRandomMessage() {
        return randomMessage.loadMessage();
    }
}
