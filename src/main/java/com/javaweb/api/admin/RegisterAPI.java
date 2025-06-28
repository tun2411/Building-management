package com.javaweb.api.admin;

import com.javaweb.model.dto.RoleDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Map<String, String> errors = new HashMap<>();
            if (userDTO.getUserName() == null || userDTO.getUserName().trim().isEmpty()) {
                errors.put("userName", "Tên đăng nhập không được để trống");
            }
            if (userDTO.getFullName() == null || userDTO.getFullName().trim().isEmpty()) {
                errors.put("fullName", "Họ và tên không được để trống");
            }
            if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
                errors.put("password", "Mật khẩu không được để trống");
            }
            if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
                errors.put("email", "Email không được để trống");
            } else if (!userDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|vn|net|org)$")) {
                errors.put("email", "Email không hợp lệ");
            }
            if (userService.existsByUserName(userDTO.getUserName())) {
                errors.put("userName", "Tên đăng nhập đã tồn tại");
            }
            if (userService.existsByEmail(userDTO.getEmail())) {
                errors.put("email", "Email đã tồn tại");
            }

            if (!errors.isEmpty()) {
                response.setMessage("Validation failed");
                response.setDetail("Lỗi xác thực dữ liệu");
                response.setData(errors);
                return ResponseEntity.badRequest().body(response);
            }

            // Gọi service để lưu người dùng
            userService.registerUser(userDTO);

            response.setMessage("Đăng ký thành công");
            response.setDetail("Người dùng đã được tạo");
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

