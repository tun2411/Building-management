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
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity users;
//
//	@ManyToOne
//    @JoinColumn(name = "role_id")
//    private RoleEntity roles;
//
//    public UserEntity getUsers() {
//        return users;
//    }
//
//    public void setUsers(UserEntity users) {
//        this.users = users;
//    }
//
//    public RoleEntity getRoles() {
//        return roles;
//    }
//
//    public void setRoles(RoleEntity roles) {
//        this.roles = roles;
//    }
//
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

}
