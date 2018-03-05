package cn.hjgx.controller.foreground;

import cn.hjgx.Annotation.Login;
import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.Utils.OrderNoUtil;
import cn.hjgx.component.LoginInterceptor;
import cn.hjgx.controller.manage.ProductController;
import cn.hjgx.entity.*;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;
import cn.hjgx.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class ClientWholeDecorationController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

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

    @Autowired
    private IProductSkuService iProductSkuService;

    @Autowired
    private IProvinceService iProvinceService;

    /**
     * 配置单页面，确认整装订单
     * @param m
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/decoration/order/confirm")
    @ResponseBody
    @Login
    public JsonNode confirm_decoration_order(Model m,
                                           MultipartHttpServletRequest request) throws IOException {

        ResultDto resultDto = new ResultDto();

        try {
            int wholeDecorationId = Integer.valueOf(request.getParameter("wholeDecorationId"));

            //获取整装商品信息
            WholeDecoration wholeDecorationDb = iWholeDecorationService.selectByPrimaryKey(wholeDecorationId);
            if (ObjectUtils.isEmpty(wholeDecorationDb)) {
                logger.error("整装商品id[{}]不存在", wholeDecorationId);
                throw new RuntimeException("整装商品不存在");
            }

            List<WholeDecorationOrderDetail> wholeDecorationOrderDetails =
                    JsonUtil.toPOJO(request.getParameter("wholeDecorationOrderDetails"), new TypeReference<List<WholeDecorationOrderDetail>>() {
                    });

            //计算订单总价，为每个SKU设置属性和下单时的价格
            double paymentTotal = 0;
            ProductSku tempProductSku;
            for (WholeDecorationOrderDetail detail : wholeDecorationOrderDetails) {
                tempProductSku = iProductSkuService.selectBySku(detail.getSku());
                if (ObjectUtils.isEmpty(tempProductSku)) {
                    logger.error("sku[{}]不存在", detail.getSku());
                    throw new RuntimeException("订单详情中保存不存在的sku");
                }
                paymentTotal += detail.getQty() * tempProductSku.getRetailPrice();

                detail.setPrice(tempProductSku.getRetailPrice());
                detail.setProductAttrs(tempProductSku.getSpecification());
            }

            WholeDecorationOrder wholeDecorationOrder = new WholeDecorationOrder();

            //保存整装订单
            wholeDecorationOrder.setUsername(((UserBusiness) request.getSession().getAttribute(LoginInterceptor.LOGIN_USER)).getUsername());
            wholeDecorationOrder.setOrderNo(OrderNoUtil.generateOrderNo("ZZ"));
            wholeDecorationOrder.setWholeDecorationId(wholeDecorationDb.getId());
            wholeDecorationOrder.setWholeDecorationName(wholeDecorationDb.getName());
            wholeDecorationOrder.setPaymentAmount(paymentTotal);
            iWholeDecorationOrderService.insertSelective(wholeDecorationOrder);

            //批量设置为订单明细设置订单ID和订单单号
            wholeDecorationOrderDetails.forEach(detail -> {
                detail.setOrderId(wholeDecorationOrder.getId());
                detail.setOrderNo(wholeDecorationOrder.getOrderNo());
            });


            iWholeDecorationOrderDetailService.batchInsert(wholeDecorationOrderDetails);
            resultDto.setMessage("整装商品订单保存成功");

            Map<String,Integer> params = Maps.newHashMap();
            params.put("orderId",wholeDecorationOrder.getId());
            resultDto.setMapData(params);

        } catch (Exception e) {
            logger.error("整装商品订单保存异常:", e);

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
    @GetMapping("/decoration/order/pay")
    @Login
    public String save_and_pay_order(Model m,
                                     int orderId,
                                     HttpServletRequest request) {

        try {

            WholeDecorationOrder wholeDecorationOrder = iWholeDecorationOrderService.getWholeDecorationOrder(orderId);

            //省信息
            List<Province> provinces = iProvinceService.getAllProvinces();
//            m.addAttribute("provinces", provinces);
            m.addAttribute("wholeDecorationOrder", wholeDecorationOrder);


        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO 跳转至pay页面
        return "foreground/whole_decoration/whole_decoration_order_pay";
    }


    /**
     * 跳转至整装下单页面
     * @param m
     * @return
     */
    @GetMapping("/decoration/order.html*")
    public String to_whole_decoration_order(Model m, WholeDecorationResultDto wholeDecoration) {

        try {
            //整装基本信息
            WholeDecoration wholeDecorationDB = iWholeDecorationService.selectByPrimaryKey(wholeDecoration.getId());
            m.addAttribute("wholeDecoration", wholeDecorationDB);

            //整装空间信息
            List<WholeDecorationSpaceDto> wholeDecorationSpaceDtos = iWholeDecorationSpaceService.selectByWholeDecorationId(wholeDecorationDB.getId());
            m.addAttribute("wholeDecorationSpaces", wholeDecorationSpaceDtos);

            //省信息
            List<Province> provinces = iProvinceService.getAllProvinces();
            m.addAttribute("provinces", provinces);

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
    public String whole_decoration_list(Model m, WholeDecorationResultDto wholeDecoration) {

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
    public String whole_decoration_detail(Model m, WholeDecorationResultDto wholeDecoration) {

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
