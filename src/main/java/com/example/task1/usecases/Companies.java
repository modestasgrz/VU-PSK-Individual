package com.example.task1.usecases;

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

    @Getter @Setter
    private Company companyToCreate = new Company();

    @Getter
    private List<Company> allCompanies;

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
    }
}
