package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
    void deleteAllByIdIn(List<Long> ids);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(c) > 0 FROM CustomerEntity c WHERE c.phone = :phone AND c.is_Active = 1 AND c.id != :id")
    boolean existsByPhoneAndIsActiveAndIdNot(String phone, Long id);

    @Query("SELECT COUNT(c) > 0 FROM CustomerEntity c WHERE c.email = :email AND c.is_Active = 1 AND c.id != :id")
    boolean existsByEmailAndIsActiveAndIdNot(String email, Long id);

}
