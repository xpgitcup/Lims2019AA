package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ThingController {

    ThingService thingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond thingService.list(params), model:[thingCount: thingService.count()]
    }

    def show(Long id) {
        respond thingService.get(id)
    }

    def create() {
        respond new Thing(params)
    }

    def save(Thing thing) {
        if (thing == null) {
            notFound()
            return
        }

        try {
            thingService.save(thing)
        } catch (ValidationException e) {
            respond thing.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'thing.label', default: 'Thing'), thing.id])
                redirect thing
            }
            '*' { respond thing, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond thingService.get(id)
    }

    def update(Thing thing) {
        if (thing == null) {
            notFound()
            return
        }

        try {
            thingService.save(thing)
        } catch (ValidationException e) {
            respond thing.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'thing.label', default: 'Thing'), thing.id])
                redirect thing
            }
            '*'{ respond thing, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        thingService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'thing.label', default: 'Thing'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'thing.label', default: 'Thing'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
