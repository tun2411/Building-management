package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class ContactAPI {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/lien-he")
    public ResponseEntity<ResponseDTO> contactUser(@RequestBody CustomerDTO customerDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            Map<String, String> errors = new HashMap<>();
            if (customerDTO.getFullName() == null || customerDTO.getFullName().trim().isEmpty()) {
                errors.put("fullName", "Họ và tên không được để trống");
            }
            if (customerDTO.getPhone() == null || customerDTO.getPhone().trim().isEmpty()) {
                errors.put("phone", "Số điện thoại không được để trống");
            } else if (!customerDTO.getPhone().matches("^(03|05|07|08|09)\\d{8}$")) {
                errors.put("phone", "Số điện thoại không hợp lệ");
            } else if (customerService.existsByPhone(customerDTO.getPhone(),null)) {
                errors.put("phone", "Số điện thoại đã tồn tại trong hệ thống");
            }
            if (customerDTO.getEmail() != null && !customerDTO.getEmail().trim().isEmpty()) {
                if (!customerDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|vn|net|org)$")) {
                    errors.put("email", "Email không đúng định dạng");
                } else if (customerService.existsByEmail(customerDTO.getEmail(),null)) {
                    errors.put("email", "Email đã tồn tại");
                }
            }
            if (!errors.isEmpty()) {
                response.setMessage("Validation failed");
                response.setDetail("Lỗi xác thực dữ liệu");
                response.setData(errors);
                return ResponseEntity.badRequest().body(response);
            }
            customerService.contactUser(customerDTO);
            response.setMessage("Gửi thông tin thành công");
            response.setData(null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Lỗi server");
            response.setDetail("Lỗi: " + e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
