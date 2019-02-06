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
    var loadName = settings.loadFunction;
    var countName = settings.countFunction;
    var loadFunction = eval(loadName);
    var countFunction = eval(countName);

    var panelDiv;
    var paginationDiv;
    var title;

    if (titles.length < 2) {
        title = titles[0];
        // 添加显示元件
        panelDiv = addNewPanelDiv(title, theDiv);
        paginationDiv = addNewPaginationDiv(title, theDiv);
        // 设置参数
        panelDiv.panel({
            href: loadFunction()
        })
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