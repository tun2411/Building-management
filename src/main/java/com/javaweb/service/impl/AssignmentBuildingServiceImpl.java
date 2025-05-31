package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
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
        BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        building.getUsers().clear();
        for (Long staffId : assignmentBuildingDTO.getStaffs()) {
            UserEntity staff = userRepository.findById(staffId).get();
            building.getUsers().add(staff);
        }
        buildingRepository.save(building);
        return "Success";
    }


}
