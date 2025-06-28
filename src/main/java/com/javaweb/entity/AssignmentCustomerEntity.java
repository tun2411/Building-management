package com.javaweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentcustomer")
@Getter
@Setter
public class AssignmentCustomerEntity extends BaseEntity{

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "staffid",nullable = false)
    private UserEntity staffs;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customerid",nullable = false)
    private CustomerEntity customers;

}
