package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.utils.*;
import com.javaweb.service.BuildingService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(buildingSearchRequest);
        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingResponse = buildingConverter.toBuildingSearchResponse(buildingEntity);
            results.add(buildingResponse);
        }
        return results;
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingConverter.toBuildingDTO(buildingEntity);
        return buildingDTO;
    }

    @Override
    public String delete(List<Long> ids) {
        buildingRepository.deleteAllByIdIn(ids);
        return "Success";
    }

    @Override
    public List<StaffResponseDTO> findAssignedStaffs(Long id) {
        List<UserEntity> staffList = userRepository.findByStatusAndUserRoleEntity_RoleEntity_Code(1,"STAFF");
        List<UserEntity> assignedStaffs = userService.getUsersByBuildingId(id);
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for (UserEntity staff: staffList){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if(assignedStaffs.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        return staffResponseDTOS;
    }

    @Override
    public BuildingEntity createBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        List<Long> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for(Long rentArea : rentAreas){
            RentAreaEntity areaEntity = new RentAreaEntity();
            areaEntity.setValue(rentArea);
            areaEntity.setBuildingEntity(buildingEntity);
            rentAreaEntities.add(areaEntity);
        }
        buildingEntity.setRentAreaEntity(rentAreaEntities);
        return buildingRepository.save(buildingEntity);
    }


    @Override
    public BuildingEntity updateBuilding(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        List<Long> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for(Long rentArea : rentAreas){
            RentAreaEntity areaEntity = new RentAreaEntity();
            areaEntity.setValue(rentArea);
            areaEntity.setBuildingEntity(buildingEntity);
            rentAreaEntities.add(areaEntity);
        }
        buildingEntity.setRentAreaEntity(rentAreaEntities);
        return buildingRepository.save(buildingEntity);
    }

//    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
//        String path = "/building/" + buildingDTO.getImageName();
//        if (null != buildingDTO.getImageBase64()) {
//            if (null != buildingEntity.getAvatar()) {
//                if (!path.equals(buildingEntity.getAvatar())) {
//                    File file = new File("C://home/office" + buildingEntity.getAvatar());
//                    file.delete();
//                }
//            }
//            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
//            uploadFileUtils.writeOrUpdate(path, bytes);
//            buildingEntity.setAvatar(path);
//        }
//    }
}
