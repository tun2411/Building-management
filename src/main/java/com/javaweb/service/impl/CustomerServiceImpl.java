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
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private TransactionRepository transactionRepository;

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;

    @Override
    public boolean existsByPhone(String phone, Long id) {
        return customerRepository.existsByPhoneAndIsActiveAndIdNot(phone, id != null ? id : -1L);
    }

    @Override
    public boolean existsByEmail(String email, Long id) {
        return customerRepository.existsByEmailAndIsActiveAndIdNot(email, id != null ? id : -1L);
    }

    @Override
    public List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        customerSearchRequest.setIs_Active(1L);
        List<CustomerEntity> customerEntities = customerRepository.searchCustomers(customerSearchRequest,pageable);
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
        return customerConverter.toCustomerDTO(customerEntity);
    }

    @Override
    public CustomerEntity createCustomer(CustomerDTO customerDTO) {
        if (existsByPhone(customerDTO.getPhone(),null)) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống.");
        }
        if (existsByEmail(customerDTO.getEmail(),null)) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        }
        customerDTO.setIs_Active(1L);
        CustomerEntity customerEntity = customerConverter.customerEntity(customerDTO);
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    @Override
    public void contactUser(CustomerDTO customerDTO){
       CustomerEntity customerEntity = customerConverter.customerEntity(customerDTO);
       customerEntity.setStatus("Chưa xử lý");
       customerEntity.setIs_Active(1L);
       customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerDTO customerDTO){
        if (existsByPhone(customerDTO.getPhone(),customerDTO.getId())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống.");
        }
        if (existsByEmail(customerDTO.getEmail(),customerDTO.getId())) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        }
        customerDTO.setIs_Active(1L);
        CustomerEntity customer = customerRepository.findById(customerDTO.getId()).get();
        Date createdDate = customer.getCreatedDate();
        String createdBy = customer.getCreatedBy();
        CustomerEntity customerEntity = customerConverter.customerEntity(customerDTO);
        customerEntity.setCreatedDate(createdDate);
        customerEntity.setCreatedBy(createdBy);
        customerRepository.save(customerEntity);
        return customerEntity;
    }


    @Override
    public String delete(List<Long> ids) {
        List<AssignmentCustomerEntity> assignments = assignmentCustomerRepository.findByCustomersIdIn(ids);
        assignmentCustomerRepository.deleteAll(assignments);
        for (Long id:ids){
            CustomerEntity customerEntity = customerRepository.findById(id).get();
            customerEntity.setIs_Active(0L);
            customerRepository.save(customerEntity);
            transactionRepository.deleteByCustomerEntity_Id(id);
        }
//        customerRepository.deleteAllByIdIn(ids);
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

    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItem(customerSearchRequest);
    }

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public boolean checkAssignedStaff(Long customerId, Long staffId) {
            if (customerId == null || staffId == null) {
                throw new IllegalArgumentException("customerId and staffId must not be null");
            }
            CustomerEntity customer = customerRepository.findById(customerId).get();
            if (customer.getCustomerUserEntities() == null || customer.getCustomerUserEntities().isEmpty()) {
                return false;
            }
            return customer.getCustomerUserEntities().stream()
                    .anyMatch(assignment -> {
                        UserEntity staff = assignment.getStaffs();
                        return staff != null && Objects.equals(staff.getId(), staffId);
                    });
    }
}
