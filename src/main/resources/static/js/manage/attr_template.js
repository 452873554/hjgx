$(function () {

});

/**
 * 删除事件绑定
 */
$('#delete-template').on('show.bs.modal', function (e) {
    $(".confirm-delete").attr("id", e.relatedTarget.id);
});
$(".confirm-delete").bind("click", function () {
    $.ajax({
        url: '/backstage/attr-template/delete?id=' + this.id,
        type: "get",
        success: function (data) {
            if(data){
                window.location.reload();
            }else{
                alert("删除失败！请重试或联系系统管理员")
            }
        },
        error:function(){
            alert("删除失败！请重试或联系系统管理员")
        }
    });
})

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

//保存属性模板
$("button.save-or-update").click(function () {
    //获取所有属性
    var attrs = $("#attr-tbody").find("input.attr");

    var requestParam = [];
    var item = {};

    for(var i = 0;i<attrs.length;i++){
        item.key = attrs[i].value;
        var attrVals = $(attrs[i]).parent().next().find(".attr-val");

        var Vals = [];
        for (var j = 0; j < attrVals.length; j++) {
            Vals.push(attrVals[j].value);
        }
        item.value = JSON.stringify(Vals);
        requestParam.push(item);
        item = {};
    }

    $("#attrsTemplate").val(JSON.stringify(requestParam));

});
