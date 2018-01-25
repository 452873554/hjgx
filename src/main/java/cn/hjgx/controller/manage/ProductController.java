package cn.hjgx.controller.manage;

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


@Controller
@RequestMapping("/backstage")
public class ProductController {

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
    private Environment env;

    @PostMapping("/product/save-or-update")
    @ResponseBody
    public JsonNode bg_product_save_or_update(Model m,
                                              MultipartHttpServletRequest request,
                                              @RequestParam(value = "previewImages", required = false) MultipartFile[] previewImages) throws IOException {
        ResultDto resultDto = new ResultDto();

        try {

            ProductSpu productSpu = JsonUtil.toPOJO(request.getParameter("productSpu"), new TypeReference<ProductSpu>() {});
            List<ProductSku> productSkus = JsonUtil.toPOJO(request.getParameter("specifications"), new TypeReference<List<ProductSku>>() {});
            ProductSpuAttrs productSpuAttrs = JsonUtil.toPOJO(request.getParameter("attr"), new TypeReference<ProductSpuAttrs>() {});

            //先校验上传的文件
            if (!ObjectUtils.isEmpty(previewImages)) {

                List<ProductSpuImgs> productSpuImgses = Lists.newArrayList();

                String previewPath = env.getProperty("image.preview.path", String.class);
                String previewRequestPath = env.getProperty("image.preview.reqRoute", String.class);

                for(MultipartFile file:previewImages){

                    ProductSpuImgs productSpuImg = new ProductSpuImgs();
                    //保存新文件
                    //生成文件名
                    String previewName = UUID.randomUUID().toString();
                    FileUtils.copyToFile(file.getInputStream(), new File(previewPath + File.separator + previewName));

                    productSpuImg.setImageName(previewName);
                    productSpuImg.setSpu(productSpu.getSpu());
                    productSpuImg.setImageUrl(previewRequestPath + File.separator + previewName);

                    productSpuImgses.add(productSpuImg);

                }
                iProductSpuImgsService.batchInsert(productSpuImgses);

            }else{
                resultDto.setFlag(0);
                resultDto.setMessage("必须上传预览图");
                return JsonUtil.toJson(resultDto);
            }




            int i = iProductSpuService.insertSelective(productSpu);
            if (i == 0){
                resultDto.setFlag(0);
                resultDto.setMessage("未添加商品信息");
                return JsonUtil.toJson(resultDto);
            }

            //保存商品规格
            int j = iProductSkuService.batchInsert(productSkus);
            resultDto.setMessage("商品信息添加成功");

            //保存商品属性
            int y =iProductSpuAttrsService.insertSelective(productSpuAttrs);

        } catch (Exception e) {
            e.printStackTrace();
            resultDto.setFlag(0);
            resultDto.setMessage("商品信息添加异常");
        }

        return JsonUtil.toJson(resultDto);

    }

    @GetMapping("/product/save.html")
    public String to_bg_product_save_or_update(Model m) {

        List<Brand> brands = brandService.getAllBrands();
        List<Supplier> suppliers = iSupplierService.getAllSuppliers();
        List<Category> categories = iCategoryService.getAllCategory();
        List<ProductStyle> styles = iProductStyleService.getAllStyle();
        List<ProductSpuAttrsTemplate> templates = iProductSpuAttrsTemplateService.getAllTemplate();


        m.addAttribute("brands", brands);
        m.addAttribute("suppliers", suppliers);
        m.addAttribute("categories", categories);
        m.addAttribute("styles", styles);
        m.addAttribute("templates", templates);

        m.addAttribute("page_title", "商品编辑");//标题
        m.addAttribute("current_menu", "product_list");//当前菜单高亮
        return "manage/product/product_save_or_update";
    }

    @GetMapping("/product/list.html*")
    public String to_bg_product_list(Model m, ProductSpuResultDto productSpu) {

        try {

            Pager<ProductSpuResultDto> pager = iProductSpuService.getProductSpuPaged(productSpu);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(productSpu);
            m.addAttribute("page_title", "商品管理");//标题
            m.addAttribute("current_menu", "product_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/product/list.html");//分页片段url
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/product/product_list";
    }
}
