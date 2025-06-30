package com.javaweb.controller.admin;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.Status;
import com.javaweb.enums.Transaction;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.service.CustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value="customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/customer-list")
    public ModelAndView getAllCustomer(@ModelAttribute CustomerSearchRequest customerSearchRequest){
        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
//        if(SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
//            Long staffId = SecurityUtils.getPrincipal().getId();
//            customerSearchRequest.setStaffId(staffId);
//        }
        modelAndView.addObject("modelSearch",customerSearchRequest);
        modelAndView.addObject("staffs",userService.getStaffs());
        modelAndView.addObject("status", Status.getStatus());
        customerSearchRequest.setIs_Active(1L);
        List<CustomerSearchResponse> responseList = customerService.searchCustomers(customerSearchRequest);
        modelAndView.addObject("customerSearchResponses",responseList);
        return modelAndView;
    }

    @GetMapping("/admin/customer-edit")
    public ModelAndView createCustomer(@ModelAttribute CustomerDTO customerDTO){
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        modelAndView.addObject("customerEdit",customerDTO);
        modelAndView.addObject("transactionType", Transaction.getTransactionType());
        modelAndView.addObject("status", Status.getStatus());
        return modelAndView;
    }

    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView updateCustomer(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        modelAndView.addObject("customerEdit",customerDTO);
        modelAndView.addObject("transactionType", Transaction.getTransactionType());
//        if(SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
//            Long staffId = SecurityUtils.getPrincipal().getId();
//            if(!buildingService.checkAssignedStaff(id, staffId)){
//                modelAndView.setViewName("/error/404");
//                return modelAndView;
//            }
//        }
        modelAndView.addObject("status", Status.getStatus());
        List<TransactionEntity> CSKH = transactionService.getTransactionByCodeAndCustomerId("CSKH",id);
        List<TransactionEntity> DDX = transactionService.getTransactionByCodeAndCustomerId("DDX",id);
        modelAndView.addObject("CSKH",CSKH);
        modelAndView.addObject("DDX",DDX);
        return modelAndView;
    }
}
