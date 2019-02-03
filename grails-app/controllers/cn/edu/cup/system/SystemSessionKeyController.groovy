package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemSessionKeyController {

    SystemSessionKeyService systemSessionKeyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemSessionKeyService.list(params), model:[systemSessionKeyCount: systemSessionKeyService.count()]
    }

    def show(Long id) {
        respond systemSessionKeyService.get(id)
    }

    def create() {
        respond new SystemSessionKey(params)
    }

    def save(SystemSessionKey systemSessionKey) {
        if (systemSessionKey == null) {
            notFound()
            return
        }

        try {
            systemSessionKeyService.save(systemSessionKey)
        } catch (ValidationException e) {
            respond systemSessionKey.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemSessionKey.label', default: 'SystemSessionKey'), systemSessionKey.id])
                redirect systemSessionKey
            }
            '*' { respond systemSessionKey, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemSessionKeyService.get(id)
    }

    def update(SystemSessionKey systemSessionKey) {
        if (systemSessionKey == null) {
            notFound()
            return
        }

        try {
            systemSessionKeyService.save(systemSessionKey)
        } catch (ValidationException e) {
            respond systemSessionKey.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemSessionKey.label', default: 'SystemSessionKey'), systemSessionKey.id])
                redirect systemSessionKey
            }
            '*'{ respond systemSessionKey, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemSessionKeyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemSessionKey.label', default: 'SystemSessionKey'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemSessionKey.label', default: 'SystemSessionKey'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
