package cn.edu.cup.os4lims

import cn.edu.cup.lims.Course
import cn.edu.cup.lims.Person
import cn.edu.cup.lims.Project
import cn.edu.cup.lims.ThingType
import cn.edu.cup.lims.ThingTypeController
import cn.edu.cup.system.JsFrame
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED

class Operation4ThingTypeController extends ThingTypeController {

    def commonQueryService
    def treeViewService
    def courseService
    def projectService

    def createProject() {
        println("${params}")
        def aThingType = thingTypeService.get(params.id)
        def project = new Project(
                thingType: aThingType
        )

        def view = "createProject"
        if (request.xhr) {
            render(template: view, model: [project: project])
        } else {
            respond project
        }
    }

    def saveProject(Project project) {
        if (project == null) {
            notFound()
            return
        }

        try {
            projectService.save(project)
        } catch (ValidationException e) {
            respond project.errors, view:'create'
            return
        }
        redirect(action: "index")
    }

    def createCourse() {
        println("${params}")
        def aThingType = thingTypeService.get(params.id)
        def myself = Person.get(session.realId)
        Calendar calendar = Calendar.getInstance();
        def y = calendar.get(Calendar.YEAR);
        def m = calendar.get(Calendar.MONTH);
        //d=calendar.get(Calendar.DATE);
        //h=calendar.get(Calendar.HOUR_OF_DAY);
        //mi=calendar.get(Calendar.MINUTE);
        //s=calendar.get(Calendar.SECOND);
        def sy
        if (m > 6) {
            sy = "${y}-${y + 1}-1"
        } else {
            sy = "${y - 1}-${y}-2"
        }
        def course = new Course(
                thingType: aThingType,
                teacher: myself,
                name: "${aThingType.name}.${sy}",
                schoolYear: sy
        )

        def view = "createCourse"
        if (request.xhr) {
            render(template: view, model: [course: course])
        } else {
            respond course
        }
    }

    def saveCourse(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view: 'create'
            return
        }
        redirect(action: "index")
    }


    def show(Long id) {
        def thingType = thingTypeService.get(id)
        //println("show ${thingType}")
        //def projectList = ThingType.findByName("科研项目").relatedThingTypeList()
        //def courseList = ThingType.findByName("教学任务").relatedThingTypeList()
        def isProject = ThingType.findByName("科研项目").bePartOf(thingType)
        def isCourse = ThingType.findByName("教学任务").bePartOf(thingType)
        def view = "show"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType, isProject: isProject, isCourse: isCourse])
        } else {
            respond thingType
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        thingTypeService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'thingType.label', default: 'ThingType'), id])

        redirect(action: "index")
    }

    def update(ThingType thingType) {
        if (thingType == null) {
            notFound()
            return
        }

        try {
            thingTypeService.save(thingType)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'thingType.label', default: 'ThingType'), thingType.id])
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def edit(Long id) {
        def thingType = thingTypeService.get(id)
        def view = "edit"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType])
        } else {
            respond thingType
        }
    }

    def save(ThingType thingType) {
        if (thingType == null) {
            notFound()
            return
        }

        try {
            thingTypeService.save(thingType)
            flash.message = message(code: 'default.created.message', args: [message(code: 'thingType.label', default: 'ThingType'), thingType.id])
        } catch (ValidationException e) {
            flash.message = e.message
        }

        redirect(action: "index")
    }

    def create(ThingType aThingType) {
        println("${params}")
        def thingType = new ThingType(upType: aThingType)
        def view = "create"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType])
        } else {
            respond thingType
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = ThingType.findAllByUpTypeIsNull(params);
        params.context = "name"
        params.subItems = "subTypes"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
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

    def index() {}
}
