var operation4QueryStatementDiv;
var jsTitleQueryStatement = "查询配置";
var title4QueryStatement = [jsTitleQueryStatement]
var localPageSizeQueryStatement = 5;

$(function () {
    console.info(jsTitleQueryStatement + "......");

    operation4QueryStatementDiv = $("#operation4QueryStatementDiv");
    var settings = {
        divId: operation4QueryStatementDiv,
        titles: title4QueryStatement,
        pageSize: localPageSizeQueryStatement,
        pageList: [1,3,5,10],
        loadFunction: loadQueryStatement,
        countFunction: countQueryStatement
    }

    configDisplayUI(settings);
});

function deleteItem(id) {
    console.info("删除：" + id);
    ajaxExecuteWithMethod("operation4QueryStatement/delete?id=" + id, 'DELETE');
    console.info("删除：" + id + "了！");
    location.reload();
}

/*
* 定位到需要编辑的记录
* */
function listToDo() {
    console.info(jsTitleQueryStatement + "+待完成......");
    var title = jsTitleQueryStatement;
    var page = 1;   //每次都定位到第一页
    var params = getParams(page, localPageSizeQueryStatement);    //getParams必须是放在最最前面！！
    ajaxRun("operation4QueryStatement/list" + params + "&key=" + title + ".TODO", 0, "list" + title + "Div");
}

/*
* 统计函数
* */
function countQueryStatement(title) {
    console.info(jsTitleQueryStatement + "+统计......");
    var total = ajaxCalculate("operation4QueryStatement/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadQueryStatement(title, page, pageSize) {
    console.info(jsTitleQueryStatement + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4QueryStatement/list" + params + "&key=" + title, 0, "list" + title + "Div");
}