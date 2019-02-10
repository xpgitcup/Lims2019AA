package cn.edu.cup.os

import cn.edu.cup.system.SystemUser
import cn.edu.cup.system.SystemUserController
import grails.converters.JSON

class Operation4SystemUserController extends SystemUserController {

    def commonQueryService

    /*
    * 创建对象
    * */
    def createSystemUser(SystemUser systemUser) {
        def newSystemUser = new SystemUser()
        if (request.xhr) {
            render(template: 'editSystemUser', model: [systemUser: newSystemUser])
        } else {
            respond newSystemUser
        }
    }

    /*
    * 保存对象
    * */
    def updateSystemUser(SystemUser systemUser) {
        println("准备更新：${systemUser}")
        //systemUser.save flush:true
        systemUserService.save(systemUser)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */
    def editSystemUser(SystemUser systemUser) {
        if (request.xhr) {
            render(template: 'editSystemUser', model: [systemUser: systemUser])
        } else {
            respond SystemUser
        }
    }

    /*
    * 获取当前id对应的对象
    * */
    def getSystemUser(SystemUser systemUser) {
        def theModel = [systemUser: systemUser]
        println("${systemUser}")
        if (request.xhr) {
            render(template: "showSystemUser", model:theModel)
        } else {
            theModel
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
