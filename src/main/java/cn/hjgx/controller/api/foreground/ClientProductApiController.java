package cn.hjgx.controller.api.foreground;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.*;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;
import cn.hjgx.entity.paramDto.ProductSpuDto;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class ClientProductApiController {


    @Autowired
    private IProductSpuService iProductSpuService;


    @Autowired
    private IProductSkuService iProductSkuService;

    @GetMapping("/product/detail*")
    public JsonNode get_product_detail(ProductSpu productSpu) throws IOException {

        ProductSpuDto spuDto = iProductSpuService.getProductSpuDetail(productSpu.getSpu());
        spuDto.setSkus(iProductSkuService.selectBySpu(productSpu.getSpu()));

        return JsonUtil.toJson(spuDto);
    }

}
