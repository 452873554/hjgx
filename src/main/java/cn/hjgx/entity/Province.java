package cn.hjgx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Province {
    private Integer id;

    @JsonProperty("name")
    private String districtName;

    private String center;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}