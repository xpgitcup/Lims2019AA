var operation4ThingDiv;
var jsTitleThing = ["科研", "教学"];
var title4Thing = jsTitleThing;
var tabsTitle = "项目数据";
var localPageSizeThing = 10;

$(function () {
    console.info(jsTitleThing + "......");

    operation4ThingDiv = $("#operation4ThingDiv");
    var settings = {
        divId: operation4ThingDiv,
        titles: title4Thing,
        tabsTitle: tabsTitle,
        pageSize: localPageSizeThing,
        pageList: [1, 3, 5, 10],
        loadFunction: loadThing,
        countFunction: countThing
    }

    configDisplayUI(settings);
});

function shiftDisplay(title) {
    console.info("显示当前提示...")
    $("#currentTemplate").html("下载[" + title + "]模板");
    $("#currentTemplate").attr("href", "operation4Thing/downloadTemplate?key=" + title);
    $("#currentImport").html("导入[" + title + "]数据");
    $("#importKey").attr("value", title);
}

/*
* 定位到需要编辑的记录
* */
function listToDo() {
    console.info(jsTitleThing + "+待完成......");
    var title = jsTitleThing;
    var page = 1;   //每次都定位到第一页
    var params = getParams(page, localPageSizeThing);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Thing/list" + params + "&key=" + title + ".TODO", 0, "list" + title + "Div");
}

/*
* 统计函数
* */
function countThing(title) {
    console.info(jsTitleThing + "+统计......");
    shiftDisplay(title);
    var total = ajaxCalculate("operation4Thing/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadThing(title, page, pageSize) {
    console.info(jsTitleThing + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Thing/list" + params + "&key=" + title, 0, "list" + title + "Div");
}