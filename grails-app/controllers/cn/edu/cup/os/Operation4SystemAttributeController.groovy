package cn.edu.cup.os

import cn.edu.cup.system.JsFrame
import cn.edu.cup.system.SystemAttribute
import cn.edu.cup.system.SystemAttributeController
import grails.converters.JSON

class Operation4SystemAttributeController extends SystemAttributeController {

    def commonQueryService
    def treeViewService

    /*
    * 创建对象
    * */
    def createSystemAttribute(SystemAttribute systemAttribute) {
        def newSystemAttribute = new SystemAttribute(upAttribute: systemAttribute)
        if (request.xhr) {
            render(template: 'editSystemAttribute', model: [systemAttribute: newSystemAttribute])
        } else {
            respond newSystemAttribute
        }
    }

    /*
    * 保存对象
    * */
    def updateSystemAttribute(SystemAttribute systemAttribute) {
        println("准备更新：${systemAttribute}")
        systemAttributeService.save(systemAttribute)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */
    def editSystemAttribute(SystemAttribute systemAttribute) {
        if (request.xhr) {
            render(template: 'editSystemAttribute', model: [systemAttribute: systemAttribute])
        } else {
            respond systemAttribute
        }
    }

    /*
    * 显示当前id对应的对象
    * */

    def show(SystemAttribute systemAttribute) {
        def theModel = [systemAttribute: systemAttribute]
        if (request.xhr) {
            render(template: "showSystemAttribute", model: theModel)
        } else {
            theModel
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = SystemAttribute.findAllByUpAttributeIsNull(params);
        params.context = "name"
        params.subItems = "subAttribues"
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
        println("统计${params}")
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
