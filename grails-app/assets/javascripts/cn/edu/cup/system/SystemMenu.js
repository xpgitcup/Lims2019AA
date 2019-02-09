var operation4SystemMenuDiv;
var jsTitle = "系统菜单";
var title4SystemMenu = [jsTitle]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemMenuDiv = $("#operation4SystemMenuDiv");
    var settings = {
        divId: operation4SystemMenuDiv,
        titles: title4SystemMenu,
        paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadSystemMenu,
        countFunction: countSystemMenu
    }

    configDisplayUI(settings);
});

/*
* 统计函数
* */
function countSystemMenu(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemMenu/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemMenu(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemMenu/list" + params + "&key=" + title, 0, "list" + title + "Div");
}