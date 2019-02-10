package cn.edu.cup.os

import cn.edu.cup.system.JsFrame
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemMenuController
import grails.converters.JSON

class Operation4SystemMenuController extends SystemMenuController {

    def commonQueryService
    def treeViewService

    /*
    * 获取当前id对应的对象
    * */

    def getSystemMenu(SystemMenu systemMenu) {
        def theModel = [systemMenu: systemMenu]
        if (request.xhr) {
            render(template: "showSystemMenu", model: theModel)
        } else {
            theModel
        }
    }

    /*
    * 创建对象
    * */

    def createSystemMenu(SystemMenu systemMenu) {
        def newSystemMenu = new SystemMenu(upMenuItem: systemMenu)
        if (request.xhr) {
            render(template: 'editSystemMenu', model: [systemMenu: newSystemMenu])
        } else {
            respond newSystemMenu
        }
    }

    /*
    * 保存对象
    * */

    def updateSystemMenu(SystemMenu systemMenu) {
        println("准备更新：${systemMenu}")
        //systemMenu.save flush:true
        systemMenuService.save(systemMenu)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editSystemMenu(SystemMenu systemMenu) {
        if (request.xhr) {
            render(template: 'editSystemMenu', model: [systemMenu: systemMenu])
        } else {
            respond systemMenu
        }
    }

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
