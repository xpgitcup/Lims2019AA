package cn.edu.cup.os

import cn.edu.cup.system.QueryStatement
import cn.edu.cup.lims.QueryStatementController
import grails.converters.JSON
import grails.validation.ValidationException

class Operation4QueryStatementController extends QueryStatementController {

    def commonQueryService
    def commonService

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
        def fileName = commonService.queryStatementConfigFileName()
        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def queryList = com.alibaba.fastjson.JSON.parse(json)
            queryList.each { e ->
                println("导入${e}")
                def nq = QueryStatement.findByKeyString(e.keyString)
                if (!nq) {
                    nq = new QueryStatement()
                }
                e.each { item ->
                    nq.properties.put(item.key, item.value)
                }
                queryStatementService.save(nq)
            }
        }
        flash.message = "导入成功!"
        redirect(action: 'index')
    }

    def exportToJsonFile() {

        def fileName = commonService.queryStatementConfigFileName()
        println("${fileName}")

        def queryStatementList = []
        QueryStatement.list().each { e ->
            def q = [:]
            e.properties.each { ee ->
                q.put(ee.key, ee.value)
            }
            queryStatementList.add(q)
        }

        def fjson = com.alibaba.fastjson.JSON.toJSONString(queryStatementList)
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
        //def (String view, List objectList, String message) = commonQueryService.listFunction(params)
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
        def result = commonQueryService.countFunction(params)
        println("统计结果：${result.count}")
        flash.message = "请完善count."
        def cresult = [count: result.count]
        if (request.xhr) {
            render cresult as JSON
        } else {
            result
        }
    }

    def index() {}
}
