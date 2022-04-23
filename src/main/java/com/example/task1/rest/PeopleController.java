package com.example.task1.rest;

import com.example.task1.entities.Person;
import com.example.task1.exceptions.CompanyNotFoundException;
import com.example.task1.persistence.CompaniesDAO;
import com.example.task1.persistence.PeopleDAO;
import com.example.task1.DTOs.PersonDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/people")
public class PeopleController {

    @Inject
    private PeopleDAO peopleDAO;

    @Inject
    private CompaniesDAO companiesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Person person = peopleDAO.findOne(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setSalary(person.getSalary());
        personDTO.setCompanyName(person.getCompany().getName());

        return Response.ok(personDTO).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PersonDTO personDTO) {
        Person personToCreate = new Person();
        try {
            personToCreate.setSalary(personDTO.getSalary());
            personToCreate.setName(personDTO.getName());
            personToCreate.setCompany(companiesDAO.findByName(personDTO.getCompanyName()));
            peopleDAO.persist(personToCreate);
            return Response.ok().build();
        }
        catch (CompanyNotFoundException e) {
            System.out.println("ERROR - CompanyNotFound Exception - " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer personId, PersonDTO personDTO) {
        try {
            Person personToUpdate = peopleDAO.findOne(personId);
            if (personToUpdate == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            personToUpdate.setName(personDTO.getName());
            personToUpdate.setSalary(personDTO.getSalary());
            personToUpdate.setCompany(companiesDAO.findByName(personDTO.getCompanyName()));
            peopleDAO.update(personToUpdate);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            System.out.println("ERROR - OptimisticLockException Exception - " + ole.getMessage());
            return Response.status(Response.Status.CONFLICT).build();
        } catch (CompanyNotFoundException e) {
            System.out.println("ERROR - CompanyNotFound Exception - " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
