package cn.edu.cup.os4lims

import cn.edu.cup.lims.Major
import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonController
import cn.edu.cup.lims.Student
import cn.edu.cup.lims.Teacher
import grails.converters.JSON

class Operation4PersonController extends PersonController {

    def excelByJxlService
    def commonService
    def studentService
    def teacherService
    def systemCommonService
    def commonQueryService

    def removeFromSystemUserGrade() {
        def k = 0
        def grade = params.grade
        def students = Student.findAllByGradeName(grade)
        students.each { e ->
            if (systemCommonService.removePersonFromUser(e)) {
                k++
            }
        }
        flash.message = "毕业${k}名学生。"
        redirect(action: "index")
    }

    def removeFromSystemUser() {
        switch (params.key) {
            case "student":
                def student = studentService.get(params.id)
                if (!student) {
                    flash.message = "${params.id} -- 找不到学生。"
                } else {
                    systemCommonService.removePersonFromUser(student)
                }
                break
            case "teacher":
                def teacher = teacherService.get(params.id)
                if (!teacher) {
                    flash.message = "${params.id} -- 找不到。"
                } else {
                    systemCommonService.removePersonFromUser(teacher)
                }
                break
        }
        redirect(action: "index")
    }

    def addToSystemUserGrade() {
        def k = 0
        def grade = params.grade
        def students = Student.findAllByGradeName(grade)
        students.each { e ->
            if (systemCommonService.addPersonToUser(e)) {
                k++
            }
        }
        flash.message = "新入学${k}名学生。"
        redirect(action: "index")
    }

    def addToSystemUser() {
        switch (params.key) {
            case "student":
                def student = studentService.get(params.id)
                if (!student) {
                    flash.message = "${params.id} -- 找不到学生。"
                }
                systemCommonService.addPersonToUser(student)
                break
            case "teacher":
                def teacher = teacherService.get(params.id)
                if (!teacher) {
                    flash.message = "${params.id} -- 找不到。"
                }
                systemCommonService.addPersonToUser(teacher)
                break;
        }
        redirect(action: "index")
    }

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
                            case "教师":
                                def t = new Teacher()
                                r = t.importFromDataSheet(e)
                                if (r.result.empty) {
                                    teacherService.save(r.teacher)
                                }
                                break
                            case "学生":
                                def s = new Student()
                                r = s.importFromDataSheet(e)
                                if (r.result.empty) {
                                    studentService.save(s)
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
            case "教师":
                head.add(Teacher.dataSheetTitles())
                fileName = commonService.webRootPath + "templates/teacher.xls"
                break
            case "学生":
                head.add(Student.dataSheetTitles())
                fileName = commonService.webRootPath + "templates/student.xls"
                break
            case "专业":
                head.add(Major.dataSheetTitles())
                fileName = commonService.webRootPath + "templates/major.xls"
                break
        }
        excelByJxlService.exportDataTable2ExcelFile(head, fileName)
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }

    /*
    * 显示当前id对应的对象
    * */

    def show(Person person) {
        def theModel = [Person: person]
        if (request.xhr) {
            render(template: "showPerson", model: theModel)
        } else {
            theModel
        }
    }

    def list() {
        //println("${params}")
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
        def count = 0
        switch (params.key) {
            case "年级":
                count = commonQueryService.countFunction(params)
                break
            default:
                count = commonQueryService.countFunction(params)
        }
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
