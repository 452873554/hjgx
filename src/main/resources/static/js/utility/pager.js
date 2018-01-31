/**
 * Created by alvin on 2018/1/29.
 *
 * 页面需要添加容器<div id="pager-bar"></div>
 *
 */
function create_pager_bar(data) {
    //pager-bar
    var mapDate = data.mapData;

    if(mapDate.pager.datas != 0){

        var content = "<ul class='pager'>";

        if(mapDate.pager.pageOffSet == 1){
            content = content + "<li class='disabled'><a href='javascript:void(0);'>首页</a></li><li class='disabled'><a href='javascript:void(0);'>上一页</a></li>";
        }else{
            content = content + "<li><a href='javascript:void(0);'>首页</a></li><li><a class='go-to-prev-page' href='javascript:void(0);'>上一页</a></li>"
        }

        content = content + "<li><span>当前第" + mapDate.pager.pageOffSet + "页，共" + mapDate.pager.totalPageCount + "页，共" + mapDate.pager.totalRecordCount+ "条记录</span></li>";

        if(mapDate.pager.totalPageCount == mapDate.pager.pageOffSet){
            content = content + "<li class='disabled'><a href='javascript:void(0);'>下一页</a></li><li class='disabled'><a href='javascript:void(0);'>尾页</a></li></ul>";
        }else{
            content = content + "<li><a class='go-to-next-page' href='javascript:void(0);'>下一页</a></li><li><a href='javascript:void(0);'>尾页</a></li></ul>"
        }
        $("#pager-bar").empty().append(content);
    }


}


