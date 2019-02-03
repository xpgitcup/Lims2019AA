package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MajorController {

    MajorService majorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond majorService.list(params), model:[majorCount: majorService.count()]
    }

    def show(Long id) {
        respond majorService.get(id)
    }

    def create() {
        respond new Major(params)
    }

    def save(Major major) {
        if (major == null) {
            notFound()
            return
        }

        try {
            majorService.save(major)
        } catch (ValidationException e) {
            respond major.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'major.label', default: 'Major'), major.id])
                redirect major
            }
            '*' { respond major, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond majorService.get(id)
    }

    def update(Major major) {
        if (major == null) {
            notFound()
            return
        }

        try {
            majorService.save(major)
        } catch (ValidationException e) {
            respond major.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'major.label', default: 'Major'), major.id])
                redirect major
            }
            '*'{ respond major, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        majorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'major.label', default: 'Major'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'major.label', default: 'Major'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
