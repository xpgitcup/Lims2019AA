var operation4QueryStatementDiv;
var jsTitle = "查询配置";
var title4QueryStatement = [jsTitle]

$(function () {
    console.info(jsTitle + "......");

    operation4QueryStatementDiv = $("#operation4QueryStatementDiv");
    var settings = {
        divId: operation4QueryStatementDiv,
        titles: title4QueryStatement,
        pageSize: 5,
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
    var total = ajaxCalculate("operation4QueryStatement/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadQueryStatement(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4QueryStatement/list" + params + "&key=" + title, 0, "list" + title + "Div");
}