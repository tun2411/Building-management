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

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private List<UserEntity> user = new ArrayList<>();

//    @OneToMany(mappedBy="roles",fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();

    @OneToMany(mappedBy = "roleEntity")
    private List<UserRoleEntity> roleUserEntity;





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
