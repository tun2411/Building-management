package com.javaweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -4988455421375043688L;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
//    private List<RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    @JsonManagedReference
    private List<UserRoleEntity> userRoleEntity;

    @OneToMany(mappedBy = "staff")
    @JsonManagedReference
    private List<AssignmentBuildingEntity> userBuildingEntities;

    public List<RoleEntity> getRoles() {
        List<RoleEntity> roles = new ArrayList<>();
        for (UserRoleEntity userRole : userRoleEntity) {
            roles.add(userRole.getRoleEntity());
        }
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.userRoleEntity = roles.stream()
                .map(role -> {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setRoleEntity(role);
                    userRoleEntity.setUserEntity(this);
                    return userRoleEntity;
                })
                .collect(Collectors.toList());
    }

}
