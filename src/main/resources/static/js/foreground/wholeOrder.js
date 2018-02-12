$(function () {

    //整装下单
    $("#save-or-date-whole-decoration").click(function () {

        //构建spu信息
        var requestParam = new FormData();
        var wholeDecorationDetails = [];
        var wholeDecorationDetail = {};
        //遍历所有空间
        $("#whole-decoration-sku-container").find("div.space-item-panel").each(function () {

            var space_id = $(this).attr("space-id");

            //遍历空间下每一个项目
            $(this).find(".item-sku-selected").each(function () {
                wholeDecorationDetail = {};
                wholeDecorationDetail.wdSpaceId = space_id;
                wholeDecorationDetail.wdItemId = $(this).attr("item-id");
                wholeDecorationDetail.productName = $($(this).find(".spu-name")[0]).text();
                wholeDecorationDetail.spu = $($(this).find(".selected-spu")[0]).text();
                wholeDecorationDetail.sku = $($(this).find(".selected-sku")[0]).text();
                wholeDecorationDetail.qty = $($(this).find(".selected-sku-qty")[0]).text();
                wholeDecorationDetails.push(wholeDecorationDetail);
            })
        });

        //是否有缺失项校验
        for (var i = 0; i < wholeDecorationDetails.length; i++) {
            if(!wholeDecorationDetails[i].spu){
                alert("有项目没有选择SPU，请检查后再次提交");
                return;
            }
        }


        requestParam.append("wholeDecorationId",$("#whole-decoration-id").val())
        requestParam.append("wholeDecorationOrderDetails", JSON.stringify(wholeDecorationDetails));

        $.ajax({
            url: "/decoration/order/save",
            type: "post",
            data: requestParam,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.flag == 1) {
                    //TODO 添加订单待确认提示
                    //TODO 添加商家前台登录拦截
                    alert(data.message);
                } else if(data.flag == -1) {
                    alert(data.message);
                    $('#login_dialog').modal('show')
                }else{
                    alert(data.message);
                }
            },
            error: function (data) {
                alert('信息保存失败，请检查登录状态是否超时，或联系系统管理员');
            }
        });

    });


    //选定sku
    $("#set-sku-to-item").click(function () {

        var selectedAttrs = []
        var attr = {};

        //查看每一组已选定的属性值
        $("#attrs-container").find("div.form-group").each(function () {
            //选取各个radio状态
            $(this).find("input[type=radio]").each(function () {
                attr = {};
                if ($(this).prop("checked")) {
                    attr.key = $(this).attr("name");
                    attr.value = $(this).val();
                    selectedAttrs.push(attr);
                }
            });
        });

        //校验选择的属性是否有对应的sku相匹配
        var skus = $spuData.skus;
        var selectedSku = "";
        var flag = false;
        for (var i = 0; i < skus.length; i++) {
            var attr = JSON.parse(skus[i].specification);

            //校验所选的属性是否和sku匹配，各个属性值完全匹配
            for (var j = 0; j < selectedAttrs.length; j++) {
                //查看sku中是否含有选定的属性值
                if (attr.hasOwnProperty(selectedAttrs[j].key)) {
                    if (attr[selectedAttrs[j].key] == selectedAttrs[j].value) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                selectedSku = skus[i].sku;
                break;
            }
        }

        if (!selectedSku) {
            alert("请选择SPU的属性");
            return;
        }

        //将选择的SKU绑定到已选栏目
        var $spuContainer = $($($originELement).parents(".rightShow")[0]).prev()
        $spuContainer.find(".spu-img").attr("src", $spuData.imageUrl);
        $spuContainer.find(".spu-name").html($spuData.productName);
        $spuContainer.find(".selected-sku").html(selectedSku);
        $spuContainer.find(".selected-spu").html($spuData.spu);
        $spuContainer.find(".selected-sku-qty").html($("#attrs-container").find(".qty")[0].value);

        // $spuContainer.find(".media-body").html(JSON.stringify(selectedAttrs));
        var $body = $spuContainer.find(".attrs-container");
        $body.empty();
        for (var i = 0; i < selectedAttrs.length; i++) {
            $body.append("<p><span>" + selectedAttrs[i].key + ": </span><span class='tip'>" + selectedAttrs[i].value + "</span></p>");
        }

        //关闭模态框
        $('#standby-spus').modal('hide');
    });

    //设置一个全局变量，保存触发显示模态框的按钮
    var $originELement = {};
    //spu数据保存
    var $spuData = {};

    //加载SKU属性模态框
    $('#standby-spus').on('show.bs.modal', function (e) {
        $originELement = e.relatedTarget;

        var spu = e.relatedTarget.id;
        //获得spu的基本信息
        $.ajax({
            url: '/api/product/detail?spu=' + spu,
            type: "get",
            success: function (data) {
                $spuData = data;

                //填充数据
                $("#standby-spus-title").html(data.spu);
                $("#spu-preview-img").attr("src", data.imageUrl);
                $("#product-name").html(data.productName);
                //属性填充

                var attrs = JSON.parse(data.attrs);
                var content = "";
                for (var i = 0; i < attrs.length; i++) {
                    content = content + "<div class='form-group'><label>" + attrs[i].key + "：</label><div class='btn-group' data-toggle='buttons'>";
                    //循环添加radio值
                    var values = attrs[i].value;
                    for (var j = 0; j < values.length; j++) {
                        content = content + "<label class='btn btn-default'><input type='radio' name='" + attrs[i].key + "' value='" + values[j] + "'>" + values[j] + "</label>";
                    }
                    content = content + "</div></div>";
                }

                $("#attrs-container").empty().append(content + "<div class='form-group'><label>数量：</label><div class='btn-group' data-toggle='buttons'><input class='form-control qty'/></div></div>");
            },
            error: function () {
                alert("删除失败！请重试或联系系统管理员")
            }
        });
    })

});

