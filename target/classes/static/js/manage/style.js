/**
 * 删除时间绑定
 */
$('#delete-style').on('show.bs.modal', function (e) {
    $(".confirm-delete").attr("id", e.relatedTarget.id);
});
$(".confirm-delete").bind("click", function () {
    $.ajax({
        url: '/backstage/style/delete?id=' + this.id,
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