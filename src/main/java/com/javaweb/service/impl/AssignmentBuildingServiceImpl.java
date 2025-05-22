package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public String updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        assignmentBuildingRepository.deleteByBuilding_Id(assignmentBuildingDTO.getBuildingId());
        List<Long> assigns = assignmentBuildingDTO.getStaffs();
        for(Long assign : assigns){
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuilding(buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get());
            assignmentBuildingEntity.setStaff(userRepository.findById(assign).get());
            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }
        return "Success";
    }


}
