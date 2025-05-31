package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = -6525302831793188081L;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;



//    @OneToMany(mappedBy = "roleEntity")
//    private List<UserRoleEntity> roleUserEntity;


    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<UserEntity> userEntities;


//    @ManyToMany(mappedBy = "roleEntities",fetch = FetchType.LAZY)
//    private List<UserEntity> userEntities;


//	public List<UserRoleEntity> getRoleUserEntity() {
//		return roleUserEntity;
//	}
//
//	public void setRoleUserEntity(List<UserRoleEntity> roleUserEntity) {
//		this.roleUserEntity = roleUserEntity;
//	}


}
