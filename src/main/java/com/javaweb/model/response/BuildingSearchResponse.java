package com.javaweb.model.response;


import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BuildingSearchResponse extends AbstractDTO {
//	private Date createdDate;
	private String name;
	private String address;
	private Long numberOfBasement;
	private String managerName;
	private String managerPhone;
	private Long floorArea;
	private String rentArea;
    private String emptyArea;
    private Long rentPrice;
    private String serviceFee;
    private Float brokerageFee;

}
