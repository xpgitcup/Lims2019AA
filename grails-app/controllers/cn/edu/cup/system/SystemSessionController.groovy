package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemSessionController {

    SystemSessionService systemSessionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemSessionService.list(params), model:[systemSessionCount: systemSessionService.count()]
    }

    def show(Long id) {
        respond systemSessionService.get(id)
    }

    def create() {
        respond new SystemSession(params)
    }

    def save(SystemSession systemSession) {
        if (systemSession == null) {
            notFound()
            return
        }

        try {
            systemSessionService.save(systemSession)
        } catch (ValidationException e) {
            respond systemSession.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemSession.label', default: 'SystemSession'), systemSession.id])
                redirect systemSession
            }
            '*' { respond systemSession, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemSessionService.get(id)
    }

    def update(SystemSession systemSession) {
        if (systemSession == null) {
            notFound()
            return
        }

        try {
            systemSessionService.save(systemSession)
        } catch (ValidationException e) {
            respond systemSession.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemSession.label', default: 'SystemSession'), systemSession.id])
                redirect systemSession
            }
            '*'{ respond systemSession, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemSessionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemSession.label', default: 'SystemSession'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemSession.label', default: 'SystemSession'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
