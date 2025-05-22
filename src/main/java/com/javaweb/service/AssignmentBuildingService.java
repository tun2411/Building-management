package com.javaweb.service;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;

public interface AssignmentBuildingService {
    String updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO);
}
