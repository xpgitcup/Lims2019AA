package cn.edu.cup.os4lims

import cn.edu.cup.lims.ThingTypeCircle
import cn.edu.cup.lims.ThingTypeCircleController
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED

class Operation4ThingTypeCircleController extends ThingTypeCircleController {

    def commonQueryService

    def save(ThingTypeCircle thingTypeCircle) {
        if (thingTypeCircle == null) {
            notFound()
            return
        }

        try {
            if (ThingTypeCircle.countByThingTypeAndPersonTitle(thingTypeCircle.thingType, thingTypeCircle.personTitle)<1)
            {
                thingTypeCircleService.save(thingTypeCircle)
                flash.message = "ok！"
            } else {
                flash.message = "重复了！"
            }
        } catch (ValidationException e) {
            respond thingTypeCircle.errors, view: 'create'
            return
        }
        redirect(action: "index")
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
