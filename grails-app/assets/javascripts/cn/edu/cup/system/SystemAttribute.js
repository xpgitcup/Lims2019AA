var operationSystemAttributeDiv;
var title4SystemAttribute = ["系统属性维护"]

$(function () {
    console.info("系统属性操作...");

    operationSystemAttributeDiv = $("#operationSystemAttributeDiv");
    var settings = {
        divId: operationSystemAttributeDiv,
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

}

/*
* 数据加载函数
* */
function loadSystemAttribute(title, page, pageSize) {

}