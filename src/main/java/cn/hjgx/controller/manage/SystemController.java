package cn.hjgx.controller.manage;

import cn.hjgx.Utils.HttpUtil;
import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.entity.City;
import cn.hjgx.entity.District;
import cn.hjgx.entity.Province;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.ICityService;
import cn.hjgx.service.IDistrictService;
import cn.hjgx.service.IProvinceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */

@Controller
@RequestMapping("/backstage")
public class SystemController {

    Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private IProvinceService iProvinceService;

    @Autowired
    private ICityService iCityService;

    @Autowired
    private IDistrictService iDistrictService;

    @Autowired
    private Environment env;

    /**
     * 更新行政区划
     *
     * @return
     */
    @GetMapping("/update-administrative-division")
    @ResponseBody
    public JsonNode update_administrative_division() throws IOException {

        ResultDto resultDto = new ResultDto();

        try {
            //获取高德地图地图API返回数据
            String responseContent = HttpUtil.getInstance().sendHttpGet(env.getProperty("amap.district"));
            System.out.println("reponse content:" + responseContent);

            JsonNode result = JsonUtil.toJson(responseContent);

            //请求成功
            if (result.get("status").asInt() == 1) {

                //清空现有数据
                iProvinceService.truncateData();
                iCityService.truncateData();
                iDistrictService.truncateData();

                //获取中国根节点
                JsonNode china = result.get("districts").get(0);

                //获取行政区域
                JsonNode provinces = china.get("districts");
                //遍历保存，并保存下级行政区划
                for (JsonNode provinceNode : provinces) {

                    //省
                    Province province = JsonUtil.toPOJO(provinceNode, new TypeReference<Province>() {
                    });
                    iProvinceService.insertSelective(province);

                    //市
                    JsonNode citys = provinceNode.get("districts");
                    for (JsonNode cityNode : citys) {
                        City city = JsonUtil.toPOJO(cityNode, new TypeReference<City>() {
                        });
                        city.setProvinceId(province.getId());
                        iCityService.insertSelective(city);

                        //区域
                        JsonNode districts = cityNode.get("districts");
                        for (JsonNode districtNode : districts) {

                            District district = JsonUtil.toPOJO(districtNode, new TypeReference<District>() {
                            });
                            district.setCityId(city.getId());
                            iDistrictService.insertSelective(district);
                        }
                    }
                }

            } else {
                resultDto.setFlag(0);
                resultDto.setMessage("第三方API返回请求失败标识");
            }
        } catch (Exception e) {
            logger.error("处理从第三方获取的行政区划信息异常:{}", e);
            resultDto.setFlag(0);
            resultDto.setMessage("处理从第三方获取的行政区划信息异常");
        }
        return JsonUtil.toJson(resultDto);
    }
}
