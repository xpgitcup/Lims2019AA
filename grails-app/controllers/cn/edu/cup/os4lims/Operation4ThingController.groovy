package cn.edu.cup.os4lims

import cn.edu.cup.lims.Course
import cn.edu.cup.lims.Project
import cn.edu.cup.lims.ThingController
import grails.converters.JSON

class Operation4ThingController extends ThingController{

    def excelByJxlService
    def commonService
    def courseService
    def projectService
    def commonQueryService

    def importFromFile() {
        println("导入...${params}")
        if (!params.uploadedFile.empty) {
            //处理文件上传
            def destDir = commonService.webRootPath + "file4import"
            params.destDir = destDir
            println(destDir)
            def sf = commonService.upload(params)
            println("上传${sf}成功...")
            def data = excelByJxlService.importExcelFileToDataTable(sf)
            println("${data}")
            if (data.size() > 0) {
                def resultstr = ""
                def r
                data.eachWithIndex { e, i ->
                    if (i > 0) {
                        println("当前 ${e}")
                        switch (params.key) {
                            case "科研":
                                def t = new Project()
                                r = t.importFromDataSheet(e)
                                if (r.result.empty) {
                                    projectService.save(r.project)
                                }
                                break
                            case "教学":
                                def s = new Course()
                                r = s.importFromDataSheet(e)
                                if (r.result.empty) {
                                    courseService.save(s)
                                }
                                break
                        }
                        if (resultstr.empty) {
                            resultstr += r.result
                        } else {
                            resultstr += ","
                            resultstr += r.result
                        }
                    }
                }
                if (resultstr.empty) {
                    flash.message = "导入${data.size()}个记录."
                } else {
                    flash.message += resultstr
                }
            }
        } else {
            flash.message = "空文件！"
        }
        redirect(action: "index")
    }

    def downloadTemplate() {
        def key = params.key
        def head = []
        def fileName
        switch (key) {
            case "科研":
                head.add(Project.dataSheetTitles())
                fileName = commonService.webRootPath + "templates/project.xls"
                break
            case "教学":
                head.add(Course.dataSheetTitles())
                fileName = commonService.webRootPath + "templates/course.xls"
                break
        }
        excelByJxlService.exportDataTable2ExcelFile(head, fileName)
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }

    def list() {
        println("${params}")
        def result = commonQueryService.listFunction(params)
        def view = result.view
        flash.message = result.message
        if (request.xhr) {
            render(template: view, model: [objectList: result.objectList, flash: flash])
        } else {
            respond result.objectList
        }
    }

    def count() {
        println("统计${params}")
        def count = commonQueryService.countFunction(params)
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() { }
}
