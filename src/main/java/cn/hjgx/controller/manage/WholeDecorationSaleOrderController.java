package cn.hjgx.controller.manage;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.ProductSpace;
import cn.hjgx.entity.ProductStyle;
import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.WholeDecorationOrder;
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
public class WholeDecorationSaleOrderController {

    @Autowired
    private IProductStyleService iProductStyleService;

    @Autowired
    private IProductSpaceService iProductSpaceService;

    @Autowired
    private IWholeDecorationService iWholeDecorationService;

    @Autowired
    private IWholeDecorationOrderService iWholeDecorationOrderService;

    @Autowired
    private IWholeDecorationItemService iWholeDecorationItemService;

    @Autowired
    private IWholeDecorationSpuService iWholeDecorationSpuService;

    @Autowired
    private Environment env;

    @GetMapping("/whole-decoration-order/list.html*")
    public String to_bg_whole_decoration_order_list(Model m, WholeDecorationOrder wholeDecorationOrder) {

        try {

            Pager<WholeDecorationOrder> pager = iWholeDecorationOrderService.getWholeDecorationOrderPaged(wholeDecorationOrder);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(wholeDecorationOrder);
            m.addAttribute("page_title", "整装订单管理");//标题
            m.addAttribute("current_menu", "whole_decoration_order_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/whole-decoration-order/list.html");//分页片段url
            m.addAttribute("wholeDecorationOrder", wholeDecorationOrder);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/whole_decoration_sale_order/sale_order_list";

    }
}
