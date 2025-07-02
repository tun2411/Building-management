package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class BuildingDTO extends AbstractDTO{
    //private Long id;
    @NotBlank(message = "Building name not be blank!")
    private String name;
    private String street;
    private String ward;
    @NotBlank(message = "District not be blank!")
    private String district;
    private Long numberOfBasement;
    private Long floorArea;
    private String level;
    @Size(min = 1,message = "Building type is required")
    private List<String> typeCode;
    private String overTimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carFee;
    private String motoFee;
    private String waterFee;
    private String structure;
    private String direction;
    private String note;
    @NotBlank(message = "Rent Area not be blank")
    private String rentArea;
    private String managerName;
    private String managerPhone;
    @NotNull(message = "rentPrice not be null")
    @Min(value = 5,message = "Rent price must >= 5")
    private Long rentPrice;
    private String serviceFee;
    private Float brokerageFee;
    private String avatar;
    private String imageBase64;
    private String imageName;

    private Map<String,String> buildingDTOs = new HashMap<>();


    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }

}