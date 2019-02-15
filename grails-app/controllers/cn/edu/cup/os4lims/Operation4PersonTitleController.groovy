package cn.edu.cup.os4lims

import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.lims.PersonTitleController
import cn.edu.cup.system.JsFrame
import grails.converters.JSON
import grails.validation.ValidationException

class Operation4PersonTitleController extends PersonTitleController {

    def commonQueryService
    def treeViewService

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        personTitleService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), id])

        redirect(action: "index")
    }

    def save(PersonTitle personTitle) {
        if (personTitle == null) {
            notFound()
            return
        }

        try {
            personTitleService.save(personTitle)
            flash.message = message(code: 'default.created.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), personTitle.id])
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def update(PersonTitle personTitle) {
        if (personTitle == null) {
            notFound()
            return
        }

        try {
            personTitleService.save(personTitle)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), personTitle.id])
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def edit(Long id) {
        def personTitle = personTitleService.get(id)
        def view = "edit"
        if (request.xhr) {
            render(template: view, model: [personTitle: personTitle])
        } else {
            respond personTitle
        }
    }

    def create(PersonTitle apersonTitle) {
        println("${params}")
        def personTitle = new PersonTitle(upTitle: apersonTitle)
        def view = "create"
        if (request.xhr) {
            render(template: view, model: [personTitle: personTitle])
        } else {
            respond personTitle
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = PersonTitle.findAllByUpTitleIsNull(params);
        params.context = "name"
        params.subItems = "subTitles"
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
