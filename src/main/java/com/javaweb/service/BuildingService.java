package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {

    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable);

    BuildingDTO findBuildingById(Long id);
    String delete(List<Long> ids);
    List<StaffResponseDTO> findAssignedStaffs(Long id);
    BuildingEntity createBuilding(BuildingDTO buildingDTO);
    BuildingEntity updateBuilding(BuildingDTO buildingDTO);
    boolean checkAssignedStaff(Long buildingId, Long staffId);
    int countTotalItems(BuildingSearchRequest buildingSearchRequest);
    void saveThumbnail(BuildingDTO buildingDTO,BuildingEntity buildingEntity);
}