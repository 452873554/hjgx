$(function () {

    //隐藏地址弹窗
    $('.btn-false,.maskwrap').click(function () {
        $('.conterwrap').fadeOut(150);
        $('.maskwrap').fadeOut(200);
    })
    //显示地址弹窗
    $('#add').click(function () {
        $('.conterwrap').fadeIn(150);
        $('.maskwrap').fadeIn(200);
        //加载行政区划内容
        $.ajax({
            url: '/api/province/default',
            type: "get",
            success: function (data) {
                if (data.flag) {
                    //填充默认行政区划
                    $(".provence").append(
                        template($('#options').html(), {list: data.mapData.provinces})
                    );
                    $(".city").append(
                        template($('#options').html(), {list: data.mapData.cities})
                    );
                    $(".district").append(
                        template($('#options').html(), {list: data.mapData.districts})
                    )
                }
                else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("获取行政区划失败！请重试或联系系统管理员")
            }
        });
    })

    //省切换
    $(".provence").change(function () {
        $(".city").empty();
        $(".district").empty();
        $.ajax({
            url: '/api/province/' + $(".provence").find("option:selected").attr("id") + '/cities',
            type: "get",
            success: function (data) {
                if (data.flag) {
                    $(".city").append(
                        template($('#options').html(), {list: data.mapData.cities})
                    );
                    $(".district").append(
                        template($('#options').html(), {list: data.mapData.districts})
                    )
                }
                else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("获取行政区划失败！请重试或联系系统管理员")
            }
        });
    })

    //市切换
    $(".city").change(function () {
        $(".district").empty();
        $.ajax({
            url: '/api/province/city/' + $(".city").find("option:selected").attr("id") + '/districts',
            type: "get",
            success: function (data) {
                if (data.flag) {
                    $(".district").append(
                        template($('#options').html(), {list: data.mapData.districts})
                    )
                }
                else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("获取行政区划失败！请重试或联系系统管理员")
            }
        });
    })

    //设置默认收货地址
    $(".rightDtop").on("click",".set-to-default-addr",function () {

        var $id = $(this).attr("addrId");
        var $originParent  = $(this).parent();

        $.ajax({
            url: '/usercenter/address/default?id=' + $id,
            type: "GET",
            success: function (data) {
                //先将所有按钮设置为“设为默认”
                $(".getPerson").find("div:last").each(function () {
                    var addrId = $(this).find("button").attr("addrid");
                    $(this).empty();
                    $(this).append(template($('#setDefaultButton').html(), {id: addrId}));
                });
                //当前点击的这一个置灰
                $originParent.empty();
                $originParent.append(template($('#defaultButton').html(), {id: $id}));
                $originParent.prev().find("input[type='radio']").prop("checked","checked");

            },
            error: function () {
                alert("设置默认收货地址失败！请重试或联系系统管理员")
            }
        });
    });

    //添加用户收货地址
    $(".add-address").click(function () {

        var address = {};

        address.provence = $(".provence").find("option:selected").text();
        address.city = $(".city").find("option:selected").text();
        address.district = $(".district").find("option:selected").text();
        address.address = $("#address").val().trim();
        address.receiver = $("#receiver").val();
        address.receiverCellPhone = $("#receiverCellPhone").val();
        address.defaultAddress = $("#defaultAddress").prop("checked");

        $.ajax({
            url: '/usercenter/address/save',
            type: "POST",
            data: address,
            success: function (data) {
                alert(data.message);
                $(".btn-false").click();
            },
            error: function () {
                alert("添加收货地址失败！请重试或联系系统管理员")
            }
        });

    });

    //提交订单
    $("#submit-order").click(function () {

        var orderId = $("#orderId").val();
        var addrId = $(".select-receive-addr:checked").attr("addrId");
        var remark = $("#remark").val();

        var requestParam = new FormData();
        requestParam.append("orderId",orderId);
        requestParam.append("addrId",addrId);
        requestParam.append("remark",remark);

        $.ajax({
            url: '/decoration/order/pay',
            type: "POST",
            data: requestParam,
            processData: false,
            contentType: false,
            success: function (data) {
                if(data.flag){
                    //跳转
                    window.location.href = "/usercenter/decoration-order/list.html";
                }else{
                    alert(data.message);
                }
            },
            error: function () {
                alert("订单保存失败！请重试或联系系统管理员")
            }
        });

    });


});

