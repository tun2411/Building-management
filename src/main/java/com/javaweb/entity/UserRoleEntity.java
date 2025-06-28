package com.javaweb.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@Getter
@Setter
public class UserRoleEntity extends BaseEntity{
    @ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id",nullable = false)
	private UserEntity userEntity;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "role_id",nullable = false)
	private RoleEntity roleEntity;
}
