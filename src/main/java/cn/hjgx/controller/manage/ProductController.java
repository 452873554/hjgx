package cn.hjgx.controller.manage;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.*;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.SitePageParam;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/backstage")
public class ProductController {

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ISupplierService iSupplierService;

    @GetMapping("/product/save.html")
    public String to_bg_product_save_or_update(Model m) {

        List<Brand> brands = brandService.getAllBrands();
        List<Supplier> suppliers = iSupplierService.getAllSuppliers();

        m.addAttribute("brands", brands);
        m.addAttribute("suppliers", suppliers);

        m.addAttribute("page_title", "商品编辑");//标题
        m.addAttribute("current_menu", "product_list");//当前菜单高亮
        return "manage/product/product_save_or_update";
    }

    @GetMapping("/product/list.html*")
    public String to_bg_product_list(Model m, ProductSpu productSpu) {

        try {

            String pathParam = ParamUtil.parseBeanToPathParam(productSpu);
            m.addAttribute("page_title", "商品管理");//标题
            m.addAttribute("current_menu", "product_list");//当前菜单高亮
            m.addAttribute("curUrl", "/manage/product/list.html");//分页片段url
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/product/product_list";
    }
}
