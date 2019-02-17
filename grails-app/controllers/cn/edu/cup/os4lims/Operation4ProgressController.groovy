package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.Progress
import cn.edu.cup.lims.ProgressController
import cn.edu.cup.lims.Team
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED

class Operation4ProgressController extends ProgressController {

    def commonQueryService

    def save(Progress progress) {
        if (progress == null) {
            notFound()
            return
        }

        try {
            progressService.save(progress)
        } catch (ValidationException e) {
            respond progress.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'progress.label', default: 'Progress'), progress.id])
                redirect(action: "index")
            }
            '*' { respond progress, [status: CREATED] }
        }
    }

    def createProgress(Team team) {
        def myself = Person.get(session.realId)
        def progress = new Progress(team: team, contributor: myself)
        def view = "createProgress"
        if (request.xhr) {
            render(template: view, model: [progress: progress])
        } else {
            respond progress
        }
    }

    def list() {
        prepareParams()
        println("${params}")
        def result = commonQueryService.listFunction(params)
        def view = result.view
        flash.message = result.message
        switch (params.key) {
            case "我参与的":
                def teams = []
                result.objectList.each { e ->
                    //println("查找 ${e}")
                    teams.add(Team.get(e.team_members_id))
                }
                //println("转换后：${teams}")
                result.objectList = teams
                break
        }
        if (request.xhr) {
            render(template: view, model: [objectList: result.objectList, flash: flash])
        } else {
            respond result.objectList
        }
    }

    def count() {
        prepareParams()
        println("统计${params}")
        def count = commonQueryService.countFunction(params)
        //println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    private void prepareParams() {
        def myself = Person.get(session.realId)
        switch (params.key) {
            case "反馈信息":
                def currentProgress = Progress.get(params.currentProgress)
                if (currentProgress) {
                    params.currentProgress = currentProgress
                }
                break
            case "进度查看":
                def currentTeam = Team.get(params.currentTeam)
                if (currentTeam) {
                    params.currentTeam = currentTeam
                }
                break
            case "我参与的":
                params.myself = myself.id
                break
            default:
                params.myself = myself
        }
    }

    def index() {}
}
