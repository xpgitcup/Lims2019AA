package cn.edu.cup.os

import cn.edu.cup.system.SystemLogController
import grails.converters.JSON

class Operation4SystemLogController extends SystemLogController{

    def commonQueryService

    def count() {
        def (count, message) = commonQueryService.countFunction(params)
        println("统计结果：${count}")
        flash.message = message
        if (count) {
            if (count[0] < 0) {
                flash.message = "功能尚未实现....."
                count = 0
            }
        }
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() { }
}
