package com.javaweb.api.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.exception.ValidateDataCustomerException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/customers")
@RestController
public class CustomerAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        CustomerEntity customerEntity = customerService.createCustomer(customerDTO);
        System.out.println("OK");
        responseDTO.setMessage("Create Customer Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        if (customerDTO.getId() == null) {
            throw new ValidateDataCustomerException("Customer Id not be null");
        }
        ResponseDTO responseDTO = new ResponseDTO();
        CustomerEntity customerEntity = customerService.updateCustomer(customerDTO);
        System.out.println("OK");
        responseDTO.setMessage("Update Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomers(@PathVariable List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new ValidateDataCustomerException("List Customer ID not be null");
        }
        ResponseDTO responseDTO = new ResponseDTO();
        System.out.print("Delete Customer id = "+ ids);
        String results = customerService.delete(ids);
        System.out.print(results);
        responseDTO.setMessage("Delete Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/{id}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        List<StaffResponseDTO> staffResponseDTOS = customerService.findAssignedStaffs(id);
        responseDTO.setMessage("Completed");
        responseDTO.setData(staffResponseDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


}



