$(function () {

    //富文本编辑器初始化
    $('.summernote').summernote({
        height: 150,
        tabsize: 2,
        lang: 'zh-CN'
    });

    //上传插件初始化
    $("#whole-decoration-preview-img").fileinput({
        allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
        maxFilePreviewSize: 10240,//KB单位，此处限制预览文件大小为10M
        maxFileSize: 1024,
        language: 'zh',
        showUpload: false, //是否显示上传按钮
        fileActionSettings: {showUpload: false}
    });

    //每个项目的预览图上传控件初始化
    $(".space-preview-img").fileinput({
        allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
        maxFilePreviewSize: 10240,//KB单位，此处限制预览文件大小为10M
        maxFileSize: 1024,
        language: 'zh',
        showUpload: false, //是否显示上传按钮
        fileActionSettings: {showUpload: false}
    });

    //弹出spu选择框
    //设定一个全局变量，保存要添加到的目标对象
    var itemTarget;
    $("#tab-content").on("click", ".add-sku-to-item", function () {
        itemTarget = this;
        $('#binding-spu').modal('show');
    })

    //确认关联
    $(".set-spu-to-item").click(function () {
        var $container = $(itemTarget).parent().parent().parent();
        $("#spu-list").find("tr").each(function () {
            //只绑定选中
            if ($(this).find(".set-to-item").prop("checked")) {
                //添加内容
                $container.find(".spu-selected").append("<tr>" + $(this).html() + "</tr>");
                //将checkout移除，添加radio
                var $lastTr = $container.find(".spu-selected").find("tr:last");
                $lastTr.find("td:last").remove();
            }
        });
        $('#binding-spu').modal('hide')
        //使用完毕，重置全局对象
        itemTarget = null;

    });

    //spu搜索
    $(".searchSpu").click(function () {
        //分页未全部完成，此处写死50
        generateSpuTable('?pageSize=50&productName=' + $("input[name='requestParam']").val());
    });

    //弹出SPU列表，关联到项目
    $('#binding-spu').on('show.bs.modal', function (e) {
        //ajax获取SPU分页数据
        $("input[name='requestParam']").val("");
        generateSpuTable();
    });

    //重新生成spu列表
    function generateSpuTable(requestParam, originTarget) {
        $.ajax({
            url: "/backstage/api/product/list" + (requestParam ? requestParam : ''),
            type: "get",
            success: function (data) {
                if (data.flag) {
                    $("#spu-list").empty();

                    var datas = data.mapData.pager.datas;

                    if (datas.length > 0) {

                        for (var i = 0; i < datas.length; i++) {
                            $("#spu-list").append("<tr><td>" + datas[i].productName + "</td><td>"
                                + datas[i].spu + "</td><td>"
                                + datas[i].categoryName + "</td><td>"
                                + datas[i].brandName + "</td><td>"
                                + datas[i].styleName + "</td><td><label><input type='checkbox' class='set-to-item'></label></td></tr>")
                        }

                    }

                    // create_pager_bar(data);

                } else {
                }
            },
            error: function (data) {
                alert('商品信息保存失败，请检查登录状态是否超时，或联系系统管理员');
            }
        });
    }


    //添加新的空间标签
    $("#add-new-space").click(function (e) {

        //填充内容
        $("#nav-tabs").append($("#space-tab-Template").html());
        $("#tab-content").append($("#space-content-Template").html());

        //修改新填充内容的ID
        $("#nav-tabs").find("a.tab-btn").each(function (i, e) {
            $(this).attr("href", "#space_" + i);
        });
        $("#tab-content").find("div.tab-container").each(function (i, e) {
            $(this).attr("id", "space_" + i);
        });
        //绑定显示内容事件
        $("#nav-tabs").find("a.tab-btn").on('click', bindTabContent);
        //显示最新添加的tab
        $("#nav-tabs").find("a.tab-btn:last").click();

        $("#tab-content").find("div.tab-container:last").find("input[name=spaceName]").focus();
    })
    //空间标签和空间名称输入框联动
    $("#tab-content").on("keyup", "input[name=spaceName]", function () {
        var $href = $(this).parents(".tab-container").attr("id");
        $("a[href='#" + $href + "']").html($(this).val());

    });
    //删除空间
    $("#tab-content").on("click", ".delete-space", function () {
        var tagid = $(this).parents(".tab-container")[0].id;
        //移除标签
        $("a[href='#" + tagid + "']").parent().remove();
        //移除标签对应的内容
        $(this).parents(".tab-container").remove();
        //第一个标签页选中显示
        $("#nav-tabs").find("a.tab-btn:eq(0)").click();

    });


    // 添加新的项目标签
    $("#tab-content").on("click", ".add-new-item", function () {

        //当前空间的id，防止每个项目的标签id重复
        var $id = this.id;

        var target = $(this).parent().parent();
        //填充内容
        target.append($("#space-item-tab-template").html())
        target.next().append($("#space-item-content-template").html());

        //修改新填充内容的ID
        target.find("a.tab-btn").each(function (i, e) {
            $(this).attr("href", "#item_" + $id + "_" + i);
        })

        target.next().find("div.tab-item-container").each(function (i, e) {
            $(this).attr("id", "item_" + $id + "_" + i);
        })

        //绑定显示内容事件
        target.find("a.tab-btn").on('click', bindTabContent);
        //显示最新添加的tab
        target.find("a.tab-btn:last").click();
    });
    //空间标签和空间名称输入框联动
    $(".tab-content").on("keyup", "input[name=item-name]", function () {
        var $href = $(this).parents(".tab-item-container").attr("id");
        $("a[href='#" + $href + "']").html($(this).val());

    });
    //删除项目
    $("#tab-content").on("click", ".delete-item", function () {
        var tagid = $(this).parents(".tab-item-container")[0].id;

        //标签所在容器
        var con = $(this).parents(".tab-item-container").parent().prev()

        //移除标签
        $("a[href='#" + tagid + "']").parent().remove();
        //移除标签对应的内容
        $(this).parents(".tab-item-container").remove();

        //第一个标签页选中显示
        con.find("a.tab-btn:eq(0)").click();
    });

    //保存整装
    $("#save-or-date-whole-decoration").click(function () {

        //构建整装信息
        var requestParam = new FormData();

        //整装信息
        var wholeDecoration = {};
        wholeDecoration.name = $("#whole-decoration-name").val();
        wholeDecoration.code = $("#whole-decoration-code").val();
        wholeDecoration.styleId = $("#style-list").val();
        wholeDecoration.description = $('#whole-decoration-description').summernote('code');
        requestParam.append("wholeDecoration", JSON.stringify(wholeDecoration));

        var bannerImg = $("#whole-decoration-preview-img")[0].files[0]
        requestParam.append("bannerImg", bannerImg);

        //整装空间信息
        var spaces = [];

        $("#tab-content").find("div.tab-container").each(function (i, e) {
            //构建每个space信息
            var space = {};
            space.name = $(this).find(".spaceName")[0].value;
            requestParam.append("spaceImgs", $(this).find(".space-preview-img")[0].files[0])

            var items = [];
            //构建每个space对应的item信息
            $(this).find(".tab-item-container").each(function (i, e) {
                var item = {};
                //构建每个项目的信息
                item.name = $(this).find(".item-name")[0].value;

                var spus = [];
                //构建每个项目所包含的spu信息
                $($(this).find(".spu-selected")[0]).find("tr").each(function () {
                    var spu = {};
                    spu.spu = $(this).find("td:eq(1)").html();
                    spus.push(spu);
                });

                item.wholeDecorationSpus = spus;
                items.push(item);
            })
            space.wholeDecorationItemDtos = items;
            spaces.push(space);

        })

        requestParam.append("spaces", JSON.stringify(spaces));

        $.ajax({
            url: "/backstage/whole-decoration/save-or-update",
            type: "post",
            data: requestParam,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.flag) {
                    alert(data.message);
                    window.location.href = '/backstage/whole-decoration/list.html';
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

function bindTabContent(e) {
    e.preventDefault();
    $(this).tab('show');
}