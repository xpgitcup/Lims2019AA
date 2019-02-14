var operation4ThingTypeDiv;
var jsTitle = "项目类型";
var title4ThingType = [jsTitle]
var isTreeView4ThingType = [true]
var treeData4ThingType = ["operation4ThingType/getTreeViewData"]

$(function () {
    console.info(jsTitle + "......");

    operation4ThingTypeDiv = $("#operation4ThingTypeDiv");
    var settings = {
        divId: operation4ThingTypeDiv,
        titles: title4ThingType,
        // 有关树形结构的设置
        isTreeView: isTreeView4ThingType,
        treeData: treeData4ThingType,
        treeNodeDoSomeThing: systemAttributeNodeSelect, //当节点被选择
        //paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadThingType,
        countFunction: countThingType
    }

    configDisplayUI(settings);
});

/*
* 新建
* */
function createThingType(id) {
    console.info("创建ThingType. 上级节点：" + id);
    ajaxRun("operation4ThingType/createThingType", id, "showThingTypeDiv");
}

/*
* 编辑
* */
function editThingType(id) {
    console.info("编辑ThingType." + id);
    ajaxRun("operation4ThingType/editThingType", id, "showThingTypeDiv");
}

/*
* 显示节点信息
* */
function showThingType(node) {
    console.info(jsTitle + "+节点显示......" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("operation4ThingType/show", id, "showThingTypeDiv");
    }
}

/*
* 节点被选择。。。
* */
function systemAttributeNodeSelect(node) {
    console.info(jsTitle + "+节点选择......" + node);
    showThingType(node);
    $("#createThingType").attr('href', 'javascript: createThingType(' + node.attributes[0] + ')');
    console.info(node);
    console.info("当前节点：" + node.target.id);
    $.cookie("currentThingType", node.target.id);
}

/*
* 统计函数
* */
function countThingType(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4ThingType/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadThingType(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ThingType/list" + params + "&key=" + title, 0, "list" + title + "Div");
}