package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.enums.Status;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value="customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/customer-list")
    public ModelAndView getAllBuilding(@ModelAttribute CustomerSearchRequest customerSearchRequest){
        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
//        if(SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
//            Long staffId = SecurityUtils.getPrincipal().getId();
//            customerSearchRequest.setStaffId(staffId);
//        }
        modelAndView.addObject("modelSearch",customerSearchRequest);
        modelAndView.addObject("staffs",userService.getStaffs());
        modelAndView.addObject("status", Status.getStatus());
        List<CustomerSearchResponse> responseList = customerService.searchCustomers(customerSearchRequest);
        modelAndView.addObject("customerSearchResponses",responseList);

        return modelAndView;
    }


}
