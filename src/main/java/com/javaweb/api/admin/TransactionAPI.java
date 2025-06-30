package com.javaweb.api.admin;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAPI {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            TransactionEntity transaction = transactionService.saveTransaction(transactionDTO);
            response.setData(transaction);
            response.setMessage("Thêm giao dịch thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Thêm giao dịch thất bại");
            response.setDetail(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Cập nhật giao dịch
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTransaction(@PathVariable("id") Long id, @RequestBody TransactionDTO transactionDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            transactionDTO.setId(id); // Đặt ID từ path variable
            TransactionEntity transaction = transactionService.updateTransaction(id,transactionDTO);
            response.setData(transaction);
            response.setMessage("Cập nhật giao dịch thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Cập nhật giao dịch thất bại");
            response.setDetail(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Xóa giao dịch
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTransaction(@PathVariable("id") Long id, @RequestParam(value = "customerId", required = false) Long customerId) {
        ResponseDTO response = new ResponseDTO();
        try {
            transactionService.deleteTransaction(id);
            response.setMessage("Xóa giao dịch thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Xóa giao dịch thất bại");
            response.setDetail(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
