$(function(){
    $('.tree').treegrid({
        expanderExpandedClass: 'glyphicon glyphicon-minus',
        expanderCollapsedClass: 'glyphicon glyphicon-plus'
    });

    /**
     * 新增事件绑定
     */
    $('#save-or-update').on('show.bs.modal', function (e) {
        $(".save-or-update-title").html('为分类[ ' + $(e.relatedTarget).attr("cate-name") + ' ]添加子分类');
        $("#parent-category-id").val(e.relatedTarget.id);
    });

    /**
     * 删除事件绑定
     */
    $('#delete-category').on('show.bs.modal', function (e) {
        $(".delete-title").html('将会删除[ ' + $(e.relatedTarget).attr("cate-name") + ' ]类目及其所有子类目，你确定吗');

        $(".confirm-delete").attr('id',e.relatedTarget.id);
    });


    $(".confirm-delete").bind("click", function () {
        $.ajax({
            url: '/backstage/category/delete?id=' + this.id,
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

});