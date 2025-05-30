package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingSearchRequest extends AbstractDTO<BuildingSearchResponse> {
    private String name;
    private Long floorArea;
    private String district;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private String level;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> typeCode;
    private Integer page;
    private Integer maxPageItems;
    private String sortName;
    private String sortBy;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPageItems() {
        return maxPageItems;
    }

    public void setMaxPageItems(Integer maxPageItems) {
        this.maxPageItems = maxPageItems;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
