package com.javaweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javaweb.enums.District;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "building")
@Getter
@Setter
public class BuildingEntity extends BaseEntity{

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    //@Enumerated(EnumType.STRING)
    @Column(name = "district")
    private String district;

    //private District district;

    @OneToMany(mappedBy = "buildingEntity",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    @JsonManagedReference
    private List<RentAreaEntity> rentAreaEntity;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice",nullable = false)
    private Long rentPrice;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "carfee")
    private String carFee;

    @Column(name = "motofee")
    private String motoFee;

    @Column(name = "overtimefee")
    private String overTimeFee;

    @Column(name = "waterfee")
    private String waterFee;

    @Column(name = "electricityfee")
    private String electricityFee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "renttime")
    private String rentTime;

    @Column(name = "decorationtime")
    private String decorationTime;

    @Column(name = "brokeragefee")
    private Float brokerageFee;

    @Column(name = "note")
    private String note;
    @Column(name = "type")
    private String type;

    @Column(name = "linkofbuilding")
    private String linkOfBuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhone;

}
