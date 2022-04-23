package com.example.task1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Company.findAll", query = "select t from Company as t")
})
@Table(name = "COMPANY")
@Getter @Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Person> people = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="connection",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> outsideConnections = new ArrayList<>();

    public Company(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.example.task1.entities.Company company = (com.example.task1.entities.Company) o;
        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
