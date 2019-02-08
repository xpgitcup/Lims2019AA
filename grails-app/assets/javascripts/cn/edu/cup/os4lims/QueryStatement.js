var operation4QueryStatementDiv;
var title4QueryStatement = ["查询维护"]
var jsTitle = "查询维护";

$(function () {
    console.info(jsTitle + "......");

    operation4QueryStatementDiv = $("#operation4QueryStatementDiv");
    var settings = {
        divId: operation4QueryStatementDiv,
        titles: title4QueryStatement,
        loadFunction: loadQueryStatement,
        countFunction: countQueryStatement
    }

    configDisplayUI(settings);
});

/*
* 统计函数
* */
function countQueryStatement(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4StudentAttribute/count?title=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadQueryStatement(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......");

}