package cn.edu.cup.os

import cn.edu.cup.system.SystemAttribute
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class Operation4SystemAttributeController {

    def systemAttributeService

    def show(Long id) {
        respond systemAttributeService.get(id)
    }

    def create() {
        respond new SystemAttribute(params)
    }

    def save(SystemAttribute systemAttribute) {
        if (systemAttribute == null) {
            notFound()
            return
        }

        try {
            systemAttributeService.save(systemAttribute)
        } catch (ValidationException e) {
            respond systemAttribute.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), systemAttribute.id])
                redirect systemAttribute
            }
            '*' { respond systemAttribute, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemAttributeService.get(id)
    }

    def update(SystemAttribute systemAttribute) {
        if (systemAttribute == null) {
            notFound()
            return
        }

        try {
            systemAttributeService.save(systemAttribute)
        } catch (ValidationException e) {
            respond systemAttribute.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), systemAttribute.id])
                redirect systemAttribute
            }
            '*' { respond systemAttribute, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemAttributeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def index() {}
}
