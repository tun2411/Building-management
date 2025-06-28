package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    void deleteAllByIdIn(List<Long> ids);
}
