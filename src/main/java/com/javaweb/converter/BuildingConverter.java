package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    // DTO -> Entity
    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntityOld = null;
        if(buildingDTO.getId() != null){
            buildingEntityOld = entityManager.find(BuildingEntity.class,buildingDTO.getId());
        }
        BuildingEntity buildingEntityNew = modelMapper.map(buildingDTO,BuildingEntity.class);
        buildingEntityNew.setType(
                String.join(",", buildingDTO.getTypeCode())
        );
        if(buildingEntityOld != null){
            buildingEntityNew.setRentAreaEntity(buildingEntityOld.getRentAreaEntity());
        }
        return buildingEntityNew;
    }

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        StringBuilder addressBuilder = new StringBuilder();
        if ((buildingEntity.getStreet() != null && !buildingEntity.getStreet().isEmpty())) {
            addressBuilder.append(buildingEntity.getStreet());
            if ((buildingEntity.getWard() != null && !buildingEntity.getWard().isEmpty()) || (buildingEntity.getDistrict() != null && !buildingEntity.getDistrict().isEmpty())) {
                addressBuilder.append(", ");
            }
        }
        if (buildingEntity.getWard() != null && !buildingEntity.getWard().isEmpty()) {
            addressBuilder.append(buildingEntity.getWard());
            if (buildingEntity.getDistrict() != null && !buildingEntity.getDistrict().isEmpty()){
                addressBuilder.append(", ");
            }

        }
        if (buildingEntity.getDistrict() != null && !buildingEntity.getDistrict().isEmpty()) {
            try {
                District districtEnum = District.valueOf(buildingEntity.getDistrict());
                addressBuilder.append(districtEnum.getName());
            } catch (IllegalArgumentException e) {
                addressBuilder.append(" ");
            }
        }
        buildingResponse.setAddress(addressBuilder.toString());
        String rentArea = buildingEntity.getRentAreaEntity().stream()
                .map(i -> i.getValue().toString())
                .collect(Collectors.joining(","));
        buildingResponse.setRentArea(rentArea);
        return buildingResponse;
    }


    //Enity-> DTO
    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity,BuildingDTO.class);
        List<String> typeCode = Arrays.stream(buildingEntity.getType().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        buildingDTO.setTypeCode(typeCode);
        String rentArea = buildingEntity.getRentAreaEntity().stream()
                .map(i -> i.getValue().toString())
                .collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentArea);
        return buildingDTO;
    }


}
