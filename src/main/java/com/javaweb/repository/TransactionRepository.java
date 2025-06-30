package com.javaweb.repository;


import com.javaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    // Tìm tất cả giao dịch theo customerId
    List<TransactionEntity> findByCustomerEntityId(Long customerId);

    // Tìm tất cả giao dịch theo staffId
    List<TransactionEntity> findByStaffId(Long staffId);

    List<TransactionEntity> findByCodeAndCustomerEntityId(String code, Long customerId);

    void deleteByCustomerEntity_Id(Long id);
}
