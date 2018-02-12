package cn.hjgx.controller.api.foreground;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.paramDto.ProductSpuDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


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

    @GetMapping("/province/all")
    public JsonNode get_all_provinces() throws IOException {
        return JsonUtil.toJson(iProvinceService.getAllProvinces());
    }

    @GetMapping("/province/{province_id}/cities")
    public JsonNode get_cities(@PathVariable int province_id) throws IOException {
        return JsonUtil.toJson(iCityService.selectByProvenceId(province_id));
    }

    @GetMapping("/province/city/{city_id}/districts")
    public JsonNode get_districts(@PathVariable int city_id) throws IOException {
        return JsonUtil.toJson(iDistrictService.selectByCityId(city_id));
    }

}
