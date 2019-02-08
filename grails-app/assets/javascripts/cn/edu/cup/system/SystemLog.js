var operation4SystemLogDiv;
var jsTitle = "系统日志";
var title4SystemLog = [jsTitle]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemLogDiv = $("#operation4SystemLogDiv");
    var settings = {
        divId: operation4SystemLogDiv,
        titles: title4SystemLog,
        pageSize: 5,
        loadFunction: loadSystemLog,
        countFunction: countSystemLog
    }

    configDisplayUI(settings);
});

/*
* 统计函数
* */
function countSystemLog(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemLog/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemLog(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemLog/list" + params + "&key=" + title, 0, "list" + title + "Div");
}