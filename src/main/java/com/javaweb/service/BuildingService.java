package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findBuildingById(Long id);
    String delete(List<Long> ids);
    List<StaffResponseDTO> findAssignedStaffs(Long id);
    BuildingEntity createBuilding(BuildingDTO buildingDTO);
    BuildingEntity updateBuilding(BuildingDTO buildingDTO);
}