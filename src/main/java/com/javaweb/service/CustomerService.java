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

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    void contactUser(CustomerDTO customerDTO);
    CustomerDTO findCustomerById(Long id);
    CustomerEntity createCustomer(CustomerDTO customerDTO);
    CustomerEntity updateCustomer(CustomerDTO customerDTO);
    String delete(List<Long> ids);
    List<StaffResponseDTO> findAssignedStaffs(Long id);
}
