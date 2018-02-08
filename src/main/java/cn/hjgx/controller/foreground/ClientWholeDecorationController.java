package cn.hjgx.controller.foreground;

import cn.hjgx.Annotation.Login;
import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.OrderNoUtil;
import cn.hjgx.component.LoginInterceptor;
import cn.hjgx.entity.*;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/")
public class ClientWholeDecorationController {

    @Autowired
    private IWholeDecorationService iWholeDecorationService;

    @Autowired
    private IProductStyleService iProductStyleService;

    @Autowired
    private IWholeDecorationSpaceService iWholeDecorationSpaceService;

    @Autowired
    private IWholeDecorationOrderService iWholeDecorationOrderService;

    @Autowired
    private IWholeDecorationOrderDetailService iWholeDecorationOrderDetailService;

    @PostMapping("/decoration/order/save")
    @ResponseBody
    @Login
    public JsonNode whole_decoration_order(Model m,
                                           MultipartHttpServletRequest request) throws IOException {

        ResultDto resultDto = new ResultDto();

        try {

//            WholeDecorationOrder wholeDecorationOrder =
//                    JsonUtil.toPOJO(request.getParameter("wholeDecorationOrder"), new TypeReference<WholeDecorationOrder>() {});

            List<WholeDecorationOrderDetail> wholeDecorationOrderDetails =
                    JsonUtil.toPOJO(request.getParameter("wholeDecorationOrderDetails"), new TypeReference<List<WholeDecorationOrderDetail>>() {});

            WholeDecorationOrder wholeDecorationOrder = new WholeDecorationOrder();

            //生成单号
            wholeDecorationOrder.setUsername(((UserBusiness)request.getSession().getAttribute(LoginInterceptor.LOGIN_USER)).getUsername());
            wholeDecorationOrder.setOrderNo(OrderNoUtil.generateOrderNo("ZZ"));
            iWholeDecorationOrderService.insertSelective(wholeDecorationOrder);

            //批处理订单明细
            wholeDecorationOrderDetails.forEach(detail -> {
                detail.setOrderId(wholeDecorationOrder.getId());
                detail.setOrderNo(wholeDecorationOrder.getOrderNo());
            });

            iWholeDecorationOrderDetailService.batchInsert(wholeDecorationOrderDetails);
            resultDto.setMessage("整装商品订单保存成功");

        } catch (Exception e) {
            e.printStackTrace();

            resultDto.setFlag(0);
            resultDto.setMessage("整装商品订单保存异常");
        }

        return JsonUtil.toJson(resultDto);
    }

    /**
     * 跳转至整装下单页面
     * @param m
     * @return
     */
    @GetMapping("/decoration/order.html*")
    public String to_whole_decoration_order(Model m,WholeDecorationResultDto wholeDecoration) {

        try {
            //整装基本信息
            WholeDecoration wholeDecorationDB = iWholeDecorationService.selectByPrimaryKey(wholeDecoration.getId());
            m.addAttribute("wholeDecoration", wholeDecorationDB);

            //整装空间信息
            List<WholeDecorationSpaceDto> wholeDecorationSpaceDtos = iWholeDecorationSpaceService.selectByWholeDecorationId(wholeDecorationDB.getId());
            m.addAttribute("wholeDecorationSpaces", wholeDecorationSpaceDtos);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "foreground/whole_decoration/whole_decoration_order";
    }

    /**
     * 整装列表，默认查询最新的8个，不限风格
     * @param m
     * @return
     */
    @GetMapping("/decoration.html*")
    public String whole_decoration_list(Model m,WholeDecorationResultDto wholeDecoration) {

        try {
            //首页固定查询8个
            wholeDecoration.setPageSize(8);
            Pager<WholeDecorationResultDto> pager = iWholeDecorationService.getWholeDecorationPaged(wholeDecoration);
            List<ProductStyle> styles = iProductStyleService.getAllStyle();

            m.addAttribute("pager", pager);
            m.addAttribute("styles", styles);
            m.addAttribute("wholeDecoration", wholeDecoration);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "foreground/whole_decoration/whole_list";
    }

    /**
     * 整装明细
     * @param m
     * @return
     */
    @GetMapping("/decoration/detail.html*")
    public String whole_decoration_detail(Model m,WholeDecorationResultDto wholeDecoration) {

        try {
            //整装基本信息
            WholeDecoration wholeDecorationDB = iWholeDecorationService.selectByPrimaryKey(wholeDecoration.getId());
            m.addAttribute("wholeDecoration", wholeDecorationDB);

            //整装空间信息
            List<WholeDecorationSpaceDto> wholeDecorationSpaceDtos = iWholeDecorationSpaceService.selectByWholeDecorationId(wholeDecorationDB.getId());
            m.addAttribute("wholeDecorationSpaces", wholeDecorationSpaceDtos);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "foreground/whole_decoration/wholeDetail";
    }
}
