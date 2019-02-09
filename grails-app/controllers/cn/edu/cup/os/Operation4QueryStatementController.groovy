package cn.edu.cup.os

import cn.edu.cup.system.QueryStatement
import cn.edu.cup.system.QueryStatementController
import grails.converters.JSON
import grails.validation.ValidationException

class Operation4QueryStatementController extends QueryStatementController {

    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def update(QueryStatement queryStatement) {
        if (queryStatement == null) {
            notFound()
            return
        }

        try {
            queryStatementService.save(queryStatement)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), queryStatement.id])
        } catch (ValidationException e) {
            flash.message = queryStatement.errors
        }
        redirect(action: "index")
    }

    def importFromJsonFile() {
        // 先清空
        QueryStatement.list().each { e ->
            queryStatementService.delete(e.id)
        }
        //再导入
        def fileName = commonService.queryStatementConfigFileName()
        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, QueryStatement.class)
            querys.each { e ->
                queryStatementService.save(e)
            }
        }
        flash.message = "导入成功!"
        redirect(action: 'index')
    }

    def exportToJsonFile() {

        def fileName = commonService.queryStatementConfigFileName()
        println("${fileName}")

        def fjson = commonService.exportObjects2JsonString(QueryStatement.list())
        println("FastJson:")
        println(fjson)

        def printer = new File(fileName).newPrintWriter('utf-8')    //写入文件
        printer.println(fjson)
        printer.close()

        flash.message = "导出json文件成功！"

        redirect(action: 'index')
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
