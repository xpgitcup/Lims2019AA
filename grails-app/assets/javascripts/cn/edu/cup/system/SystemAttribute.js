var operation4SystemAttributeDiv;
var jsTitle = "系统属性";
var title4SystemAttribute = [jsTitle]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemAttributeDiv = $("#operation4SystemAttributeDiv");
    var settings = {
        divId: operation4SystemAttributeDiv,
        titles: title4SystemAttribute,
        //paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadSystemAttribute,
        countFunction: countSystemAttribute
    }

    configDisplayUI(settings);
});

/*
* 统计函数
* */
function countSystemAttribute(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemAttribute/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemAttribute(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemAttribute/list" + params + "&key=" + title, 0, "list" + title + "Div");
}