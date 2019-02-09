/**
 * Created by LiXiaoping on 2019/2/6.
 *
 * 缺省的页面长度=10
 * 用对象传递参数，这样保证所有的函数只有一个传入参数
 * 标签页、面板页--能够二合一，只有一个标签的时候，就是面板；多余一个就是标签
 *
 */

var defaultPageSize = 10

/*
* 显示界面配置函数
* */
function configDisplayUI(settings) {
    var theDiv = settings.divId;
    var titles = settings.titles;

    var loadFunction = eval(settings.loadFunction);
    var countFunction = eval(settings.countFunction);
    var treeNodeDoSomeThing = eval(settings.treeNodeDoSomeThing)

    var paginationMessage = settings.paginationMessage;
    var pageList = settings.pageList;

    var panelDiv;
    var paginationDiv;
    var treeViewUl;
    var title;
    var localPageSize;

    //处理页面长度
    if (settings.pageSize) {
        localPageSize = settings.pageSize;
    } else {
        localPageSize = defaultPageSize;
    }

    console.info(settings.isTreeView);

    if (titles.length < 2) {
        title = titles[0];
        // 添加显示元件
        panelDiv = addNewPanelDiv(title, theDiv);

        if (settings.isTreeView != undefined) {
            if (settings.isTreeView[0]) {
                treeViewUl = addNewTreeView(title, theDiv);
            }
        }

        paginationDiv = addNewPaginationDiv(title, theDiv);
        // 设置参数
        console.info("开始设置参数......")

        // 分页处理
        var currentPage = readCookie("currentPage" + title, 1);
        var total = countFunction(title)
        paginationDiv.pagination({
            pageSize: localPageSize,
            total: total,
            pageList: pageList, //[1, 3, 5, 10, 20, 30],
            showPageList: settings.showPageList,
            pageNumber: currentPage,
            displayMsg: paginationMessage,
            onSelectPage: function (pageNumber, pageSize) {
                console.info("setupPaginationParams4TabPage: " + title)
                $.cookie("currentPage" + title, pageNumber);     //记录当前页面
                loadFunction(title, pageNumber, pageSize);
            }
        })
        // 单页的显示--加载数据
        if ((settings.isTreeView == undefined)) {
            panelDiv.panel({
                href: loadFunction(title, currentPage, localPageSize)
            })
        } else {
            if (settings.isTreeView[0]) {
                treeViewUl.tree({
                    url: settings.treeData[0],
                    onSelect: function (node) {
                        console.info("树形结构节点选择：" + node)
                        treeNodeDoSomeThing(node)
                    },
                    onLoadSuccess: function () {
                        treeViewUl.tree("collapseAll");
                    }
                })
            }
        }
    } else {
        // 添加显示元件
        for (var i in titles) {
            title = titles[i];

            theDiv.tabs('add', {
                title: title,
                closable: false
            })

            //插入到tab中
            tabsDiv.tabs('select', x)
            var tab = tabsDiv.tabs('getSelected');
            var listDiv = addNewNormalDiv(title, tab);
            var paginationDiv = addNewPaginationDiv(title, tab);
        }
    }


    /*
    * 私有函数
    * */

    function addNewPaginationDiv(title, parentDiv) {
        //分页Div------加入到标签中
        var paginationDiv = $('<div class="easyui-pagination"></div>');
        paginationDiv.attr('id', 'pagination' + title + 'Div');
        paginationDiv.appendTo(parentDiv)
        return paginationDiv;
    }

    function addNewTreeView(title, parentDiv) {
        //树形显示页面
        console.info("创建树形结构：" + title);
        var treeViewUl = $('<ul class="easyui-tree"></ul>');
        treeViewUl.attr('id', 'treeView' + title + 'Ul');
        treeViewUl.appendTo(parentDiv)
        return treeViewUl;
    }

    function addNewNormalDiv(title, parentDiv) {
        //显示页面
        var listDiv = $('<div></div>');
        listDiv.attr('id', 'list' + title + 'Div');
        listDiv.appendTo(parentDiv)
        return listDiv;
    }

    function addNewPanelDiv(title, parentDiv) {
        //显示页面
        var listDiv = $('<div></div>');
        listDiv.attr('id', 'list' + title + 'Div');
        listDiv.attr("class", "easyui-panel");
        listDiv.attr("title", title);
        listDiv.appendTo(parentDiv)
        return listDiv;
    }

}