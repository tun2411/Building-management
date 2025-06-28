package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.repository.*;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AssignmentCustomerServiceImpl implements AssignmentCustomerService {

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;



    @Override
    public String updateAssignment(AssignmentCustomerDTO assignmentCustomerDTO) {
        assignmentCustomerRepository.deleteByCustomers_Id(assignmentCustomerDTO.getCustomerId());
        List<Long> assigns = assignmentCustomerDTO.getStaffs();
        for(Long assign : assigns){
            AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
            assignmentCustomerEntity.setCustomers(customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get());
            assignmentCustomerEntity.setStaffs(userRepository.findById(assign).get());
            assignmentCustomerRepository.save(assignmentCustomerEntity);
            System.out.println("xxx");
        }
        return "Success";
    }
}
