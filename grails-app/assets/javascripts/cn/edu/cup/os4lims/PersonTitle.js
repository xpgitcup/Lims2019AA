var operation4PersonTitleDiv;
var jsTitle = "人员类型";
var title4PersonTitle = [jsTitle]
var isTreeView4PersonTitle = [true]
var treeData4PersonTitle = ["operation4PersonTitle/getTreeViewData"]

$(function () {
    console.info(jsTitle + "......");

    operation4PersonTitleDiv = $("#operation4PersonTitleDiv");
    var settings = {
        divId: operation4PersonTitleDiv,
        titles: title4PersonTitle,
        // 有关树形结构的设置
        isTreeView: isTreeView4PersonTitle,
        treeData: treeData4PersonTitle,
        treeNodeDoSomeThing: systemAttributeNodeSelect, //当节点被选择
        //paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadPersonTitle,
        countFunction: countPersonTitle
    }

    configDisplayUI(settings);
});

/*
* 新建
* */
function createPersonTitle(id) {
    console.info("创建PersonTitle. 上级节点：" + id);
    ajaxRun("operation4PersonTitle/createPersonTitle", id, "showPersonTitleDiv");
}

/*
* 编辑
* */
function editPersonTitle(id) {
    console.info("编辑PersonTitle." + id);
    ajaxRun("operation4PersonTitle/editPersonTitle", id, "showPersonTitleDiv");
}

/*
* 显示节点信息
* */
function showPersonTitle(node) {
    console.info(jsTitle + "+节点显示......" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("operation4PersonTitle/show", id, "showPersonTitleDiv");
    }
}

/*
* 节点被选择。。。
* */
function systemAttributeNodeSelect(node) {
    console.info(jsTitle + "+节点选择......" + node);
    showPersonTitle(node);
    $("#createPersonTitle").attr('href', 'javascript: createPersonTitle(' + node.attributes[0] + ')');
    console.info(node);
    console.info("当前节点：" + node.target.id);
    $.cookie("currentPersonTitle", node.target.id);
}

/*
* 统计函数
* */
function countPersonTitle(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4PersonTitle/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadPersonTitle(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4PersonTitle/list" + params + "&key=" + title, 0, "list" + title + "Div");
}