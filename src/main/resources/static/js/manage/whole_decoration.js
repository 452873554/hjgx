$(function () {

    //富文本编辑器初始化
    $('.summernote').summernote({
        height: 150,
        tabsize: 2,
        lang: 'zh-CN'
    });

    //上传插件初始化
    $("#preview-img ").fileinput({
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        maxFilePreviewSize: 10240,//KB单位，此处限制预览文件大小为10M
        maxFileSize: 1024,
        language: 'zh',
        showUpload: false, //是否显示上传按钮
        fileActionSettings: {showUpload: false}
    });

    //每个项目的预览图上传控件初始化
    $(".space-preview-img").fileinput({
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        maxFilePreviewSize: 10240,//KB单位，此处限制预览文件大小为10M
        maxFileSize: 1024,
        language: 'zh',
        showUpload: false, //是否显示上传按钮
        fileActionSettings: {showUpload: false}
    });

    //添加新的空间标签
    $("#add-new-space").click(function (e) {

        //填充内容
        $("#nav-tabs").append($("#space-tab-Template").html());
        $("#tab-content").append($("#space-content-Template").html());

        //修改新填充内容的ID
        $("#nav-tabs").find("a.tab-btn").each(function (i,e) {
            $(this).attr("href","#space_" + i);
        });
        $("#tab-content").find("div.tab-container").each(function (i,e) {
            $(this).attr("id","space_" + i);
        });

        //绑定显示内容事件
        $("#nav-tabs").find("a.tab-btn").on('click',bindTabContent);
        //显示最新添加的tab
        $("#nav-tabs").find("a.tab-btn:last").click();

        $("#tab-content").find("div.tab-container:last").find("input[name=spaceName]").focus();
    })
    //空间标签和空间名称输入框联动
    $("#tab-content").on("keyup","input[name=spaceName]",function () {
        var $href = $(this).parents(".tab-container").attr("id");
        $("a[href='#"+$href+"']").html($(this).val());

    });
    //删除空间
    $("#tab-content").on("click",".delete-space",function () {
        var tagid = $(this).parents(".tab-container")[0].id;
        //移除标签
        $("a[href='#"+tagid+"']").parent().remove();
        //移除标签对应的内容
        $(this).parents(".tab-container").remove();
        //第一个标签页选中显示
        $("#nav-tabs").find("a.tab-btn:eq(0)").click();

    });


    // 添加新的项目标签
    $("#tab-content").on("click",".add-new-item",function () {

        //当前空间的id，防止每个项目的标签id重复
        var $id = this.id;

        var target= $(this).parent().parent();
        //填充内容
        target.append($("#space-item-tab-template").html())
        target.next().append($("#space-item-content-template").html());

        //修改新填充内容的ID
        target.find("a.tab-btn").each(function (i,e) {
            $(this).attr("href","#item_" + $id + "_" + i);
        })

        target.next().find("div.tab-item-container").each(function (i,e) {
            $(this).attr("id","item_" + $id + "_" + i);
        })

        //绑定显示内容事件
        target.find("a.tab-btn").on('click',bindTabContent);
        //显示最新添加的tab
        target.find("a.tab-btn:last").click();
    });
    //空间标签和空间名称输入框联动
    $(".tab-content").on("keyup","input[name=item-name]",function () {
        var $href = $(this).parents(".tab-item-container").attr("id");
        $("a[href='#"+$href+"']").html($(this).val());

    });
    //删除项目
    $("#tab-content").on("click",".delete-item",function () {
        var tagid = $(this).parents(".tab-item-container")[0].id;

        //标签所在容器
        var con = $(this).parents(".tab-item-container").parent().prev()

        //移除标签
        $("a[href='#"+tagid+"']").parent().remove();
        //移除标签对应的内容
        $(this).parents(".tab-item-container").remove();

        //第一个标签页选中显示
        con.find("a.tab-btn:eq(0)").click();
    });

});

function bindTabContent(e) {
    e.preventDefault();
    $(this).tab('show');
}