package cn.edu.cup.os

import cn.edu.cup.system.JsFrame
import cn.edu.cup.system.SystemMenu
import grails.converters.JSON

class Operation4SystemMenuController {

    def commonQueryService
    def treeViewService

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = SystemMenu.findAllByUpMenuItemIsNull(params)     //这是必须调整的
        params.context = "menuContext"
        params.subItems = "menuItems"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def list() {
        println("${params}")
        def result = commonQueryService.listFunction(params)
        def view = result.view
        flash.message = result.message
        if (request.xhr) {
            render(template: view, model: [objectList: result.objectList, flash: flash])
        } else {
            respond result.objectList
        }
    }

    def count() {
        def count = commonQueryService.countFunction(params)
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
