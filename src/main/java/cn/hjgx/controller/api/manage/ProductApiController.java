package cn.hjgx.controller.api.manage;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.*;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/backstage/api")
public class ProductApiController {

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ISupplierService iSupplierService;

    @Autowired
    private IProductSpuService iProductSpuService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IProductStyleService iProductStyleService;

    @Autowired
    private IProductSkuService iProductSkuService;

    @Autowired
    private IProductSpuAttrsService iProductSpuAttrsService;

    @Autowired
    private IProductSpuAttrsTemplateService iProductSpuAttrsTemplateService;

    @Autowired
    private IProductSpuImgsService iProductSpuImgsService;

    @Autowired
    private IProductSpaceService iProductSpaceService;

    @Autowired
    private Environment env;

    @GetMapping("/product/detail*")
    @ResponseBody
    public JsonNode get_product_spu(Model m,String spu) throws IOException {
        ProductSpu productSpu = iProductSpuService.getProductSpu(spu);
        return JsonUtil.toJson(productSpu);
    }

    @GetMapping("/product/list*")
    public JsonNode to_bg_product_list(ProductSpuResultDto productSpu) throws IOException {

        ResultDto resultDto = new ResultDto();
        try {

            Map<String,Object> resultMap = Maps.newHashMap();

            Pager<ProductSpuResultDto> pager = iProductSpuService.getProductSpuWithOutPageHelper(productSpu);
            String pathParam = ParamUtil.parseBeanToPathParam(productSpu);

            resultMap.put("pager",pager);
            resultMap.put("curUrl", "/backstage/api/product/list.html");//分页片段url
            resultMap.put("pathParam", pathParam);

            resultDto.setMapData(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return JsonUtil.toJson(resultDto);
    }
}
