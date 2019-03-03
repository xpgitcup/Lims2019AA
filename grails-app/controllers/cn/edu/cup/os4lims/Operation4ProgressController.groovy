package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.Progress
import cn.edu.cup.lims.ProgressController
import cn.edu.cup.lims.Team
import grails.converters.JSON
import grails.validation.ValidationException

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Month
import java.time.Period
import java.time.Year
import java.util.Date

import static org.springframework.http.HttpStatus.CREATED

class Operation4ProgressController extends ProgressController {

    def commonQueryService
    def teamService
    def commonService

    def save(Progress progress) {
        if (progress == null) {
            notFound()
            return
        }

        try {
            progressService.save(progress)
            flash.message = message(code: 'default.created.message', args: [message(code: 'progress.label', default: 'Progress'), progress.id])

            if (!params.uploadedFile.empty) {
                //处理文件上传
                def destDir = commonService.webRootPath + "documents/${progress.id}"
                params.destDir = destDir
                println(destDir)
                def sf = commonService.upload(params)
                println("上传${sf}成功...")
            }
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def createNextProgress(Progress prevProgress) {
        def myself = Person.get(session.realId)
        def progress = new Progress(
                team: prevProgress.team,
                prevProgress: prevProgress,
                contributor: myself
        )
        Date prev = prevProgress.regDate
        Date now = progress.regDate
        def dif = (now.getTime() - prev.getTime()) / 1000 / 60
        println("时间差：${dif}")
        if (dif < 1) {
            def year = prevProgress.regDate[Calendar.YEAR]
            def month = prevProgress.regDate[Calendar.MONTH]
            def day = prevProgress.regDate[Calendar.DATE]
            def hour = prevProgress.regDate[Calendar.HOUR_OF_DAY]
            def minute = prevProgress.regDate[Calendar.MINUTE] + 1
            println("时间没有错开！${year} ${month} ${day} ${hour} ${minute}")
            //progress.regDate = new Date(year: year, month: month, date: day, hours: hour, minutes: minute)    //不对--完全是乱的
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm")
            progress.regDate = df.parse("${year}-${month + 1}-${day} ${hour}:${minute}")    // 月份+1
            println("修正后的时间：${progress.regDate}")
        } else {
            println("两个时间：${prevProgress.regDate} ${prevProgress.regDate}")
        }
        def view = "createProgress"
        if (request.xhr) {
            render(template: view, model: [progress: progress])
        } else {
            respond progress
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
        def team = null
        if (params.currentTeam) {
            team = teamService.get(params.currentTeam)
        }

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
            render(template: view, model: [
                    objectList: result.objectList,
                    team      : team,
                    flash     : flash])
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
