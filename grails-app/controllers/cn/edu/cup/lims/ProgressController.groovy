package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProgressController {

    ProgressService progressService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond progressService.list(params), model:[progressCount: progressService.count()]
    }

    def show(Long id) {
        respond progressService.get(id)
    }

    def create() {
        respond new Progress(params)
    }

    def save(Progress progress) {
        if (progress == null) {
            notFound()
            return
        }

        try {
            progressService.save(progress)
        } catch (ValidationException e) {
            respond progress.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'progress.label', default: 'Progress'), progress.id])
                redirect progress
            }
            '*' { respond progress, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond progressService.get(id)
    }

    def update(Progress progress) {
        if (progress == null) {
            notFound()
            return
        }

        try {
            progressService.save(progress)
        } catch (ValidationException e) {
            respond progress.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'progress.label', default: 'Progress'), progress.id])
                redirect progress
            }
            '*'{ respond progress, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        progressService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'progress.label', default: 'Progress'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'progress.label', default: 'Progress'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
