var operation4SystemAttributeDiv;
var title4SystemAttribute = ["系统属性维护"]
var jsTitle = "系统属性";

$(function () {
    console.info(jsTitle + "......");

    operation4SystemAttributeDiv = $("#operation4SystemAttributeDiv");
    var settings = {
        divId: operation4SystemAttributeDiv,
        titles: title4SystemAttribute,
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
    var total = ajaxCalculate("operation4StudentAttribute/count?title=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemAttribute(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......");

}