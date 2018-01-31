$(function () {

    //富文本编辑器初始化
    $('.summernote').summernote({
        height: 150,
        tabsize: 2,
        lang: 'zh-CN'
    });

    //上传插件初始化
    $("#preview-img").fileinput({
        allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
        maxFilePreviewSize: 10240,//KB单位，此处限制预览文件大小为10M
        maxFileSize: 1024,
        language: 'zh',
        enctype: 'multipart/form-data',
        showUpload: false, //是否显示上传按钮
        uploadUrl: "/testDemo/fileupload/upload.do",
        fileActionSettings: {showUpload: false}
    });

    //模板变动，重新填充
    $("#template-list").change(function () {
        //清空原有值
        $("#attr-tbody").empty()

        //添加属性
        var $attr_template = JSON.parse(this.value);
        for (var i = 0; i < $attr_template.length; i++) {

            $("#attr-tbody").append("<tr>" + $("#attrTemplate").html() + "</tr>");
            $("#attr-tbody").find("tr:last").find("td:eq(1)").find("input.attr").val($attr_template[i].key);

            //添加属性值
            $("#attr-tbody").find("tr:last").find("td:eq(2)").find("div.div-val").empty();
            var $attr_value = JSON.parse($attr_template[i].value);
            for (var j = 0; j < $attr_value.length; j++) {
                var attr_val_content = "<div class='btn-group' role='group' style='width: 24%'><button class='btn btn-xs btn-danger delete-attr-val' style='width: 30%' type='button'>删除</button><input class='attr-val' style='height: 100%;width: 70%' type='text' value='" + $attr_value[j] + "'></div> ";
                $("#attr-tbody").find("tr:last").find("td:eq(2)").find("div.div-val").append(attr_val_content);
            }

        }


    });

    //新增属性
    $("#add-attr").click(function () {
        $("#attr-tbody").append("<tr>" + $("#attrTemplate").html() + "</tr>");
    });

    //新增属性值
    $("#attr-tbody").on("click", ".add-attr-val", function () {
        $(this).next().append($("#attrValTemplate").html());
    });

    //移除属性值
    $("#attr-tbody").on("click", ".delete-attr-val", function () {
        $(this).parent().remove();
    });

    //删除属性
    $("#attr-tbody").on("click", ".del-attr", function () {
        $(this).parent().parent().remove();
    });

    //删除规格
    $("#specification-table-body").on("click", ".del-specification", function () {
        $(this).parent().parent().remove();
    });

    //生成规格表格
    $("#generate-specification").on("click", function () {

        //spu需要提前填写
        if (!$("#spu").val()) {
            $("#spu").attr("placeHolder", "生成规格之前请填写SPU，商品编号将依据SPU生成");
            $("#spu").focus();
            return;
        }

        //清空已有数据
        $("#specification-table-header").empty().append($("#specification-table-header-template").html());
        var header = $("#specification-table-header");
        $("#specification-table-body").empty();

        //获取所有属性
        var attrs = $("#attr-tbody").find("input.attr");

        //属性填充表头
        var headerHolder = "";
        for (var i = 0; i < attrs.length; i++) {
            headerHolder = headerHolder + "<th>" + attrs[i].value + "</th>";
        }
        header.prepend(headerHolder);

        for (var i = 0; i < attrs.length; i++) {

            //属性值填充行
            var attrVals = $(attrs[i]).parent().next().find(".attr-val");
            //保存下先前的行记录
            var $tr = $("#specification-table-body").find("tr");
            //每行必有的内容

            for (var j = 0; j < attrVals.length; j++) {
                //先前的行记录不存在，说明是第一次添加，直接添加新行
                if ($tr.length == 0) {
                    $("#specification-table-body").append("<tr><td>" + $(attrVals[j]).val() + "</td></tr>");
                } else {
                    //之前的行记录存在，拼接上本次的属性值，再次添加
                    for (var y = 0; y < $tr.length; y++) {
                        $("#specification-table-body").append("<tr>" + $($tr[y]).html() + "<td>" + $(attrVals[j]).val() + "</td></tr>");
                    }
                }
                //移除旧的行记录，防止重复出现
                $tr.remove();
            }
        }

        //添加公共单元格
        var $commontd = "<td><input type='text' class='code'></td><td><input type='text' class='stock'></td><td><input type='text' class='stock-warning'></td><td><input type='text' class='cost-price'></td><td><input type='text' class='retail-price'></td><td><input type='text' class='market-price'></td><td><input type='text' class='volume'></td><td><button type='button' class='btn btn-xs btn-danger del-specification' style='width: 100%;'>删除</button></td><td><input type='radio' name='set-default'></td>"

        //构建带顺序的SKU
        $("#specification-table-body").find("tr").each(function (index) {
            $(this).append("<td><input class='sku' value='" + $("#spu").val() + "-" + (index + 1) + "'></td>" + $commontd);
        });

        //单元格样式调整
        var $thSize = $("#specification-table-header").find("th").size();
        $("#specification-table-body").find("input[type='text']").css("width", "100%").css("height", "100%");
        $("#specification-table-body").find("td").css("width", 100 / $thSize + '%');

    });

    //保存商品信息
    $("#save-or-date-product").click(function () {
        //先上传图片

        // $('#preview-img').fileinput('upload');
        if ($("input[name='previewImages']")[0].files.length <= 0) {
            alert("必须上传预览图");
            return;
        }

        //构建属性集
        var attrs = [];
        $("#attr-tbody").find("tr").each(function () {
            var attr = {};
            attr.key = $(this).find(".attr")[0].value;
            var attr_values = [];
            $(this).find(".attr-val").each(function () {
                attr_values.push($(this).val());
            })

            attr.value = attr_values;
            attrs.push(attr);
        });


        //spu
        var productSpu = {};
        //如果没有选定默认的sku作为价格基础，那么选定第一个
        if ($("input[name='set-default']:radio:checked").length <= 0) {
            $($("input[name='set-default']:radio")[0]).prop("checked", "checked");
        }

        //构建规格
        var specifications = [];
        var totalStock = 0;
        $("#specification-table-body").find("tr").each(function () {

            var specification = {};
            var $tds = $(this).find("td");

            //构建每一行的规格信息
            var attr = {};
            for (var i = 0; i < $tds.size(); i++) {
                //每一个规格信息的属性对象

                //把变长的属性部分单独抽出来
                if (i <= attrs.length - 1) {
                    attr[attrs[i].key] = $($tds[i]).html()
                } else {
                    specification.spu = $("#spu").val();
                    specification.sku = $($tds[i]).find(".sku").val();
                    specification.productName = $("#productName").val();
                    specification.code = $($tds[i + 1]).find(".code").val();
                    specification.stock = $($tds[i + 2]).find(".stock").val();
                    totalStock = totalStock + parseInt(specification.stock);
                    specification.stockWarning = $($tds[i + 3]).find(".stock-warning").val();
                    specification.costPrice = $($tds[i + 4]).find(".cost-price").val();
                    specification.retailPrice = $($tds[i + 5]).find(".retail-price").val();
                    specification.marketPrice = $($tds[i + 6]).find(".market-price").val();
                    specification.volume = $($tds[i + 7]).find(".volume").val();

                    //当前行被勾选为默认价格数据
                    if ($(this).find("input[name='set-default']:radio:checked").length > 0) {
                        productSpu.costPrice = specification.costPrice;
                        productSpu.retailPrice = specification.retailPrice;
                        productSpu.marketPrice = specification.marketPrice;
                    }

                    specification.specification = JSON.stringify(attr);
                    specifications.push(specification);
                    break;
                }
            }
        });

        //库存计算完后，合计数作为SPU总数
        productSpu.stock = totalStock;


        //构建spu信息
        var requestParam = new FormData();

        productSpu.spu = $("#spu").val();
        productSpu.productName = $("#productName").val();
        productSpu.categoryId = $("#category-id").val();
        productSpu.brandId = $("#brand-list").val();
        productSpu.styleId = $("#style-list").val();
        productSpu.supplierId = $("#supplier-list").val();
        productSpu.unit = $("#unit").val();
        var spaceIds = [];
        $("input[name='space']:checkbox:checked").each(function () {
            spaceIds.push(this.value);
        });
        productSpu.spaceIds = JSON.stringify(spaceIds);
        productSpu.description = $('#description').summernote('code');

        requestParam.append("productSpu", JSON.stringify(productSpu));

        //添加规格信息
        requestParam.append("specifications", JSON.stringify(specifications));
        //添加属性信息
        var attr = {};
        attr.spu = $("#spu").val();
        attr.attrs = JSON.stringify(attrs);
        requestParam.append("attr", JSON.stringify(attr));

        var files = $("input[name='previewImages']")[0].files
        for (var i = 0; i < files.length; i++) {
            requestParam.append("previewImages", files[i]);   // 文件对象
        }

        $.ajax({
            url: "/backstage/product/save-or-update",
            type: "post",
            data: requestParam,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.flag) {
                    alert(data.message);
                    window.location.href = '/backstage/product/list.html';
                } else {
                    alert(data.message);
                    return;
                }
            },
            error: function (data) {
                alert('商品信息保存失败，请检查登录状态是否超时，或联系系统管理员');
            }
        });


    });

});