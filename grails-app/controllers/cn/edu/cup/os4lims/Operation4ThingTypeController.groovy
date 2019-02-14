package cn.edu.cup.os4lims

import cn.edu.cup.lims.ThingType
import cn.edu.cup.lims.ThingTypeController
import cn.edu.cup.system.JsFrame
import grails.converters.JSON

class Operation4ThingTypeController extends ThingTypeController {

    def commonQueryService
    def treeViewService

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = ThingType.findAllByUpTypeIsNull(params);
        params.context = "name"
        params.subItems = "subTypes"
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
