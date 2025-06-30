package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private CustomerService customerService;

    @Override
    public TransactionEntity saveTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transaction = transactionConverter.convertToEntity(transactionDTO);

        // Gán customer nếu có customerId
        if (transactionDTO.getCustomerId() != null) {
            CustomerEntity customer = customerService.findById(transactionDTO.getCustomerId());
            if (customer != null) {
                transaction.setCustomerEntity(customer);
            } else {
                throw new IllegalArgumentException("Customer not found with id: " + transactionDTO.getCustomerId());
            }
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public TransactionEntity updateTransaction(Long id, TransactionDTO transactionDTO) {
            TransactionEntity transaction = transactionConverter.convertToEntity(transactionDTO);
            TransactionEntity transactionEntity = transactionRepository.findById(id).get();
            Date createdDate = transactionEntity.getCreatedDate();
            String createdBy = transactionEntity.getCreatedBy();
            transaction.setCreatedBy(createdBy);
            transaction.setCreatedDate(createdDate);
            if (transactionDTO.getCustomerId() != null) {
                CustomerEntity customer = customerService.findById(transactionDTO.getCustomerId());
                if (customer != null) {
                    transaction.setCustomerEntity(customer);
                } else {
                    throw new IllegalArgumentException("Customer not found with id: " + transactionDTO.getCustomerId());
                }
            }
            return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionEntity> findByCustomerId(Long customerId) {
        return transactionRepository.findByCustomerEntityId(customerId);
    }

    @Override
    public List<TransactionEntity> findByStaffId(Long staffId) {
        return transactionRepository.findByStaffId(staffId);
    }

    @Override
    public List<TransactionEntity> getTransactionByCodeAndCustomerId(String code, Long customerId) {
        if (code == null || customerId == null) {
            throw new IllegalArgumentException("Code and customerId must not be null");
        }
        return transactionRepository.findByCodeAndCustomerEntityId(code, customerId);
    }
}