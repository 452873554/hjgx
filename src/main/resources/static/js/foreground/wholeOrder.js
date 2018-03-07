$(function () {

    //整装下单
    $("#save-or-date-whole-decoration").click(function () {

        //构建spu信息
        var requestParam = new FormData();
        var wholeDecorationDetails = [];
        var wholeDecorationDetail = {};

        //遍历所有空间
        $("#whole-decoration-sku-container").find("div.leftList").each(function () {

            var space_id = $(this).attr("space-id");

            //遍历空间下每一个项目
            $(this).find(".waiting-for-the-selection").each(function () {
                wholeDecorationDetail = {};
                wholeDecorationDetail.wdSpaceId = space_id;
                wholeDecorationDetail.wdItemId = $(this).attr("item-id");
                wholeDecorationDetail.productName = $(this).find(".spu-name").text();
                wholeDecorationDetail.spu = $(this).find(".spu").val();
                wholeDecorationDetail.sku = $(this).find(".sku").val();
                wholeDecorationDetail.qty = $(this).find(".qty").val();
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
            url: "/decoration/order/confirm",
            type: "post",
            data: requestParam,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.flag == 1) {
                    alert(data.message);
                    //跳转至订单支付页面
                    window.location.href = "/decoration/order/binding.html?orderId="+data.mapData.orderId;
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

    //spu数据保存
    var $spuData = [];
    var $target_item = {};

    //属性值选中，取消选中
    $(".attr-container").on("click",".attr-value",function () {
        $(this).addClass("selected");
        $(this).siblings().removeClass("selected");
    });

    //点击项目的“添加”按钮，列出所有的备选SPU
    $(".add-sku-to-item").click(function () {

        //保存“添加”对象，后续填充内容
        $target_item = $(this);

        //绑定标题
        $(".alternative-spu-list").find(".item-title").text($(this).parent().prev().text());

        //获取项目下所有备选的SPU
        $spus = $(this).parent().next().find("input");

        $(".attr-container").empty();
        //加载每个SPU的属性信息
        for(var i= 0;i<$spus.length;i++){
            //获得spu的基本信息
            $.ajax({
                url: '/api/product/detail?spu=' + $spus[i].value,
                type: "get",
                success: function (data) {
                    $spuData.push(data);

                    //填充数据
                    $("#standby-spus-title").html(data.spu);
                    $("#spu-preview-img").attr("src", data.imageUrl);
                    $("#product-name").html(data.productName);
                    //属性填充

                    var attrs = JSON.parse(data.attrs);
                    var content = "";
                    for (var i = 0; i < attrs.length; i++) {
                        var attrValues = document.getElementById('attr-values').innerHTML;
                        content = content + template(attrValues, {attrVals: attrs[i].value,
                                                 attr:attrs[i].key,
                                                 imgurl:data.imageUrl,
                                                 spuName:data.productName});


                    }

                    var attrValuesContainer = document.getElementById('tpl').innerHTML;
                    var spuAttr = template(attrValuesContainer, {attrValues:content,
                        imgurl:data.imageUrl,
                        spuName:data.productName});
                    $(".attr-container").append(spuAttr);
                },
                error: function () {
                    alert("获取SPU！请重试或联系系统管理员")
                }
            });
        }
    })

    //选定sku
    $(".alternative-spu-list").on("click",".set-sku-to-item",function () {

        var selectedAttrs = []
        var attr = {};

        //查看每一组已选定的属性值
        $(this).parent().prev().find(".attr-values-container").each(function () {
            attr = {};
            attr.key = $(this).find(".spu-attr-key").text();
            attr.value = $(this).find(".selected").text();
            selectedAttrs.push(attr);
        });

        //校验选择的属性是否有对应的sku相匹配
        var skus = [];//每个SPU所含的SKU
        var selectedSku = "";//已选SKU
        var selectedSpuName = "";//所选SPU名字
        var selectedSpu = "";//所选SPU
        var flag = false;

        //遍历已保存的SPU
        for (var i = 0; i < $spuData.length; i++) {
            skus = $spuData[i].skus;
            //遍历SPU的每个SKU
            for(var j = 0;j < skus.length;j++){
                var attr = JSON.parse(skus[j].specification);

                //校验所选的属性是否和sku匹配，各个属性值完全匹配
                for (var y = 0; y < selectedAttrs.length; y++) {
                    //查看sku中是否含有选定的属性值
                    if (attr.hasOwnProperty(selectedAttrs[y].key)) {
                        if (attr[selectedAttrs[y].key] == selectedAttrs[y].value) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    selectedSku = skus[j].sku;
                    selectedSpuName = $spuData[i].productName;
                    selectedSpu = $spuData[i].spu;
                    break;
                }
            }
        }

        //将已选的sku进行绑定
        var $dynamicDataHolder = $($target_item).parent().parent().next();
        $dynamicDataHolder.find(".spu-name").text(selectedSpuName);
        $dynamicDataHolder.find(".sku").val(selectedSku);
        $dynamicDataHolder.find(".spu").val(selectedSpu);

        $dynamicDataHolder.find(".qty").val(1);

    });




});

