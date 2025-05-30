package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/building-list")
    public ModelAndView getAllBuilding(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        BuildingDTO model = new BuildingDTO();
        
        // Set default values for pagination
        if (buildingSearchRequest.getPage() == null) {
            buildingSearchRequest.setPage(1);
        }
        if (buildingSearchRequest.getMaxPageItems() == null) {
            buildingSearchRequest.setMaxPageItems(5);
        }
        
        // Get total items and list result
        List<BuildingSearchResponse> responseList = buildingService.searchBuildings(buildingSearchRequest);
        int totalItems = responseList.size();
        
        // Calculate start and end index for current page
        int startIndex = (buildingSearchRequest.getPage() - 1) * buildingSearchRequest.getMaxPageItems();
        int endIndex = Math.min(startIndex + buildingSearchRequest.getMaxPageItems(), totalItems);
        
        // Get sublist for current page
        List<BuildingSearchResponse> pageList = responseList.subList(startIndex, endIndex);
        
        // Set model attributes
        model.setListResult(pageList);
        model.setTotalItems(totalItems);
        model.setMaxPageItems(buildingSearchRequest.getMaxPageItems());
        model.setPage(buildingSearchRequest.getPage());
        
        // Add to modelAndView
        modelAndView.addObject("model", model);
        modelAndView.addObject("modelSearch", buildingSearchRequest);
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        modelAndView.addObject("formUrl", request.getRequestURI());

        return modelAndView;
    }

    //modelAndView.addObject("modelSearch",buildingSearchRequest); (một view có thể trả ra nhiều model)

    //project 2 tra ve list building va quang ve ben view
    //tim kiem thì kết hợp quăng ra view còn thêm sửa xoá làm ở API
    //HIển thị ra - xử lý ở Controller(API)


    @GetMapping("/admin/building-edit")
    public ModelAndView createBuilding(@ModelAttribute BuildingDTO buildingDTO){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("buildingEdit",buildingDTO);
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject(SystemConstant.BUTTON, "Thêm");
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView updateBuilding(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findBuildingById(id);
        modelAndView.addObject("buildingEdit",buildingDTO);
        modelAndView.addObject(SystemConstant.BUTTON, "Chỉnh sửa");
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }


}
