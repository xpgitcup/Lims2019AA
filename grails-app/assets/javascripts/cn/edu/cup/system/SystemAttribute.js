var operation4SystemAttributeDiv;
var title4SystemAttribute = ["系统属性维护"]

$(function () {
    console.info("系统属性操作...");

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

}

/*
* 数据加载函数
* */
function loadSystemAttribute(title, page, pageSize) {

}