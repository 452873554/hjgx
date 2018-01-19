$(function(){

    //富文本编辑器初始化
    $('.summernote').summernote({
        height: 150,
        tabsize: 2,
        lang: 'zh-CN'
    });

    //上传插件初始化
    $("#preview-img").fileinput({
        allowedFileExtensions : ['jpg', 'png','gif'],
        maxFilePreviewSize: 10240,//KB单位，此处限制预览文件大小为10M
        maxFileSize: 1024,
        language: 'zh',
        uploadUrl: '#'
    });

    //新增属性
    $("#add-attr").click(function () {
        $("#attr-tbody").append("<tr>" + $("#attrTemplate").html() + "</tr>");
    });

    //新增属性值
    $("#attr-tbody").on("click",".add-attr-val",function () {
        $(this).next().append($("#attrValTemplate").html());
    });

    //移除属性值
    $("#attr-tbody").on("click",".delete-attr-val",function () {
        $(this).parent().remove();
    });

    //删除属性
    $("#attr-tbody").on("click",".del-attr",function () {
        $(this).parent().parent().remove();
    });

    //生成规格表格
    $("#generate-specification").on("click",function () {

        //spu需要提前填写
        if(!$("#spu").val()){
            $("#spu").attr("placeHolder","生成规格之前请填写SPU，商品编号将依据SPU生成");
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
        for(var i = 0;i<attrs.length;i++){
            headerHolder = headerHolder + "<th>" + attrs[i].value + "</th>";
        }
        header.prepend(headerHolder);


        for(var i = 0;i<attrs.length;i++){

            //属性值填充行
            var attrVals = $(attrs[i]).parent().next().find(".attr-val");
            //保存下先前的行记录
            var $tr = $("#specification-table-body").find("tr");
            //每行必有的内容

            for(var j = 0;j<attrVals.length;j++){
                //先前的行记录不存在，说明是第一次添加，直接添加新行
                if($tr.length == 0){
                    $("#specification-table-body").append("<tr><td>" +$(attrVals[j]).val()+ "</td></tr>");
                }else{
                    //之前的行记录存在，拼接上本次的属性值，再次添加
                    for(var y = 0;y<$tr.length;y++) {
                        $("#specification-table-body").append("<tr>" + $($tr[y]).html() + "<td>" +$(attrVals[j]).val()+ "</td></tr>");
                    }
                }
                //移除旧的行记录，防止重复出现
                $tr.remove();
            }
        }

        //添加公共单元格
        var $commontd = "<td><input class='sku'></td><td><input class='code'></td><td><input class='stock'></td><td><input class='stock-warning'></td><td><input class='cost-price'></td><td><input class='retail-price'></td><td><input class='market-price'></td><td><input class='volume'></td>"
        $("#specification-table-body").find("tr").append($commontd);

        var $thSize = $("#specification-table-header").find("th").size();

        //单元格样式调整
        $("#specification-table-body").find("input").css("width","100%").css("height","100%");
        $("#specification-table-body").find("td").css("width",100/$thSize + '%');

    });




});