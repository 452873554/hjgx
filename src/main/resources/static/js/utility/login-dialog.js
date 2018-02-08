/**
 * Created by alvin on 2018/2/7.
 */


$(function () {

    //登录模态框
    $("#user-login").click(function () {

        var user = {};
        user.username = $("#user-name").val();
        user.password = $("#user-password").val();

        var requestParam = new FormData();
        requestParam.append("user",JSON.stringify(user));

        $.ajax({
            url: "/userlogin/dologin",
            type: "post",
            data: requestParam,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.flag == 1) {
                    alert(data.message);
                    $('#login_dialog').modal('hide')
                } else if(data.flag == -1) {
                    alert(data.message);
                }else{
                    alert(data.message);
                }
            },
            error: function (data) {
                alert('信息保存失败，请检查登录状态是否超时，或联系系统管理员');
            }
        });
    });

});