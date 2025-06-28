package com.javaweb.repository;

import com.javaweb.entity.AssignmentCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity,Long> {

    void deleteByCustomersId(Long id);
    List<AssignmentCustomerEntity> findByCustomersId(Long buildingId);
    List<AssignmentCustomerEntity> findByCustomersIdIn(List<Long> ids);
    void deleteByCustomers_Id(Long id);


}
