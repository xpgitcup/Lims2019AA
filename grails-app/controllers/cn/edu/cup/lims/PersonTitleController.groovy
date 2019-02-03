package cn.edu.cup.lims

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PersonTitleController {

    PersonTitleService personTitleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond personTitleService.list(params), model:[personTitleCount: personTitleService.count()]
    }

    def show(Long id) {
        respond personTitleService.get(id)
    }

    def create() {
        respond new PersonTitle(params)
    }

    def save(PersonTitle personTitle) {
        if (personTitle == null) {
            notFound()
            return
        }

        try {
            personTitleService.save(personTitle)
        } catch (ValidationException e) {
            respond personTitle.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), personTitle.id])
                redirect personTitle
            }
            '*' { respond personTitle, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond personTitleService.get(id)
    }

    def update(PersonTitle personTitle) {
        if (personTitle == null) {
            notFound()
            return
        }

        try {
            personTitleService.save(personTitle)
        } catch (ValidationException e) {
            respond personTitle.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), personTitle.id])
                redirect personTitle
            }
            '*'{ respond personTitle, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        personTitleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personTitle.label', default: 'PersonTitle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
