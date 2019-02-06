var operation4SystemMenuDiv;
var title4SystemMenu = ["系统菜单维护"]

$(function () {
    console.info("系统菜单操作...");

    operation4SystemMenuDiv = $("#operation4SystemMenuDiv");
    var settings = {
        divId: operation4SystemMenuDiv,
        titles: title4SystemMenu,
        loadFunction: loadSystemMenu,
        countFunction: countSystemMenu
    }

    configDisplayUI(settings);
});

/*
* 统计函数
* */
function countSystemMenu(title) {

}

/*
* 数据加载函数
* */
function loadSystemMenu(title, page, pageSize) {

}