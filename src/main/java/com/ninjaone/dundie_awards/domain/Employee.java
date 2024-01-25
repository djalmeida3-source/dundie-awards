package com.ninjaone.dundie_awards.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dundie_awards")
    private Integer dundieAwards;

    @ManyToOne
    private Organization organization;

    @OneToMany(mappedBy = "employee")
    private List<EmployeesDundieAwards> dundieAwardsList;

    public Employee(String firstName, String lastName, Organization organization) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
    }

}