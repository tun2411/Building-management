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
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.domain.Pageable;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(buildingSearchRequest,pageable);
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
        List<RentAreaEntity> rentAreas = rentAreaRepository.findByBuildingEntity_IdIn(ids);
        rentAreaRepository.deleteAll(rentAreas);

        List<AssignmentBuildingEntity> assignments = assignmentBuildingRepository.findByBuildingIdIn(ids);
        assignmentBuildingRepository.deleteAll(assignments);

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
        saveThumbnail(buildingDTO,buildingEntity);
        buildingRepository.save(buildingEntity);
        List<Long> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        for(Long rentArea : rentAreas){
            RentAreaEntity areaEntity = new RentAreaEntity();
            areaEntity.setValue(rentArea);
            areaEntity.setBuildingEntity(buildingEntity);
            rentAreaRepository.save(areaEntity);
        }
        return buildingEntity;
    }

    @Override
    public BuildingEntity updateBuilding(BuildingDTO buildingDTO){
        BuildingEntity entity = buildingRepository.findById(buildingDTO.getId()).get();
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        buildingEntity.setAvatar(entity.getAvatar());
        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
        rentAreaRepository.deleteAll(buildingEntity.getRentAreaEntity());
        List<Long> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        for(Long rentArea : rentAreas){
            RentAreaEntity areaEntity = new RentAreaEntity();
            areaEntity.setValue(rentArea);
            areaEntity.setBuildingEntity(buildingEntity);
            rentAreaRepository.save(areaEntity);
        }
        return buildingEntity;
    }

    @Override
    public boolean checkAssignedStaff(Long buildingId, Long staffId) {
        if (buildingId == null || staffId == null) {
            throw new IllegalArgumentException("buildingId and staffId must not be null");
        }
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        if (building.getBuildingUserEntities() == null || building.getBuildingUserEntities().isEmpty()) {
            return false;
        }
        return building.getBuildingUserEntities().stream()
                .anyMatch(assignment -> {
                    UserEntity staff = assignment.getStaff();
                    return staff != null && Objects.equals(staff.getId(), staffId);
                });
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchRequest);
    }

    @Override
    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        String imageBase64 = buildingDTO.getImageBase64();
        if (imageBase64 != null) {
            if (buildingEntity.getAvatar() != null && !path.equals(buildingEntity.getAvatar())) {
                    File file = new File("C://home/office" + buildingEntity.getAvatar());
                    file.delete();
            }
            byte[] bytes = Base64.decodeBase64(imageBase64);
            UploadFileUtils uploadFileUtils = new UploadFileUtils();
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }
}