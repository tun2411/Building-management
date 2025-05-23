package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity,Long> {

    void deleteByBuildingId(Long id);
    List<AssignmentBuildingEntity> findByBuildingId(Long buildingId);
    List<AssignmentBuildingEntity> findByBuildingIdIn(List<Long> ids);
    void deleteByBuilding_Id(Long id);


}
