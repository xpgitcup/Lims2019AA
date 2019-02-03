package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ThingTypeCircleController {

    ThingTypeCircleService thingTypeCircleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond thingTypeCircleService.list(params), model:[thingTypeCircleCount: thingTypeCircleService.count()]
    }

    def show(Long id) {
        respond thingTypeCircleService.get(id)
    }

    def create() {
        respond new ThingTypeCircle(params)
    }

    def save(ThingTypeCircle thingTypeCircle) {
        if (thingTypeCircle == null) {
            notFound()
            return
        }

        try {
            thingTypeCircleService.save(thingTypeCircle)
        } catch (ValidationException e) {
            respond thingTypeCircle.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), thingTypeCircle.id])
                redirect thingTypeCircle
            }
            '*' { respond thingTypeCircle, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond thingTypeCircleService.get(id)
    }

    def update(ThingTypeCircle thingTypeCircle) {
        if (thingTypeCircle == null) {
            notFound()
            return
        }

        try {
            thingTypeCircleService.save(thingTypeCircle)
        } catch (ValidationException e) {
            respond thingTypeCircle.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), thingTypeCircle.id])
                redirect thingTypeCircle
            }
            '*'{ respond thingTypeCircle, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        thingTypeCircleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
