package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QueryStatementController {

    QueryStatementService queryStatementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond queryStatementService.list(params), model:[queryStatementCount: queryStatementService.count()]
    }

    def show(Long id) {
        respond queryStatementService.get(id)
    }

    def create() {
        respond new QueryStatement(params)
    }

    def save(QueryStatement queryStatement) {
        if (queryStatement == null) {
            notFound()
            return
        }

        try {
            queryStatementService.save(queryStatement)
        } catch (ValidationException e) {
            respond queryStatement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), queryStatement.id])
                redirect queryStatement
            }
            '*' { respond queryStatement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond queryStatementService.get(id)
    }

    def update(QueryStatement queryStatement) {
        if (queryStatement == null) {
            notFound()
            return
        }

        try {
            queryStatementService.save(queryStatement)
        } catch (ValidationException e) {
            respond queryStatement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), queryStatement.id])
                redirect queryStatement
            }
            '*'{ respond queryStatement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        queryStatementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
