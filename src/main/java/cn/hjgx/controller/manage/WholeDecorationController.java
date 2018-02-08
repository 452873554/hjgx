package cn.hjgx.controller.manage;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.ProductSpace;
import cn.hjgx.entity.ProductStyle;
import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.WholeDecorationItem;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.entity.paramDto.WholeDecorationItemDto;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/backstage")
public class WholeDecorationController {

    @Autowired
    private IProductStyleService iProductStyleService;

    @Autowired
    private IProductSpaceService iProductSpaceService;

    @Autowired
    private IWholeDecorationService iWholeDecorationService;

    @Autowired
    private IWholeDecorationSpaceService iWholeDecorationSpaceService;

    @Autowired
    private IWholeDecorationItemService iWholeDecorationItemService;

    @Autowired
    private IWholeDecorationSpuService iWholeDecorationSpuService;

    @Autowired
    private Environment env;

    @PostMapping("/whole-decoration/save-or-update")
    @ResponseBody
    public JsonNode bg_whole_decoration_save_or_update(Model m,
                                                       MultipartHttpServletRequest request,
                                                       @RequestParam(value = "bannerImg", required = false) MultipartFile bannerImg,
                                                       @RequestParam(value = "previewImg", required = false) MultipartFile previewImg,
                                                       @RequestParam(value = "spaceImgs", required = false) MultipartFile[] spaceImgs) throws IOException {
        ResultDto resultDto = new ResultDto();
        try {
            //空间图片校验
            List<WholeDecorationSpaceDto> wds = JsonUtil.toPOJO(request.getParameter("spaces"), new TypeReference<List<WholeDecorationSpaceDto>>() {
            });
            if (spaceImgs.length != wds.size()) {
                resultDto.setFlag(0);
                resultDto.setMessage("空间预览图必须一一对应的上传");
                return JsonUtil.toJson(resultDto);
            }

            //保存整装信息
            WholeDecoration wd = JsonUtil.toPOJO(request.getParameter("wholeDecoration"), new TypeReference<WholeDecoration>() {
            });

            //保存整装预览图片
            String previewPath = env.getProperty("image.preview.path", String.class);
            String previewRequestPath = env.getProperty("image.preview.reqRoute", String.class);

            String previewName = UUID.randomUUID().toString();
            FileUtils.copyToFile(previewImg.getInputStream(), new File(previewPath + File.separator + previewName));
            wd.setPreviewImgUrl(previewRequestPath + File.separator + previewName);

            //保存整装banner图片
            String bannerName = UUID.randomUUID().toString();
            FileUtils.copyToFile(bannerImg.getInputStream(), new File(previewPath + File.separator + bannerName));
            wd.setBannerImgUrl(previewRequestPath + File.separator + bannerName);

            iWholeDecorationService.insertSelective(wd);

            //逐个保存整装空间信息
            String spacePreviewName = "";
            WholeDecorationSpaceDto tempSpace = null;

            for (int i = 0; i < wds.size(); i++) {
                //保存空间图片信息
                tempSpace = wds.get(i);
                spacePreviewName = UUID.randomUUID().toString();
                FileUtils.copyToFile(spaceImgs[i].getInputStream(), new File(previewPath + File.separator + spacePreviewName));
                tempSpace.setPreviewImgUrl(previewRequestPath + File.separator + spacePreviewName);
                tempSpace.setWholeDecorationId(wd.getId());

                //保存当前空间信息
                iWholeDecorationSpaceService.insertSelective(tempSpace);
                final int spaceId = tempSpace.getId();
                List<WholeDecorationItemDto> items = tempSpace.getWholeDecorationItemDtos();

                //保存空间对应的项目
                items.forEach(itemDto -> {
                    itemDto.setSpaceId(spaceId);
                    iWholeDecorationItemService.insertSelective(itemDto);

                    //保存项目对应的SPU
                    itemDto.getWholeDecorationSpus().forEach(spu -> {
                        spu.setItemId(itemDto.getId());
                        iWholeDecorationSpuService.insertSelective(spu);
                    });

                });
            }
            resultDto.setMessage("保存整装商品正常");


        } catch(Exception e){
            e.printStackTrace();
            resultDto.setFlag(0);
            resultDto.setMessage("保存整装商品异常");
        }
        return JsonUtil.toJson(resultDto);

    }


    @GetMapping("/whole-decoration/save.html")
    public String to_bg_whole_decoration_save_or_update(Model m) {

        List<ProductStyle> styles = iProductStyleService.getAllStyle();
        List<ProductSpace> spaces = iProductSpaceService.getAllProductSpaces();

        m.addAttribute("styles", styles);
        m.addAttribute("spaces", spaces);

        m.addAttribute("page_title", "整装编辑");//标题
        m.addAttribute("current_menu", "whole_decoration_list");//当前菜单高亮
        return "manage/whole_decoration/whole_decoration_save_or_update";

    }

    @GetMapping("/whole-decoration/list.html*")
    public String to_bg_whole_decoration_list(Model m,WholeDecorationResultDto wholeDecoration) {

        try {

            Pager<WholeDecorationResultDto> pager = iWholeDecorationService.getWholeDecorationPaged(wholeDecoration);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(wholeDecoration);
            m.addAttribute("page_title", "整装管理");//标题
            m.addAttribute("current_menu", "whole_decoration_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/whole-decoration/list.html");//分页片段url
            m.addAttribute("pathParam", pathParam);
            m.addAttribute("wholeDecoration",wholeDecoration);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/whole_decoration/whole_decoration_list";

    }
}
