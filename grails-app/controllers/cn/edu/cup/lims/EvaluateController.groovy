package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EvaluateController {

    EvaluateService evaluateService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond evaluateService.list(params), model:[evaluateCount: evaluateService.count()]
    }

    def show(Long id) {
        respond evaluateService.get(id)
    }

    def create() {
        respond new Evaluate(params)
    }

    def save(Evaluate evaluate) {
        if (evaluate == null) {
            notFound()
            return
        }

        try {
            evaluateService.save(evaluate)
        } catch (ValidationException e) {
            respond evaluate.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), evaluate.id])
                redirect evaluate
            }
            '*' { respond evaluate, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond evaluateService.get(id)
    }

    def update(Evaluate evaluate) {
        if (evaluate == null) {
            notFound()
            return
        }

        try {
            evaluateService.save(evaluate)
        } catch (ValidationException e) {
            respond evaluate.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), evaluate.id])
                redirect evaluate
            }
            '*'{ respond evaluate, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        evaluateService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
