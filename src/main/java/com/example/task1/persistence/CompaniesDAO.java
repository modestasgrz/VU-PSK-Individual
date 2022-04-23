package com.example.task1.persistence;

import com.example.task1.entities.Company;
import com.example.task1.exceptions.CompanyNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CompaniesDAO {

    @Inject
    private EntityManager em;

    public List<Company> loadAll() {
        return em.createNamedQuery("Company.findAll", Company.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Company Company){
        this.em.persist(Company);
    }

    public Company findOne(Integer id) {
        return em.find(Company.class, id);
    }

    public Company findByName(String name) throws CompanyNotFoundException {
        List<Company> companies = loadAll();
        for (Company company : companies) {
            if (company.getName().equals(name)) {
                return company;
            }
        }
        throw new CompanyNotFoundException("Company was not found in the database. " +
                "Check if naming is correct");
    }
}
