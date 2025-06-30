package com.javaweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity extends BaseEntity{

    private static final long serialVersionUID = -4988455421375043688L;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "demand")
    private String demand;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Long is_Active;

    @OneToMany(mappedBy = "customers")
    @JsonManagedReference
    private List<AssignmentCustomerEntity> customerUserEntities;

    @OneToMany(mappedBy = "customerEntity",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TransactionEntity> transactionEntities;

}
