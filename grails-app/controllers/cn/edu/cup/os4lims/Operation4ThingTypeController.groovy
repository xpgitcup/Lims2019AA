package cn.edu.cup.os4lims

import cn.edu.cup.lims.ThingType
import cn.edu.cup.lims.ThingTypeController
import cn.edu.cup.system.JsFrame
import grails.converters.JSON
import grails.validation.ValidationException

class Operation4ThingTypeController extends ThingTypeController {

    def commonQueryService
    def treeViewService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Long id) {
        def thingType = thingTypeService.get(id)
        println("show ${thingType}")
        def view = "show"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType])
        } else {
            respond thingType
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        thingTypeService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'thingType.label', default: 'PersonTitle'), id])

        redirect(action: "index")
    }

    def update(ThingType thingType) {
        if (thingType == null) {
            notFound()
            return
        }

        try {
            thingTypeService.save(thingType)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'thingType.label', default: 'ThingType'), thingType.id])
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def edit(Long id) {
        def thingType = thingTypeService.get(id)
        def view = "edit"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType])
        } else {
            respond thingType
        }
    }

    def save(ThingType thingType) {
        if (thingType == null) {
            notFound()
            return
        }

        try {
            thingTypeService.save(thingType)
            flash.message = message(code: 'default.created.message', args: [message(code: 'thingType.label', default: 'ThingType'), thingType.id])
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def create(ThingType aThingType) {
        println("${params}")
        def thingType = new ThingType(upType: aThingType)
        def view = "create"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType])
        } else {
            respond thingType
        }
    }

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
