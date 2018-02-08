/**
 * 删除时间绑定
 */
$('#delete-userBusiness').on('show.bs.modal', function (e) {
    $(".confirm-delete").attr("id", e.relatedTarget.id);
});
$(".confirm-delete").bind("click", function () {
    $.ajax({
        url: '/backstage/userbusiness/delete?id=' + this.id,
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

$('#save-or-update').on('show.bs.modal', function (e) {
    //自动生成密码
    $.ajax({
        url: '/backstage/userbusiness/generatePassword',
        type: "get",
        success: function (data) {
            if(data){
                $("input[name=password]").val(data)
            }else{
                alert("自动生成密码失败，请手动填写并及时通知系统管理员")
            }
        },
        error:function(){
            alert("自动生成密码失败，请手动填写并及时通知系统管理员")
        }
    });
});

$('#reset-pwd').on('show.bs.modal', function (e) {
    //自动生成密码
    $.ajax({
        url: '/backstage/userbusiness/resetPassword?id=' + e.relatedTarget.id,
        type: "get",
        success: function (data) {
            if(data){
                $("#user-new-password").html(e.relatedTarget.name + "的")
                $("#new-password").html(data);
            }else{
                $("#new-password-tip").html("密码重置失败，请及时通知系统管理员");
            }
        },
        error:function(){
            $("#new-password-tip").html("密码重置失败，请及时通知系统管理员");
        }
    });
});
