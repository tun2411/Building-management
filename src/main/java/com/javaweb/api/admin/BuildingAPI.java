package com.javaweb.api.admin;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.exception.ValidateDataBuildingException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/api/buildings")
@RestController
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;


    @PostMapping
    public ResponseEntity<?> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity buildingEntity = buildingService.createBuilding(buildingDTO);
        System.out.println("OK");
        responseDTO.setMessage("Create Building Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateBuilding(@Valid @RequestBody BuildingDTO buildingDTO){
        if (buildingDTO.getId() == null) {
            throw new ValidateDataBuildingException("Building Id not be null");
        }
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity buildingEntity = buildingService.updateBuilding(buildingDTO);
        System.out.println("OK");
        responseDTO.setMessage("Update Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBuildings(@PathVariable List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new ValidateDataBuildingException("List Building ID not be null");
        }
        ResponseDTO responseDTO = new ResponseDTO();
        System.out.print("Delete Building id = "+ ids);
        String results = buildingService.delete(ids);
        System.out.print(results);
        responseDTO.setMessage("Delete Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/{id}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        List<StaffResponseDTO> staffResponseDTOS = buildingService.findAssignedStaffs(id);
        responseDTO.setMessage("Completed");
        responseDTO.setData(staffResponseDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }



}


