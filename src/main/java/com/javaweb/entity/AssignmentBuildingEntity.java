package com.javaweb.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "assignmentbuilding")
@Getter
@Setter
public class AssignmentBuildingEntity extends BaseEntity {

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "staffid",nullable = false)
    private UserEntity staff;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "buildingid",nullable = false)
    private BuildingEntity building;

}

