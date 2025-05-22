package com.javaweb.api.admin;


import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/assign")
@RestController
public class AssignmentBuildingAPI {

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        if(assignmentBuildingDTO.getBuildingId() == null){
            responseDTO.setMessage("Validate Failed");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        String assignmentBuildingEntity = assignmentBuildingService.updateAssignment(assignmentBuildingDTO);
        responseDTO.setMessage("Assignment Building Updated Successfully");
        return ResponseEntity.ok().body(responseDTO);
    }
}
