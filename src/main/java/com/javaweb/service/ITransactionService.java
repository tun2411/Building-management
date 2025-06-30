package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    TransactionEntity saveTransaction(TransactionDTO transactionDTO);
    TransactionEntity updateTransaction(Long id, TransactionDTO transactionDTO);
    void deleteTransaction(Long id);
    List<TransactionEntity> findByCustomerId(Long customerId);
    List<TransactionEntity> findByStaffId(Long staffId);
    List<TransactionEntity> getTransactionByCodeAndCustomerId(String code, Long customerId);
}