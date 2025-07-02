package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    boolean existsByPhone(String phone, Long id);
    boolean existsByEmail(String email, Long id);
    void contactUser(CustomerDTO customerDTO);
    CustomerDTO findCustomerById(Long id);
    CustomerEntity createCustomer(CustomerDTO customerDTO);
    CustomerEntity updateCustomer(CustomerDTO customerDTO);
    String delete(List<Long> ids);
    List<StaffResponseDTO> findAssignedStaffs(Long id);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);
    CustomerEntity findById(Long id);
    boolean checkAssignedStaff(Long customerId, Long staffId);
}
