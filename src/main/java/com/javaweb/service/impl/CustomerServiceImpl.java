package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.*;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;

    @Override
    public boolean existsByPhone(String phone) {
        return customerRepository.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest) {
        List<CustomerEntity> customerEntities = customerRepository.searchCustomers(customerSearchRequest);
        List<CustomerSearchResponse> results = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerSearchResponse customerResponse = customerConverter.toCustomerSearchResponse(customerEntity);
            results.add(customerResponse);
        }
        return results;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerConverter.toCustomerDTO(customerEntity);
        return customerDTO;
    }

    @Override
    public CustomerEntity createCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.customerEntity(customerDTO);
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    @Override
    public void contactUser(CustomerDTO customerDTO){
       CustomerEntity customerEntity = customerConverter.customerEntity(customerDTO);
       customerEntity.setStatus("Chưa xử lý");
       customerEntity.setIsActive(true);
//       // Set default email if null
//       if (customerEntity.getEmail() == null || customerEntity.getEmail().trim().isEmpty()) {
//           customerEntity.setEmail(customerDTO.getEmail());
//       }
//       // Set default demand if null
//       if (customerEntity.getDemand() == null || customerEntity.getDemand().trim().isEmpty()) {
//           customerEntity.setDemand("Không có nội dung");
//       }
       customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = customerConverter.customerEntity(customerDTO);
        customerRepository.save(customerEntity);
        return customerEntity;
    }


    @Override
    public String delete(List<Long> ids) {
        List<AssignmentCustomerEntity> assignments = assignmentCustomerRepository.findByCustomersIdIn(ids);
        assignmentCustomerRepository.deleteAll(assignments);
        customerRepository.deleteAllByIdIn(ids);
        return "Success";
    }

    @Override
    public List<StaffResponseDTO> findAssignedStaffs(Long id) {
        List<UserEntity> staffList = userRepository.findByStatusAndUserRoleEntity_RoleEntity_Code(1,"STAFF");
        List<UserEntity> assignedStaffs = userService.getUsersByCustomerId(id);
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for (UserEntity staff: staffList){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if(assignedStaffs.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        return staffResponseDTOS;
    }
}
