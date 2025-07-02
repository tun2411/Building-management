package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/building-list")
    public ModelAndView getAllBuilding(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/list");

        DisplayTagUtils.of(request,buildingSearchRequest);
        if(SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
        }
        modelAndView.addObject("modelSearch",buildingSearchRequest);
        modelAndView.addObject("staffs",userService.getStaffs());
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        List<BuildingSearchResponse> responseList = buildingService.searchBuildings(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage()-1,buildingSearchRequest.getMaxPageItems()));
        buildingSearchRequest.setListResult(responseList);
        int totalItems = buildingService.countTotalItems(buildingSearchRequest);
        buildingSearchRequest.setTotalItems(totalItems);
        modelAndView.addObject(SystemConstant.MODEL,buildingSearchRequest);
//        System.out.println(totalItems);
        return modelAndView;
    }



    @GetMapping("/admin/building-edit")
    public ModelAndView createBuilding(@ModelAttribute BuildingDTO buildingDTO){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("buildingEdit",buildingDTO);
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView updateBuilding(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findBuildingById(id);
        modelAndView.addObject("buildingEdit",buildingDTO);
        if(SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            if(!buildingService.checkAssignedStaff(id, staffId)){
                return new ModelAndView("redirect:/login?accessDenied");
            }
        }
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }


}