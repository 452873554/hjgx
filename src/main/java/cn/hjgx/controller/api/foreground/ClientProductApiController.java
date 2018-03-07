package cn.hjgx.controller.api.foreground;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.entity.City;
import cn.hjgx.entity.District;
import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.Province;
import cn.hjgx.entity.paramDto.ProductSpuDto;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ClientProductApiController {


    @Autowired
    private IProductSpuService iProductSpuService;
    @Autowired
    private IProductSkuService iProductSkuService;
    @Autowired
    private IProvinceService iProvinceService;
    @Autowired
    private ICityService iCityService;
    @Autowired
    private IDistrictService iDistrictService;

    @GetMapping("/product/detail*")
    public JsonNode get_product_detail(ProductSpu productSpu) throws IOException {
        ProductSpuDto spuDto = iProductSpuService.getProductSpuDetail(productSpu.getSpu());
        spuDto.setSkus(iProductSkuService.selectBySpu(productSpu.getSpu()));
        return JsonUtil.toJson(spuDto);
    }

    @GetMapping("/province/default")
    public JsonNode get_default() throws IOException {

        ResultDto resultDto = new ResultDto();
        try {
            List<Province> provinces = iProvinceService.getAllProvinces();
            List<City> cities = iCityService.selectByProvenceId(provinces.get(0).getId());
            List<District> districts = iDistrictService.selectByCityId(cities.get(0).getId());

            Map<String, Object> adminDivision = Maps.newHashMap();
            adminDivision.put("provinces", provinces);
            adminDivision.put("cities", cities);
            adminDivision.put("districts", districts);
            resultDto.setMapData(adminDivision);
        } catch (Exception e) {
            resultDto.setFlag(0);
            resultDto.setMessage("获取行政区划失败！请重试或联系系统管理员");
        }

        return JsonUtil.toJson(resultDto);
    }

    @GetMapping("/province/all")
    public JsonNode get_all_provinces() throws IOException {
        return JsonUtil.toJson(iProvinceService.getAllProvinces());
    }

    @GetMapping("/province/{province_id}/cities")
    public JsonNode get_cities(@PathVariable int province_id) throws IOException {

        ResultDto resultDto = new ResultDto();
        try {
            List<City> cities = iCityService.selectByProvenceId(province_id);
            List<District> districts = iDistrictService.selectByCityId(cities.get(0).getId());

            Map<String, Object> adminDivision = Maps.newHashMap();
            adminDivision.put("cities", cities);
            adminDivision.put("districts", districts);
            resultDto.setMapData(adminDivision);

        } catch (Exception e) {
            resultDto.setFlag(0);
            resultDto.setMessage("获取行政区划失败！请重试或联系系统管理员");
        }

        return JsonUtil.toJson(resultDto);
    }

    @GetMapping("/province/city/{city_id}/districts")
    public JsonNode get_districts(@PathVariable int city_id) throws IOException {

        ResultDto resultDto = new ResultDto();
        try {
            List<District> districts = iDistrictService.selectByCityId(city_id);
            Map<String, Object> adminDivision = Maps.newHashMap();
            adminDivision.put("districts", districts);
            resultDto.setMapData(adminDivision);
        } catch (Exception e) {
            resultDto.setFlag(0);
            resultDto.setMessage("获取行政区划失败！请重试或联系系统管理员");
        }

        return JsonUtil.toJson(resultDto);
    }

}
