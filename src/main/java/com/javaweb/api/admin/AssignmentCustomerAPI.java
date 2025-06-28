package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/assignCustomer")
@RestController
public class AssignmentCustomerAPI {
    @Autowired
    private AssignmentCustomerService assignmentCustomerService;

    @PostMapping
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        if(assignmentCustomerDTO.getCustomerId() == null){
            responseDTO.setMessage("Validate Failed");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        String assignmentCustomerEntity = assignmentCustomerService.updateAssignment(assignmentCustomerDTO);
        responseDTO.setMessage("Assignment Customer Updated Successfully");
        return ResponseEntity.ok().body(responseDTO);
    }
}
